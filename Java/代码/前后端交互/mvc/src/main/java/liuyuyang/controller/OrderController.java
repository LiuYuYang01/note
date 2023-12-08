package liuyuyang.controller;

import io.swagger.annotations.Api;
import liuyuyang.domain.Order;
import liuyuyang.service.OrderService;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/order")
@Transactional
@Api(tags = "订单管理", description = "订单增删改查接口")
public class OrderController {
    @Resource
    private OrderService orderService;

    // 新增数据
    @PostMapping("")
    public ResponseEntity<String> add(@RequestBody Order order) {
        String result = orderService.add(order) == 1 ? "新增成功" : "新增失败";
        return ResponseEntity.ok(result);
    }

    // 删除数据
    @DeleteMapping("/{oid}")
    public ResponseEntity<String> del(@PathVariable("oid") Integer oid){
        String result = orderService.del(oid) == 1 ? "删除成功" : "删除失败";
        return ResponseEntity.ok(result);
    }

    // 编辑数据
    @PatchMapping("")
    public ResponseEntity<String> update(@RequestBody Order order) {
        String result = orderService.update(order) == 1 ? "编辑成功" : "编辑失败";
        return ResponseEntity.ok(result);
    }

    // 查询数据
    @GetMapping("/{oid}")
    public ResponseEntity<Order> get(@PathVariable("oid") int oid) {
        Order data = orderService.info(oid);
        return ResponseEntity.ok(data);
    }

    // 查询全部数据
    @GetMapping("")
    public ResponseEntity<List<Order>> list() {
        List<Order> data = orderService.list();
        return ResponseEntity.ok(data);
    }
}
