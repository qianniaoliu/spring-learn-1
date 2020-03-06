package com.athena.sample.array;

/**
 * @author yusheng
 */
public class QuickSortDemo {

    public static void main(String[] args) {
        int[] data = new int[]{3, 2, 5, 4, 6, 7, 45, 4354, 231, 7, 5};
        sort(data, 0, data.length - 1);
        System.out.println(1);
    }

    private static void sort(int[] data, int low, int high) {
        if (low >= high) {
            return;
        }
        int va = partition1(data, low, high);
        sort(data, low, va - 1);
        sort(data, va + 1, high);
    }

    private static int partition1(int[] data, int low, int high) {
        int d = data[high];
        int i = low;
        for(int j = low; j < high; j++){
            if(data[j] < d){
                int tmp = data[i];
                data[i] = data[j];
                data[j] = tmp;
                i++;
            }
        }

        int tmp = data[i];
        data[i] = d;
        data[high] = tmp;
        return i;
    }

    private static int partition(int[] data, int low, int high) {
        int d = data[high];
        int s = high - low;
        int[] d1 = new int[s];
        int[] d2 = new int[s];
        int n1 = 0, n2 = 0;
        for (int i = low; i < high; i++) {
            if (data[i] > d) {
                d2[n2++] = data[i];
            } else {
                d1[n1++] = data[i];
            }
        }
        int k = low;
        int k1 = 0;
        int k2 = 0;
        while (n1 > 0) {
            data[k++] = d1[k1++];
            n1--;
        }

        int res = k;
        data[k] = d;
        k++;
        while (n2 > 0) {
            data[k++] = d2[k2++];
            n2--;
        }
        return res;
    }
}
