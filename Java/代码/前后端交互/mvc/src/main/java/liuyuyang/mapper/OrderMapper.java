package liuyuyang.mapper;

import liuyuyang.domain.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {
    // 新增数据
    @Insert("insert into `order` (oid, price, user_id) values(#{oid}, #{price}, #{userId})")
    public Integer add(Order order);

    // 删除数据
    @Delete("delete from `order` where oid=#{oid}")
    public Integer del(Integer oid);

    // 修改数据
    @Update("update `order` set price=#{price}, user_id=#{userId} where oid=#{oid}")
    public Integer update(Order order);

    // 查询数据
    @Select("select * from `order` where oid = #{oid}")
    public Order info(int id);

    // 查询全部数据
    @Select("select * from `order`")
    // @Results(id = "orderMap", value = {
    //         @Result(property = "userId", column = "user_id"),
    //         @Result(property = "user", one = @One(select = "liuyuyang.mapper.UserMapper.info"), column = "user_id")
    // })
    public List<Order> list();
}
