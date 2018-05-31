package jianzhioffer.array;
import java.util.Arrays;
/**
 * 快排就不唠叨了，上上下下写了不下十遍 哎 笨鸟*******
 */
public class QuickSortTest {
    public static void main(String[] args) {
       int[] arr = {9,7,3,66,22,1,6};
      arr =  QuickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));

    }
    private static int[] QuickSort(int[] arr,int start,int end){
        if(arr ==  null || arr.length <= 0){
            return null;
        }
        int mid = partition1(arr,start,end);
        if(start < mid) {
            QuickSort(arr, start, mid - 1);
        }
        if(mid > end){
            QuickSort(arr, mid + 1, end);
        }
        return arr;
    }

    private static int partition(int[] arr, int start, int end) {
        int key = arr[start];
        while(start < end){
            while(start < end && arr[end] > key){
                end --;
            }
            int temp = arr[end];
            arr[end] = arr[start];
            arr[start] = temp;
            while(start < end && arr[start] < key){
                start ++;
            }
            temp = arr[end];
            arr[end] = arr[start];
            arr[start] = temp;
        }
        return start;
    }
    public static int partition1(int[] arr,int start,int end){
        /**
         * 好的算法通常都是可以改进的 哈哈
         * key取arr中的随机位 的值；交换位置改为覆盖失效数据
         */
       int key = arr[start];
        while(start < end){
            while(start < end && arr[end] > key){
                end --;
            }
            arr[start] = arr[end];
            while(start < end && arr[start] < key){
                start ++;
            }
            arr[end] = arr[start];
        }
       arr[start] = key;
        return start;
    }
}