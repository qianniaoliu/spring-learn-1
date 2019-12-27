package com.athena.sample.array;

import sun.misc.Unsafe;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yusheng
 */
public class ArrayStackDemo {

    private static class ArrayStack {
        private int[] data;

        private int count;

        private int size;

        public ArrayStack(int size) {
            this.size = size;
            data = new int[size];
        }

        public boolean push(int d) {
            if (this.count == this.size) {
                return false;
            }
            data[count] = d;
            count++;
            return true;
        }

        public int pop() {
            if (this.count == 0) {
                return -1;
            }
            int result = data[--count];
            return result;
        }
    }

    private static class Node {

        public int value;

        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    private static class LinkedStack {
        private Node head;

        private Node preTail;

        private Node tail;


        private static VarHandle headOffset;
        private static VarHandle tailOffset;
        private static VarHandle nextOffset;

        private final ReentrantLock pushLock = new ReentrantLock();

        public LinkedStack() {
//            head = tail = new Node(-1);
        }

        static {
            try {
                MethodHandles.Lookup lookup = MethodHandles.lookup();
                headOffset = lookup.findVarHandle(LinkedStack.class, "head", Node.class);
                tailOffset = lookup.findVarHandle(LinkedStack.class, "tail", Node.class);
                nextOffset = lookup.findVarHandle(Node.class, "next", Node.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private final boolean compareAndSetHead(Node update) {
            return headOffset.compareAndSet(this, null, update);
        }

        private final boolean compareAndSetTail(Node expected, Node update) {
            return tailOffset.compareAndSet(this, expected, update);
        }

        private final boolean compareAndSetNext(Node expected, Node update) {
            return nextOffset.compareAndSet(this, expected, update);
        }


        public boolean push(int a) {
            Node current = new Node(a);
            if (head == null) {
                if (compareAndSetHead(current)) {
                    tail = current;
                    return true;
                }
            } else {
                Node oldTail = this.tail;
                if(compareAndSetTail(oldTail, current)){
                    preTail = oldTail;
                    oldTail.next = current;
                    return true;
                }
            }
            return false;
        }

        public int pop(){
            Node res = this.head;
            if(res == null){
                return -1;
            }
            if(head == tail){
                head = tail = null;
                return res.value;
            }

            Node next = res.next;
            this.head = next;
            return res.value;

        }
    }

    public static void main(String[] args) {
        LinkedStack linkedStack = new LinkedStack();
        linkedStack.push(1);
        linkedStack.push(2);
        linkedStack.push(3);
        linkedStack.push(4);
        linkedStack.push(5);
        linkedStack.push(6);
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.pop());

    }

}
