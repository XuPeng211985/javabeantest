package jianzhioffer.array;

import java.util.Random;

/**
 * 题目描述 ：为一个公司的员工年龄排序
 * 思路： 题目中的数据是在一定范围内的，并且范围不是特别大
 *        所以可以拿一个辅助数组，记录每个年龄的出现个数
 *        然后按个年龄排序。
 */
public class SortAgeTest {
    public static void main(String[] args) {
        Random r
        int[] arr =  new int[100000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] =
        }
    }
    public static int[] sortAge(int[] arr){
        int[] tempNumber = new int[101];
        for (int i = 0; i < arr.length; i++) {
            int age = arr[i];
            if(age < 0 || age > 100){
                System.out.println("输入年龄中有不合法数字");
            }
            tempNumber[age] ++;
        }
        int j = 0;
        for (int i = 0; i < tempNumber.length; i++) {
            while(tempNumber[i] > 0){
                arr[j] = i;
                j++;
            }
        }
        return arr;
    }
}
