package jianzhioffer.other;

/**
 * 题目描述：求 1+2+....+n
 *
 * 思路一：循环实现，初始值为0的sum 每次累加for循环或者while循环
 *         中不断加1的i值，直到i变为n+1则退出循环
 *
 * 思路二：递归实现，每次累加n时先累加n+1，即重复调用自己
 *         直到n被递归为1 返回结果
 */
public class RecursiveTest {
    public static void main(String[] args) {
     int result =  AddFrom1ToN_RecurSive(100);
     long sum = AddFrom1ToN_Interative(10000000);
        System.out.println(result);
        System.out.println(sum);
    }
    public static long AddFrom1ToN_Interative(int n){
        if(n < 1){
            return -1;
        }
        long sum = 0;
        int i = 1;
        while(i <= n){
            sum = sum + i;
            i++;
        }
        return sum;
    }
    public static int AddFrom1ToN_RecurSive(int n){
        return n == 1 ? 1 : n + AddFrom1ToN_RecurSive(n-1);
    }
}
