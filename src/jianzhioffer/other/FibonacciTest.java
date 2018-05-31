package jianzhioffer.other;
/**
 * 题目描述：求斐波那契数列的第n项
 *    思路一：递归实现，第一项默认为0 第二项默认为1 依次前两项相加
 *            变为第三项，假如n=0 模拟出递归调用树；由于每个函数体
 *            里面会调用自身两次，所以递归调用树为二叉树
 *                         fun(10)
 *                          /
 *                     fun(9)
 *                       /
 *                  fun(8)
 *                    /
 *               fun(7)
 *               /
 *            fun(6)
 *            /
 *         fun(5)
 *         /
 *       fun(4)
 *       /
 *    fun(3)
 *    /      画了第一个分支就画不下去了   正如你所想 它是一个枝繁叶茂的二叉树
 * fun(2)    并且有很多叶子是一模一样的 做了很多的重复工作
 * /
 *    fun(1) + fun(0)
 *
 *    思路二：用循环实现，假如n = 0 输出0  n = 1 则直接输出1
 *           当n > 1 时需要循环遍历,定义两个临时变量 表示当前
 *           所求位置的值的前一位和前一位的前一位的值，每次
 *           循环 更新前一位的值为下一位的值，下一位的值为前两位
 *           值的和 n --；当n==3 是退出循环
 *
 *       青蛙跳台阶问题  小矩形覆盖大矩形的方式 都是斐波那契数列的变形
 */
public class FibonacciTest {
    public static void main(String[] args) {
      long temp =  fibonacciRecursive(10);
      long temp1 = fibonacciUnRecursive(10);
        System.out.println(temp);
        System.out.println(temp1);
    }
    public static long fibonacciRecursive(int n){
        if(n <= 0){
            return 0;
        }
        if(n == 1){
            return 1;
        }
        return fibonacciRecursive(n-1) + fibonacciRecursive(n-2);
    }

    public static long fibonacciUnRecursive(int n){
        if(n <= 0){
            return 0;
        }
        System.out.print(0 + "-->");
        System.out.print(1 + "-->");
        int numberOne = 0;
        int numberTwo = 1;
        int result = 0;
        while(n > 2){
            result = numberOne + numberTwo;
            if(n != 3) {
                System.out.print(result + "-->");
            }else{
                System.out.print(result);
            }
            numberOne = numberTwo;
            numberTwo = result;
            n--;
        }
        System.out.println();
        return result;
    }
}
