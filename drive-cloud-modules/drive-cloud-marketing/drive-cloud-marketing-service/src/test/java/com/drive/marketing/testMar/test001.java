package com.drive.marketing.testMar;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class test001 {

    /**
     * 位移
     * @param args
     */
    public static void main(String[] args) {
        HashMap hashMap = new HashMap();
        System.out.println(10 << 2);

        System.out.println(10 >> 2);



        int[] arr = {1,3,5,7,9,11};
        int key = 4;
        //int position = recursionBinarySearch(arr,key,0,arr.length - 1);

        int position = commonBinarySearch(arr, key);

        if(position == -1){
            System.out.println("查找的是"+key+",序列中没有该数！");
        }else{
            System.out.println("查找的是"+key+",找到位置为："+position);
        }

        appClassLoader();

    }

    /**
     * app 类加载器
     */
    public static void appClassLoader() {
        String property = System.getProperty("java.class.path");
        List<String> list = Arrays.asList(property.split(";"));
        list.forEach((t) -> {
            System.out.println("应用类加载器" + t);
        });
    }

    /**
     * 不使用递归的二分查找
     *title:commonBinarySearch
     *@param arr
     *@param key
     *@return 关键字位置
     */
    public static int commonBinarySearch(int[] arr,int key){
        int low = 0;
        int high = arr.length - 1;
        int middle = 0;			//定义middle

        if(key < arr[low] || key > arr[high] || low > high){
            return -1;
        }

        while(low <= high){
            middle = (low + high) / 2;
            if(arr[middle] > key){
                //比关键字大则关键字在左区域
                high = middle - 1;
            }else if(arr[middle] < key){
                //比关键字小则关键字在右区域
                low = middle + 1;
            }else{
                return middle;
            }
        }

        return -1;		//最后仍然没有找到，则返回-1
    }
}