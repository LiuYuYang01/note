const router = require('koa-router')()

// 连接数据库
require('../db/db')

// 导入数据模型
const { lyy } = require('../db/dataModel');

// 新增数据
router.post('/add', async (ctx, next) => {
  // 接收表单传的数据
  let data = JSON.parse(ctx.request.body);

  // 新增数据
  const insertObj = new lyy(data)

  // 方法一
  // insertObj.save(err => {
  //   if (!err) {
  //     console.log('新增成功');
  //   } else {
  //     console.log('新增失败', err)
  //   }
  // })

  //方法二
  const res = await insertObj.save()
    .then(() => {
      console.log('新增成功');
      return "新增成功!"
    })
    .catch((err) => {
      console.log('新增失败', err)
      return "新增失败!"
    })

  ctx.body = {
    status: 200,
    message: res
  }
})

// 查询数据
router.get('/find', async (ctx, next) => {
  // ctx.body = {
  //   status:200,
  //   message:"数据获取成"
  // }

  let data = await lyy.find().then(res => {
    return res
  });

  ctx.body = {
    status: 200,
    message: "数据获取成功",
    data
  }
})

// 删除数据
router.post('/remove', async (ctx, next) => {
  let _id = ctx.request.body;

  let message = await lyy.deleteOne({ _id })
    .then(() => {
      return "删除数据成功"
    })
    .catch(() => {
      return "删除数据失败"
    })

  ctx.body = {
    status: 200,
    message
  }
})

// 修改数据
router.post('/update', async (ctx, next) => {
  let obj = JSON.parse(ctx.request.body);
  console.log(obj);

  let message = await lyy.updateOne({ _id: obj.id }, { $set: obj })
    .then(res => {
      console.log("修改成功!");
      return "修改成功!"
    })
    .catch(err => {
      console.log('修改失败', err);
      return "修改失败!"
    })

    ctx.body = {
      status:200,
      message
    }
})

module.exports = router