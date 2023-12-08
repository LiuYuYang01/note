package liuyuyang.mapper;

import liuyuyang.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("insert into user(uname) values(#{uname})")
    public Integer add(User user);

    @Update("update user set uname=#{uname} where uid=#{uid}")
    public Integer update(User user);

    @Select("select * from user where uid = #{uid}")
    public User info(int id);

    @Select("select * from user")
    public List<User> list();
}
