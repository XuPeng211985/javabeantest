package jianzhioffer.array;
/**
 * 题目描述：找出数组中重复的某个元素，1 可以修改数组  2 不可以修改数组
 *          （1 数组长度为 n 元素范围在0---->n-1之间 |->2 1------>n-1之间）
 * 1.思路 由于可以修改数组，假设该数组中的元素没有重复，那么n个数刚好
 *        放满整个数组，进一步如果让数组中的元素从0开始增序排列 那么下标位
 *        和元素值恰好一一对应，如果不一一对应那么必定有重复
 * 2.思路 不可以修改数组内容，就需要换个思路，假如数组中的元素不重复
 *         每个元素只在数组中出现一次，那么每个元素出现次数的和就是
 *         数组的长度，同理将该数组划分为两部分，每部分中元素在总数组中
 *         出现的次数就等于子数组的长度
 */
public class Duplicate {
    public static void main(String[] args) {
        int[] arr = {0,1,2,3,4,5};
        int[] arr1 = {1,2,3,4,6,6,7};
        //静态方法里面可以直接调用静态方法
        int number = Duplicate1(arr);
        System.out.println(number);
        System.out.println("==============第二题================");
        number = Duplication(arr1);
        System.out.println(number);
    }
    public static int Duplicate1(int[] arr){
        //判空
        if(arr == null || arr.length <= 0){
            return -1;
        }
        //检查元素值是否满足要求
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] < 0 || arr[i] > arr.length-1){
                return -1;
            }
        }
        //遍历一遍数组，将每个数值都放在与下标相等的位置
        for (int i = 0; i < arr.length; i++) {
            //如果与下标相等，直接跳过该位置，继续下一个元素
            if(arr[i] == i){
                continue;
            }else{
                //不相等，就要把元素值插在与它相等的下标位置
                //插入之前判断下标位置的值是否已经匹配 如果匹配，则该值是一个重复元素直接输出
                if(arr[arr[i]] == arr[i]){
                    return arr[i];
                }
                //判断不匹配后 交换该元素值和下标为该元素值的位置的元素值
                int temp = arr[i];
                arr[i] = arr[temp];
                arr[temp] = temp;
            }
        }
        return -1;
    }
    public static int Duplication(int[] arr){
        //判空
        if(arr == null || arr.length <= 0){
            return -1;
        }
        //判断元素输入是否规范
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] < 1 || arr[i] > arr.length){
                return -1;
            }
        }
        //定义两个指针，一个指向1 一个指向length-1
        //因为只需要判断该范围内的元素个数
        int start = 1;
        int end = arr.length-1;
        while(start <= end){
            //从中间拆分整个范围 成两个小部分
            int mid = (start + end) >> 1;
            //计算出左半部分的元素在整个数组中出现的次数
            int number = count(arr,start,mid);
            //当两个指针相遇时表示 范围缩小为一个元素，如果在数组中该元素
            //出现的次数为1 那么这个元素没有重复  反之出现次数大于1 那么表示该元素重复了
            if(start == end){
                if(number == 1){
                    return -1;
                }
                return start;
            }
            //每次循环 左半部分的数值范围差如果小于number则说明，重复数字在左边
            //反之在右边
            if(number > (mid - start +1)){
                end = mid;
            }else{
                start = mid + 1;
            }
        }
        return -1;
    }
//记录一定范围内所有数字在数组中出现的总次数
    private static int count(int[] arr, int start, int end) {
        if(arr == null){
            return 0;
        }
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] >= start && arr[i] <= end){
                count++;
            }
        }
        return count;
    }
}
