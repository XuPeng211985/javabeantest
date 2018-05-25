package jianzhioffer.list;
import jianzhioffer.utils.LinkeList;
import java.util.Stack;
/**
 *题目描述：从尾到头打印链表
 * 思路1：非递归实现，定义一个栈空间将链表中的每个节点都入栈
 *        根据栈先进后出的特性，逐个抛出所有节点然后输出，就会
 *        得到原链表的反序列
 * 思路2：递归实现，当访问某一个节点时先输出它的下一个节点
 *        输出函数反复调用自己，一直到最后一个节点时由于下一个节点为null
 *        所以最顶层的输出函数输出节点数据并退栈，以此类推当所有的
 *        函数都退栈链表也就刚好输出完毕
 */
public class PrintfList {
    public static void main(String[] args) {
        LinkeList list = new LinkeList();
        list.headInsert(3);
        list.headInsert(4);
        list.headInsert(5);
        list.headInsert(7);
        list.headInsert(6);
        list.print();
        //PrintListReversingly(list.getHead());
        PrintListReversingly1(list.getHead());
    }

    /**
     * 非递归 用栈实现链表的逆序输出
     * @param head
     */
    public static void PrintListReversingly(LinkeList.Node head){
        if(head == null){
            return;
        }
        Stack<LinkeList.Node> stack = new Stack<>();
        LinkeList.Node p = head;
        while(p != null){
            stack.push(p);
            p = p.getNext();
        }
        while(!stack.empty()){
            LinkeList.Node tempNode = stack.pop();
            System.out.print(tempNode.getData() + "->");
        }
        System.out.println("null");
    }

    /**
     * 递归实现，输出一个节点数值的前提是先输出它的下一个节点
     * @param head
     */
    public static void PrintListReversingly1(LinkeList.Node head){
        //只有当遍历到链表的最后一个节点的下一个节点时，最底层函数返回
        //接着最后一个节点执行输出，最后一个节点输出后，以他为头结点
        //的函数执行完成返回，开始前一个节点跳出第二个if循环，开始输出
        //以此类推，直到以头结点为参数的第一层函数退出为止
        if(head == null){
            return;
        }
        if(head.getNext() != null){
            PrintListReversingly1(head.getNext());
        }
        System.out.print(head.getData() + "->");
    }
}
