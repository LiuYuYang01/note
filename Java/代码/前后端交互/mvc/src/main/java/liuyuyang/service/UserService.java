package liuyuyang.service;

import liuyuyang.domain.User;

import java.util.List;

public interface UserService {
    public Integer add(User user);
    public Integer update(User user);
    public User info(int id);
    public List<User> list();
}
