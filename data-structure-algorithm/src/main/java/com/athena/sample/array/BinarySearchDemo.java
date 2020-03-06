package com.athena.sample.array;

/**
 * @author yusheng
 */
public class BinarySearchDemo {

    public static void main(String[] args) {
        int[] data = new int[]{1,2,3,4,4,4,5,7,8,9,9,9};
        /*System.out.println(bsearch(data, 4));
        System.out.println(bsearchFirst(data, 4));
        System.out.println(bsearchLast(data, 4));*/
        System.out.println(bsearchMoreThanFirst(data, 4));
        System.out.println(bsearchLessThanLast(data, 9));
    }

    private static int bsearch(int[] data, int value){
        int low = 0;
        int high = data.length - 1;
        while (low <= high){
            int mid = (low + high) / 2;
            if(data[mid] == value){
                return mid;
            }else if(data[mid] > value){
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }
        return -1;
    }

    private static int bsearchFirst(int[] data, int value){
        int low = 0;
        int high = data.length - 1;
        while (low <= high){
            int mid = (low + high) / 2;
            if(data[mid] == value){
                if(mid == 0 || data[mid - 1] != value) {
                    return mid;
                }else {
                    high = mid - 1;
                }
            }else if(data[mid] > value){
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }
        return -1;
    }

    private static int bsearchLast(int[] data, int value){
        int low = 0;
        int high = data.length - 1;
        while (low <= high){
            int mid = (low + high) / 2;
            if(data[mid] == value){
                if(mid == high || data[mid + 1] != value) {
                    return mid;
                }else {
                    low = mid + 1;
                }
            }else if(data[mid] > value){
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }
        return -1;
    }

    private static int bsearchMoreThanFirst(int[] data, int value){
        int low = 0;
        int high = data.length - 1;
        while (low <= high){
            int mid = (low + high) / 2;
            if(data[mid] >= value){
                if(mid == 0 || data[mid - 1] < value) {
                    return mid;
                }else {
                    high = mid - 1;
                }
            }else {
                low = mid + 1;
            }
        }
        return -1;
    }

    private static int bsearchLessThanLast(int[] data, int value){
        int low = 0;
        int high = data.length - 1;
        int n = data.length - 1;
        while (low <= high){
            int mid = (low + high) / 2;
            if(data[mid] <= value){
                if(mid == high || data[mid + 1] > value) {
                    return mid;
                }else {
                    low = mid + 1;
                }
            }else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
