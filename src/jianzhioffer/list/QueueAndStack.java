package jianzhioffer.list;

import java.util.Stack;

/**
 * 问题描述：用两个栈实现一个队列的两个方法： 队尾添加和队头删除
 *
 *思路：由于栈的特性是先进后出，所以要想做到先进先出，只能在一个栈中
 *      执行添加操作，在删除时把添加进栈的元素都弹出到另一个栈中
 *      这样最先入栈的元素就被放在了第二个栈的栈顶，抛出第二个栈
 *      顶的元素，自然就实现了先进先出的队列特性。
 */
public class QueueAndStack {
    private  Stack<Integer> stack1 = new Stack<>();
    private Stack<Integer> stack2 = new Stack<>();
    public static void main(String[] args) {
        QueueAndStack queueAndStack = new QueueAndStack();
        queueAndStack.addTail(5);
        queueAndStack.addTail(3);
        queueAndStack.addTail(4);
        queueAndStack.deleteHead();
        queueAndStack.addTail(2);
        queueAndStack.deleteHead();
        queueAndStack.deleteHead();
    }
    public  void addTail(int element){
        stack1.push(element);
    }
    public Integer deleteHead(){
        if(stack2.size() <= 0){
            while(stack1.size() > 0){
                stack2.push(stack1.peek());
                stack1.pop();
            }
        }
        if(stack2.size() == 0){
            return -1;
        }
        Integer temp = stack2.pop();
        return temp;
    }


}
