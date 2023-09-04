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



### chatAt

获取指定位置的字符串

```java
String s = new String("Hello World!");

System.out.println(s.charAt(0)); // H
System.out.println(s.charAt(s.length() - 1)); // !
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



## 循环

### for

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



## 面向对象

### 定义 / 使用类

```java
// src/Phone.java
public class Phone {
    // 类属性
    String brand = "苹果";
    int price = 7999;

    // 类方法
    public void call() {
        System.out.println("打电话");
    }

    public void sendMessage() {
        System.out.println("发短信");
    }
}
```



两个类在同一个包中，那么不需要引入彼此就可以直接使用

```java
// src/Main.java
public class Main {
    public static void main(String[] args) {
        // 可以直接使用同一个包中的类
        Phone p = new Phone();

        System.out.println(p.brand); // 苹果
    }
}
```



### 成员变量 / 局部变量

* 类中位置不同：成员变量（类中方法外）局部变量（方法内部或方法声明上）
* 内存中位置不同：成员变量（堆内存）局部变量（栈内存）
* 生命周期不同：成员变量（随着对象的存在而存在，随着对象的消失而消失）局部变量（随着方法的调用而存在，醉着方法的调用完毕而消失）
* 初始化值不同：成员变量（有默认初始化值）局部变量（没有默认初始化值，必须先定义，赋值才能使用）

```java
public class Main {
    // 这里的是成员变量，也称之为全局变量，定义后不赋值就输出不会报错，因为有默认值
    // 比如String默认值为null，int为0，boolean为false等等...
    String name;
    int age;

    public static void main(String[] args) {
        // 这里的是局部变量，定义后不赋值就输出会报错
        int n;
        System.out.println(n);
    }
}
```



### private

被设置了 `private` 的属性表示私有的，只能在当前类中使用，其他类中不能 **直接** 使用或赋值

```java
public class Main {
    public static void main(String[] args) {
        Phone p = new Phone();
        System.out.println(p.brand); // 苹果
        System.out.println(p.price); // 报错，原因私有的属性不能被使用
    }
}
```

```java
public class Phone {
    String brand = "苹果";

    // 将price属性设置为私有的，只能在当前类中使用
    private int price = 7999;

    public void call() {
        System.out.println("打电话");
    }

    public void sendMessage() {
        System.out.println("发短信");
    }
}
```



如果想要改变私有属性的值，可以定义一个方法，通过调用该方法完成设置、获取操作

```java
public class Main {
    public static void main(String[] args) {
        Phone p = new Phone();

        // 调用该方法完成设置操作
        p.setPrice(8999);
    }
}
```

```java
public class Phone {
    String brand = "苹果";
    private int price = 7999;

    public void setPrice(int price) {
        // 通过this可以指向类里面的属性
        this.price = price;
    }
}
```



### this

`this` 修饰的变量用于指向成员变量，其主要作用是（区分局部变量和成员变量的重名问题）

* 方法的形参如果与成员变量同名，不带 `this` 修饰的变量指的是形参，而不是成员变量
* 方法的形参没有与成员变量同名，不带 `this` 修饰的变量指的是成员变量

```java
public class Main {
    public static void main(String[] args) {
        Phone p = new Phone();

        p.func("python");
    }
}
```

```java
public class Phone {
    String name = "java";

    public void func(String name) {
        // 当与方法形参重名时不加this就是形参name
        System.out.println(name); // python

         // 加this表示类属性中的name
        System.out.println(this.name); // java
    }
}
```

当没有形参情况下，不加 `this` 也可以访问到类属性 `name`



### 构造方法

类名 `==` 构造方法名

```java
public class Main {
    public static void main(String[] args) {
        Phone p = new Phone("Java", (byte) 20);
    }
}
```

```java
public class Phone {
    String name;
    byte age;

    // 构造方法，等同于js中的constructor
    public Phone(String name, byte age) {
        System.out.format("%s,%d", name, age);
    }
}
```



**构造方法的注意事项**

1. 构造方法的创建

   如果没有定义构造方法，系统将给出一个默认的无参数构造方法
   如果定义了构造方法，系统将不再提供默认的构造方法

2. 构造方法的重载

   如果自定义了带参构造方法，还要使用无参数构造方法，就必须再写一个无参数构造方法

3. 推荐的使用方式

   无论是否使用，都手工书写无参数构造方法

3. 重要功能！

   可以使用带参构造，为成员变量进行初始化

```java
class Student {
    private String name;
    private int age;

    public Student() {}

    public Student(String name) {
        this.name = name;
    }

    public Student(int age) {
        this.age = age;
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```



### 标准类

① 类名需要见名知意

② 成员变量使用private修饰

③ 提供至少两个构造方法 （带参与不带参）

④ 提供每一个成员变量对应的 `setXxx()` / `getXxx()` 方法

⑤ 如果还有其他行为，也需要写上

```java
public class Main {
    public static void main(String[] args) {
        // 带参数
        // Phone p = new Phone("Java", (byte) 20);
        
        // 不带参数
        Phone p = new Phone();

        p.setName("Java");
        p.setAge((byte) 20);
        p.getName(); // Java
        p.getAge(); // 20
    }
}
```

```java
public class Phone {
    private String name;
    private byte age;

    // 有参数
    public Phone(String name, byte age) {
        this.name = name;
        this.age = age;
        System.out.println(this.name + " " + this.age);
    }

    // 无参数
    public Phone() {}

    // 通过set / get来设置或访问类属性的值
    public void setName(String name) {
        this.name = name;
    }
    public void getName() {
        System.out.println(this.name);
    }

    public void setAge(byte age) {
        this.age = age;
    }
    public void getAge() {
        System.out.println(this.age);
    }
}
```



### static

#### 静态属性

当类的属性或方法被 `static` 修饰后，说明这个成员变量是属于类的，它称为类变量或静态成员变量。在使用时通过类名.属性名访问。因为类只有一个，所以静态成员变量在内存中也存在一份，所有的对象都可以使用这个变量

```java
public class Main {
    public static void main(String[] args) {
        User u = new User();

        // 实例也可以访问静态成员，但是不推荐
        System.out.println(u.data);
        
        System.out.println(User.data); // Hello World!
    }
}
```

```java
public class User {
    static String data = "Hello World!";
}
```

尽管我们使用实例对象来访问静态方法和属性，但实际上编译器会将它们转换为对应的类名进行访问。因此，实例对象和类名都可以用来访问静态方法和属性。

需要注意的是，尽管可以使用实例对象来访问静态方法和属性，**但这种做法并不推荐**。因为静态方法和属性属于类本身，它们不依赖于实例对象。更好的做法是直接使用类名来访问静态方法和属性，以提高代码的可读性和易于理解



**实例属性**

如果没有 `static` 修饰的成员变量表示实例属性，它是属于每个对象的，必须创建类的对象才可以访问



#### 静态方法

与静态属性同理

```java
public class Main {
    public static void main(String[] args) {
        User u = new User();

        User.func();
        u.func();
    }
}
```

```java
public class User {
    public static void func(){
        System.out.println("Hello World!");
    }
}
```



需要注意的是静态成员能够被实例访问也可以通过类名访问，而实例成员只能实例访问，不能通过类名访问

```java
public class User {
    String data = "Hello World!";

    public static void func() {
        // 被static修饰的称为静态方法，他不能访问实例方法、属性
        System.out.println(this.data); // 报错
    }
}
```



### 继承

通过 `extends` 关键字，可以声明一个子类继承另外一个父类，定义格式如下：

```java
class 父类 {
	...
}

class 子类 extends 父类 {
	...
}
```

**注意：** `Java` 是单继承的，一个类只能继承一个直接父类。如果想要实现多继承可以B类继承A类，C类继承B类以此类推......



**应用场景**

老师类，学生类，还有班主任类，实际上都是属于人类的，我们可以定义一个人类，把他们相同的属性和行为都定义在人类中，然后继承人类即可，子类特有的属性和行为就定义在子类中

![](image/360截图20181202211331250.jpg)

**代码示例**

```java
// Main.java
public class Main {
    public static void main(String[] args) {
        // 教师类
        Teacher teacher = new Teacher();
        teacher.setName("播仔");
        teacher.setAge((byte) 31);
        teacher.setSalary(1000.99);
        System.out.format("%s %d %f ", teacher.getName(), teacher.getAge(), teacher.getSalary());
        teacher.teach();

        // 班主任类
        BanZhuRen banzhuren = new BanZhuRen();
        banzhuren.setName("灵涛");
        banzhuren.setAge((byte) 28);
        banzhuren.setSalary(1000.99);
        System.out.format("%s %d %f ", banzhuren.getName(), banzhuren.getAge(), banzhuren.getSalary());
        banzhuren.admin();

        // 学生类
        Student student = new Student();
        student.setName("播仔");
        student.setAge((byte) 31);
        System.out.format("%s %d ", student.getName(), student.getAge());
        student.behavior();
        // student.setSalary(1000.99); // student类没有薪水属性，报错！
    }
}
```



`Human` 公共类 将可复用的代码写在这个类中，让其他的类来继承他

```java
// Human.java
public class Human {
    private String name;
    private byte age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }
}
```



`Student` 类需要用到 `Human` 中的属性，可以继承它，减少代码冗余，提高代码规范性。不仅如此还可以在类的原有基础上继续扩展，比如扩展一个方法 `behavior` 

```java
// Student.java
public class Student extends Human {
    public void behavior(){
        System.out.println("好好学习，天天向上!");
    }
}
```



`Teacher` 如果没有我们想要的属性，也可以自己扩展

```java
// Teacher.java
public class Teacher extends Human {
    // 扩展一个薪资属性
    private double salary;

    public void teach(){
        System.out.println("老师在认真教技术！");
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
```


```java
// BanZhuRen.java
public class BanZhuRen extends Human {
    private double salary ;

    public void admin(){
        System.out.println("班主任强调纪律问题！");
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
```



#### super

子父类中出现了同名的成员变量时，在子类中需要访问父类中 **非私有成员变量** 时，需要使用 `super` 关键字，修饰父类成员变量，类似于之前学过的 `this` 

不同的是 `super` 代表父类对象的引用，而 `this` 代表当前对象的引用

```java
public class Main {
    public static void main(String[] args) {
        Student s = new Student();
    }
}
```

```java
public class Student extends Person {
    String name = "子类属性";

    public Student(){
        // 访问当前子类属性
        System.out.println(this.name); // 子类属性

        // 访问父类属性 / 方法
        System.out.println(super.name); // 父类属性
        super.func(); // 父类方法
    }
}
```

```java
public class Person {
    String name = "父类属性";

    public void func() {
        System.out.println("父类方法");
    }
}
```

**注意：** `Person` 类中的成员变量是非私有的，子类中可以直接访问。若 `Person` 类中的成员变量私有了，子类是不能直接访问的。通常编码时，我们遵循封装的原则，使用 `private` 修饰成员变量，那么如何访问父类的私有成员变量呢？对！可以在父类中提供公共的 `getXxx` 方法和 `setXxx` 方法。



**类在构造函数中会自动调用 super 从而导致父类的构造函数触发**

```java
public class Main {
    public static void main(String[] args) {
        Student s = new Student();
        // 父类被调用了
        // 子类被调用了
    }
}
```

```java
public class Person {
    public Person(){
        System.out.println("父类被调用了");
    }
}
```

```java
public class Student extends Person {
    public Student(){
        System.out.println("子类被调用了");
    }
}
```



#### 访问特点

继承中成员变量与方法的访问特点

1. 如果当前类中属性与变量同名，那么优先输出变量

2. 如果子类与父类属性重名，那么输出子类的重名属性

3. 如果想要使用父类的属性，可以通过 `super`

```java
// Main.java
public class Main {
    public static void main(String[] args) {
        Student s = new Student();
        s.func();
    }
}
```

```java
// Person.java
public class Person {
    int n = 100;

    public void func(){}
}
```

```java
// Student.java
public class Student extends Person {
    int n = 200;

    public void func() {
        int n = 300;

        System.out.println(n); // 300
        System.out.println(this.n); // 200
        System.out.println(super.n); // 100
    }
}
```

**注意：** 通过 `super` 不能访问父类私有的属性



#### 方法重写

当子类中出现与父类一模一样的方法时（返回值类型，方法名和参数列表都相同），会出现覆盖效果，也称为重写或者复写。**声明不变，重新实现**



**应用场景**

子类继承了父类的方法，但是子类觉得父类的这方法不足以满足自己的需求，子类重新写了一个与父类同名的方法，以便覆盖父类的方法

例如：我们定义了一个动物类代码如下：

```java
public class Animal  {
    public void run(){
        System.out.println("动物跑的很快！");
    }
    public void cry(){
        System.out.println("动物都可以叫~~~");
    }
}
```

然后定义一个猫类，猫可能认为父类 `cry()` 方法不能满足自己的需求



**代码示例**

```java
public class Cat extends Animal {
    // 重写cry方法，覆盖父类的
    public void cry(){
        System.out.println("喵喵喵！");
    }
}

public class Test {
	public static void main(String[] args) {
      	// 创建子类对象
      	Cat ddm = new Cat()；
        // 调用父类继承而来的方法
        ddm.run();
      	// 调用子类重写的方法
      	ddm.cry(); // 喵喵喵！
	}
}
```



**注意：**

1. 方法重写是发生在子父类之间的关系。
2. 子类方法覆盖父类方法，必须要保证权限大于等于父类权限。
3. 子类方法覆盖父类方法，返回值类型、函数名和参数列表都要一模一样，否则会导致方法重载。



#### @Override

在Java继承中，对于方法重写，不是必须要加上 `@Override` 注解。但是，建议在重写父类方法时使用 `@Override` 注解，这样可以增加代码的可读性和可维护性。

使用 `@Override` 注解可以确保方法的正确重写。如果在重写方法时，不小心拼写错误或者方法签名不匹配，编译器会报错。而使用注解，编译器会检查该方法是否确实是重写了父类的方法，如果没有重写成功，编译器会报错，提醒开发者进行修正。

总之，虽然不是强制要求加上 `@Override` 注解，但是建议在重写父类方法时使用该注解，以增加代码的可读性和可维护性，并避免潜在的错误。

```java
public class Main {
    public static void main(String[] args) {
        Student s = new Student();
        s.func(); // 子类方法
    }
}
```

```java
public class Student extends Person {
    String name = "子类属性";

    @Override
    public void func() {
        System.out.println("子类方法");
    }
}
```

```java
public class Person {
    String name = "父类属性";

    public void func() {
        System.out.println("父类方法");
    }
}
```



### 多态

当一个方法的形参是一个类，我们可以传递这个类所有的子类对象，而且还可以根据不同的对象来调用不同类中的方法。同时多态也是面向对象的三大特性之一



**为什么使用多态？**

如果没有多态，那么在下图中 `register` 方法只能传递 `Student` 学生对象，其他的 `Teacher` 和 `administrator` 对象是无法传递给 `register` 方法方法的，在这种情况下，只能定义三个不同的 `register` 方法分别接收学生，老师这两个类

![多态的应用场景](image/多态的应用场景1.png)

有了多态之后，方法的形参就可以定义为共同的父类 `Person`，换句话说只要是继承于 `Person` 的父类都可以作为 `Person` 类型传递参数



**代码示例**

```java
// Main.java
public class Main {
    public static void main(String[] args) {
        Student s = new Student();
        s.setName("张三");
        s.setAge((byte) 18);

        Teacher t = new Teacher();
        t.setName("王建国");
        t.setAge((byte) 30);

        Register(s);
        Register(t);
    }

    // 只要继承于Person的类都可以当做形参传递过来
    public static void Register(Person p){
        // 传递哪个类，就调用哪个类的show方法
        p.show();
    }
}
```

```java
// Person.java
public class Person {
    private String name;
    private byte age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public void show() {
        System.out.println(name + ", " + age);
    }
}
```

```java
// Student.java
public class Student extends Person {

    @Override
    public void show() {
        System.out.println("学生的信息为：" + getName() + ", " + getAge());
    }
}
```

```java
// Teacher.java
public class Teacher extends Person {

    @Override
    public void show() {
        System.out.println("老师的信息为：" + getName() + ", " + getAge());
    }
}
```

**注意：** 多态必须是以继承的关系才能被定义



#### 弊端

多态编译阶段是看左边父类 类型的，如果子类有些 **独有** 的功能，那么使用多态就无法访问子类独有功能了

```java
public class Main {
    public static void main(String[] args) {
        Person p = new Student();
        p.setName("张三");
        p.setAge((byte) 18);

        // Person类有show方法，即使被Student类重写也是可以的
        p.show();
        
        // 编译报错，编译看左边的Person父类，Person中没有func变量
        p.func();
    }
}
```



#### 转型

当使用多态方式调用方法时，首先检查父类中是否有该方法，如果没有，则编译错误。也就是说，**不能调用**子类拥有，而父类没有的方法。编译都错误，更别说运行了。这也是多态给我们带来的一点"小麻烦"。所以，想要调用子类特有的方法，必须做向下转型。

```java
public class Main {
    public static void main(String[] args) {
        // 向上转型  
        Animal a = new Cat();  
        a.eat(); // 调用的是 Cat 的 eat

        // 向下转型：强制转换为子类的类型自然就可以使用子类自己的方法了
        Cat c = (Cat) a;       
        c.catchMouse(); // 调用的是 Cat 的 catchMouse
    }  
}
```

```java
abstract class Animal {  
    abstract void eat();  
}  

class Cat extends Animal {  
    public void eat() {  
        System.out.println("吃鱼");  
    }  
    public void catchMouse() {  
        System.out.println("抓老鼠");  
    }  
}  

class Dog extends Animal {  
    public void eat() {  
        System.out.println("吃骨头");  
    }  
    public void watchHouse() {  
        System.out.println("看家");  
    }  
}
```



#### instanceof

为了避免 `ClassCastException` 的发生，Java提供了 `instanceof` 关键字，给引用变量做类型的校验，注意是引用类型才能使用该关键字

```java
String s = new String("Hello");
System.out.println(s instanceof String); // true
```



**测试**

如果变量属于该数据类型或者其子类类型，返回 `true`，反之 `false`

```java
public class Main {
    public static void main(String[] args) {
        Person p = new Student();
        p.setName("张三");
        p.setAge((byte) 18);


        Student student = (Student) p;
        student.StudentFunc();

        // p属于Person或Student类型
        System.out.println(p instanceof Person); // true
        System.out.println(p instanceof Student); // true
        System.out.println(student instanceof Person); // true
        System.out.println(student instanceof Student); // true
        
        // 不属于Teacher类型
        System.out.println(p instanceof Teacher); // false
        System.out.println(student instanceof Teacher); 
        // 报错：不兼容的类型: Student无法转换为Teacher
        
        
        // 根据不同的类型转换为不同的类调用不同的方法
        if (p instanceof Student) {
            Student s = (Student) p;
            s.StudentFunc();
        } else if (p instanceof Teacher) {
            Teacher t = (Teacher) p;
            t.TeacherFunc();
        }
    }
}
```



**instanceof 新特性**

`JDK14` 的时候提出了新特性，把判断和强转合并成了一行

```java
// 如果p为Student类型 就自动强制转换为Student并赋值给s变量
if (p instanceof Student s) {
	s.StudentFunc();
} else if (p instanceof Teacher t) {
	t.TeacherFunc();
}
```



### 权限修饰符

| 修饰符    | 同一个类中 | 同一个包中 | 不同包的子类 | 不同包的无关类 |
| --------- | ---------- | ---------- | ------------ | -------------- |
| private   | √          |            |              |                |
| default   | √          | √          |              |                |
| protected | √          | √          | √            |                |
| public    | √          | √          | √            | √              |



### 练习

**1. 双人对战小游戏**

需求：

​	格斗游戏，每个游戏角色的姓名，血量，都不相同，在选定人物的时候（new对象的时候），这些信息就应该被确定下来。 

举例：

```
第1回合
角色：Python 伤害：13 当前血量：87
角色：Java 伤害：7 当前血量：93

第2回合
角色：Python 伤害：19 当前血量：68
角色：Java 伤害：3 当前血量：90

第3回合
角色：Python 伤害：19 当前血量：49
角色：Java 伤害：7 当前血量：83

第4回合
角色：Python 伤害：19 当前血量：30
角色：Java 伤害：15 当前血量：68

第5回合
角色：Python 伤害：2 当前血量：28
角色：Java 伤害：16 当前血量：52

第6回合
角色：Python 伤害：15 当前血量：13
角色：Java 伤害：11 当前血量：41

第7回合
角色：Python 伤害：20 当前血量：0
Java KO了 Python
```

代码示例：

```java
// Role.java
import java.util.Random;

public class Role {
    private String name;
    private byte blood;

    public Role(String name, byte blood) {
        this.name = name;
        this.blood = blood;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getBlood() {
        return blood;
    }

    public void setBlood(byte blood) {
        this.blood = blood;
    }

    // 攻击方法
    public void attack(Role role) {
        // 随机伤害
        Random r = new Random();
        byte hurt = (byte) (r.nextInt(20) + 1);
        byte boold = (byte) (role.getBlood() - hurt);

        // 如果血量小于0就是0
        boold = boold < 0 ? 0 :boold;
        role.setBlood(boold);

        // 对战记录
        System.out.format("角色：%s 伤害：%d 当前血量：%d\n", role.getName(), hurt, role.getBlood());
    }
}
```

```java
// Main.java
public class Main {
    public static void main(String[] args) {
        Role java = new Role("Java", (byte) 100);
        Role python = new Role("Python", (byte) 100);

        // 回合数
        byte round = 1;

        while (true) {
            System.out.println("第" + round + "回合");

            java.attack(python);
            if (python.getBlood() == 0) {
                System.out.format("%s KO了 %s", java.getName(), python.getName());
                break;
            }

            python.attack(java);
            if (java.getBlood() == 0) {
                System.out.format("%s KO了 %s", python.getName(), java.getName());
                break;
            }

            round += 1;
            System.out.println();
        }
    }
}
```



**2. 商品数据录入**

需求：

定义数组存储 3 个商品的对象。

商品的属性：名称、价格。

创建三个商品对象，数据通过键盘录入而来，并把数据存入到数组当中。

代码示例：

```java
// Goods.java
public class Goods {
    private String name;
    private int price;

    public Goods() {
    }

    public Goods(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
```

```java
// Main.java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Goods[] arr = new Goods[3];

        // 数据录入
        for (byte i = 0; i < arr.length; i++) {
            Goods g = new Goods();

            System.out.println("请输入商品名称：");
            String name = sc.next();
            g.setName(name);

            System.out.println("请输入商品价格：");
            int price = sc.nextInt();
            g.setPrice(price);

            arr[i] = g;
        }

        // 遍历数据
        for (byte i = 0; i < arr.length; i++) {
            Goods goods = arr[i];
            System.out.println(goods.getName() + ", " + goods.getPrice());
        }
    }
}
```



## 集合

数组一经定义，长度且无法改变，所以不能添加和删除数据，在某些应用场景中我们必须对数据进行增删改查。所以就需要用到集合。集合是一种 **可变** 数据存储空间，数据容量可以发生改变，解决了数组的缺陷



**集合长度可变原理**

1. 当创建 `ArrayList` 集合容器时，底层会存在一个长度为 `10` 的空数组
2. 如果长度超过了 `10` 则自动创建一个新的数组容器并扩容原数组的 `1.5` 倍
3. 将旧数组的数据自动拷贝到新数组中
4. Java 垃圾回收机制会自动把旧数组删除



集合与数组分别的应用场景：当存储的元素个数固定不变时选择使用数组，否则使用集合



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
|                                       |                                               |

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



### forEach

通过 `forEach` 可以很方便的遍历集合

```java
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();

        list.add("aaaa");
        list.add("bbbb");
        list.add("cccc");

        // 简写（单行代码可以省略{}花括号）
        list.forEach(item -> System.out.println(item));
        // aaaa
        // bbbb
        // cccc

        // 完整写法
        // list.forEach(item -> {
        //     System.out.println(item);
        // });
    }
}
```



### 学生管理系统

```java
// Main.java
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<Student>();

        // 加载一些数据
        init(list);

        while (true) {
            System.out.println("-----------------欢迎来到传智学生管理系统-------------------");
            System.out.println("1: 添加学生");
            System.out.println("2: 删除学生");
            System.out.println("3: 修改学生");
            System.out.println("4: 查询学生");
            System.out.println("5: 退出系统");
            System.out.print("请输入您的选择：");

            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();

            switch (n) {
                case 1:
                    // 添加学生
                    addStudent(list);
                    break;
                case 2:
                    // 删除学生
                    delStudent(list);
                    break;
                case 3:
                    // 修改学生
                    editStudent(list);
                    break;
                case 4:
                    // 获取学生
                    getStudent(list);
                    break;
                case 5:
                    System.out.println("退出成功~");
                    // 这里的break与结束循环的break重复了，会导致无法结束循环，所以使用return将整个函数结束掉
                    return;
                default:
                    System.out.println("请输入正确的序号!");
                    break;
            }
        }
    }

    // 初始化一些数据
    public static void init(ArrayList<Student> list) {
        Student s1 = new Student((byte) 1, "zs", (byte) 13, "北京");
        Student s2 = new Student((byte) 2, "ls", (byte) 17, "上海");
        Student s3 = new Student((byte) 3, "ww", (byte) 19, "广州");
        list.add(s1);
        list.add(s2);
        list.add(s3);
    }

    // 添加学生
    public static void addStudent(ArrayList<Student> list) {
        Scanner sc = new Scanner(System.in);

        System.out.print("请输入学生姓名：");
        String name = sc.next();

        System.out.print("请输入学生年龄：");
        byte age = sc.nextByte();

        System.out.print("请输入学生地址：");
        String address = sc.next();

        Student data = new Student((byte) (list.size() + 1), name, age, address);

        list.add(data);

        System.out.println("添加成功~");
    }

    // 删除学生
    public static void delStudent(ArrayList<Student> list) {
        Scanner sc = new Scanner(System.in);

        System.out.print("请输入学生ID：");
        byte index = sc.nextByte();

        list.remove(index - 1);

        System.out.println("删除学生成功~");
    }

    // 修改学生
    public static void editStudent(ArrayList<Student> list) {
        Scanner sc = new Scanner(System.in);

        System.out.print("请输入学生ID：");
        byte index = sc.nextByte();

        System.out.print("请输入学生姓名：");
        String name = sc.next();

        System.out.print("请输入学生年龄：");
        byte age = sc.nextByte();

        System.out.print("请输入学生地址：");
        String address = sc.next();

        Student data = new Student(index, name, age, address);

        list.set(index - 1, data);
        System.out.println("修改学生信息成功");
    }

    // 获取学生
    public static void getStudent(ArrayList<Student> list) {
        System.out.println("获取学生信息成功：");

        // 如果集合为空就输出暂无数据
        if (list.isEmpty()) {
            System.out.println("暂无数据~");
            return;
        }

        list.forEach(item -> {
            System.out.format("ID：%d | 姓名：%s | 年龄：%d | 地址：%s \n", item.getId(), item.getName(), item.getAge(), item.getAddress());
        });
    }
}
```

```java
// Student.java
public class Student {
    private byte id;
    private String name;
    private int age;
    private String address;

    public Student(byte id, String name, byte age, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(byte id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getAge() {
        return (byte) age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
```

