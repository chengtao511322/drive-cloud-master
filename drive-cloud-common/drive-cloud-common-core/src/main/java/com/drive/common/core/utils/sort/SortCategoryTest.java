package com.drive.common.core.utils.sort;

public class SortCategoryTest {

    public static void main(String[] args) throws Exception {
        int[] arr = {7,10,2,4,7,1,8,5,19};
        IArraySort arraySort = new InsertSort();
        int[] arrList =arraySort.sort(arr);
        for (int i:
                arrList) {
            System.out.println(i);
        }
    }
}
