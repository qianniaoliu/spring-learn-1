package com.athena.sample.array;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author yusheng
 */
public class HeapDemo {

    public static void main(String[] args) {
        Heap heap = new Heap(256);
        heap.insert(1);
        heap.insert(5);
        heap.insert(9);
        heap.insert(55);
        Arrays.stream(heap.data).reduce((i, j) ->{
            int sum = i + j;
            return sum;
        } ).ifPresent(System.out::println);



    }


    private static class Heap {
        private int[] data;

        private int capacity;

        private int count;

        public Heap(int capacity) {
            this.capacity = capacity;
            count = 0;
            data = new int[capacity + 1];
        }

        public void insert(int d) {
            if (count >= capacity) {
                return;
            }
            data[++count] = d;
            int parentIndex = count / 2;
            int currentIndex = count;
            while (true) {
                if (parentIndex == 0) {
                    break;
                }
                if (d > data[parentIndex]) {
                    swap(data, currentIndex, parentIndex);
                    currentIndex = parentIndex;
                    parentIndex = currentIndex / 2;
                } else {
                    break;
                }
            }
        }

        public int removeMax(){
            if(count == 0){
                return -1;
            }
            int result = data[1];
            data[1] = data[count];
            data[count] = 0;
            heapify(data, count, 1);
            count--;
            return result;
        }

        /**
         * 从 i 的位置开始向下堆化
         *
         * @param data 数据
         * @param n    总共数据个数
         * @param i    当前向下堆化位置
         */
        private void heapify(int[] data, int n, int i) {
            while (true) {
                if (i >= n) {
                    break;
                }
                if (2 * i <= n && data[i] < data[2 * i]) {
                    swap(data, i, 2 * i);
                    i = 2 * i;
                } else if (2 * i + 1 <= n && data[i] < data[2 * i + 1]) {
                    swap(data, i, 2 * i + 1);
                    i = 2 * i + 1;
                }else {
                    break;
                }
            }
        }

        private void swap(int[] data, int currentIndex, int parentIndex) {
            int tmp = data[currentIndex];
            data[currentIndex] = data[parentIndex];
            data[parentIndex] = tmp;
        }

        @Override
        public String toString() {
            return "Heap{" +
                    "data=" + Arrays.toString(data) +
                    '}';
        }
    }

}
