package keyword;
/**
 * String StringBuffer StringBuild之间的区别:
 *   String：简单来说String是不可变的字符串类型，StringBuffer和StringBuild
 *      是可以改变的字符串类型；进一步来说就是每次对一个String类型的对象
 *      进行改变时，JVM都会在内存中重新new一个新的对象，如果需要对一个字符串
 *      频繁的进行修改（追加或者剪切），这样修改前的旧字符将会失去引用，当
 *      JVM中出现大量没有引用的对象时就会进行频繁的GC操作，这样会很大程度上影响
 *      程序的性能。
 *   StringBuffer：为解决String存在的问题，引入了StringBuffer,每当修改一个StringBuffer
 *            对象的内容时，都只是在原先字符串的基础上进行拼接，而不会new一个新的对象，
 *            再改变对象的引用。由于StringBuffer是线程安全的，所以在执行拼接操作时
 *            当前线程必须获得该对象的锁，如果在单线程环境下，我们可以
 *            采用非线程安全的StringBuild来代替StringBuffer，这样可以提升性能。
 *   总结：String是值不可变的常量，是线程安全的；由于它被final关键字，所以它不能
 *         作为父类被继承；StringBuffer是一个线程安全的字符串容器，可以在该容器中
 *         添加和改变字符串的内容。
 *   引用场景：String适合于表示需要少量操作的字符串
 *             StringBuffer适合于表示多线程环境下需要大量操作的字符串
 *             StringBuild适合于表示单线程环境下需要大量操作的字符串
 *   速度：  由于String在修改时会重新new一个新的对象，然后把原先字符串
 *           引用指向新的对象，频繁这样做就会使JVM堆中出现大量没有直接引用
 *           的字符串对象，从而引发GC 运行速度比较慢。相对来说StringBuffer
 *           拼接字符串要比String快很多，因为它是直接在原字符串上进行拼接
 *           由于同步时需要加锁，多个线程竞争一个资源会发生很多无效等待
 *           在单线程环境下，StringBuild速度要比StringBuffer快一点
 */
public class StringTest {
    public static void main(String[] args) {
        /**
         * 不可变测试：定义一个String类型和StringBuffer类型的变量 分别对他们的值进行修改
         *         比较修改前和修改后的hashCode值是否相等（这里有一个问题，就是由于String
         *         重写了equals方法和hashCode方法，所以hashCode值相同，并不代表这两个对象的地址相等）
         */
        String str = "123";
        StringBuffer strBuffer = new StringBuffer();
        strBuffer.append("abc");
        System.out.println(str.getClass() + "  " + str.hashCode() + "-->" + strBuffer.getClass() + strBuffer.hashCode());
        str = str + "456";
        strBuffer.append("aaa");
        System.out.println(str.getClass() + "  " + str.hashCode() + "-->" + strBuffer.getClass() + " " + strBuffer.hashCode());
        str = "abc";
       //1.相当于直接new 一个String（"myString123456"）快于逐个拼接
        String s = "myString" + "123"+"456";
        //2.这种情况下相当于不可变字符串对象的拼接 效率特别低
        String s1 = "myString";
        String s2 = "123";
        String s3 = "456";
        String s4 = s1 + s2 + s3;
        //不用new新的字符串对象，直接在原有字符串的基础上进行拼接
        StringBuffer sb = new StringBuffer("myString").append("123").append("456");
    }
}
