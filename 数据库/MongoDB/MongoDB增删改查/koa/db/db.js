// 连接数据库（mongodb 的服务端）
const mongoose = require('mongoose')

// 数据库地址
const url = 'mongodb://localhost:27017/'
// 数据库
const dbName = 'lyy'

// 开始连接
mongoose.connect(url + dbName, {
    useNewUrlParser: true,
    useUnifiedTopology: true
})

// 连接实例
const conn = mongoose.connection

// 连接成功
conn.on('open', err => {
    console.error('MongoDB 连接成功!')
})

// 连接出错
conn.on('error', err => {
    console.error('MongoDB 连接出错!', err)
})

module.exports = mongoose
