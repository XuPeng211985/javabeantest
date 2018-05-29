package listsetmap;
import java.util.HashMap;
/**
 * http://www.importnew.com/20386.html 优秀博文
 * HashMap是继承了AbstractMap抽象类实现了Map接口，用于存储Key-Value
 * 键值对的集合，它的主干是一个存储Entry对象的数组，初始值都被设置为null.
 * put原理：
 * 每次想在HashMap中存储一个键值对时，第一步先创建一个Entry(java8中改成了Node)对象，然后将
 * 该对象键的hashcode值通过hash函数映射计算出该Entry应该存储在HashMap
 * 中的具体位置下标，通常情况下 ，多个不同的对象可能会计算出相同的hashcode
 * 值，这样就会出现散列冲突，为了解决冲突，具有相同hashcode值的不同对象
 * 会通过链表的形式存储在同一个索引上,当一个索引的长度超过8时，会把链表转换为
 * 红黑树。关于红黑树是个大专题在这里不瞎谈，改天单独认真聊。
 *
 *  put时值的注意的一些细节：
 *    1.为什么要求hashMap的默认长度以及扩容后的Node桶的个数必须是2的次幂
 *      简单的说是为了避免出现过多的hash冲突，举个例子说明：
 *      假设 key = "Apple"的hashcode值为：          1111 1111 1111 1111 1111 0000 1110 1010
 *                                                   &
 *      假设table的Node桶的个数length为10：         0000 0000 0000 0000 0000 0000 0000 1010
 *
 *                             计算结果是：         0000 0000 0000 0000 0000 0000 0000 1010
 *     如果下一个要存入的Node对象的key的hashcode最后四位是： 1111 那么和 1010取 &运算结果
 *     仍然是1010，明显hashcode的后四位值不相同，结果经过hash后却获得了相同的索引，还有一个问题
 *     就是在上面的例子中，有些索引值是永远不会出现的：比如1111 0101 0001 0100
 *     这样会导致更多的hash冲突不说还会导致某些索引值所在的位置永远不会存入元素，这些都是我们不希望看到的。
 *
 *     然后把问题回到起点，发生更多的hash冲突的原因是table中的node桶个数的二进制低位上有0，这样取
 *     &运算 0所在的位置当然不会计算出1，为了解决这个问题java设计师将node桶个数规定为2的次幂
 *     这样hashcode值和length-1取&运算这样就会使得每次计算出的索引值都和hashcode值的低位值一致
 *     并且不会出现某些索引被遗忘的情况，继续上面的例子：
 *        key = "Apple"的hashcode值为：          1111 1111 1111 1111 1111 0000 1110 1010
 *                                                    &
 *     假设table的Node桶的个数length-1为15：     0000 0000 0000 0000 0000 0000 0000 1111
 *
 *                          计算结果是：         0000 0000 0000 0000 0000 0000 0000 1010
 *  如果下一个要存入的Node对象key的hashcode最后四位是： 1111 那么和 1010取 &运算结果
 *  是1111，这样就很大程度上减少了hash冲突，同时也不会出现某些桶不会存入Node对象的情况
 *
 *  JDK1.7中的hash函数就是这样实现的，但是在1.8中又做了进一步的改进，假设有两个Node对象
 *  的hashcode值计算出来低16位完全相同，但是高16位值不同，当table的桶个数较小的时候
 *  这两个对象的hashcode值直接和length-1取 &，求得的索引是相同的，但是如果在取&
 *  之前，将两个对象的hashcode值右移16位，再和原来的hashcode值取异或，这样就会使得原来
 *  相同的低16变为不同，接着和length-1取&，求得的索引值是不相同的两个数，很好地解决了这种
 *  冲突，但是这样也会有不好的情况，就是可能会把之前低16不相等hashcode值变为相等，不过
 *  出现的几率会很小。
 *  //hash函数 主要hashcode的高位和低位做异或运算
 *   static final int hash(Object key) {
 *         int h;
 *         return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
 *     }
 *     //put中的片段，虽然没有了indexOf方法单独确定下标，但是在做插入判断的时候依然是和length-1做&运算
 *  if ((p = tab[i = (n - 1) & hash]) == null)
 *  举个例子：
 *   hashcode值1：1111 1111 1111 1010 1111 0000 1110 1010
 *              ^
 *     >>>16     0000 0000 0000 0000 1111 1111 1111 1010
 *
 *     异或结果  0000 0000 0000 0000 1111 1111 1111 0000
 *              &
 *     length-1                                     1111
 *     hash结果：                                   0000
 *
 *     hashcode值2：1111 1111 1111 0101 1111 0000 1110 0101
 *  *              ^
 *  *     >>>16     0000 0000 0000 0000 1111 1111 1111 0101
 *  *
 *  *     异或结果  1111 1111 1111 1111 0000 1111 0001 0000
 *  *              &
 *  *     length-1                                     1111
 *  *     hash结果：                                   0000
 *  总得来说每一次改进都只是更好的一种优化权衡，而不是直接消除
 *  hash冲突，因为hash冲突只能尽量避免而不能完全消除。不然链地址法
 *  就完全没有用武之地了。
 *
 *  接下来谈谈table中很重要的一个字段threshold（允许在table中插入的桶个数） ：
 *      threshold  = Load factor负载因子(默认值是0.75)* length;
 *      引入负载因子的目的是用它来衡量一个table到底能装多满，负载因子很大
 *      则说明内存空间被充分利用，由于查找效率和table中的桶个数有关，table
 *      装的越满，则查找效率越低；相反如果负载因子很小则说明一个table中有很多
 *      的空桶，这样会造成内存空间浪费，除非在对内存空间和执行效率把控特别
 *      到位的时候才可以适当调整默认值0.75，否则慎改。
 *
 *  put具体步骤：
 *  1. 判断键值对数组table[i]是否为空或为null，否则执行resize()进行扩容；
 *
 * 2 根据键值key计算hash值得到插入的数组索引i，如果table[i]==null，直接新建节点添加，转向⑥，如果table[i]不为空，转向③；
 *
 * 3 判断table[i]的首个元素是否和key一样，如果相同直接覆盖value，否则转向④，这里的相同指的是hashCode以及equals；
 *
 * 4.判断table[i] 是否为treeNode，即table[i] 是否是红黑树，如果是红黑树，则直接在树中插入键值对，否则转向⑤；
 *
 * 5.遍历table[i]，判断链表长度是否大于8，大于8的话把链表转换为红黑树，在红黑树中执行插入操作，否则进行链表的插入操作；遍历过程中若发现key已经存在直接覆盖value即可；
 *
 * 6..插入成功后，判断实际存在的键值对数量size是否超多了最大容量threshold，如果超过，进行扩容。
 *  JDK1.7扩容：
 *  1    void resize(int newCapacity) {   //传入新的容量
 *  2     Entry[] oldTable = table;    //引用扩容前的Entry数组
 *  3     int oldCapacity = oldTable.length;
 *  4     if (oldCapacity == MAXIMUM_CAPACITY) {  //扩容前的数组大小如果已经达到最大(2^30)了
 *  5         threshold = Integer.MAX_VALUE; //修改阈值为int的最大值(2^31-1)，这样以后就不会扩容了
 *  6         return;
 *  7     }
 *  9     Entry[] newTable = new Entry[newCapacity];  //初始化一个新的Entry数组
 * 10     transfer(newTable);                         //！！将数据转移到新的Entry数组里
 * 11     table = newTable;                           //HashMap的table属性引用新的Entry数组
 * 12     threshold = (int)(newCapacity * loadFactor);//修改阈值
 * 13 }
 *
 * JDK1.8中的扩容优化：首先会创建原table两倍大小的新table，
 *     然后将旧数组中的元素通过再hash插入到新的数组中，与
 *     1.7不同的是在移动旧元素时1.8中会根据元素在旧数组中索引位
 *     判断出该元素在新数组中应该插入的位置而不需要在此hash，因
 *     为新数组的长度是原数组的2倍，所以假如再次进行hash 变化的
 *     只是第三步和length-1做&运算的时候，由于length-1比之前多了
 *     一位，多出来的一位和hashcode高低位异或后对应的那一位要么是0
 *     要么是1 如果是0，那么该元素应该插入到与旧数组索引值相同的位置
 *     如果是1，那么该元素应该插入到在旧数组索引值加上旧数组的总长度
 *     的位置。这样就很好的避免了再次求一次hash值，只需要查看原来的
 *     hash值中异或后多出来的一位时0还是1。
 *
 * 线程不安全解释：简单的说HashMap的线程不安全主要体现在
 *     两个线程同时对一个数组扩容时，最后一个执行线程可能会
 *     在插入元素时会形成环路，导致在环中查找一个不存在的元素
 *     时会进入死循环。
 */
public class HashMapTest {
    static void test(int mapSize) {

        HashMap<Key, Integer> map = new HashMap<Key,Integer>(mapSize);
        for (int i = 0; i < mapSize; ++i) {
            map.put(Keys.of(i), i);
        }

        long beginTime = System.nanoTime(); //获取纳秒
        for (int i = 0; i < mapSize; i++) {
            map.get(Keys.of(i));
        }
        long endTime = System.nanoTime();
        System.out.println(endTime - beginTime);
    }
    public static void main(String[] args) {
        for(int i=10;i<= 10000000;i*= 10){
            test(i);
        }
    }
}