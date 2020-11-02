package com.athena.sample.array.tree;

import java.util.Objects;

public class BinarySearchTree {

    private Node tree;

    public BinarySearchTree(Node tree) {
        this.tree = tree;
    }

    public BinarySearchTree(){}

    static class Node {

        private String data;

        private Node left;

        private Node right;

        private int hash;

        public Node(String data) {
            this.data = data;
            this.hash = data.hashCode();
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data='" + data + '\'' +
                    ", hash=" + hash +
                    '}';
        }
    }

    public Node find(String data){
        if(Objects.isNull(data) || Objects.isNull(tree)){
            return null;
        }
        int dataHash = data.hashCode();
        Node r = tree;
        while (r != null && dataHash != r.hash)
        if(dataHash > r.hash){
            r = r.right;
        }else {
            r = r.left;
        }
        return r;
    }

    public void insert(String data){
        if(Objects.isNull(data)){
            return ;
        }
        if(Objects.isNull(tree)){
            tree = new Node(data);
            return;
        }
        Node currentNode = new Node(data);
        int dataHash = currentNode.hash;
        Node r = tree;
        Node p = null;
        while (r != null){
            p = r;
            if(dataHash < r.hash){
                r = r.left;
            }else {
                r = r.right;
            }
        }
        if(dataHash < p.hash){
            p.left = currentNode;
        }else {
            p.right = currentNode;
        }
    }

    public String delete(String data){
        if(Objects.isNull(data) || Objects.isNull(tree)){
            return null;
        }
        int dataHash = data.hashCode();
        if(dataHash == tree.hash){
            tree = null;
            return data;
        }
        Node r = tree;
        Node p = null;
        while (r != null){
            p = r;
            if(r.hash == dataHash){
                break;
            }else if(dataHash > r.hash){
                r = r.right;
            }else {
                r = r.left;
            }
        }
        if(r == null){
            return null;
        }
        if(r.left != null && r.right != null){
            Node rMin = r.right;
            Node prMin = r;
            while (rMin.left != null){
                prMin = rMin;
                rMin = rMin.left;
            }
            r.data = rMin.data;
            r.hash = rMin.hash;
            r = rMin;
            p = prMin;
        }
        Node child;
        if(r.left != null){
            child = r.left;
        }else if(r.right != null){
            child = r.right;
        }else {
            child = null;
        }
        if(r.hash > p.hash){
            p.right = child;
        }else {
            p.left = child;
        }
        return data;
    }


    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.insert("R");
        binarySearchTree.insert("Z");
        binarySearchTree.insert("S");
        binarySearchTree.insert("H");
        binarySearchTree.insert("L");
        binarySearchTree.insert("I");
        binarySearchTree.insert("O");
        binarySearchTree.insert("B");
        binarySearchTree.insert("C");
        binarySearchTree.insert("P");
        binarySearchTree.insert("E");
        binarySearchTree.insert("F");
        binarySearchTree.insert("G");

        System.out.println(binarySearchTree.find("B"));
    }



}
