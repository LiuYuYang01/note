package liuyuyang.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import liuyuyang.domain.User;
import liuyuyang.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
@Transactional
@Api(tags = "用户管理", description = "用户增删改查接口")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("")
    @ApiOperation("新增用户")
    public ResponseEntity<String> add(@RequestBody User user) {
        System.out.println(user);
        String result = userService.add(user) == 1 ? "新增成功" : "新增失败";
        return ResponseEntity.ok(result);
    }

    @PatchMapping("")
    @ApiOperation("编辑用户")
    public ResponseEntity<String> update(@RequestBody User user) {
        String result = userService.update(user) == 1 ? "编辑成功" : "编辑失败";
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{uid}")
    @ApiOperation("查询单个用户信息")
    public User get(@PathVariable("uid") Integer uid) {
        User data = userService.info(uid);
        return data;
    }

    @GetMapping("")
    @ApiOperation("查询用户列表")
    public ResponseEntity<List<User>> list() {
        List<User> list = userService.list();
        return ResponseEntity.ok(list);
    }
}
