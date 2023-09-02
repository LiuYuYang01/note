const mongoose = require('./db');

// 规范lyy数据库集合
// module.exports.lyy = mongoose.model('lyy', {
//     name: { type: String, default: "默认值" },
//     age: { type: Number },
//     sex: { type: String },
//     //required:true 必选值
//     //unique:true 唯一值
//     user: { type: String, required: true, unique: true },
//     password: { type: String },
// })

// 规范lyy数据库集合
const lyySchema = mongoose.Schema({
    name: { type: String, default: "默认值" },
    age: { type: Number },
    sex: { type: String },
    user: { type: String, required: true, unique: true },
    password: { type: String },
})

module.exports.lyy = mongoose.model('lyy', lyySchema)