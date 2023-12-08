package liuyuyang.service.impl;

import liuyuyang.domain.Order;
import liuyuyang.domain.User;
import liuyuyang.mapper.OrderMapper;
import liuyuyang.mapper.UserMapper;
import liuyuyang.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderServicelmpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public Integer add(Order order) {
        return orderMapper.add(order);
    }

    @Override
    public Integer del(Integer oid) {
        return orderMapper.del(oid);
    }

    @Override
    public Integer update(Order order) {
        return orderMapper.update(order);
    }

    @Override
    public Order info(int id) {
        Order order = orderMapper.info(id);

        // 通过订单的所属用户id查询对应用户数据
        User user = userMapper.info(order.getUserId());
        order.setUser(user);
        return order;
    }

    @Override
    public List<Order> list() {
        List<Order> orderList = orderMapper.list();

        // 循环所有订单数据，拿到订单所属用户的ID，然后通过用户的ID查询对应用户的数据
        for (Order order : orderList) {
            User user = userMapper.info(order.getUserId());
            order.setUser(user);
        }

        return orderList;
    }
}
