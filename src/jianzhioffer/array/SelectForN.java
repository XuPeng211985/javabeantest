package jianzhioffer.array;
/**
 * 题目描述： 在一个上下左右都顺序的二维数组中查找一个数  m
 *     思路：从右上角的一个元素开始和 m 作比较，如果 m 大于右上角元素
 *           则搜索数组可以在原数组的基础上去掉第一行，如果  m 小于右
 *           上角元素，则搜索数组可以在原数组的基础上去掉第一列，这样以此类推
 *           不断缩小搜索数组的大小，最终找到 m 所在的位置
 */
public class SelectForN {
    public static void main(String[] args) {
        int[] arr = {1,2,8,9,2,4,8,12,4,7,10,13,6,8,11,15};
        int index = find(arr,4,4,15);
        System.out.println(index);
    }
    public static int find(int[] arr,int lows,int cloumns,int m){
        //判空（常规操作）
        if(arr == null || lows <= 0 || cloumns <= 0){
            return -1;
        }
        //定义两个指针
        int low = 0;
        int cloumn = cloumns -1;
        while(cloumn >= 0 && low <= lows-1){
            if(m == arr[low * cloumns + cloumn]){
                return low * cloumns + cloumn;
            }
            if(m > arr[low * cloumns + cloumn]){
                low ++;
            }else{
                cloumn --;
            }
        }
        return -1;
    }
}
