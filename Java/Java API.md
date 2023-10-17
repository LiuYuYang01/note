# Random

生成一个指定范围的随机数，例如 `0 ~ 100`

```java
// 导入随机数包
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // 创建随机数对象
        Random random = new Random();
        
        // 随机布尔值 false/true
        System.out.println(random.nextBoolean()); // false

        // 随机小数
        System.out.println(random.nextDouble()); // 0.9450686158798193

        // 随机整数
        System.out.println(random.nextInt()); // 1784827402

        // 生成指定范围内的随机数（0~99）
        System.out.println(random.nextInt(100)); // 0~99 不包含100
        System.out.println(random.nextInt(50, 100)); // 50~99
    }
}
```



# isNull

判断对象是否为空

```java
// 导包
import static java.util.Objects.isNull;

public class Main {
    public static void main(String[] args) {
        String a = null;
        String b = "Hello";

        int[] c = null;
        int[] d = {1, 2, 3, 4, 5};

        System.out.println(isNull(a)); // true
        System.out.println(isNull(b)); // false
        System.out.println(isNull(c)); // true
        System.out.println(isNull(d)); // false
    }
}
```



# Date

```java
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Date d = new Date();
        
        // 获取年月日周时分秒（该方法已弃用）
        System.out.println("年：" + d.getYear()); // 123
        System.out.println("月：" + d.getMonth() + 1); // 9
        System.out.println("日：" + d.getDate()); // 19
        System.out.println("周：" + d.getDay()); // 2
        System.out.println("时：" + d.getHours()); // 15
        System.out.println("分：" + d.getMinutes()); // 4
        System.out.println("秒：" + d.getSeconds()); // 41

        // 获取当前时间
        System.out.println(d); // Tue Sep 19 14:51:52 CST 2023

        // 获取当前时间戳
        System.out.println(d.getTime()); // 1695106312928

        // 浮点数如何转换为整数
        System.out.printf("距离1970已经过去：%.0f年了\n", d.getTime() * 1.0 / 1000 / 60 / 60 / 24 / 365);

        // 将时间戳转换为对应的时间 日期对象
        System.out.println(new Date(1695106312928L)); // Tue Sep 19 14:51:52 CST 2023
    }
}
```



## SimpleDateFormat

从 Java 8 开始，引入了新的日期和时间 API，其中包括 `DateTimeFormatter` 类，用于格式化和解析日期和时间。相比之下，`SimpleDateFormat` 是旧的日期和时间 API 提供的一个类

建议在现代 Java 应用程序中使用 `DateTimeFormatter`，而不是 `SimpleDateFormat`。以下是几个理由：

1. 线程安全性：`SimpleDateFormat` 不是线程安全的，如果在多线程环境中使用，可能会导致不正确的结果。而 `DateTimeFormatter` 是线程安全的，可以在多个线程之间共享使用。
2. API 设计：`DateTimeFormatter` 提供了更清晰、更一致和更易于使用的 API，支持更丰富的日期和时间格式，并提供了通过方法链进行操作的便利性。
3. 不可变性：`DateTimeFormatter` 是不可变的，一旦创建就不能修改。这样可以确保线程安全性和提高性能。
4. 新特性支持：`DateTimeFormatter` 支持解析和格式化时区、偏移量、本地化等新特性，而 `SimpleDateFormat` 不支持。

```java
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws ParseException {
        Date d = new Date();

        // 将时间对象转换为时间字符串
        SimpleDateFormat date = new SimpleDateFormat("yyy-MM-dd HH:mm:ss:SSS");
        System.out.println(date.format(d));
        // 2023-09-19 15:26:30:025

        // 将字符串转换为时间对象
        String str = "2023-09-19 15:21:40:264";
        System.out.println(date.parse(str));
        // Tue Sep 19 15:21:40 CST 2023
    }
}
```



## Calendar

获取年月日周时分秒

```java
import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();

        System.out.println(c);

        System.out.println(c.get(Calendar.YEAR)); // 年：2023
        System.out.println(c.get(Calendar.MONTH) + 1); // 月：9
        System.out.println(c.get(Calendar.DAY_OF_MONTH)); // 日：19
        System.out.println(c.get(Calendar.DAY_OF_WEEK) - 1); // 周：2
        System.out.println(c.get(Calendar.HOUR)); // 时：4
        System.out.println(c.get(Calendar.MINUTE)); // 分：7
        System.out.println(c.get(Calendar.SECOND)); // 秒：0
        System.out.println(c.get(Calendar.MILLISECOND)); // 毫秒：206
    }
}
```


增加 / 设置

```java
import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();

        System.out.println(c.get(Calendar.YEAR)); // 年：2023

        // 年份增加100年
        c.add(Calendar.YEAR, 100);

        System.out.println(c.get(Calendar.YEAR)); // 年：2123

        // 设置年份
        c.set(Calendar.YEAR, 2030);

        System.out.println(c.get(Calendar.YEAR)); // 年：2030
    }
}
```



**应用场景：** 判断2023年9月21日是星期几

```java
import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();

        // 判断2023年9月21日是星期几
        c.set(Calendar.YEAR, 2023);
        c.set(Calendar.MONTH, 9 - 1);
        c.set(Calendar.DAY_OF_MONTH, 21);
        
        System.out.println(c.get(Calendar.MONTH));
        System.out.println(c.get(Calendar.DAY_OF_WEEK));
    }
}
```



## LocalDate

获取年、月、日、周

**基本使用：** 获取年月日周

```java
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();

        // 获取年月日
        System.out.println(date); // 2023-09-22

        // 获取指定的时间对象
        LocalDate d = LocalDate.of(2023, 10, 11);

        int year = d.getYear(); // 年：2023
        int month = d.getMonthValue(); // 月：10
        int day = d.getDayOfMonth(); // 日：11
        int week = d.getDayOfWeek().getValue(); // 星期几：3
        int dayYear = d.getDayOfYear(); // 一年中的第几天：284

        System.out.printf("%d年%d月%d日星期%d 这是今年的第%d天\n", year, month, day, week, dayYear);
        // 2023年10月11日 这是今年的第：284天
    }
}
```



**判断时间：** `isBefore` 与 `isAfter` 方法分别用于检查当前 `LocalDate` 对象是否在指定的日期之前与之后

```java
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        LocalDate d1 = LocalDate.of(2023, 9, 11);
        LocalDate d2 = LocalDate.of(2023, 10, 11);

        // isBefore：如果当前日期早于指定日期，则返回true；否则返回false
        System.out.println(d1.isBefore(d2)); // d1时间比d2早所以为：true
        System.out.println(d2.isBefore(d1)); // d2时间比d1晚所以为：false

        // isAfter：如果当前日期晚于指定日期，则返回true；否则返回false。
        System.out.println(d1.isAfter(d2)); // d1时间比d2早所以为：false
        System.out.println(d2.isAfter(d1)); // d2时间比d1晚所以为：true
    }
}
```



**操作时间：** 时间增加、减少、修改操作

```java
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        System.out.println(date); // 2023-09-22

        // 修改年份为2024
        LocalDate d1 = date.withYear(2024);
        System.out.println(d1); // 2024-09-22

        // 批量修改年月日
        LocalDate d2 = date.withYear(2024).withMonth(10).withDayOfMonth(1);
        System.out.println(d2); // 2024-10-01

        // 增加年份10年
        LocalDate d3 = date.plusYears(10);
        System.out.println(d3); // 2033-09-22

        // 批量增加年月日
        LocalDate d4 = date.plusYears(10).plusMonths(2).plusDays(15);
        System.out.println(d4); // 2033-12-07

        // 减少年份10年
        LocalDate d5 = date.minusYears(10);
        System.out.println(d5); // 2013-09-22

        // 批量减少年月日
        LocalDate d6 = date.minusYears(10).minusMonths(2).minusDays(15);
        System.out.println(d6); // 2013-07-07
    }
}
```



**比较时间：** `isEqual ` 方法用于检查两个 `LocalDate` 对象是否表示相同的日期

```java
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        LocalDate d1 = LocalDate.of(2023, 9, 22);
        LocalDate d2 = LocalDate.of(2023, 9, 23);
        LocalDate d3 = LocalDate.of(2023, 9, 22);

        System.out.println(d1.isEqual(d2)); // false
        System.out.println(d1.isEqual(d3)); // true
    }
}
```



## LocalTime

获取时、分、秒、纳秒

```java
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        LocalTime date = LocalTime.now();
        System.out.println(date); // 17:21:32.452112700
        
        System.out.println(date.getHour()); // 时：17
        System.out.println(date.getMinute()); // 分：10
        System.out.println(date.getSecond()); // 秒：23
        System.out.println(date.getNano()); // 纳秒：901570300
    }
}
```

它的对应方法 `is` 、`with` 、`plus` 、`isEqual` 与 `LocalDate` 对象使用一致



## LocalDateTime

获取年、月、日、周、时、分、秒

```java
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        LocalDateTime date = LocalDateTime.now();
        System.out.println(date); // 2023-09-22T17:20:38.421201
        
        System.out.println(date.getYear());; // 年：2023
        System.out.println(date.getMonthValue());; // 月：9
        System.out.println(date.getDayOfMonth());; // 日：22
        System.out.println(date.getDayOfWeek().getValue());; // 星期几：5
        System.out.println(date.getDayOfYear());; // 一年中的第几天：265
        System.out.println(date.getHour()); // 时：17
        System.out.println(date.getMinute()); // 分：10
        System.out.println(date.getSecond()); // 秒：23
        System.out.println(date.getNano()); // 纳秒：901570300
        
        // 只获取年、月、日
        System.out.println(date.toLocalDate()); // 2023-09-22
        // 只获取时、分、秒、纳秒
        System.out.println(date.toLocalTime()); // 17:23:06.269336500
    }
}
```



## DateTimeFormatter

`DateTimeFormatter` 是 `Java 8` 引入的日期时间类，在处理日期和时间时提供了更好的功能和更简洁的API。它可以表示一个不带时区信息的日期和时间，例如：2023-09-22T06:49:15

```java
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        LocalDateTime date = LocalDateTime.now();
        // 2023-09-21T09:52:12.367316400

        // 将日期格式化
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒");
        System.out.println(f.format(date));
        // 2023年09月21日 09时52分12秒
    }
}
```

```java
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒");
        LocalDateTime ldt = LocalDateTime.now();

        // 时间对象转换为时间字符串
        System.out.println(ldt); // 2023-09-21T10:17:59.817119300
        System.out.println(ldt.format(date)); // 2023年09月21日 10时17分48秒
        
        // 时间字符串转换为时间对象
        System.out.println(LocalDateTime.parse("2023年09月21日 10时17分59秒", date));
    }
}
```



## Zoned

获取不同的时区

```java
import java.time.ZoneId;

public class Main {
    public static void main(String[] args) {
        // 获取默认时区
        ZoneId zoneId = ZoneId.systemDefault();
        System.out.println(zoneId); // Asia/Shanghai

        // 获取指定时区
        // System.out.println(ZoneId.of("UTC+8")); // UTC+8
        System.out.println(ZoneId.of("Asia/Shanghai")); // Asia/Shanghai

         // 获取所有时区
        System.out.println(ZoneId.getAvailableZoneIds()); // [Asia/Aden, America/Cuiaba, Etc/GMT+9, Etc/GMT+8, .....]
    }
}
```



## ZonedDateTime

获取不同的时区时间

```java
import java.time.Clock;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Main {
    public static void main(String[] args) {
        // 获取默认带有时间的时区
        ZonedDateTime z1 = ZonedDateTime.now();
        ZonedDateTime z2 = ZonedDateTime.now(Clock.systemDefaultZone());
        System.out.println(z1); // 2023-09-21T09:04:27.993323500+08:00[Asia/Shanghai]
        System.out.println(z2); // 2023-09-21T09:04:27.993823500+08:00[Asia/Shanghai]

        // 获取指定的时区
        ZonedDateTime z3 = ZonedDateTime.now(ZoneId.of("UTC+8"));
        ZonedDateTime z4 = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(z3); // 2023-09-21T09:04:27.993323500+08:00[UTC+08:00]
        System.out.println(z4); // 2023-09-21T09:04:27.993823500+08:00[Asia/Shanghai]
    }
}
```



**格式化时区的时间**

```java
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        ZonedDateTime z = ZonedDateTime.now();
        System.out.println(z); // 2023-09-21T09:04:27.993323500+08:00[UTC+08:00]

        // 格式化
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(f.format(z)); // 2023-09-21 09:04:27
    }
}
```



**应用场景：** 获取不同国家的时区时间

```java
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 生成当前时区
        ZoneId chinaZone = ZoneId.of("UTC+8");
        ZoneId usaZone = ZoneId.of("America/New_York");
        ZoneId sriLankaZone = ZoneId.of("Asia/Colombo");

        // 获取指定时区的时间
        ZonedDateTime chinaTime = ZonedDateTime.now(chinaZone);
        ZonedDateTime usaTime = ZonedDateTime.now(usaZone);
        ZonedDateTime sriLankaTime = ZonedDateTime.now(sriLankaZone);

        System.out.println("中国当前时间：" + f.format(chinaTime));
        System.out.println("美国当前时间：" + f.format(usaTime));
        System.out.println("斯里兰卡当前时间：" + f.format(sriLankaTime));
        // 中国当前时间：2023-09-22 21:01:44
        // 美国当前时间：2023-09-22 09:01:44
        // 斯里兰卡当前时间：2023-09-22 18:31:44
    }
}
```



**操作时区：** 对时区进行修改、增加、减少操作

```java
import java.time.ZonedDateTime;
import java.time.ZoneId;

public class Main {
    public static void main(String[] args) {
        ZonedDateTime date = ZonedDateTime.of(2023, 10, 1,
                11, 0, 12, 0, ZoneId.of("Asia/Shanghai"));
        System.out.println(date); // 2023-10-01T11:00:12+08:00[Asia/Shanghai]

        // 修改时区年份
        ZonedDateTime d1 = date.withYear(2000);
        System.out.println(d1.getYear()); // 2000

        // 减少时区年份
        ZonedDateTime d2 = date.minusYears(1000);
        System.out.println(d2.getYear()); // 1023

        // 增加时区年份
        ZonedDateTime d3 = date.plusYears(1000);
        System.out.println(d3.getYear()); // 3023
    }
}
```



## Period

计算两个日期的时间差（只能是年月日）

```java
import java.time.Period;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // 当前时间
        LocalDate d1 = LocalDate.now(); // 2023-09-22
        // 指定时间
        LocalDate d2 = LocalDate.of(2002, 10, 11); // 2002-10-11

        // 求出两个日期之间的间隔
        Period d = Period.between(d2, d1);
        System.out.printf("距离2002年10月11日已经过去%s年%s月%s日", d.getYears(), d.getMonths(), d.getDays());
        // 距离2002年10月11日已经过去20年11月11日
    }
}
```



## Duration

计算两个日期的时间差（只能是时分秒）

```java
import java.time.LocalDateTime;
import java.time.Duration;

public class Main {
    public static void main(String[] args) {
        LocalDateTime d1 = LocalDateTime.of(2023, 10, 11, 0, 0, 0);
        LocalDateTime d2 = LocalDateTime.of(2023, 10, 11, 10, 30, 15);

        Duration d = Duration.between(d1, d2);

        // 获取时间差的总分钟数
        System.out.println(d.toMinutes()); // 630
        // 获取时间差的剩余分钟数
        System.out.println(d.toMinutesPart()); // 30

        // 获取指定的时间段
        int hours = d.toHoursPart();
        int minutes = d.toMinutesPart();
        int seconds = d.toSecondsPart();

        System.out.printf("距离2023年10月11日0时0分0秒已经过去%d小时%d分钟%d秒", hours, minutes, seconds);
        // 距离2023年10月11日0时0分0秒已经过去10小时30分钟15秒
    }
}
```



## 计算年月日时分秒



# Arrays

## toString

```java
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50};

        // 直接输出打印的是数组内存地址
        System.out.println(arr); // [I@4eec7777

        // 通过Arrays.toString方法打印出数组的值
        System.out.println(Arrays.toString(arr)); // [10, 20, 30, 40, 50]
    }
}
```



## setAll

对数组中的每一项进行操作

```java
import java.util.Arrays;
import java.util.function.IntUnaryOperator;

public class Main {
    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50};

        // 通过Arrays.toString方法打印出数组的值
        System.out.println(Arrays.toString(arr)); // [10, 20, 30, 40, 50]

        // 对数组的每一项进行操作
        Arrays.setAll(arr, new IntUnaryOperator() {
            @Override
            public int applyAsInt(int operand) {
                // 对数组中每一项*10
                return arr[operand] * 10;
            }
        });

        System.out.println(Arrays.toString(arr));
        // [100, 200, 300, 400, 500]
    }
}
```



## sort

对数组进行从小到大、从大到小排序

```java
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr = {50, 40, 30, 20, 10};

        // 默认从小到大排序
        Arrays.sort(arr);

        System.out.println(Arrays.toString(arr));
        // [10, 20, 30, 40, 50]
    }
}
```

如果想要实现从大到小，可以这么做

```java
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Integer[] arr = {10, 20, 30, 40, 50};

        Arrays.sort(arr, new Comparator<Integer>() {
            @Override
            public int compare(Integer x, Integer y) {
                // return x - y; // [10, 20, 30, 40, 50]
                return y - x; //[50, 40, 30, 20, 10]
            }
        });

        System.out.println(Arrays.toString(arr));
    }
}
```



# lambda

基本使用

```java
public interface Swimiming {
    void swim();

    // 使用lambda接口中只能有一个需要被重新的方法
    // void info();
}
```

```java
public class Main {
    public static void main(String[] args) {
        // 常规写法
        Swimiming s1 = new Swimiming() {
            @Override
            public void swim() {
                System.out.println("哈哈哈");
            }
        };
        s1.swim();

        // lambda写法
        Swimiming s2 = () -> {
            System.out.println("呵呵呵");

            // 内部相当于重写了swim的方法：
            // @Override
            // public void swim() {
            //     System.out.println("呵呵呵");
            // }
        };
        s2.swim();
    }
}
```



使用 `lambda` 简化 `Arrays.setAll` 写法

```java
import java.util.Arrays;
import java.util.function.IntUnaryOperator;

public class Main {
    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50};

        // 原始写法
        // Arrays.setAll(arr, new IntUnaryOperator() {
        //     @Override
        //     public int applyAsInt(int operand) {
        //         return arr[operand] * 10;
        //     }
        // });

        // 使用lambda写法
        Arrays.setAll(arr, operand -> arr[operand] * 10);
        // 如果只有一个参数，那么operand可以省略类型以及括号

        System.out.println(Arrays.toString(arr));
    }
}
```



使用 `lambda` 简化 `Arrays.sort` 写法

```java
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Integer[] arr = {10, 20, 30, 40, 50};

        // 原始写法
        // Arrays.sort(arr, new Comparator<Integer>() {
        //     @Override
        //     public int compare(Integer x, Integer y) {
        //         // return x - y; // [10, 20, 30, 40, 50]
        //         return y - x; //[50, 40, 30, 20, 10]
        //     }
        // });

        // lambda写法
        // 从小到大
        Arrays.sort(arr, (x, y) -> x - y);

        // 从大到小
        Arrays.sort(arr, (x, y) -> y - x);

        System.out.println(Arrays.toString(arr));
    }
}
```

