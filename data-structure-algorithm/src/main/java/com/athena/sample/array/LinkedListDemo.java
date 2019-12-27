package com.athena.sample.array;

/**
 * @author yusheng
 */
public class LinkedListDemo {

    private static class Node{
        private Node next;

        private String value;

        public Node(String value) {
            this.value = value;
        }
    }

    private static class LoopLinked{
        private String[] data;

        private int n;

        private int count;


        public LoopLinked(int n) {
            this.n = n;
            this.data = new String[n];
        }

        public boolean push(String s){

            return false;
        }

    }



}
