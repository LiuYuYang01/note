# Java

## 起步

### Hello World

第一段代码

```java
public class Main {
    public static void main(String[] args) {
        System.out.print("Hello World!");
    }
}
```



### 输出语句

#### print

输出不换行

```java
System.out.print("Hello");
System.out.print(" Java!");
# Hello Java!
```



#### println 

输出并换行

```java
System.out.println("Hello");
System.out.println("Java!");
# Hello
# Java!
```



#### format

如果不使用字符串格式化输出，在数据拼接时候就会显得非常麻烦

```java
 System.out.println("zs " + 20 + " 男"); // zs 20 男
```



所以就需要用到字符串格式化输出

```java
System.out.format("%s %d %c", "zs", 20, '男'); // zs 20 男
```



也可以将格式化后的数据作为返回值使用

```java
import static java.lang.String.format;

public class Main {
    public static void main(String[] args) {
        String s = format("%s %d %c", "zs", 20, '男');
        System.out.printf(s); // zs 20 男
    }
}
```



#### printf

```java
System.out.printf("%s %d %c", "zs", 20, '男'); // zs 20 男
```



## 数据类型

| 数据类型 | 关键字  | 内存占用 |                 取值范围                  |
| :------: | :-----: | :------: | :---------------------------------------: |
|   整数   |  byte   |    1     |    负的2的7次方 ~ 2的7次方-1(-128~127)    |
|          |  short  |    2     | 负的2的15次方 ~ 2的15次方-1(-32768~32767) |
|          |   int   |    4     |        负的2的31次方 ~ 2的31次方-1        |
|          |  long   |    8     |        负的2的63次方 ~ 2的63次方-1        |
|  浮点数  |  float  |    4     |        1.401298e-45 ~ 3.402823e+38        |
|          | double  |    8     |      4.9000000e-324 ~ 1.797693e+308       |
|   字符   |  char   |    2     |                  0-65535                  |
|   布尔   | boolean |    1     |                true，false                |

**最为常用的数据类型选择：**

- 在定义变量的时候，要根据实际的情况来选择不同类型的变量。

  比如：人的年龄，可以选择byte类型。

  比如：地球的年龄，可以选择long类型。

- 如果整数类型中，不太确定范围，那么默认使用int类型。

- 如果小数类型中，不太确定范围，那么默认使用double类型。

- 如果要定义字符类型的变量，那么使用char

- 如果要定义布尔类型的变量，那么使用boolean



**定义数据类型**

数据类型 变量名 = 数据值;

```java
public class Main{
    public static void main(String[] args){
        //1.定义byte类型的变量
        byte a = 10;
        System.out.println(a);

        //2.定义short类型的变量
        short b = 20;
        System.out.println(b);

        //3.定义int类型的变量
        int c = 30;
        System.out.println(c);

        //4.定义long类型的变量
        long d = 123456789123456789L;
        System.out.println(d);

        //5.定义float类型的变量
        float e = 10.1F;
        System.out.println(e);

        //6.定义double类型的变量
        double f = 20.3;
        System.out.println(f);

        //7.定义char类型的变量
        char g = 'a';
        System.out.println(g);

        //8.定义boolean类型的变量
        boolean h = true;
        System.out.println(h);

    }
}
```

- 如果要定义 一个整数类型的变量，不知道选择哪种数据类型了，默认使用 `int`
- 如果要定义 一个小数类型的变量，不知道选择哪种数据类型了，默认使用 `double`
- 如果要定义一个 `long` 类型的变量，那么在数据值的后面需要加上L后缀（大小写都可以，建议大写。）
- 如果要定义一个 `float` 类型的变量，那么在数据值的后面需要加上F后缀（大小写都可以）



**示例**

```java
public class Main{
	public static void main(String[] args){
		// 定义字符串类型的变量记录老师的姓名
		String name = "liuyuyang";
		// 定义整数类型的变量记录老师的年龄
		int age = 20;
		// 定义字符类型的变量记录老师的性别
		char gender = '男';
		// 定义小数类型的变量记录老师的身高
		double height = 180.1;
		// 定义布尔类型的变量记录老师的婚姻状况
		boolean flag = false;
		
		System.out.println(name);
		System.out.println(age);
		System.out.println(gender);
		System.out.println(height);
		System.out.println(flag);
	}
}
```



**数据类型相互转换**

```java
public class Main {
    public static void main(String[] args) {
        // 字符串转整型
        int a = Integer.parseInt("100");

        // 整型转字符串
        String b = Integer.toString(100);

        // 字符串转浮点型
        double c = Double.parseDouble("100");

        // 浮点型转字符串
        String d = Double.toString(100.00);
    }
}
```



## 隐式转换

整数可以隐式转换为浮点数，而浮点数不能隐式转换为整数

```java
System.out.println(d); // 1024.0
```



取值范围小的可以赋值给取值范围大的，但取值范围大的不能赋值给小的

```java
short s = 1024;
int i = s;
```



取值范围小的，和取值范围大的进行运算，小的会先提升为大的，再进行运算。

取值顺序：byte、short、int、long、float、double

```
int i = 1000;
long l = 24L;
??? res = i + l; // long
```

上述代码相当于两个 `long` 类型相加所以他的结果也是 `long` 类型的



默认为 `int` 类型，在进行运算时都会先提升为 `int` 类型

```java
byte b = 10;
byte s = 20;
??? res = b + s; // int
```

```java
byte b = 10;
short s = 20;
??? res = b + s; // int
```



此处相当于 `int + int + long`，范围小的与大的进行运算结果就是范围大的类型

```java
byte b = 10;
short s = 20;
long l = 100L;
??? res = b + s + l; // long
```



**强制转换**

目标数据类型 变量名 = （目标数据类型）被强转的数据；

```java
double d = 10.241012102410241;
int i = (int) d;
System.out.println(i); // 10
```



**总结**

自动转换：小类型 转换 大类型

强制转换：大类型 转换 小类型



## 键盘录入

键盘录入的实际功能 `Java` 已经帮我们写好了，不需要我们自己再实现了，而 `Java` 写好的功能都放在了 `Scanner` 这个类中，所以，我们只要直接使用 `Scanner` 这个类就可以了。

```java
//导入Scanner包
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 创建Scanner对象
        Scanner sc = new Scanner(System.in);

        System.out.print("请输入你的名字：");
        // 接收输入的数据
        String name = sc.next();

        // 输出刚刚输入的数据
        System.out.println("你的名字：" + name);
    }
}
```

接收字符串：`sc.next()`

接收整数：`sc.nextInt()`

接收浮点数：`sc.nextDouble`



**注意1：** 在接收数据的时候，如果遇到空格就会停止接收空格后面的数据

```java
Scanner sc = new Scanner(System.in);
double d = sc.nextDouble();
System.out.println(d);
//键盘录入：1.1 2.2//注意录入的时候1.1和2.2之间加空格隔开。
//此时控制台打印1.1
//表示nextDouble方法在接收数据的时候，遇到空格就停止了，后面的本次不接收。
```

```java
Scanner sc = new Scanner(System.in);
int i = sc.nextInt();
System.out.println(i);
//键盘录入：1 2//注意录入的时候1和2之间加空格隔开。
//此时控制台打印1
//表示nextInt方法在接收数据的时候，遇到空格就停止了，后面的本次不接收。
```

```java
Scanner sc = new Scanner(System.in);
String s = sc.next();
System.out.println(s);
//键盘录入：a b//注意录入的时候a和b之间加空格隔开。
//此时控制台打印a
//表示next方法在接收数据的时候，遇到空格就停止了，后面的本次不接收。
```



**注意2：** 虽然停止接收空格后面的数据但是这些数据还在内存中，如果后面还有其他键盘录入的方法，会自动将这些数据接收并赋值给下一个

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 如果输入的是a b那么就会自动将str1为a，str2为b
        System.out.println("1.请输入：");
        String str1 = sc.next();

        System.out.println("2.请输入：");
        String str2 = sc.next();

        System.out.println(str1); // a
        System.out.println(str2); // b
    }
}
```



**注意3：** 如果使用 `nextLine` 就不会出现上述问题，遇到空格不会停止接收、赋值

```java
System.out.println("1.请输入：");
String str1 = sc.nextLine();

System.out.println("2.请输入：");
String str2 = sc.nextLine();

System.out.println(str1); // a b
System.out.println(str2); // c d
```



**`nextLine` 不能混用跟其他键盘录入混用**

如果输入的是：`a b c d e` 那么运行结果 `str1` 为 `a`，`str2` 为 ` b c d e`

```java
System.out.println("1.请输入：");
String str1 = sc.next();

System.out.println("2.请输入：");
String str2 = sc.nextLine();

System.out.println(str1); // a
System.out.println(str2); //  b c d e
```



如果输入的是：`a`，实际上我们录的是 `a + 回车`

而 `next` 是遇到空格，回车，制表符就会停止

所以 `next` 只能接受 `a`，回车还在内存中没有被接收。

此时就被 `nextLine` 接收了。

所以，如果混用就会导致 `nextLine` 接收不到数据。



## 字符串拼接

字符串跟任何类型相加都为拼接操作

```java
System.out.println(1 + "abc" + 2); // 1abc2
System.out.println(1 + 2 + "abc" + 2 + 3); // 3abc5

String name = "liuyuyang";
System.out.println("我的名称：" + name); // 我的名称：liuyuyang
```



当字符类型与数值类型相加时，会隐式将字符转换为对应的 `ASCII` 值然后进行运算

字符 `a` 对应的值为： `97 + 0 = 107`

```java
char c = 'a';
int res = c + 10;
System.out.println(res); // 107
```



## 逻辑运算符

**&：逻辑与（而且）**

​	两边都为真，结果才是真，只要有一个为假，那么结果就是假。

**&&：短路与**

跟逻辑与一致，唯一的区别就是短路与在判断结果时，如果左边为 `false`，那么右边就不再执行了

```java
//两边都是真，结果才是真。
System.out.println(true & true);//true
System.out.println(false & false);//false
System.out.println(true & false);//false
System.out.println(false & true);//false
```



**|：逻辑或（或者）**

​	两边都为假，结果才是假，只要有一个为真，那么结果就是真。

**||：短路或**

如果左边为 `true`，那么右边就不再执行了

```java
//两边都是假，结果才是假，如果有一个为真，那么结果就是真。
System.out.println(true | true);//true
System.out.println(false | false);//false
System.out.println(true | false);//true
System.out.println(false | true);//true
```



**^：异或**

如果两边相同，结果为 `false`，如果两边不同，结果为 `true`

```java
//左右不相同，结果才是true，左右相同结果就是false
System.out.println(true ^ true);//false
System.out.println(false ^ false);//false
System.out.println(true ^ false);//true
System.out.println(false ^ true);//true
```



**!：取反**

如果值为 `true`，结果为 `false`，如果值为 `fasle`，结果为 `true`

```java
System.out.println(!false); //true
System.out.println(!true); //false
```

在 `Java` 中取反只能对布尔类型使用，而 `JS` 中可以对大部分类型使用



## 三元表达式

```java
byte x = 10;
byte y = 20;
String z = x > y ? "x 大于 y" : "x 小于 y";
System.out.println(z); // x 小于 y
```



## Switch

JDK12 新特性

```java
int number = 10;

switch (number) {
    case 1 -> System.out.println("一");
    case 2 -> System.out.println("二");
    case 3 -> System.out.println("三");
    default -> System.out.println("其他"); // 输出这个
}
```



## 字符串

可以通过以下两种方式创建字符串

```java
String a = new String("")
String b = ""
```



**两种创建方式 的 区别**

使用 `new String` 创建的字符串虽然内容相同，但地址不同，因为每一次 `new` 时就会重新开辟一个新的内存空间。

```java
String a = new String("Hello");
String b = new String("Hello");

System.out.println(a == b); // false
```

每 `new` 一次就会新创建一个内存空间，所以两次比较不相等



而直接赋值形式创建字符串只要字符串相同就不会创建创建多个内存空间，而是使用一个

```java
String a = "Hello";
String b = "Hello";

System.out.println(a == b); // true
```

**注意：** 使用 `==` 号比较的是内存地址而不是数据内容，如果比较字符串的内容可以使用字符串的 `equals` 方法



通过字符串内置的 `equals` 方法比较内容是否相等

```java
String a = new String("Hello");
String b = new String("Hello");

System.out.println(a.equals(b)); // true
```

```java
String a = "Hello";
String b = "Hello";

System.out.println(a.equals(b)); // true
```

`==` 在比较基本数据类型时跟 `equlas` 是一样的，只比较内容是否相等。但如果是引用类型那么就比较的是内存地址 



**注意：** 如果创建两个字符串是同样的值，那么第二个字符串并不会重新创建一个内存，而是从第一个字符串的常量池中获取



### chatAt

获取指定位置的字符串

```java
String s = new String("Hello World!");

System.out.println(s.charAt(0)); // H
System.out.println(s.charAt(s.length() - 1)); // !
```



### toCharArray

将字符串转换为字符数组并返回

```java
String s = "Hello World";

char[] list = s.toCharArray();

System.out.println(list); // Hello World
System.out.println(list[0]); // H
System.out.println(list[1]); // e
```



### equals

比较两个字符串是否相等，区分大小写字符串

```java
String s = "Hello";

// 区分大小写
System.out.println("Hello".equals(s)); // t
System.out.println("hello".equals(s)); // f
```



使用 `equalsIgnoreCase` 不区分大小写

```java
String s = "Hello";

// 区分大小写
System.out.println("Hello".equalsIgnoreCase(s)); // t
System.out.println("hello".equalsIgnoreCase(s)); // t
```



### contains

查询字符串中是否存在指定的值

```java
String s = "Hello World!";

System.out.println(s.contains("Hello")); // true
System.out.println(s.contains("Java")); // false
```



### indexOf

查询字符串中指定的值是否存在，如果存在就返回下标，否则返回 `-1` 

```java
String s = new String("Hello World!");

System.out.println(s.indexOf("World")); // 6
System.out.println(s.indexOf("Java")); // -1
```

`lastIndexOf()` 与 `indexOf` 相似，不同的是前者从后往前查询，后者从前往后查询



### startsWith / endsWith

查询字符串是否以指定的值开头、结尾

```java
"Hello".startsWith("He"); // true
"Hello".endsWith("lo"); // true
```



### substring

从指定位置截取指定的字符串

```java
String s = "Hello World!";

System.out.println(s.substring(0));
// 为0代表从0的位置截取到最后一个：Hello World!

System.out.println(s.substring(0, 5));
// 从0的位置截取到第5个：Hello

System.out.println(s.substring(6));
// 从第6个位置开始截取到最后一个：World!

System.out.println(s.substring(2, 5));
// 从第二个位置截取到第5个：llo
```



### trim

去除字符串中首尾的空格，中间空格无法去除

```java
String s = "Hello World!";

System.out.println("  He   llo  ".trim()); // He   llo
```



### replace

将指定字符串中所有的 `o` 替换为 `123`

```java
String s = "Hello World!";

String str = s.replace("o", "123");
System.out.println(s + " / " + str);
// Hello World! / Hell123 W123rld!
```

`replace` 会替换所有字符串，如果只需要替换第一个的话可以使用：`replaceFirst`



### split

将字符串以指定分隔符转换为数组

```java
String s = "Hello World!";

// 以空格形式将字符串转换为数组
String arr[] = s.split("");

// 此时已经将字符串转换为了数组
System.out.println(arr);
// [Ljava.lang.String;@4eec7777

// 可以访问一个数据测试一下
System.out.println(arr[1]); // e

// 打印数据
System.out.println(Arrays.toString(arr));
// [H, e, l, l, o,  , W, o, r, l, d, !]
```



### 练习

**1. 以 `-` 作为分隔符转换为数组**

```java
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String s = "a-b-c-d-e-g-f";

        // 以-形式将字符串转换为数组
        String arr[] = s.split("-");

        // 打印数据
        System.out.println(Arrays.toString(arr));
        // [a, b, c, d, e, g, f]
    }
}
```



**2. 手机号屏蔽**

```java
public class Main {
    public static void main(String[] args) {
        String phone = "17788116666";

        // 截取前三位数
        String start = phone.substring(0, 3);
        // 截取后四位数
        String end = phone.substring(7);

        // 拼接字符串
        System.out.println(start + "****" + end);
    }
}
```



**3. 敏感词过滤**

```java
public class Main {
    public static void main(String[] args) {
        String talk = "后裔你玩什么啊，TMD";

        // 敏感词替换
        talk = talk.replace("TMD", "***");

        System.out.println(talk);
    }
}
```

如果有多个敏感词，可以这么做

```java
public class Main {
    public static void main(String[] args) {
        String talk = "后裔你玩什么啊，TMD,GDX,ctmd,ZZ";

        // 所有的敏感词
        String[] arr = {"TMD", "GDX", "ctmd", "ZZ", "lj", "FW", "nt"};

        for (byte i = 0; i < arr.length; i++) {
            talk = talk.replace(arr[i], "***");
        }

        System.out.println(talk);
    }
}
```



## for

```java
public class Main {
    public static void main(String[] args) {
        String[] list = {"aaaa", "bbbb", "cccc"};

        //   类型   当前项   数组
        for (String item : list) {
            System.out.println(item);
            // aaaa
            // bbbb
            // cccc
        }
    }
}
```



## StringBuilder

StringBuilder 它的性能比传统定义字符串的方式高，下面来做个对比，使用两种方式追加10万条数据

**传统字符串**

```java
        long start = System.currentTimeMillis();

        String s = "";
        for (int i = 1; i <= 100000; i++) {
            s += i;
        }

        long end = System.currentTimeMillis();

		// 求出追加字符串的耗时
        System.out.println(end - start); # 2500
```

**StringBuilder**

```java
		long start = System.currentTimeMillis();

        StringBuilder s = new StringBuilder("");
        for (int i = 1; i <= 100000; i++) {
            s.append(i);
        }

        long end = System.currentTimeMillis();

        System.out.println(end - start); # 5
```

可以发现传统字符串追加 `10` 万条数据用了 `2500` 毫秒，也就是2秒，而使用 `StringBuilder` 才花了 `5` 毫秒。可见能够有效提高字符串的操作效率



### 基本使用

```java
public class Main {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("abcde");

        // 获取某一个
        System.out.println(sb.charAt(2));

        // 添加元素
        sb.append("fg");
        sb.append(123);
        sb.append(true);
        System.out.println(sb); // abcdefg123true

        // 反转字符串
        System.out.println(sb.reverse()); // eurt321gfedcba

        // 获取长度
        byte len = (byte) sb.length();
        System.out.println(len);
    }
}
```



### 链式编程

```java
public class Main {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("abcde");

        // 链式编程
        sb.append("fg").append(123).append(true);
        System.out.println(sb); // abcdefg123true
    }
}
```

```java
public class Main {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("abcde");

        // 链式编程
        sb.append("f").reverse().append("g");
        System.out.println(sb); // fedcbag
    }
}
```



### 数据可变性

StringBuilder 可以看成是一个容器，创建之后的字符串内容是 **可变** 的

```java
public class Main {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("abcde");

        sb.reverse();

        System.out.println(sb); // edcba
    }
}
```



### 练习

**1. 对称字符串**

```java
public class Main {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("abcde");

        // 原数据
        String newValue = sb.toString();

        sb.reverse();

        // 反转后的数据
        String usedValue = sb.toString();

        // 用没有反转的数据与反转的数据对比，如果为true就是对称字符串
        System.out.println(newValue.equals(usedValue));
    }
}
```



### 扩展

1. **字符串存储的内存原理**

   `String s = “abc”` 直接赋值

   特点：

   ​	此时字符串 `abc` 是存在字符串常量池中的。

   ​	先检查字符串常量池中有没有字符串 `abc`，如果有，不会创建新的，而是直接复用。如果没有 `abc`，才会创建一个新的。

   所以，直接赋值的方式，代码简单，而且节约内存。

2. **new 出来的字符串**

   看到new关键字，一定是在堆里面开辟了一个小空间。

   ```java
   String s1 = new String（“abc”）；
   
   String s2 = “abc”；
   ```

   `s1` 记录的是 `new` 出来的，在堆里面的地址值。

   `s2` 是直接赋值的，所以记录的是字符串常量池中的地址值。

3. **== 号比较的到底是什么？**

   如果比较的是基本数据类型：比的是具体的数值是否相等。

   如果比较的是引用数据类型：比的是地址值是否相等。

   **结论：** `==` 只能用于比较基本数据类型。不能比较引用数据类型



## StringJoiner

- StringJoiner 跟 StringBuilder一样，也可以看成是一个容器，创建之后里面的内容是可变的。
- 作用：提高字符串的操作效率，而且代码编写特别简洁，但是目前市场上很少有人用。 
- JDK8出现的

基本使用：

```java
//1.创建一个对象，并指定中间的间隔符号
StringJoiner sj = new StringJoiner("---");
//2.添加元素
sj.add("aaa").add("bbb").add("ccc");
//3.打印结果
System.out.println(sj);//aaa---bbb---ccc
//1.创建对象
StringJoiner sj = new StringJoiner(", ","[","]");
//2.添加元素
sj.add("aaa").add("bbb").add("ccc");
int len = sj.length();
System.out.println(len);//15
//3.打印
System.out.println(sj);//[aaa, bbb, ccc]
String str = sj.toString();
System.out.println(str);//[aaa, bbb, ccc]
```



## 数组

定义数组的两种方式

```java
double[] arr = new double[]{1.85,1.82,1.78,1.65};
int[] arr = {10, 23, 8, 50, 3, 44};
```



定义的数组不能直接输出，直接输出得到的是该数组在 `JVM` 的引用地址

```java
System.out.println(arr); // [I@4eec7777
```

[ ：表示现在打印的是一个数组

I：表示现在打印的数组是 `int` 类型的

@：仅仅是一个间隔符号而已

4eec7777：就是数组在内存中真正的地址值（十六进制的）

但是，我们习惯性会把 `[I@4eec7777` 这个整体称之为数组的地址值



如果想查看数据，可以借助内置的 `Arrays` 方法实现

```java
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr = {10, 23, 8, 50, 3, 44};
        
        String list = Arrays.toString(arr);
        System.out.println(list);
        // [10, 23, 8, 50, 3, 44]
    }
}
```



也可以通过 `for` 来遍历数组

```java
int[] arr = {10, 23, 8, 50, 3, 44};

for (int i = 0; i < arr.length; i++) {
    System.out.println(arr[i]);
}
```



规定数组中最多只能有 `3` 个字符

```java
int[] list = new int[3];
list[0] = 1;
list[1] = 2;
list[2] = 3;

list[3] = 4; // 多出3个就会报错
```



**数组两种初始化方式的区别**

**静态初始化：** 手动指定数组的元素，系统会根据元素的个数，计算出数组的长度

```java
int[] arr = {1,2,3,4,5};
```



**动态初始化：** 手动指定数组长度，由系统给出默认初始化值

```java
int[] arr = new int[3];
```



定义一个不限数据类型的数组

```java
Object[] list = {"hello", 2, true, 'a'};
```



**使用场景：**

只明确元素个数，但是不明确具体的数据，推荐使用动态初始化

已经明确了要操作的所有数据，推荐使用静态初始化

**举个栗子：**

* 使用数组来存储键盘录入的5个整数

  ```java
  int[] arr = new int[5];
  ```

  

* 将全班的学生成绩存入数组中，已知学生成绩为：`66,77,88,99,100`

  ```java
  int[] arr = new int[5];
  
  arr[0] = 66;
  arr[1] = 77;
  ... 
  ```

  虽然可以实现，但是太麻烦了

  建议使用静态初始化

  ```java
  int[] arr = {66,77,88,99,100};
  ```



**默认值**

整数类型：`0`

小数类型：`0.0`

布尔类型：`false`

字符类型：`'\u0000'`

引用类型：`null`



## 方法

### 定义方法

```java
public class Main {
    // 所有方法在main方法中调用，他是项目的入口
    public static void main(String[] args) {
        // 调用该方法
        func();
    }

    // 定义一个func方法
    public static void func(){
        System.out.println("Hello Java!");
    }
}
```

**注意：** `main` 方法的名称不能更改，并且当程序运行时，JVM会自动查找并调用带有 `main` 的方法体，不需要手动调用



### 方法传参

```java
public class Main {
    public static void main(String[] args) {
        func(10, 20);
    }

    public static void func(int x, int y) {
        System.out.println(x + y);
    }
}
```



### 方法返回值

```java
public class Main {
    public static void main(String[] args) {
        int sum = func(10, 20);
        System.out.println(sum);
    }

    public static int func(int x, int y) {
        return x + y;
    }
}
```



### 注意事项

1. 方法不能嵌套定义

```java
public class MethodDemo {
    public static void main(String[] args) {

    }

    public static void methodOne() {
		public static void methodTwo() {
       		// 这里会引发编译错误!!!
    	}
    }
}
```



2. `void` 表示无返回值，可以省略 `return`，也可以单独的书写 `return`，后面不加数据

```java
public class MethodDemo {
    public static void main(String[] args) {

    }
    public static void methodTwo() {
        //return 100; 编译错误，因为没有具体返回值类型
        return;	
        //System.out.println(100); return语句后面不能跟数据或代码
    }
}
```



### 方法重载

当多个方法在一个类中并且方法名相同、类型不相同就构成了方法重载



**示例：** 通过传递不同的类型触发不同的方法体代码

```java
public class Main {
    public static void main(String[] args) {
        func(1024);
        func("1024");
        func(10.24);
    }

    // 传字符串类型就触发这个
    public static void func(String x) {
        System.out.println("参数是字符串类型");
    }

    // 传数值类型就触发这个
    public static String func(int x) {
        System.out.println("参数是数值类型");
        return "参数是数值类型";
    }

    public static void func(double x) {
        System.out.println("参数是浮点数类型");
    }
}
```

**注意：** 方法重载只针对同一个类中方法的名称与参数进行识别，与返回值无关，换句话说不能通过返回值来判定两个方法是否相互构成重载



### 可变参数

当我们不确定要传多少个参数时候可以采用可变参数

```java
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        info("aaa", "bbb", "ccc");
    }

    // 注意：可变参数只能作为形参列表的最后一项
    public static void info(String data, String... args) {
        System.out.println(data); // aaa
        System.out.println(Arrays.toString(args)); // 参数以数组形式返回：[bbb, ccc]

        for (String item : args) {
            System.out.println(item);
            // bbb
            // ccc
        }
    }
    
    //public static void info(String data, String... args)      //语法：对
    //public static void info(String... args, String data)      //语法：错
    //public static void info(String... args1, String... args2)  //语法：错
}
```



**以数组形式作为可变参数传递**

```java
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // 如果传递一个数组，那么会自动展开数组中的数据作为可变参数传递
        info(new String[]{"aaa", "bbb"}); // 正确
    }

    public static void info(String... args) {
        System.out.println(Arrays.toString(args)); // 参数以数组形式返回：[aaa, bbb]
    }
}
```



注意点

```java
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[] arr = {"bbb", "ccc"};

        info("aaa", arr); // 正确
        // info("aaa", new String[]{"bbb", "ccc"}); // 正确
        // info("aaa", "bbb", "ccc", arr); // 错误
    }

    // 注意：可变参数必须放在最后
    public static void info(String data, String... args) {
        System.out.println(data); // aaa
        System.out.println(Arrays.toString(args)); // 参数以数组形式返回：[bbb, ccc]
    }
}
```



传多个数组

```java
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // 如果传多个数组
        info(new String[]{"aaa", "bbb"}, new String[]{"ccc", "ddd"});
    }

    public static void info(String[]... args) {
        System.out.println(Arrays.toString(args)); // 参数以数组形式返回：[aaa, bbb]
    }
}
```



## 包

1. 如果当前程序中要调用自己所在包下的其他程序，那么可以直接调用（同一个包下的类可以相互直接调用）
2. 如果当前程序中要调用其他包下的程序，必须在当前程序中导包才能访问：`import 包名.类名`
3. 如果当前程序中要调用 `java.lang` 包下的程序，不需要导包可以直接使用

4. 如果当前程序中要调用多个不同包下的程序，且这些程序名正好相同。此时默认只能导入一个程序，另一个程序必须带包名访问，举个栗子：

```java
// src/Main
import aaa.Book;
// import bbb.Book; 不能导入两个同名的类

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        // 如果有两个包重名，第一个包可以直接使用
        Book b1 = new Book();
        b1.func();

        // 第二个包必须加上包的全路径
        bbb.Book b2 = new bbb.Book();
        b2.func();
    }
}
```

```java
// src/aaa/Book
package aaa;

public class Book {
    public void func() {
        System.out.println("Book1");
    }
}
```

```java
// src/bbb/Book
package bbb;

public class Book {
    public void func() {
        System.out.println("Book2");
    }
}
```



## 异常

### try / catch

通过 `try/catch ` 来捕获异常信息

```java
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d = sdf.parse("2028-11-11 10:24");
            System.out.println(d);
            // Sat Nov 11 10:24:00 CST 2028
        } catch (ParseException e) {
            // 打印报错
            e.printStackTrace();

            // 打印报错为字符串格式
            System.out.println(e);
            // java.text.ParseException: Unparseable date: "2028-11-11 10:24"
        } finally {
            System.out.println("不管有没有异常都会执行finally");
        }
    }
}
```



### 捕获异常

手动造成一些异常然后演示捕获异常

```java
import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            info(2);
        } catch (IOException e1) {
            System.out.println("触发IO异常：" + e1);
        } catch (SQLException e2) {
            System.out.println("触发SQL异常：" + e2);
        } finally {
            System.out.println("不管有没有异常都会执行finally");
        }

        // try {
        //     info(2);
        // } catch (IOException | SQLException e) {
        //     System.out.println("异常：" + e);
        // } finally {
        //     System.out.println("不管有没有异常都会执行finally");
        // }
    }

    public static void info(int i) throws IOException, SQLException {
        // 如果i=1就抛出IOException异常，为2就抛出SQLException异常
        if (i == 1) {
            throw new IOException();
        }

        if (i == 2) {
            throw new SQLException();
        }

        System.out.println("info方法调用");
    }
}
```



### 自定义异常

自定义异常

```java
public class Main {
    public static void main(String[] args) {
        try {
            isAge(30);
            // isAge(121);
        } catch (Err e) {
            System.out.println(e); // Err: 年龄不合法
        }
    }

    // 判断年龄是否合法
    public static void isAge(int n) throws Err {
        if (n >= 1 && n <= 120) {
            System.out.println("年龄合法");
        } else {
            throw new Err("年龄不合法");
        }
    }
}
```

```java
public class Err extends Exception {
    public Err() {
    }

    public Err(String message) {
        super(message);
    }
}
```



## Stream

假如有一个 `List` 集合，元素有 `"张三丰","张无忌","周芷若","赵敏","张强"`，找出姓张，且是 `3` 个字的名字，存入到一个新集合中去。



**定义数据**

```java
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<User> list = new ArrayList<>();

        list.add(new User(1, "张三", 18, "上海"));
        list.add(new User(2, "王五", 16, "上海"));
        list.add(new User(3, "李四", 20, "上海"));
        list.add(new User(4, "张雷", 22, "北京"));
        list.add(new User(5, "张超", 15, "深圳"));
        list.add(new User(6, "李雷", 24, "北京"));
        list.add(new User(7, "王爷", 21, "上海"));
        list.add(new User(8, "张三丰", 18, "广州"));
        list.add(new User(9, "赵六", 16, "广州"));
        list.add(new User(10, "赵无极", 26, "深圳"));
    }
}
```



### 初体验

**传统写法：**

```java
list.forEach(s -> {
    if (s.name.startsWith("张") && s.name.length() == 3) {
         System.out.println(s.name);
         // 张三丰
         // 张无忌
    }
});
```



**Stream写法：**

```java
list.stream().filter(item -> item.name.startsWith("张") && item.name.length() == 3).forEach(item -> System.out.println(item.name));
// 张三丰
// 张无忌
```



### forEach

遍历所有的数据

```java
list.stream().forEach(item -> System.out.println(item.name));
// 张三
// 王五
// 李四
// ...
```



### sorted

根据年龄对数据进行排序

```java
// 根据年龄从小到大排序
list.stream().sorted((a, b) -> a.age - b.age).forEach(item -> System.out.println(item.name));
// 根据年龄从大到小排序
list.stream().sorted((a, b) -> b.age - a.age).forEach(item -> System.out.println(item.name));
```



### filter

返回过滤后的数据

```java
// 过滤出姓张且名为3个字的用户数据
list.stream().filter(item -> item.name.startsWith("张") && item.name.length() == 3).forEach(item -> System.out.println(item.name));
// 张三丰
// 张无忌
```



```java
// 查找id为5的数据
list.stream().filter(item -> item.id == 5).forEach(item -> System.out.println(item.name));
// 张超
```



### map

返回被处理后的数据

```java
// 给每个用户名称之前拼接数据并返回
List<String> s =  list.stream().map(item -> "姓名：" + item.name).toList();
System.out.println(s);
// [姓名：张三, 姓名：王五, 姓名：李四, ...]

// 给所有用户数据的年龄增加10岁并返回
List<Integer> n = list.stream().map(item -> item.age + 10).toList();
System.out.println(n);
// [28, 26, 30, 32, 25, 34, 31, 28, 26, 36]
```



### peek

对数据进行处理

```java
// 对所有用户的年龄增加10岁
List<User> data = list.stream().peek(item -> item.age += 10).toList();
for (User item : data) {
    System.out.printf("姓名：%s 年龄：%d\n", item.name, item.age);
    // 姓名：张三 年龄：28
    // 姓名：王五 年龄：26
    // 姓名：李四 年龄：30
    // ...
}
```

**疑惑点：** `map` 与 `peek` 的区别是什么？

**答：** `map` 用于对流中的元素进行转换，生成一个新的流，而`peek` 用于在流处理过程中查看元素，对其进行操作，但不改变元素的值。



### limit

获取指定个数的数据

```java
// 只获取前三个数据
list.stream().limit(3).forEach(item -> System.out.println(item.name));
```



### skip

从哪个位置开始获取数据

```java
// 从第八个获取到最后一个数据
list.stream().skip(8).forEach(item -> System.out.println(item.name));
// 赵六
// 张无忌
```



### count

统计数量

```java
// 统计姓张的用户数量
long n = list.stream().filter(item -> item.name.startsWith("张")).count();
System.out.printf("姓张的有：%d个人", n);
// 姓张的有：5个人
```



### max / min

返回最大值与最小值的数据

```java
// 求出最大的年龄
int max = list.stream().max((a, b) -> Integer.compare(a.age, b.age)).get().age;
System.out.printf("最大的年龄为：%d", max);
// 最大的年龄为：26
```



```java
// 找出年龄最大与最小的用户
User max = list.stream().max((a, b) -> Integer.compare(a.age, b.age)).get();
System.out.printf("年龄最大的用户是：%s 他的年龄为：%d", max.name, max.age);
// 年龄最大的用户是：张无忌 他的年龄为：26

User min = list.stream().min((a, b) -> Integer.compare(a.age, b.age)).get();
System.out.printf("年龄最大的用户是：%s 他的年龄为：%d", min.name, min.age);
// 年龄最小的用户是：张超 他的年龄为：15
```



**使用 Stream**

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, "张三丰", "张无忌", "周芷若", "赵敏", "张强");
        
        // 从第1个开始取2个数据
        list.stream().skip(0).limit(2).forEach(System.out::println);
        // 张三丰
        // 张无忌
        System.out.println("-----------------");

        // 从第2个开始取2个数据
        list.stream().skip(2).limit(2).forEach(System.out::println);
        // 周芷若
        // 赵敏
        System.out.println("-----------------");

        // 模拟分页
        int pageSize = 2; // 每页显示多少个
        for (int page = 1; page <= 3; page++) { // 第几页
            int startIndex = (page - 1) * pageSize; // 当前索引

            list.stream().skip(startIndex).limit(pageSize).forEach(s -> System.out.println(s));
            System.out.println("~~~~~~~~~~~~~~~~~");
        }

        

        List<String> arr = new ArrayList<>();
        Collections.addAll(arr, "aaa", "bbb", "ccc", "ddd");
        
        // 将stream转换为集合
        List<String> newArr = arr.stream().map(s -> s.toUpperCase()).toList();
        // List<String> newArr = arr.stream().map(s->s.toUpperCase()).collect(Collectors.toList());
        System.out.println(newArr); // [AAA, BBB, CCC, DDD]
        System.out.println("-----------------");
    }
}
```



### distinct

去重



## File

### exists

判断指定的目录或文件是否存在，存在就返回 `true` 反之 `false`

```java
import java.io.File;

public class Main {
    public static void main(String[] args) {
        File f1 = new File("src");
        File f2 = new File("src/1.txt");

        // 既可以判断目录，也可以判断文件是否存在
        System.out.println(f1.exists()); // true
        System.out.println(f2.exists()); // true
    }
}
```



### isFile

判断指定的文件是否存在，存在就返回 `true` 反之 `false`

```java
import java.io.File;

public class Main {
    public static void main(String[] args) {
        File f1 = new File("src");
        File f2 = new File("src/1.txt");

        // 不能判断目录，只能判断文件是否存在
        System.out.println(f1.isFile()); // false
        System.out.println(f2.isFile()); // true
    }
}
```



### isDirectory

判断是否是文件夹，是就返回 `true` 反之 `false`

```java
import java.io.File;

public class Main {
    public static void main(String[] args) {
        File f1 = new File("src");
        File f2 = new File("src/1.txt");

        // 是文件夹
        System.out.println(f1.isDirectory()); // true

        // 不是文件夹
        System.out.println(f2.isDirectory()); // false
    }
}
```



### getName

获取文件的名称

```java
import java.io.File;

public class Main {
    public static void main(String[] args) {
        File f = new File("src/1.txt");

        System.out.println(f.getName()); // 1.txt
    }
}
```



### length

获取文件的大小，返回字节个数

```java
import java.io.File;

public class Main {
    public static void main(String[] args) {
        File f = new File("src/1.txt");

        System.out.println(f.length()); // 12
    }
}
```



### lastModified

获取文件上一次被修改的时间

```java
import java.io.File;

public class Main {
    public static void main(String[] args) {
        File f = new File("src/1.txt");

        System.out.println(f.lastModified());
        // 1696926150928
    }
}
```



### getPath

获取创建文件对象时的路径 `src/1.txt`

```java
import java.io.File;

public class Main {
    public static void main(String[] args) {
        File f = new File("src/1.txt");

        // 字符串
        System.out.println(f.getPath()); // src\1.txt

        // File对象
        System.out.println(f); // src\1.txt
    }
}
```



### getAbsolutePath

获取文件的绝对路径

```java
import java.io.File;

public class Main {
    public static void main(String[] args) {
        File f = new File("src/1.txt");

        System.out.println(f.getAbsolutePath());
        // C:\Users\33111\IdeaProjects\liuyuyang\src\1.txt
    }
}
```



### getParentFile

获取指定文件的所有父级目录

```java
import java.io.File;

public class Main {
    public static void main(String[] args) {
        File file = new File("a/b/c/d/e/f/g.txt");

        System.out.println(file.getParentFile());
        // a\b\c\d\e\f
    }
}
```



### createNewFile

如果创建成功返回 `true`，反之 `false`

```java
import java.io.File;

public class Main {
    public static void main(String[] args) {
        File f = new File("src/2.txt");
        System.out.println(f.createNewFile());
    }
}
```

**注意：** `createNewFile` 只能创建文件，不能创建目录



### mkdir

与 `createNewFile` 使用相似，不同的是该方法只能创建目录

```java
import java.io.File;

public class Main {
    public static void main(String[] args)  {
        // 创建单个目录
        File f1 = new File("src/aaa");
        System.out.println(f1.mkdirs());

        // 创建多级目录
        File f2 = new File("src/aaa/bbb");
        System.out.println(f2.mkdirs());
    }
}
```



### delete

如果删除成功返回 `true`，反之 `false`

```java
import java.io.File;

public class Main {
    public static void main(String[] args) {
        File f = new File("src/1.txt");
        System.out.println(f.delete());
    }
}
```



### list

通过该方法可以将指定目录下的所有目录以及文件以字符串数组形式获取

```java
import java.io.File;

public class Main {
    public static void main(String[] args) {
        File f = new File("src");

        String[] files = f.list();

        for (String file : files) {
            System.out.println(file);
            // aaa
            // Err.java
            // Main.java
        }
    }
}
```



### listFiles

通过该方法可以将指定目录下的所有目录以及文件以 `File` 对象数组形式获取

```java
import java.io.File;

public class Main {
    public static void main(String[] args) {
        File f = new File("src");

        File[] files = f.listFiles();

        for (File item : files) {
            System.out.println(item.getName() + " ~ " + item);
            // a.txt ~ src\a.txt
            // abc ~ src\abc
            // Anno.java ~ src\Anno.java
            // Main.java ~ src\Main.java
            // User.java ~ src\User.java
        }
    }
}
```

**使用 listFiles 方法时的注意事项：**

1. 当主调是文件，或者路径不存在时，返回 `null`
2. 当主调是空文件夹时，返回一个长度为 `0` 的数组
3. 当主调是一个有内容的文件夹时，将里面所有一级文件和文件夹的路径放在 `File` 数组中返回
4. 当主调是一个文件夹，且里面有隐藏文件时，将里面所有文件和文件夹的路径放在 `File` 数组中返回，包含隐藏文件
5. 当主调是一个文件夹，但是没有权限访问该文件夹时，返回 `null`



### 练习

**练习一：** 过滤出指定目录中的所有以 `.java` 作为后缀的文件

```java
import java.io.File;
import java.io.FileFilter;

public class Main {
    public static void main(String[] args) {
        File f = new File("src");

        // 过滤文件
        File[] files = f.listFiles(new FileFilter() {
            @Override
            public boolean accept(File data) {
                // 只要返回值为true就返回哪个数据
                // 返回以.java后缀的所有文件
                return data.toString().endsWith(".java");
            }
        });

        for (File item : files) {
            System.out.println(item);
        }
    }
}
```



**练习二：** 遍历指定文件目录中所有的目录，不含文件

```java
import java.io.File;

public class Main {
    public static void main(String[] args) {
        File dir = new File("src/abc");
        System.out.println(dir); // src/abc

        // 遍历abc文件目录下所有的目录
        listAllFiles(dir, "");
    }

    public static void listAllFiles(File dir, String tab) {
        File[] files = dir.listFiles();

        for (File item : files) {
            // 只打印文件目录
            if (item.isDirectory()) {
                System.out.println(tab + item.getName());

                // 通过递归实现无限遍历，直到遍历完毕为止
                listAllFiles(item, tab + "\t");
                // a
                //    aa
                //    bb
                // b
                //    bb
                // c
            }
        }
    }
}
```



**练习三：** 查询指定目录中是否存在 `.avi` 格式的文件，如果有就返回 `true ` 反之 `false`

```java
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("src");
        System.out.println(haveAVI(file));
    }

    // 查询指定目录中是否存在.avi格式的文件，如果有就返回true 反之false
    public static boolean haveAVI(File file) {
        File[] list = file.listFiles();

        for (File item : list) {
            if (file.isFile() && item.getName().endsWith(".avi")) {
                return true;
            }
        }

        return false;
    }
}
```



**练习四：** 使用代码创建目录 `a/b/c/d/e/f/g.txt`

```java
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("a/b/c/d/e/f/g.txt");

        // 判断父级目录是否存在
        if(!file.getParentFile().exists()){
            // 如果父级目录不存在就创建
            file.getParentFile().mkdirs();
        }

        // 创建文件g.txt
        file.createNewFile();
    }
}
```



**疑惑点：**  `list` 方法与 `listFiles` 方法都可以获取到所有的目录、文件列表，那么他们的区别是什么？

**答：** `list` 获取的是文件的名称，而 `listFiles` 获取的是文件对象



## IO

### 字节流

#### FileOutputStream

操作本地文件的字节输出流，可以吧程序中的数据写到本地文件中

```java
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("src/a.txt");
        // FileOutputStream fos = new FileOutputStream(new File("src/a.txt"));
        
        // 将数据97写入到a.txt：97对应的Asllc码值为a，所以在a.txt文件中的内容为a
        fos.write(97);
        // 然后又将b写入到文件中，此时内容为：ab
        fos.write(98);
        
        // 释放资源
        fos.close();
    }
}
```



批量将数据写入到 `a.txt` 文件中：`abcde`

```java
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("src/a.txt");

        // 批量将数据写入到a.txt文件中：abcde
        byte[] list = {97, 98, 99, 100, 101};
        fos.write(list);

        // 释放资源
        fos.close();
    }
}
```



将数据转换为字节并写入数据

```java
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("src/a.txt");

        // 将数据转换为字节
        byte[] bytes = "你好呀".getBytes();
        System.out.println(Arrays.toString(bytes));
        // [-28, -67, -96, -27, -91, -67, -27, -111, -128]

        // 一键写入数据
        fos.write(bytes);

        fos.close();
    }
}
```



将该数组从第二个开始往后取 `3` 个数据批量写入到 `a.txt` 文件中：`cde`

```java
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("src/a.txt");

        // 将该数组从第二个开始往后取3个数据批量写入到a.txt文件中：cde
        byte[] list = {97, 98, 99, 100, 101};
        fos.write(list, 2, 3);

        // 释放资源
        fos.close();
    }
}
```



**使用细节**

① 创建字节输出流对象

1. 参数可以是字符串路径，也可以是File对象

2. 如果文件不存在 会创建一个新的文件，但必须保证父级路径是存在的

3. 如果文件已经存在，则会清空文件



② 写入数据

`write` 方法的参数为整数类型，但实际上写到本地文件中的是整数在 `ASCll` 上对应的字符



③ 释放资源

每次使用完留之后都要释放资源

`write` 方法的参数为整数类型，但实际上写到本地文件中的是整数在 `ASCll` 上对应的字符



**换行 & 续写**

```java
import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        // 如果第二个参数为true则表示支持续写，默认情况下为false表示不续写，每次写入的内容会覆盖之前的内容
        FileOutputStream fos = new FileOutputStream("src/a.txt", true);

        // 数据内容
        String content1 = "Hello";
        // 将内容转换为byte格式
        byte[] content1Bytes = content1.getBytes();
        System.out.println(Arrays.toString(content1Bytes)); // [72, 101, 108, 108, 111]

        // 将转换后的byte对应的字符写入到文件内容中
        fos.write(content1Bytes);

        // 换行：为了兼容性，我们通常使用"\r\n"来表示换行
        String wrap = "\r\n";
        byte[] wrapBytes = wrap.getBytes();
        fos.write(wrapBytes);

        String content2 = "World";
        byte[] content2Bytes = content2.getBytes();
        fos.write(content2Bytes);

        // 释放资源
        fos.close();
        
        // 此时a.txt文件的内容为：
        // Hello
        // World
    }
}
```



#### FileInputStream

操作本地文件的字节输入流，将本地文件中的数据读取到程序中

```java
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        FileInputStream fos = new FileInputStream("src/a.txt");

        // 每次调用read方法都会获取a.txt文件中的一个数据
        System.out.println(fos.read()); // 97 -> a
        System.out.println(fos.read()); // 98 -> b
        System.out.println(fos.read()); // 99 -> c
        System.out.println(fos.read()); // 100 -> d
        System.out.println(fos.read()); // 101 -> e

        // 如果没有数据了就返回-1
        System.out.println(fos.read()); // -1

        // 释放资源
        fos.close();
    }
}
```



**使用细节**

① 创建字节输入流对象

如果文件不存在就直接报错



② 写入数据

1. 一次性只能读一个字节，读出来的是数据在 `ASCll` 上对应的数字
2. 读取到文件末尾后，`read` 方法就会返回 `-1`



③ 释放资源

每次使用完留之后都要释放资源



**通过循环批量获取数据**

```java
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        FileInputStream fos = new FileInputStream("src/a.txt");

        int b;

        // 循环读取数据
        while ((b = fos.read()) != -1) {
            System.out.printf("%d -> %c | ", b, (char) b);
            // 97 -> a | 98 -> b | 99 -> c | 100 -> d | 101 -> e | 
        }

        // 释放资源
        fos.close();
    }
}
```



**一次性读取多个数据**

```java
import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("src/a.txt");

        byte[] bytes = new byte[5];

        int len;
        while ((len = fis.read(bytes)) != -1) {
            System.out.print(len + " - "); // 每次读取的个数
            System.out.println(new String(bytes, 0, len)); // 每次读取的数据
            // 5 - abcde
            // 2 - fg

            // System.out.write(bytes, 0, len);
        }
        
        fos.close();
    }
}
```



#### 拷贝文件

将 `mac.jpg` 这张图片拷贝一张

```java
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("mac.jpg");
        FileOutputStream fos = new FileOutputStream("macCory.jpg");

        // 记录开始时间
        long start = System.currentTimeMillis();

        System.out.println("拷贝中 请稍后~");

        int len;
        while ((len = fis.read()) != -1) {
            fos.write(len);
        }

        // 记录结束时间
        long end = System.currentTimeMillis();

        System.out.printf("拷贝成功 总用时：%d毫秒", end - start);
        // 拷贝成功 总用时：7407毫秒

        fis.close();
        fos.close();
    }
}
```



#### 优化拷贝时间

上面拷贝文件因为一次只能拷贝一个数据，所以导致非常缓慢，所以我们可以这样做，使每次可以拷贝多个数据从而优化拷贝文件的时间

```java
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("src/a.txt");

        byte[] bytes = new byte[2];

        // bytes数组中存储的个数
        // System.out.println(fis.read(bytes)); // 2

        fis.read(bytes);
        System.out.println(new String(bytes)); // ab

        fis.read(bytes);
        System.out.println(new String(bytes)); // cd

        fis.read(bytes);
        // 因为最后一个数据是e，e将cd的c覆盖之后就没有数据了
        // 而d并没有被覆盖。所以此时就是：ed
        System.out.println(new String(bytes)); // ed

        fis.read(bytes);
        System.out.println(new String(bytes)); // ed
        
        fos.close();
    }
}
```



上面代码存在一些问题，可以发现有一个多余的数据。所以这并不是最佳的解决方法

我们可以这么做来解决上面的问题

```java
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("src/a.txt");

        byte[] bytes = new byte[2];

        // bytes数组中存储的个数
        System.out.println(fis.read(bytes)); // 2

        // 读取2个数据
        int l1 = fis.read(bytes);
        System.out.println(l1); // 2
        System.out.println(new String(bytes, 0, l1)); // ab

        // 读取2个数据
        int l2 = fis.read(bytes);
        System.out.println(l2); // 2
        System.out.println(new String(bytes, 0, l2)); // cd

        // 读取1个数据
        int l3 = fis.read(bytes);
        System.out.println(l3); // 1
        System.out.println(new String(bytes, 0, l3)); // e
        
        fos.close();
    }
}
```



循环读取数据

```java
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("src/a.txt");

        byte[] bytes = new byte[2];

        int len;

        while ((len = fis.read(bytes)) != -1) {
            System.out.print(new String(bytes, 0, len));
        }
        
        fos.close();
    }
}
```



**下面是文件拷贝缓慢的解决方案：** 默认一次只能读取一个数据，我们可以设置为一次读取 `5` 个数据，这样拷贝的时间就快了好几倍

```java
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("mac.jpg");
        FileOutputStream fos = new FileOutputStream("macCory.jpg");

        long start = System.currentTimeMillis();
        
        System.out.println("拷贝中 请稍后~");

        int len;
        byte[] bytes = new byte[1024 * 1024 * 5];

        while ((len = fis.read(bytes)) != -1) {
            fos.write(bytes, 0, len);
        }

        long end = System.currentTimeMillis();

        System.out.printf("拷贝成功 总用时：%d毫秒", end - start);
		// 拷贝成功 总用时：4毫秒
        
        fis.close();
        fos.close();
    }
}
```

经过这样的优化后，文件拷贝的速度快了几千倍



**疑惑点：** `new byte[]` 括号中的值越大，读取就越快。那为什么不能写成 `99999999999999` 呢，岂步更快？

```java
byte[] bytes = new byte[1024 * 1024 * 5];
```

**答：** 将这个值设置得过大可能会浪费内存资源。原因是，在读取文件时，内存中只能容纳有限数量的数据。如果一次性读取的数据超过内存的限制，就会导致内存溢出的情况发生，程序可能会因此崩溃。

所以我们一般根据文件的大小来决定，比如一个 `10M` 的视频，那么就写 `1024 * 1024 * 10` 相当于一次读取 `10M` 的数据，这样做最合适



#### 编码 & 解码

默认为操作系统编码，而国内 `window` 系统编码为 `utf-8`  

所以编码与解码都为 `utf-8` 格式，也可以指定为其他格式

```java
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println("------编码------");
        
        String s1 = "爱你哟";
        // 不传参就是系统默认的编码格式，国内window操作系统默认编码为UTF-8
        byte[] list1 = s1.getBytes();
        System.out.println(Arrays.toString(list1));
        // [-25, -120, -79, -28, -67, -96, -27, -109, -97]
        // 爱：-25, -120, -79
        // 你：-28, -67, -96
        // 呦：-27, -109, -97

        String s2 = "爱你哟";
        // 指定为GBK编码
        byte[] list2 = s2.getBytes("GBK");
        System.out.println(Arrays.toString(list2));
        // [-80, -82, -60, -29, -45, -76]
        // 爱：-80, -82
        // 你：-60, -29
        // 呦：-45, -76



        System.out.println("------解码------");

        // 默认是UTF-8格式解码
        System.out.println(new String(list1)); // 爱你哟

        // 不传值相当于使用默认UTF-8，而这个是GBK数据，所以解码时候会乱码
        System.out.println(new String(list2)); // ����Ӵ

        // 指定为GBK格式解码
        System.out.println(new String(list2, "GBK")); // 爱你哟
        
        fos.close();
    }
}
```

根据上述代码可以看出，`UTF-8` 编码一次性读取 3 个字节，而 `GBK` 一次读取 2 个字节



### 字符流

#### FineReader

字符输入流用来将文件中的字符数据读取到程序中来，他比 `FileInputStream` 更适合读取中文数据

```java
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("src/a.txt");

        int ch;

        while ((ch = fr.read()) != -1) {
            System.out.printf("%d %s | ", ch, (char) ch);
            // 20320 你 | 22909 好 | 32   | 74 J | 97 a | 118 v | 97 a |
        }

        fr.close();
    }
}
```



**疑惑点：** `FileInputStream` 与 `FineReader` 都可以实现读取数据，那么他们的区别是什么？

**答：** `FileReader` 与 `FileInputStream` 都可以用来读取文件中的中文数据。然而，它们确实有一些区别。

`FileReader` 是用来读取字符流的，它是 `InputStreamReader` 类的子类，并且使用默认的字符编码。它将文件中的字节流解码成字符流，因此适用于读取文本文件。对于读取文本文件中的中文数据，使用 `FileReader` 是一个比较方便的选择。

`FileInputStream` 是用来读取字节流的。它可以读取任何类型的文件，包括文本文件和二进制文件。读取文本文件时，需要在读取后进行字符编码处理才能正确地解析中文数据。



**一次性读取多个数据**

```java
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("src/a.txt");

        char[] chars = new char[2];
        int len;

        while ((len = fr.read(chars)) != -1) {
            // 把数组中的数据变成字符串再打印
            System.out.print(new String(chars, 0, len));
            // 你好 Java
        }

        fr.close();
    }
}
```



## 反射

```java
public class User {
    public String name = "zs";
    private int age = 18;
    
    public User(){
        System.out.println("User~");
    }

    public User(int i){
        System.out.println("User" + i);
    }

    public String info() {
        System.out.println("info");
        return "aaa";
    }

    private int info(int i, String s) {
        System.out.println("info" + i + s);
        return 123;
    }
}
```



**通过反射调用类的构造方法**

```java
import java.lang.reflect.Constructor;

public class Main {
    public static void main(String[] args) throws Exception {
        // 获取class
        Class clazz = Class.forName("User");

        // 获取构造方法
        Constructor cons1 = clazz.getConstructor();
        Constructor cons2 = clazz.getConstructor(int.class);

        // 调用无参构造
        cons1.newInstance(); // User~
        // 调用有参构造方法
        cons2.newInstance(123); // User123
    }
}
```



**通过反射获取、修改类的属性**

```java
import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) throws Exception {
        // 获取class
        Class clazz = Class.forName("User");

        // 获取实例
        Object obj = clazz.newInstance();

        // 获取属性
        // Field nameAttribute = clazz.getField("name");
        Field nameAttribute = clazz.getDeclaredField("name");
        Field ageAttribute = clazz.getDeclaredField("age");

        // 访问公有属性
        System.out.println(nameAttribute.get(obj)); // zs

        // 访问私有属性
        ageAttribute.setAccessible(true); // 设置为可访问的
        System.out.println(ageAttribute.get(obj)); // 18

        // 修改属性
        nameAttribute.set(obj, "ls");
        ageAttribute.set(obj, 20);
        System.out.println(nameAttribute.get(obj)); // ls
        System.out.println(ageAttribute.get(obj)); // 20
    }
}
```

**注意：** `getField` 只能获取 `public` 修饰的属性，不能获取私有属性。而 `getDeclaredField` 都可以获取



**通过反射调用类的公共属性和方法**

```java
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws Exception {
        // 获取class
        Class clazz = Class.forName("User");

        // 获取实例
        Object obj = clazz.newInstance();

        // 获得User类的info方法
        Method infoMethod = clazz.getDeclaredMethod("info");

        // 通过invoke调用类的info方法
        Object result = infoMethod.invoke(obj); // info
        // 打印该方法的返回值
        System.out.println(result); // aaa
    }
}
```



**通过反射调用类的私有属性和方法**

```java
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws Exception {
        // 获取class
        Class clazz = Class.forName("User");

        // 获取实例
        Object obj = clazz.newInstance();

        // 获得方法User类的info私有方法
        Method infoMethod = clazz.getDeclaredMethod("info", int.class, String.class);

        // 将私有方法设置为可访问的
        infoMethod.setAccessible(true);

        // 通过invoke调用类的info方法
        Object result = infoMethod.invoke(obj, 100, "_sss"); // 100_sss
        // 打印该方法的返回值
        System.out.println(result); // 123
    }
}
```



## 注解

### 基本使用

```java
public @interface Anno {
    public String value() default "";
    public String name() default "";

    public int age() default 0;
}
```

```java
@Anno(name = "ls", age = 20)
public class User {
    // @Anno()
    // @Anno("")
    @Anno(name = "ww")
    public String name = "zs";

    @Anno(age = 20)
    public void info() {
        System.out.println("info");
    }
}
```



### 元注解

#### @Retention

自定义声明周期、保留时间

| 属性                    | 含义                                                         |
| ----------------------- | ------------------------------------------------------------ |
| RetentionPolicy.SOURCE  | 源码，自定义注解只能在源码中使用，字节码中没有。提供给编译器使用 |
| RetentionPolicy.CLASS   | 字节码，自定义注解在源码、字节码中使用。运行后没有。提供运行器使用 |
| RetentionPolicy.RUNTIME | 运行时，自定义注解在源码、字节码、运行时都可以使用。开发中常见 |



**举个栗子：** 获取某个方法被注解的数据

```java
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Anno {
    public String name() default "";

    public int age() default 0;
}
```

```java
public class User {
    @Anno(name = "zs", age = 20)
    public void info() {
        System.out.println("info");
    }

    public void show() {
        System.out.println("show");
    }
}
```

```java
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws Exception {
        Class clazz = Class.forName("User");

        Method infoMethod = clazz.getMethod("info");

        // 获取info方法上的Anno注解对象
        Anno anno = (Anno) infoMethod.getAnnotation(Anno.class);
        System.out.println(anno); // @Anno(name="zs", age=20)

        System.out.println(anno.name()); // zs
        System.out.println(anno.age()); // 20
    }
}
```



#### @Target

用于确定 `自定义注解` 使用位置（类、属性、构造、方法等）

| 属性            | 含义                               |
| --------------- | ---------------------------------- |
| ANNOTATION_TYPE | 可以用于注解类型的声明             |
| CONSTRUCTOR     | 可以用于构造方法的声明             |
| FIELD           | 可以用于字段（成员变量）的声明     |
| LOCAL_VARIABLE  | 可以用于局部变量的声明             |
| METHOD          | 可以用于方法的声明                 |
| MODULE          | 可以用于模块的声明（Java 9+）      |
| PACKAGE         | 可以用于包的声明                   |
| PARAMETER       | 可以用于方法参数的声明             |
| TYPE            | 可以用于类、接口、枚举等类型的声明 |

如果不写 `@Taget` 则表示在任何位置都可以使用该注解



**举个栗子：** 执行某个类中所有被注解的方法

```java
// 表示只能被方法使用
@Target({ElementType.METHOD})
// 表示可以通过反射来获取被注解的属性或方法
@Retention(RetentionPolicy.RUNTIME) 
public @interface Anno {

}
```

```java
public class User {
    @Anno
    public void info() {
        System.out.println("info");
    }

    public void show() {
        System.out.println("show");
    }
}
```

```java
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws Exception {
        // 获取User类
        Class clazz = Class.forName("User");

        // 获取类实例
        Object obj = clazz.newInstance();

        // 获取指定类中所有的方法
        Method[] allMethods = clazz.getMethods();

        for (Method method : allMethods) {
            // 判断当前方法是否被Anno注解
            boolean r = method.isAnnotationPresent(Anno.class);

            // 如果被注解就调用
            if (r) method.invoke(obj);
        }
    }
}
```
