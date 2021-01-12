//package com.athena.sample.test;
//
//import org.junit.Test;
//
///**
// * @author yusheng
// */
//public class QuickSortTest {
//
//
//    @Test
//    public void quickSort(){
////        int[] arr = new int[]{1, 4, 8, 2, 55, 3, 4, 8, 6, 4, 0, 11, 34, 90, 23, 54, 77, 9, 2, 9, 4, 10};
//        int[] arr = new int[]{19, 15, 30,34,22,9,56};
//        qSort(arr, 0, arr.length - 1);
//        String out = "";
//        for (int digit : arr) {
//            out += (digit + ",");
//        }
//        System.out.println(out);
//    }
//
//    public void qSort(int[] arr, int head, int tail) {
//        if (head >= tail || arr == null || arr.length <= 1) {
//            return;
//        }
//        int i = head, j = tail, pivot = arr[(head + tail) / 2];
//        while (i <= j) {
//            while (arr[i] < pivot) {
//                ++i;
//            }
//            while (arr[j] > pivot) {
//                --j;
//            }
//            if (i < j) {
//                int t = arr[i];
//                arr[i] = arr[j];
//                arr[j] = t;
//                ++i;
//                --j;
//            } else if (i == j) {
//                ++i;
//            }
//        }
//        qSort(arr, head, j);
//        qSort(arr, i, tail);
//    }
//}
