package jianzhioffer.list;
import java.util.LinkedList;
import java.util.Queue;
/**
 * 问题描述：用两个队列实现一个先进后出的栈
 *
 * 思路：由于队列先进先出的特性，要想实现栈的先进后出
 *      就必须将存在元素的队列依次出队到第二个队列中
 *      直到遇到最后一个元素则直接删除即可，这样就做
 *      到了栈的先进后出
 */
public class StackAndQueue {
    private Queue<Integer> queue1 = new LinkedList<>();
    private Queue<Integer> queue2 = new LinkedList<>();
    public static void main(String[] args) {
        StackAndQueue stackAndQueue = new StackAndQueue();
        stackAndQueue.push(3);
        stackAndQueue.push(6);
        stackAndQueue.push(5);
        stackAndQueue.print();
        stackAndQueue.pop();
        stackAndQueue.pop();
        stackAndQueue.push(1);
        stackAndQueue.print();
    }
    /**
     * 每次插入时都会向非空的队列直接插入
     * 因为弹出栈的时候一定是将一个队列中的非尾元素移动到空队列中
     * 然后弹出队尾元素
     * @param temp
     */
    public void push(int temp){
        if(temp == -1){
            return;
        }
        if(queue1.size() == 0){
            queue2.offer(temp);
            return;
        }
        if(queue2.size() == 0){
            queue1.offer(temp);
            return;
        }
    }
    /**
     * 弹出栈前，一个队列肯定是空的 然后不断移动非空队列里的元素直到剩下
     * 一个元素，然后弹出，直接返回弹出元素的数值
     * @return
     */
    public Integer pop(){
        Integer temp = -1;
        if(queue1.size() == 0){
            while(queue2.size() != 1){
               queue1.offer(queue2.remove());
            }
            temp = queue2.remove();
            return temp;
        }
        if(queue2.size() == 0){
            while(queue1.size() != 1){
                queue2.offer(queue1.remove());
            }
            temp = queue1.remove();
        }
        return temp;
    }
    public void print(){
        if(queue2.size() == 0){
            System.out.println(queue1.toString());
            return ;
        }
        if(queue1.size() == 0){
            System.out.println(queue2.toString());
        }
    }
}
