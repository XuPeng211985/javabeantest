package listsetmap;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
/**
 * ArrayList:是一个数组列表，相比数组的优势是它可以动态扩容
 *      另外它是非线程安全的，当多个线程同时操作一个ArrayList对象
 *      时不保证同步。
 * transient关键字：阻止一个可以被序列化的类中某些变量不被序列化
 *     这样使得该属性在被反序列化时不会恢复以及持久化。
 *     例如，当反序列化对象——数据流（例如，文件）可能不存在，
 *     原因是你的对象中存在类型为java.io.InputStream的变量时，序列化一个
 *     输入流是毫无意义的，因为在反序列化时大多数情况下，输入流对象
 *     中都是不存在数据的。有些变量在使用该类的时候有作用 用完之后就没有作用了
 *     持久化只能多占用点内存空间
 *     private transient InputStream is;
 * 为什么不直接序列化elementData：因为elementDate是一个缓存数组，大多数情况下
 *     该数组中有很多空闲的位置，并没有保存实际数据，序列化这些位置是没有意义
 *     的，为了节省空间ArrayList采用WriteObject 和 ReadObject两个方法进行序列化
 *     和反序列化，这两个方法会遍历临时数组中的每一个元素，并且把他们序列化，这样
 *     比直接序列化整个缓存空间要节省空间。
 * 源码分析 ：
 * ArrayList包含了两个重要的对象：elementData 和 size。
 * transient Object[] elementData : ArrayList容器，保存添加到ArrayList中的元素。
 * private int size ：The size of the ArrayList
 *
 * 解释一下它的默认长度为什么是Integer-8：有些虚拟机在数组中保留了一些头信息。避免内存溢出！
 *
 *在指定位置插入一个元素：
 *
 *  public void add(int index, E element) {
 *      //判断index是否合法
 *         rangeCheckForAdd(index);
 *      //扩容，分为两步：首先判断添加元素后需要的数组空间是否超过数组本身长度
 *      //如果超过则需要扩容调用grow()
 *         ensureCapacityInternal(size + 1);  // Increments modCount!!
 *      //从index开始后面的元素一次后移一位  复制实现移动
 *         System.arraycopy(elementData, index, elementData, index + 1,
 *                          size - index);
 *         elementData[index] = element;
 *         size++;
 *     }
 *     //扩容
 *      private void grow(int minCapacity) {
 *         // overflow-conscious code
 *         int oldCapacity = elementData.length;
 *         //原数组长度加上原数组的长度的0.5倍  1.8将除法改进为右移位运算
 *         int newCapacity = oldCapacity + (oldCapacity >> 1);
 *         if (newCapacity - minCapacity < 0)
 *             newCapacity = minCapacity;
 *         if (newCapacity - MAX_ARRAY_SIZE > 0)
 *             newCapacity = hugeCapacity(minCapacity);
 *         // minCapacity is usually close to size, so this is a win:
 *         elementData = Arrays.copyOf(elementData, newCapacity);
 *     }
 *     modCount的作用：修改次数计数器
 *     在一个改变ArrayList的结构的方法中需要对modCount进行自增，比如一些添加，
 *     删除的方法中。在ArrayList的迭代器中需要使用这个字段，如果在迭代过程
 *     中modCount发生了改变，（通常在多线程的环境下发生）在迭代的时候就会抛出
 *     ConcurrentModificationException异常。因为如果持续迭代读取的数据可能不是
 *     最新的数据，已经过期
 *
 * 值的注意的一些细节：
 *      1.遍历ArrayList时，使用随机访问(即通过索引序号访问)效率最高，而使用迭代器的效率最低
 *      2.ListItr继承自Itr，添加了向前遍历的功能，当获取了iterator实例后，list就不可改变。
 *      当ArrayList使用了ListIterator()方法产生自身对应的Iterator后，只能使用Iterator自身的remove()
 *      和add()方法来修改ArrayList的结构，因为这些方法对expectedModCount和modCount变量自动同步。
 *      3.默认ArrayList的长度是10个，所以如果你要往list里添加20个元素肯定要扩充一次（newCapacity 扩充
 *       为原来的1.5倍，但和输入的minCapacity相比发现小于minCapacity，于是 newCapacity = minCapacity，所以只扩容一次
 *      4.Vector的实现基本上和ArrayList相同，不过ArrayList是线程不安全的 Vector是线程安全的
 */
public class ArrayListTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        List<Integer> list1 = new ArrayList<>();
        List<Integer>list2 = new ArrayList<>();
        List<Integer>list3 = new ArrayList<>(20);
/**
 * list1和list2指向的是同一个对象引用  都是空数组
 */
        Field f1 = list1.getClass().getDeclaredField("elementData");//根据变量名elementData获得字段
        f1.setAccessible(true);	//设置字段可访问，即暴力反射
        Object o1 = f1.get(list1);	//在那个对象上获取此字段的值
        System.out.println(o1);
        Field f2 = list2.getClass().getDeclaredField("elementData");
        f2.setAccessible(true);
        Object o2 = f2.get(list2);
        System.out.println(o2);
        Field f3 = list3.getClass().getDeclaredField("elementData");
        f3.setAccessible(true);
        Object o3 = f3.get(list3);
        System.out.println(o3);
        System.out.println("================================");
        list1.add(1);
        list1.add(2);
		list1.add(3);
       /* Iterator<Integer> it = list1.iterator();
        //list1.add(2); throw java.util.ConcurrentModificationException
      // 获得迭代器后，将不能再进行修改操作，因为获得迭代起的同时就确定了当前的modCount
        //迭代过程中这个值不允许改变
        while (it.hasNext()) {
            System.out.println(it.next());
        }*/
        ListIterator<Integer> listIterator = list1.listIterator();
        listIterator.add(4); //调用自身的方法修改
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }
    }
}
