package liuyuyang.service.impl;

import liuyuyang.mapper.UserMapper;
import liuyuyang.service.UserService;
import liuyuyang.domain.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public Integer add(User user){
        return userMapper.add(user);
    }

    @Override
    public Integer update(User user){
        return userMapper.update(user);
    }

    @Override
    public User info(int id) {
        return userMapper.info(id);
    }

    @Override
    public List<User> list() {
        return userMapper.list();
    }
}
