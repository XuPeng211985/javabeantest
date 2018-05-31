package listsetmap;
/**
 * LinkedHashMap的相关介绍：
 *  //LinkedHashMap继承了HashMap实现了Map接口，在HashMap的基础上，根据插入的
 *    Entry对象的先后顺序维持了一个双向链表，Entry<K,V>在HashMap.Node
 *    的基础上添加了连个节点属性，分别用来表示当前链表节点在双向链表的
 *    前一个节点和后一个节点。
 *    LinkedHashMap作为子类并没有重写HashMap中的put方法，只不过
 *    在new每一个新的Entry时维持了一个有头有尾的双向链表，也是很好理解
 *    每次插入一个节点前就将它插入到了双向链表中。LinkedHashMap的特点
 *    已经凸现出来了，剩下的工作就和HashMap一样了；LinkedHashMap增添了
 *    一个方法，在插入节点后判断是否需要删除旧的节点（head或很久没访问过的节点）
 * public class LinkedHashMap<K,V> extends HashMap<K,V> implements Map<K,V>
 ********************************************************************
 * static class Entry<K,V> extends HashMap.Node<K,V> {
 *         Entry<K,V> before, after;
 *         Entry(int hash, K key, V value, Node<K,V> next) {
 *             super(hash, key, value, next);
 *         }
 * }
 * *****************************************************************
 *  //每次new一个新的节点时，都将该节点采用尾插法插入到双向链表中
 *  //这样在查找时，从头指针开始遍历一直到尾节点，就恰好是所有节点
 *  //的插入顺序
 * Node<K,V> newNode(int hash, K key, V value, Node<K,V> e) {
 *         LinkedHashMap.Entry<K,V> p =
 *             new LinkedHashMap.Entry<K,V>(hash, key, value, e);
 *         linkNodeLast(p);
 *         return p;
 *     }
 *  **************************************************************
 *   尾插法插入新节点
 *  private void linkNodeLast(LinkedHashMap.Entry<K,V> p) {
 *         LinkedHashMap.Entry<K,V> last = tail;
 *         tail = p;
 *         if (last == null)
 *             head = p;
 *         else {
 *             p.before = last;
 *             last.after = p;
 *         }
 * }
 * ******************************************************************
 *  //链表头
 *  transient LinkedHashMap.Entry<K,V> head;
 * //链表尾
 *  transient LinkedHashMap.Entry<K,V> tail;
 *  //迭代顺序， true 在table中查找， false为插入顺序
 *  final boolean accessOrder;
 *  *****************************************************************
 *
 */
public class LinkedHashMapTest {
    public static void main(String[] args) {

    }
}
