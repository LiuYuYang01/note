# 集合

数组一经定义，长度且无法改变，所以不能添加和删除数据，在某些应用场景中我们必须对数据进行增删改查。所以就需要用到集合。集合是一种 **可变** 数据存储空间，数据容量可以发生改变，解决了数组的缺陷



## List

### ArrayList

**集合长度可变原理**

1. 当创建 `ArrayList` 集合容器时，底层会存在一个长度为 `10` 的空数组
2. 如果长度超过了 `10` 则自动创建一个新的数组容器并扩容原数组的 `1.5` 倍
3. 将旧数组的数据自动拷贝到新数组中
4. Java 垃圾回收机制会自动把旧数组删除



集合与数组分别的应用场景：当存储的元素个数固定不变时选择使用数组，否则使用集合



**初始化一些数据**

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
        System.out.println(list); // [1, 2, 3]
    }
}
```



#### 增删改查

**方法**

| 方法名                                | 说明                                          |
| ------------------------------------- | --------------------------------------------- |
| public boolean add(要添加的元素)      | 将指定的元素追加到此集合的末尾                |
| public boolean remove(要删除的元素)   | 删除指定元素,返回值表示是否删除成功           |
| public E  remove(int   index)         | 删除指定索引处的元素，返回被删除的元素        |
| public E   set(int index,E   element) | 修改指定索引处的元素，返回被修改的元素        |
| public E   get(int   index)           | 返回指定索引处的元素                          |
| public int   size()                   | 返回集合中的元素的个数                        |
| public void   clear()                 | 清空数组中所有的数据                          |
| public boolean isEmpty()              | 判断数据是否为空，有数据就返回true，反之false |
| public void allAdd()                  | 将指定集合中的所有元素添加到另一个集合中      |



**代码示例**

```java
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // <String>是泛型，用于约束集合中的数据类型
        ArrayList<String> list = new ArrayList<String>();

        // 添加
        list.add("aa");
        list.add("bb");
        list.add("cc");
        list.add("dd");
        list.add("ee");
        list.add("ff");
        System.out.println(list); // [aa, bb, cc, dd, ee, ff]

        // 删除
        list.remove("aa"); // 根据元素删除
        list.remove(1); // 根据元素下标删除
        System.out.println(list); // [bb, dd, ee, ff]

        // 修改
        list.set(1, "aa");
        System.out.println(list); // [bb, aa, ee, ff]

        // 获取
        System.out.println(list.get(1)); // aa

        // 长度
        System.out.println(list.size()); // 4
    }
}
```

**注意：** 集合不能存储基本数据类型如：`int`、`double`、`boolean` 等



**封装查询数据方法**

```java
// Main.java
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<User> list = new ArrayList<User>();

        User u1 = new User("heima001", "zhangsan", "123456");
        User u2 = new User("heima002", "lisi", "1234");
        User u3 = new User("heima003", "wangwu", "1234qwer");

        list.add(u1);
        list.add(u2);
        list.add(u3);

        // 通过ID获取数据
        String data = getIndex("heima002", list);
        System.out.println(data);
    }

    // 封装查询数据方法
    public static String getIndex(String id, ArrayList list) {
        byte len = (byte) list.size();

        for (byte i = 0; i < len; i++) {
            User item = (User) list.get(i);

            if (id.equals(item.id)) {
                return String.format("%s %s %s", item.id, item.username, item.password);
            }
        }

        return "没有找到该数据~";
    }
}
```

```java
// User.java
public class User {
    String id;
    String username;
    String password;

    public User(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
```



#### 遍历方式

```java
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("张三");
        list.add("李四");
        list.add("王五");

        // 传统写法
        for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        System.out.println("--------------------");

        // 增强版for循环写法
        for (String s : list) {
            System.out.println(s);
        }

        System.out.println("--------------------");

        // 使用forEach写法
        list.forEach(new Consumer<String>() {
            // 该方法在每次遍历集合时会把当前的元素传递给accept方法然后执行
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

        System.out.println("--------------------");

        // 使用Lambda表达式简化forEach写法
        list.forEach(s -> System.out.println(s));

        System.out.println("--------------------");

        // 使用迭代器写法
        Iterator<String> it = list.iterator();
        while(it.hasNext()) {
            String s = it.next();
            System.out.println(s);
        }
    }
}
```

```java
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<User> list = new ArrayList<>();

        list.add(new User("张三", 30));
        list.add(new User("李四", 10));
        list.add(new User("王五", 20));

        // 查询list数组中为张三的元素并删除;
        list.removeIf(user -> user.getName().equals("张三"));

        // 对list数组中的每个元素的年龄加10;
        list.forEach((user -> user.setAge(user.getAge() + 10)));
        System.out.println(list);
    }
}
```



### LinkedList

`LinkedList` 和 `ArrayList` 是 `Java` 集合框架中的两种不同的列表实现方式。它们在以下几个方面有所不同：

1. **存储结构：** `ArrayList` 底层使用数组实现，而 `LinkedList` 使用双向链表实现。
2. **插入和删除操作：** 插入和删除元素时，`ArrayList` 需要移动元素来保持连续性，而 `LinkedList` 只需要调整指针的指向，效率更高。
3. **随机访问：** `ArrayList` 可以通过索引来随机访问列表中的元素，时间复杂度为O(1)；而 `LinkedList` 需要从头节点开始遍历，时间复杂度为O(n)。

```java
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList<Object> list = new LinkedList<>();
        // 添加
        list.add("a");
        list.add("b");
        list.add("c");
        // 在指定位置插入元素
        list.add(1, "d");

        // 查询
        System.out.println(list); // [a, d, b, c]

        // 修改
        list.set(0, "e"); // 修改指定位置的元素
        System.out.println(list); // [e, d, b, c]

        // 删除
        list.remove(); // 默认删除第一个元素
        list.remove("c"); // 删除指定元素
        list.remove(1); // 删除指定位置的元素

        // 查询
        System.out.println(list); // [d]
    }
}
```



特有的方法

```java
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList<Object> list = new LinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        System.out.println(list);

        list.addFirst("d"); // 在头部添加元素
        list.addLast("e"); // 在尾部添加元素
        System.out.println(list); // [d, a, b, c, e]

        System.out.println(list.getFirst()); // 获取第一个数据：d
        System.out.println(list.getLast()); // 获取第二个数据：e
    }
}
```

**基于以上差异，两者在不同的场景下有不同的应用：**

- `ArrayList`适用于频繁进行随机访问以及插入和删除操作较少的场景。由于其支持随机访问的特性，适用于需要根据索引进行快速查找和访问元素的场景。
- `LinkedList` 适用于频繁进行插入和删除操作的场景。由于其插入和删除操作的高效性，适用于需要频繁在列表中间插入或删除元素的场景。

需要注意的是 `LinkedList` 相比 `ArrayList` 会占用更多的内存空间，因为每个节点都需要维护前后节点的引用。因此，在选择使用哪种列表实现时，要根据具体场景需求进行权衡和选择。



**总结：**

- `ArrayList` 集合

  ​	底层是数组结构实现，查询快、增删慢

- `LinkedList` 集合

  ​	底层是链表结构实现，查询慢、增删快



## Collections

注意 `Collections` 并不是集合，它比 `Collection` 多了一个 `s`，一般后缀为 `s` 的类很多都是工具类。这里的Collections是用来操作 `Collection` 的工具类。它提供了一些好用的静态方法

```java
import java.util.Collections;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();

        // 批量添加数据
        Collections.addAll(list,"c", "b", "a");
        System.out.println(list); // [c, b, a]

        // 随机打乱排序
        Collections.shuffle(list);
        System.out.println(list); // [b, c, a]

        // 数据排序
        Collections.sort(list);
        System.out.println(list); // [a, b, c]
    }
}
```

上面我们往集合中存储的元素要么是 `Stirng` 类型，要么是 `Integer` 类型，他们本来就有一种自然顺序所以可以直接排序



但如果我们往 `ArrayList` 集合中存储 `Student` 对象，这个时候就需要对 `ArrayList` 集合进行自定义比较规则

**方法一**

```java
import java.util.Collections;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<>();

        list.add(new Student("蜘蛛精", 23, 169.7));
        list.add(new Student("紫霞", 22, 169.8));
        list.add(new Student("紫霞", 22, 169.8));
        list.add(new Student("至尊宝", 26, 169.5));

        Collections.sort(list);
    }
}
```

```java
// 继承Comparable<Student>接口，重写对应的排序方法
public class Student implements Comparable<Student> {
    private String name;
    private int age;
    private double height;

    public Student(String name, int age, double height){
        this.name = name;
        this.age = age;
        this.height = height;
    }

    // 重写compareTo方法
    @Override
    public int compareTo(Student o){
        return this.getAge() - o.getAge();
    }

    // get / set方法...
}
```



**方法二**

```java
import java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<>();

        list.add(new Student("蜘蛛精", 23, 169.7));
        list.add(new Student("紫霞", 22, 169.8));
        list.add(new Student("紫霞", 22, 169.8));
        list.add(new Student("至尊宝", 26, 169.5));
        System.out.println(list);

        // 使用匿名内部类实现重写compare方法
        Collections.sort(list, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getAge() - o2.getAge();
            }
        });

        // 通过lambda表达式实现简化上述写法
        Collections.sort(list, (o1, o2) -> o1.getAge() - o2.getAge());

        // 推荐这么写
        list.sort((o1, o2) -> o1.getAge() - o2.getAge());

        System.out.println(list);
    }
}
```



**Collections 常见的操作**

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        // 添加数据
        list.add("aaa");

        // 批量添加数据
        Collections.addAll(list, "bbb", "ccc", "ddd", "eee");
        System.out.println(list); // [aaa, bbb, ccc, ddd, eee]

        // 随机打乱集合数据
        Collections.shuffle(list);
        System.out.println(list); // [aaa, ddd, ccc, eee, bbb]

        // 排序：从小到大
        Collections.sort(list);
        System.out.println(list); // [aaa, bbb, ccc, ddd, eee]

        // 排序：从大到小
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                // return a.compareTo(b); // 升序：[aaa, bbb, ccc, ddd, eee]
                return b.compareTo(a); // 降序：[eee, ddd, ccc, bbb, aaa]
            }
        });
        System.out.println(list); // [eee, ddd, ccc, bbb, aaa]

        // 简写：从大到小
        Collections.sort(list, (a, b) -> b.compareTo(a));
        System.out.println(list); // [eee, ddd, ccc, bbb, aaa]

        // 从大到小排序
        list.sort((a, b) -> a.compareTo(b));
        System.out.println(list); // [aaa, bbb, ccc, ddd, eee]

        // 将指定的数组转换为不可改变的数组并返回
        List<String> newList = Collections.unmodifiableList(list);
        System.out.println(newList); // [aaa, bbb, ccc, ddd, eee]
        // newList.add("fff"); // 报错：不能添加、修改、删除

        list.add("fff"); // 允许
        System.out.println(list); // [aaa, bbb, ccc, ddd, eee, fff]
    }
}
```



## Set

`Set` 集合是一种无序的集合，它不保留元素的插入顺序，并且不允许重复元素。这意味着当你通过 `Set` 集合访问元素时，你不能依赖于元素的顺序。如果你需要有序的集合，可以考虑使用 `List` 接口的实现类，例如 `ArrayList` 或 `LinkedList`



### HashSet

如果 `HashSet` 集合中有重复的数据，那么会自动去重

```java
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<String> list = new HashSet<>();
        list.add("张三");
        list.add("李四");
        list.add("张三");
        list.add("王五");

        for(String item : list) {
            System.out.println(item);
        }
        
        // 李四
        // 张三
        // 王五
    }
}
```



### HashMap

HashMap是一种键值对映射的集合，它不允许重复的键。当你向 `HashMap` 中插入相同的键时，新的键值对会覆盖旧的键值对。

#### 增删改查

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<String, List> list = new HashMap<>();

        // 新增
        list.put("1", List.of(1, 2));
        list.put("2", List.of(3, 4));
        list.put("3", List.of(5, 6));

        // 删除：返回值是被删除的值
        System.out.println(list.remove("2")); // [3, 4]

        // 修改：返回值是被修改之前的值
        System.out.println(list.put("1", List.of(1, 2, 3))); // [1, 2]

        // 查询
        System.out.println(list); // {1=[1, 2], 3=[5, 6]}
    }
}
```



虽然值可以重复，但键必须是唯一的。因此，`HashMap` 中的键是无重复的，但值可以重复。

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<String, String> list = new HashMap<>();

        // 添加数据
        list.put("jack", "111");
        list.put("rose", "222");
        list.put("tom", "333");

        // 获取数据
        System.out.println(list.get("jack")); // 111

        // 获取map的所有key
        Set<String> keys = list.keySet();
        System.out.println(keys); // [jack, rose, tom]

        // 获取map的所有value
        Collection<String> values = list.values();
        System.out.println(values); // [111, 222, 333]

        System.out.println("--------------------");

        // 增强for循环遍历键
        for (String k : keys) {
            System.out.println(k + " : " + list.get(k));
        }

        System.out.println("--------------------");

        // 迭代器遍历键
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            String k = it.next();
            System.out.println(k);
        }

        System.out.println("--------------------");

        // lambda遍历键
        keys.forEach(k -> System.out.println(k));
    }
}
```



#### entrySet

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<String, String> list = new HashMap<>();

        // 添加数据
        list.put("jack", "111");
        list.put("rose", "222");
        list.put("tom", "333");

        // 获取所有的键值对
        System.out.println(list.entrySet());
        // [tom=333, rose=222, jack=111]

        // 通过增强for循环遍历
        for (Map.Entry<String, String> entry : list.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        System.out.println("------------------------");

        // 通过迭代器遍历
        Iterator<Map.Entry<String, String>> it = list.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        System.out.println("------------------------");

        // 通过lambda表达式遍历
        list.entrySet().forEach(entry -> System.out.println(entry.getKey() + " : " + entry.getValue()));
    }
}
```



**复杂应用场景**

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<String, List<Student>> list = new HashMap<>();

        List<Student> l1 = new ArrayList<>();
        l1.add(new Student("张三", 13));
        l1.add(new Student("李四", 23));

        List<Student> l2 = new ArrayList<>();
        l2.add(new Student("王五", 33));

        List<Student> l3 = new ArrayList<>();
        l3.add(new Student("张亮", 25));

        list.put("A", l1);
        list.put("B", l2);
        list.put("C", l3);

        for (Map.Entry<String, List<Student>> e : list.entrySet()) {
            System.out.println(e.getKey());

            // 如果数组有值就遍历
            if (!e.getValue().isEmpty()) {
                for (Student item : e.getValue()) {
                    System.out.println(item.name + " " + item.age);
                }
            }
        }
    }
}
```



## 电影管理系统

实现电影增删改查功能

```java
// Main.java
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Film> list = new ArrayList<Film>();

    public static void main(String[] args) {
        init();

        while (true) {
            System.out.println("============== 电影管理系统 ===============");
            System.out.println("1 新增电影信息");
            System.out.println("2 删除电影信息");
            System.out.println("3 修改电影信息");
            System.out.println("4 查询指定电影信息");
            System.out.println("5 查询所有电影信息");
            System.out.println("6 退出系统");

            Scanner sc = new Scanner(System.in);
            System.out.print("请输入您的选择：");
            int n = sc.nextInt();

            switch (n) {
                // 添加电影信息
                case 1 -> addFilm();
                // 删除电影信息
                case 2 -> delFilm();
                // 修改电影信息
                case 3 -> editFilm();
                // 查询指定电影信息
                case 4 -> {
                    System.out.print("请输入电影名称：");
                    String name = sc.next();

                    // 传值就是查询指定电影信息
                    QueryFilm(name);
                }
                // 查询所有电影信息
                case 5 -> {
                    // 不传值就是查询所有电影信息
                    queryFilm("");
                }
                // 退出程序
                case 6 -> {
                    System.exit(0);
                }
            }
        }
    }

    // 初始化数据
    vstatic void init() {
        Film f1 = new Film();
        f1.setName("肖申克的救赎");
        f1.setType("剧情");
        f1.setDescribe("两个被囚禁的男人多年来建立了深厚的友谊，通过共同的善行找到了慰藉和最终的救赎。");
        f1.setPerformer("蒂姆·罗宾斯，摩根·弗里曼");
        f1.setPrice(9.99);

        Film f2 = new Film();
        f2.setName("教父");
        f2.setType("犯罪");
        f2.setDescribe("一个有组织犯罪家族的老大将他秘密的帝国的控制权转交给他不情愿的儿子。");
        f2.setPerformer("马龙·白兰度，阿尔·帕西诺");
        f2.setPrice(12.99);

        Film f3 = new Film();
        f3.setName("低俗小说");
        f3.setType("犯罪");
        f3.setDescribe("两个杀手、一个拳击手、一个黑帮妻子和一对餐馆抢劫犯的生活在四个暴力和救赎的故事中交织在一起。");
        f3.setPerformer("约翰·特拉沃尔塔，乌玛·瑟曼");
        f3.setPrice(10.99);

        list.add(f1);
        list.add(f2);
        list.add(f3);
    }

    // 添加电影信息
    public static void addFilm() {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入电影名称：");
        String name = sc.next();

        System.out.print("请输入电影类型：");
        String type = sc.next();

        System.out.print("请输入电影简述：");
        String describe = sc.next();

        System.out.print("请输入电影演员：");
        String performer = sc.next();

        System.out.print("请输入电影票价：");
        double price = sc.nextDouble();

        // 创建一个新的电影对象
        Film f = new Film();
        f.setName(name);
        f.setType(type);
        f.setDescribe(describe);
        f.setPerformer(performer);
        f.setPrice(price);

        // 添加到集合中
        list.add(f);
        System.out.format("添加电影：《 %s 》成功\n", f.getName());
    }

    // 删除电影信息
    public static void delFilm() {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入电影名称：");
        String name = sc.next();

        boolean isFilm = false;

        // 判断是否存在该电影
        for (Film item : list) {
            if (item.getName().equals(name)) {
                isFilm = true;

                // 删除电影
                list.remove(item);

                System.out.format("删除电影：《 %s 》成功\n\n", item.getName());
                break;
            }
        }

        if (!isFilm) {
            System.out.format("需要删除的电影：《 %s 》不存在\n", name);
        }
    }

    // 修改电影信息
    public static void editFilm() {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入电影名称：");
        String name = sc.next();

        // 判断是否存在该电影
        boolean isExist = false;
        Film f = null;

        for (Film item : list) {
            if (item.getName().equals(name)) {
                isExist = true;
                f = item;
                break;
            }
        }

        // 如果存在就修改，否则就提示不存在
        if (isExist) {
            System.out.print("请输入电影类型：");
            String type = sc.next();

            System.out.print("请输入电影简述：");
            String describe = sc.next();

            System.out.print("请输入电影演员：");
            String performer = sc.next();

            System.out.print("请输入电影票价：");
            double price = sc.nextDouble();

            // 创建一个新的电影对象
            f.setName(name);
            f.setType(type);
            f.setDescribe(describe);
            f.setPerformer(performer);
            f.setPrice(price);

            System.out.format("修改电影：《 %s 》成功\n\n", f.getName());
        } else {
            System.out.format("需要修改的电影：《 %s 》不存在\n", name);
        }
    }

    // 查询电影信息
    public static void queryFilm(String name) {
        // 判断name是否为空，如果为空就是查询所有电影信息，否则就是查询指定电影信息
        if (name.isEmpty()) {
            for (Film item : list) {
                System.out.println("============== 电影信息 ===============");
                System.out.format("电影：%s\n类型：%s\n简述：%s\n演员：%s\n票价：%.2f\n", item.getName(), item.getType(), item.getDescribe(), item.getPerformer(), item.getPrice());
                System.out.println("=====================================");
            }

            System.out.format("查询所有电影成功 共计：%d部\n\n", list.size());
        } else {
            for (Film item : list) {
                if (item.getName().equals(name)) {
                    System.out.println("============== 电影信息 ===============");
                    System.out.format("电影：%s\n类型：%s\n简述：%s\n演员：%s\n票价：%.2f\n", item.getName(), item.getType(), item.getDescribe(), item.getPerformer(), item.getPrice());
                    System.out.println("=====================================");
                }
            }

            System.out.format("查询电影：《 %s 》成功\n", name);
        }
    }
}
```

```java
// Film.java
public class Film {
    private String name;
    private String type;
    private String describe;
    private String performer;
    private double price;


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescribe() {
        return this.describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getPerformer() {
        return this.performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
```

