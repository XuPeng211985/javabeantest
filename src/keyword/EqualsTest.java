package keyword;
/**
 *   1》== 比较的是两个对象或者基本数据类型的存储地址是否相同
 *   2》equals方法是Object类中提供用来比较两个对象是否相等的方法
 *     默认情况下比较两个对象是否指向同一个引用，也就是比较两个对象
 *    所指向的地址是否相同，但是设计equals的初衷是为了比较两个对象的状态
 *    是否相等，比如一个学生对象可以由姓名和学号唯一确定，那么只要两个学生
 *    对象的姓名和学号对应相等，那么我们就说这两个学生是同一个学生，因此通常
 *    情况下我们需要重写equals方法用它来比较两个对象状态是否相等，必须满足5个
 *    特性：自反性，对称性，传递性，一致性，非空引用e.equals.(null)必须返回null
 *    注：通常重写时会调用对象中某个字段的equals方法，来比较两个字段对象的值是否相等
 *        为了避免两个对象都为null时返回false
 *
 *    应用场景：通常在开发系统登录功能时，首先需要调用equals方法比较
 *              数据库中查出的对象是否和前端界面传入的对象信息是否匹配，
 *              如果采用默认equals方法，两个对象是不同时刻创建的两个不同的对象
 *              equals方法肯定会返回false,永远也登录不进去，那么你就算正确输入
 *              登录名和密码也没有办法。
 *  ==============================================================
 *  ==============================================================
 *  关于hashCode方法：hashCode的字面意思是散列码，它是由对象导出的一个整型值
 *       散列码的生成是没有特定规律的，(1)如果两个对象相同（equals方法返回true），那么它们的hashCode值一定要相同；(2)如果两个对象的hashCode相同，它们并不一定相同。当然，你未必要按照要求去做，
 *       但是如果你违背了上述原则就会发现在使用容器时，相同的对象可以出现在Set集
 *       合中，同时增加新元素的效率会大大下降（对于使用哈希存储的系统，如果哈希
 *       码频繁的冲突将会造成存取性能急剧下降）。
 *       ========================================================
 *       ========================================================
 * 关于instanceof ：比较两个对象的类型是否相等，通常在多态下判断父类的不同子类的类型
 */
public class EqualsTest {
    public static void main(String[] args) {
        String t1 = "test";
        String t2 = "test";
        String t3 = new String("test");
        System.out.println("t2从常量池中找到了test 并且这个test就是刚才t1创建的");
        System.out.println(t1 == t2);
        System.out.println("t3直接new了一个新的String对象");
        System.out.println(t1 == t3);
        System.out.println("String类重写了equals方法 比较char[] 数组中每一个元素是否相等");
        System.out.println(t1.equals(t2));
        System.out.println(t1.equals(t3));
        System.out.println("===============================");
        System.out.println("===============================");
        String s = "OK";
        StringBuffer sb = new StringBuffer(s);
        System.out.println(s.hashCode() + " " + sb.hashCode());
        String t = new String("OK");
        StringBuilder tb = new StringBuilder(t);
        System.out.println(t.hashCode() + " " + tb.hashCode());
        System.out.println("总结：String重写了hashCode 方法，由字符串内容决定 所以两个String类型的值相等");
        System.out.println("      StringBuffer没有重写hashCode 方法，默认比较地址 所以两个String类型的hash值不相等");
    }
}
