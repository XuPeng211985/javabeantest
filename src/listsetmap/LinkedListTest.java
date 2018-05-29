package listsetmap;
/**
 * List list = Collections.synchronizedList(new LinkedList(...));同步实现
 *
 * LinkedList和ArrayList一样都是实现了List接口，只是ArrayList是List接口的
 *     大小可变数组的实现，LinkedList是List接口链表的实现。基于链表实现的方式
 *     使得LinkedList在插入和删除时更优于ArrayList，而随机访问则比ArrayList逊色些。
 *     LinkedList继承于AbstractSequentialList（双向链表抽象类） 可以被当作堆栈、队列或双端队列进行操作
 *
 * 很细节的一个方法：返回标记值为index的节点，从所有元素的最中间开始遍历
 *      如果index的值小于size的一半则从头结点开始向中间查找 直到找到index为止
 *      相反如果index的值大于size的一半则从尾节点开始向中间遍历 直到找到index为止
 *  Node<E> node(int index) {
 *         // assert isElementIndex(index);
 *
 *         if (index < (size >> 1)) {
 *             Node<E> x = first;
 *             for (int i = 0; i < index; i++)
 *                 x = x.next;
 *             return x;
 *         } else {
 *             Node<E> x = last;
 *             for (int i = size - 1; i > index; i--)
 *                 x = x.prev;
 *             return x;
 *         }
 *     }
 *
 *  添加一整个集合：基本思路是首先定义两个临时节点，后继节点指向要插入的位置
 *        连接index后面的所有节点，前驱节点一直指向index位置的前一个节点
 *        每次要插入一个新节点，如果前驱节点为空，则将新插入的节点当做头结点
 *        如果不为空，前驱节点的后继指向新节点，最后让前驱节点指向新插入的节点
 *        直到遍历完所有要插入的节点后，在判断后继节点是否为空，如果是空，则将
 *        最后一个节点指向前驱节点 如果不是空，直接将前驱节点和后继节点相连
 *     public boolean addAll(int index, Collection<? extends E> c) {
 *         //若插入的位置小于0或者大于链表长度，则抛出IndexOutOfBoundsException异常
 *         checkPositionIndex(index);
 *         //首先将集合变为对象数组
 *         Object[] a = c.toArray();
 *         int numNew = a.length;//插入元素个数
 *         if (numNew == 0)
 *             return false;
 *         Node<E> pred, succ;     //定义临时前驱和后继
 *         if (index == size) {    //如果在链表尾部插入
 *          1.1   succ = null;    //后继置空
 *          1.2   pred = last;    //前驱指向队尾元素last
 *         } else {            //在指定位置插入
 *             succ = node(index); //后继指向该位置，后继一直连接当前位置后面的所有节点
 *             pred = succ.prev;   //前驱指向前一个元素 前驱一直连接当前位置前面的所有节点
 *         }
 *         for (Object o : a) {
 *             @SuppressWarnings("unchecked") E e = (E) o;
 *             Node<E> newNode = new Node<>(pred, e, null);//创建一个新节点，指定前驱，后继置空
 *             if (pred == null)//如果前驱不存在，说明当前要插入的位置是头
 *                 first = newNode;//表头first指向此节点
 *             else
 *             1.3    pred.next = newNode;//前驱存在，则将其next指向新节点
 *            1.4 pred = newNode;//前驱向后移动，继续创建新节点
 *         }
 *         if (succ == null) {
 *             last = pred;//如果后继为空，说明当前插入的节点应该在链表尾部
 *         } else {
 *           //后继不为空，那么前驱节点和后继节点相连
 *             pred.next = succ;
 *             succ.prev = pred;
 *         }
 *         size += numNew;
 *         modCount++;
 *         return true;
 *     }
 * 关于线程不安全的讨论：从fil-fast到写时拷贝
 *     ArrayList和LinkedList共同的特点是他们都是线程不安全的，
 *     在多个线程同时操作一个集合对象时就会发生数据脏读和数据
 *     覆盖等问题，同时再调用它们的迭代器时，都会记录当前的修
 *     改版本modCount的值，如果在迭代的过程中该值发生了变化，
 *     迭代器立马抛出一个ConcurrentModificationException异常。
 *     像这种迭代时修改抛异常的情况被称为fil-fast机制；当然在
 *     在日常开发中我们是不希望看到这种情况发生的，多线程并发
 *     在迭代器遍历列表时只是简单的读取里面的数据而不做任何修
 *     改，凭什么不允许其他线程操作该列表对象，CPU大哥的时间
 *     可是很宝贵的，我们应该抓住CPU大哥的每一ms时间，让它做
 *     更多有意义的事，而不是做大量的空闲等待。
 *
 *     基于上面的争议我们引入写时拷贝技术，注意它是一项技术，不归属
 *     于任何语言，也不归属于任何领域，只不过大多数情况下被运用到计
 *     算机的相关领域，比如Linux操作系统fork的内存分配，还有就是现在说的
 *     CopyOnWriteArrayList,该类在ArrayList的基础上引用了写时拷贝
 *     技术，具体引用过程如下：对于多个线程操作同一个列表时，如果
 *     某个线程只是简单读取列表的值而不做任何修改，那么当前线程将不需要
 *     获得该对象的锁，这样就不会阻塞其他线程对该对象进行其他操作。
 *     当某个线程想要修改一个列表对象的元素时，该线程将会获得列表对象的锁 并且从主存中拿到
 *     当前数组对象的一份副本，所有的修改操作都是对副本而言的，对于主存中的实际数据
 *     并不会产生新的影响，在修改完成后将修改后的副本更新到主存 释放对象锁；则其他线程可
 *     以任意读取数组对象中的数据，但是不能修改，因为该对象的锁不能同时被两个线程所拥有。
 *     需要注意的是这里的没有修改是片面的，也就是说当一个线程修改这个数组对象时
 *     虽然操作的是一个副本，但是真正想修改的还是主存中的真实数据，只不过暂时
 *     还把修改的结果没有更新到主存中，所以在其他线程无锁读取数组元素时，有可能
 *     会读到已经过期的数据，只不过这个过期数据在主存中体现出了一种没有改变的假象
 *     同样的原理，在CopyOnWriteArrayList中的迭代器
 *     也做了一定的优化，CowIterator是list迭代器的子类，它不支持获得迭代器的线程
 *     对数组元素做任何的修改，在迭代过程中其他线程对该数组的更新也不会体现在迭代器中
 *     也就是说迭代器中的数组相当于是不可变的，获得迭代器的线程如果想修改数组元素
 *     必须获得对象的锁，而这又违背了多个线程同时迭代的初衷；换言之，CowIterator仅仅
 *     保证了在调用迭代器的同时其他线程在修改数据则不会抛出异常，仅此而已。
 */
public class LinkedListTest {
    public static void main(String[] args) {
    }
}
