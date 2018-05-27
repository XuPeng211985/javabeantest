package oopfeature.generic;
import java.util.ArrayList;
import java.util.List;
/**
 * 是什么：可以使一个类或者一个方法通用不同类型的特定对象而不发生类型冲突的设计方式
 *         比如ArrayList里面可以存放String 或者 File对象，在接收容器中的元素
 *         时可以明确知道他的具体类型而不用强制类型转换。
 * 解决的问题：1.获取一个容器中的元素时，避免了强制转型（没有泛型，取出的元素都是Object）
 *             2.不会在一个特定类型的容器中添加其他类型的元素，比如猫类型的容器里添加
 *               一个狗类型对象就会报错，如果没有泛型这种神级操作是不会报错的
 *注意的问题：1.引入泛型后，想要使用compareTo()方法比较两个对象的相对大小，
 *              T必须继承Comparable，因为这样才能确保T所属的类具有compareTo方法
 *            2.在对一个泛型类没有指定特定类型之前默认T为Object类型
 *            3.在调用一个泛型类型的方法时JVM会进行泛型擦除也就是所有的对象
 *              都以Object类型存储，然后在调用时JVM自动进行强制类型转换
 *            4.基本数据类型的变量不能作为泛型参数，因为类型擦除后都会以Object类型存储
 *            5.不能创建参数化类型的数组 Pair<String>[] table = new Pair<>[10];//error
 *              原因是类型擦除时，table被记录的类型是Pair[] ,而忽略了Pair的类型限制String
 *              类型擦除：Object[] objarray = table;
 *                      objarray[0] = new Pair<Employee><正常编译/>
 *              这样就会使泛型机制失去作用
 * 通配符类型：1.上界通配符 假设编写一个泛型类型的盘子类，然后将盘子的泛型类型设定为水果
 *               我们实例化一个水果盘子，并用它来装水果
 *               Plate<Fruit> p = new Plate<Apple>(new Apple());
 *               理论上上句代码是没有问题的，水果盘子里面可以放苹果，但是java编译器
 *               不认可这种做法，它会认为苹果是一种水果没错 但是水果盘子里只能放水果 不能放苹果
 *               为了让水果盘子里可以放苹果，我们可以将盘子的泛型用通配符定义 Plate<? extends Fruit>
 *               这样盘子中可以放水果类型以及它子类型的所有对象；
 *             2.下界通配符   和上界通配符刚好相反Plate<? supper Fruit></>
 *               通过下界通配符可以定义可以存放水果，以及它基类类型的对象盘子 但是不能存放水果的子类
 * 引用场景：上界通配符定义的集合List<? extends T> 中不能插入任何类型的对象，因为List里面
 *            存储的对象类型是由歧义的</>，但是可以保证在读取时只能读取到T或者T类型的子类对象
 *            ####如果你需要从一个列表中读取T类型以及它子类型的元素，那么可以把这个列表声明为
 *                 上界通配
 *           下界通配符定义的集合List<? super T>中 不能读取任何类型的对象 因为List里面存储对象
 *            类型是有歧义的</>，但是可以保证在写入时只能写入T子类类型的对象
 *            #####如果你想把T类型的元素写入到列表中，那么此时你可以把这个列表声明为下界通配
 *            PECS原则（生产者用extends 消费者使用super） 集合中的copy方法
 */
public class GenericTest {
    public static void main(String[] args) {
        /**
         * 上界通配符定义的集合中你可以读取Number类型的对象，但是你不能插入任何类型的对象
         * 因为插入Number元素时，你不能确定列表中实际存储的对象类型是什么
         */
        List<? extends Number> eoo0 = new ArrayList<>();
        /**
         * 下界通配符定义的集合中，唯一能确保读取到的对象是一个Object类型 而不能确定他的具体类型
         *  但是可以插入T类型以及T类型的子类对象
         */
        List<? super Integer> foo0 = new ArrayList<Integer>();
        List<? super Integer> foo1 = new ArrayList<Number>();
        List<? super Integer> foo2 = new ArrayList<Object>();
        /**
         * 总结：下界通配符中限定的列表中的元素类型都是下界类型的父类，所以
         *        在该列表中插入下界类型的子类是可以被允许的，因为子类类型
         *        一定兼容父类类型，但是读取时不能确定在列表中保存的具体子类
         *        类型。
         *        上界通配符中限定的列表中的元素类型都是上界类型的子类类型
         *        在该列表中可以读取上界通配符类型的对象，但是不能写入具体类型
         *        的对象，因为列表元素没有下界限制，不能确定列表中具体存储的是
         *        什么类型的对象。
         */
        String[] words = {"as","abc","bcs","dsa","fgh"};
        Pair<String> pair = ArrayAlg.minmax(words);
        System.out.println("min =  "+pair.getFirst());
        System.out.println("max =  "+pair.getSecond());
    }
}
