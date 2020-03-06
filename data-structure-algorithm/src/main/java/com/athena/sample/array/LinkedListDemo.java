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

        private int head = 0;

        private int tail = 0;


        public LoopLinked(int n) {
            this.n = n;
            this.data = new String[n];
        }

        public boolean enqueue(String s){
            if((tail + 1) % n == head){
                return false;
            }
            data[tail] = s;
            tail = (tail + 1) % n;
            return true;
        }

        public String dequeue(){
            if(head == tail){
                return null;
            }
            String res = data[head];
            head = (head + 1) % n;
            return res;
        }

    }


    public static void main(String[] args) {
        LoopLinked loopLinked = new LoopLinked(3);
        loopLinked.enqueue("a");
        loopLinked.enqueue("b");
        loopLinked.enqueue("c");

        System.out.println(loopLinked.dequeue());
        System.out.println(loopLinked.dequeue());
        System.out.println(loopLinked.dequeue());
    }



}
