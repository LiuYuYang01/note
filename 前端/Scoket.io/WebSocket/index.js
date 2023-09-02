const app = require("express")();
const http = require("http").Server(app);
const io = require("socket.io")(http);

app.get("/", (req, res) => {
  res.sendFile(__dirname + "/index.html");
});

let userNum = 0; //当前在线人数

io.on("connection", (socket) => {
  socket.on('joinRoom',(data)=>{
    // 用户加入房间
    socket.join(data.id)

    // 对房间内的用户发送消息
    io.in(data.id).emit(data.value);
  })

  // 访问聊天室用户在线输了加1
  ++userNum;
  // 将用户在线数量实时同步给客户端
  io.emit("userNum", userNum);

  // 相应客户端传递的数据
  socket.on("value", (value) => {
    io.emit("value", `${socket.username}：${value}`);
  });

  // 监听用户进入聊天室
  socket.on("join", (name) => {
    socket.username = name;

    io.emit("join", {
      name: name,
      status: "已进入",
    });
  });

  // 离开聊天室用户在线数量减1
  socket.on("disconnect", () => {
    --userNum;
    io.emit("userNum", userNum);

    io.emit("join", {
      name: socket.username,
      status: "已退出",
    });
  });
});

http.listen(1231, () => {
  console.log("服务器已启动：http://localhost:1231");
});
