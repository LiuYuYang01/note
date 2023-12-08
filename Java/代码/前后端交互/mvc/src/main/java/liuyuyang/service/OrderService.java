package liuyuyang.service;

import liuyuyang.domain.Order;

import java.util.List;

public interface OrderService {
    public Integer add(Order order);

    public Integer del(Integer oid);

    public Integer update(Order order);

    public Order info(int id);

    public List<Order> list();
}
