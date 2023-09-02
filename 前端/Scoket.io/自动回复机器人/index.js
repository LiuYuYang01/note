// 导入WebSocket模块
const WebSocket = require("ws");

const wss = new WebSocket.Server({ port: 5000 });

wss.on("connection", function (ws) {
  ws.on("message", function (message) {
    console.log("客户端发送过来的信息是：" + message);

    if(message == '你好'){
        ws.send("Hi 你好，我是机器人");
    }else if(message == '你是男生还是女生'){
        ws.send('我是一个男孩子')
    }else if(message == '你今年多大了'){
        ws.send('我今年刚满5岁')
    }else if(message == '你的家乡在哪'){
        ws.send('我的家乡在上海！')
    }else{
        ws.send('这个问题好难，我不知道该回什么！')
    }

    // 服务端向客户端发送消息
    // ws.send("Hi 我接收到了你的信息，我是服务器");
  });
});
