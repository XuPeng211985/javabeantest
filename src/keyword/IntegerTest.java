package keyword;

/**
 * int和Integer的区别：
 *    java中为了实现纯面向对象，为每种基本数据类型都设计了包装类
 *    从java5开始提供了自动装箱和自动拆箱机制，使得包装类型和基本数据类型
 *    可以自动转换。（一个是数据，一个是包着数据的对象）
 *    原始类型: boolean，char，byte，short，int，long，float，double
 *    包装类型：Boolean，Character，Byte，Short，Integer，Long，Float，Double
 * 注：如果同时自动装箱两个相同值的int类型变量，在-128---127之间 不会new新的Integer对象
 *     不在-128---127之间 才会new新的Integer对象
 *
 */
public class IntegerTest {
    public static void main(String[] args) {
        Integer b1 = 3;
        Integer b = 3;// 将3自动装箱成Integer类型
        Integer a = new Integer(3);
        int c = 3;
        System.out.println(b1 == b);
        System.out.println(a == b);     // false 两个引用没有引用同一对象
        System.out.println(a == c);     // true a自动拆箱成int类型再和c比较
        System.out.println("=================================");
        Integer f1 = 100, f2 = 100, f3 = 150, f4 = 150;
        System.out.println("如果不在范围内直接new一个新的Integer对象");
        System.out.println(f1 == f2);
        System.out.println("自动装箱时 如果int值在-128---127之间 直接将值赋给该对象");
        System.out.println(f3 == f4);
        System.out.println(f3.equals(f4));
    }
}
