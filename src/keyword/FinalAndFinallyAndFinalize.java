package keyword;
public class FinalAndFinallyAndFinalize {
    /**
     *final：如果一个类不希望其他人定义它的子类，我们通常可以把该类
     *       定义为final类型的，被final修饰的类中的方法也被默认为final类型；
     *       如果一个方法不希望被子类重写则此时可以把该方法定义为final类型；
     *       如果一个域在第一次给定它的初始值后就不希望再做任何改变，此时可以把该域设定为final类型。
     *       注意：如果域是一个对象类型被final修饰后只能说明该对象的引用
     *             不能被改变，但是对象的具体内容可以改变。
     *finally:finally语句是try/catch异常处理语句后附带的一条后处理语句，
     *        该语句表明是否发生异常或是异常是否被捕获或处理该条语句都会执行，finally语句
     *        中通常做一下IO的关闭操作和后处理工作。
     *finalize:它是一个方法，属于Object类，相当于C++中的析构函数，通常在
     *         系统垃圾回收一个对象时被默认调用，在java中垃圾回收是由JVM
     *         的GC完成的，对一个对象宣判死刑需要经过两个过程，第一个过程是
     *         搜索该对象有没有到达gc root的引用链，如果没有引用链则说明可能没有
     *         作用了需要回收，第二个过程判断该对象的finalize有没有被重写或者已经调用过，
     *         确保该对象的finalize被调用（执行一些该对象占用的一些特殊资源），并且调用过程该对象任然没有指向gc root
     *         引用链，则此时可以彻底宣判该对象死刑。
     */
    public static void main(String[] args) {
        System.out.println("没有任何联系！！！！");
    }
}
