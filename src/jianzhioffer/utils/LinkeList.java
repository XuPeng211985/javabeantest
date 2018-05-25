package jianzhioffer.utils;

public class LinkeList {
    private Node head;
    public void headInsert(int data){
        Node node = new Node(data);
        if(this.head == null){
           this.head = node;
        }else {
            node.next = head;
            head = node;
        }
    }
    public void print(){
        if(this.head == null){
            System.out.println("null");
            return;
        }
        Node p = this.head;
        while(p != null){
            System.out.print(p.data + "->");
            p = p.next;
        }
        System.out.println("null");
    }
    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public class Node{
        private int data;
        private Node next;
        public Node(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
