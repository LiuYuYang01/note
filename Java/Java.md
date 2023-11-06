# Java基础

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
