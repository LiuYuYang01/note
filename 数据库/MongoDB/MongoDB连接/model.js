const mongoose = require('./db');

// 定义Schema数据规范
const UserSchema = mongoose.Schema({
    name: {
        type: String,
        require: true, //必需
        unique: true //唯一，不重复
    },
    password: String,
    age: Number,
    city: String,
    gender: {
        type: Number,
        default: 0 //保密：0   男：1   女：2
    }
}, {
    timestamps: true //时间戳，自动添加文档创建的时间
})