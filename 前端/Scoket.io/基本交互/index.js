// 导入WebSocket模块
const WebSocket = require("ws");

const wss = new WebSocket.Server({ port: 5000 });

wss.on("connection", function (ws) {
  ws.on("message", function (message) {
    console.log("客户端发送过来的信息是：" + message);

    // 服务端向客户端发送消息
    ws.send('Hi 我接收到了你的信息，我是服务器')
});
});
