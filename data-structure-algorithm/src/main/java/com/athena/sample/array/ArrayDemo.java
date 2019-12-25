package com.athena.sample.array;

/**
 * @author yusheng
 */
public class ArrayDemo {

    private static class Node{
        public String value;

        public Node next;
    }

    public static void main(String[] args) {
        Node head = new Node();
        Node node2 = new Node();
        Node node3 = new Node();
        Node node4 = new Node();
        Node node5 = new Node();
        Node tail = new Node();
        head.value = "11";
        head.next = node2;

        node2.value = "22";
        node2.next = node3;

        node3.value = "335";
        node3.next = node5;

        node4.value = "33";
        node4.next = node5;

        node5.value = "22";
        node5.next = tail;

        tail.value = "11";


        System.out.println(isPalindrome(head));


    }

    public static boolean isPalindrome(Node head){
        Node fastNode = head;
        Node slowNode = head;
        while (fastNode.next != null && fastNode.next.next != null){

            fastNode = fastNode.next.next;
            slowNode = slowNode.next;

        }
        Node reverseNode = reverseNode(slowNode);

        while (head != null && reverseNode != null){
            if(!head.value.equals(reverseNode.value)){
                return false;
            }
            head = head.next;
            reverseNode = reverseNode.next;
        }
        return true;
    }

    /**
     * 单项链表反转
     * @param node
     * @return
     */
    public static Node reverseNode(Node node){
        Node p = null;
        Node n;
        while (node != null){
            n = node.next;
            node.next = p;

            p = node;
            node = n;
        }
        return p;
    }

    /**
     * 链表中环的检测
     * @param head
     * @return
     */
    public static boolean examineLoop(Node head){
        Node fastNode = head;
        Node slowNode = head;
        while (fastNode.next != null && fastNode.next.next != null){
            fastNode = fastNode.next.next;
            slowNode = slowNode.next;

            if(fastNode == slowNode){
                return true;
            }
        }
        return false;
    }
}
