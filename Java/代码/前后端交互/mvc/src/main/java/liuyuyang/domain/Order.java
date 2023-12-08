package liuyuyang.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Integer oid;
    private Integer price;
    private Integer userId;

    private User user;
}