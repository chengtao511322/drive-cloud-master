/*
package com.drive.test;
import java.util.*;
public class QuickSort {

    */
/**快速排序方法（数组）*//*

    public static void quickSort(int[] a, int lo0, int hi0) {
        int lo = lo0;
        int hi = hi0;

        if (lo >= hi)
            return;

        //确定指针方向的逻辑变量
        boolean transfer=true;

        while (lo != hi) {
            if (a[lo] > a[hi]) {
                //交换数字
                int temp = a[lo];
                a[lo] = a[hi];
                a[hi] = temp;
                //决定下标移动，还是上标移动
                transfer = (transfer == true) ? false : true;
            }

            //将指针向前或者向后移动
            if(transfer)
                hi--;
            else
                lo++;

            //显示每一次指针移动的数组数字的变化
       */
/*for(int i = 0; i < a.length; ++i) {
         System.out.print(a[i] + ",");
       }
       System.out.print(" (lo,hi) = " + "(" + lo + "," + hi + ")");
       System.out.println(""); *//*

        }

        //将数组分开两半，确定每个数字的正确位置
        lo--;
        hi++;
        quickSort(a, lo0, lo);
        quickSort(a, hi, hi0);
    }

    */
/**快速排序方法（列表）*//*

    public static void quickSortByList(List<MrBean> list, int lo0, int hi0) {
        int lo = lo0;
        int hi = hi0;
        if (lo >= hi)
            return;

        //确定指针方向的逻辑变量
        boolean transfer=true;

        while (lo != hi) {
            if (list.get(lo).getTRX_ID() > list.get(hi).getTRX_ID()) {
                //交换
                MrBean temp = list.get(lo);
                list.set(lo, list.get(hi));
                list.set(hi, temp);
                //决定下标移动，还是上标移动
                transfer = (transfer == true) ? false : true;
            }

            //将指针向前或者向后移动
            if(transfer)
                hi--;
            else
                lo++;
        }

        //将数组分开两半，确定每个数字的正确位置
        lo--;
        hi++;
        quickSortByList(list, lo0, lo);
        quickSortByList(list, hi, hi0);
    }

}*/
