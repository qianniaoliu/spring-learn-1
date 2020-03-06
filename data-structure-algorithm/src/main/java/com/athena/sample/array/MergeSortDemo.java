package com.athena.sample.array;

/**
 * @author yusheng
 */
public class MergeSortDemo {

    public static void main(String[] args) {

        int[] data = new int[]{3, 2, 5, 4, 6, 7, 45, 4354, 231};
        sort(data, 0, data.length - 1);
        System.out.println(data);

    }

    private static void sort(int[] data, int low, int high){
        if(low >= high){
            return;
        }
        int mid = (low + high) / 2;
        sort(data, low , mid);
        sort(data, mid + 1, high);
        merge(data, low, mid, high);
    }

    private static void merge(int[] data, int low, int mid, int high) {
        int n1 = mid - low + 1;
        int n2 = high - mid;
        int[] d1 = new int[n1];
        int[] d2 = new int[n2];
        System.arraycopy(data, low, d1, 0, n1);
        System.arraycopy(data, mid +1, d2, 0, n2);

        int k = low;
        int i = 0, j = 0;

        for(; i < n1 && j < n2; k++){
            if(d1[i] <= d2[j]){
                data[k] = d1[i];
                i++;
            }
            else {
                data[k] = d2[j];
                j++;

            }
        }
        while (i < n1){
            data[k] = d1[i];
            i++;
            k++;
        }
        while (j < n2){
            data[k] = d2[j];
            j++;
            k++;
        }
    }
}
