package com.example.demo.agorithm;

public class LinkCircle {


    public static void main(String[] args) {
        Node c = new Node();
//        Node f = new Node("f",null);
        Node f = new Node("f",c);
        Node e = new Node("e",f);
        Node d = new Node("d",e);
         c.setNext(d);
         c.setValue("c");
        Node b = new Node("b",c);
        Node a = new Node("a.json",b);

//        System.out.println(f.next.value);

        System.out.println(findCircleNode(a));
    }

    public static boolean findCircleNode(Node node){
        if (node == null) {
            return false;
        }
        if (node.next == null) {
            return false;
        }

        Node slow = node.next;
        Node fast = node.next.next;

        while (slow != fast && fast != null) {
            slow = slow.next;
            fast = fast.next != null ?  fast.next.next : null;
            if (slow == fast) {
                return true;
            }
        }

        return false;
    }

    private static class Node{
        private String value;
        private Node next;

        public Node() {
        }

        public Node(String value, Node next) {
            this.value = value;
            this.next = next;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }


    }

}
