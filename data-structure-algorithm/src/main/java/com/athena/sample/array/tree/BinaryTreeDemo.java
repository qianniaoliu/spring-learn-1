package com.athena.sample.array.tree;

public class BinaryTreeDemo {

    static class Node {

        private String data;

        private Node left;

        private Node right;

        public Node(String data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Node tree = builder();
        inOrder(tree);
        System.out.println("B".hashCode());
    }


    private static void preOrder(Node node){
        if(node == null){
            return;
        }
        System.out.println(node.data);
        preOrder(node.left);
        preOrder(node.right);
    }

    private static void inOrder(Node node){
        if(node == null){
            return;
        }
        inOrder(node.left);
        System.out.println(node.data);
        inOrder(node.right);
    }

    private static void postOrder(Node node){
        if(node == null){
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.data);
    }

    private static Node builder(){
        Node tree = new Node("A");
        Node bNode = new Node("B");
        Node cNode = new Node("C");
        Node dNode = new Node("D");
        Node eNode = new Node("E");
        Node fNode = new Node("F");
        Node gNode = new Node("G");

        tree.left = bNode;
        tree.right = cNode;

        bNode.left = dNode;
        bNode.right = eNode;

        cNode.left = fNode;
        cNode.right = gNode;

        return tree;
    }

}
