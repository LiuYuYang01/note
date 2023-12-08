package liuyuyang.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class User {
    @ApiModelProperty(value="编号", example = "1")
    private Integer uid;
    @ApiModelProperty(value="姓名", required = true, example = "张三")
    private String uname;

    // @ApiModelProperty(value="订单列表")
    // private List<Order> orderList;
}
