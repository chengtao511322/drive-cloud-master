package com.drive.common.core.utils.sort;

import java.util.Arrays;

/**
 * 冒泡排序 (稳定)
 *
 * xiaoguo
 */
public class BubbleSort implements IArraySort {

    @Override
    public int[] sort(int[] sourceArray) throws Exception {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        for (int i = 1; i < arr.length; i++) {
            // 设定一个标记，若为true，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已经完成。
            boolean flag = true;

            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;

                    flag = false;
                }
            }

            if (flag) {
                break;
            }
        }
        return arr;
    }

    public static void main(String[] args) throws Exception {
        int[] arr = {7,10,2,4,7,1,8,5,19};
        IArraySort arraySort = new  BubbleSort();
        int[] arrList =arraySort.sort(arr);
        for (int i:
                arrList) {
            System.out.println(i);
        }
    }
}