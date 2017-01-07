package ghn.shopandgo;

import java.util.ArrayList;

/**
 * Created by bukbukbukh on 12/22/16.
 */

public class SuperMarketKart<T> {

    private SupermarketItemNode<T> head;
    private SupermarketItemNode<T> tail;
    private int cap;

    public SuperMarketKart() {
        cap = 0;
        head = null;
        tail = null;
    }

    public void addToKart(T data) {
        SupermarketItemNode<T> newItem = new SupermarketItemNode<T>(data);
        if (isKartEmpty()) {
            head = newItem;
            tail = newItem;
            head.setNext(null);
            head.setPrev(null);
        } else {
            if (cap == 1) {
                head.setNext(newItem);
            }
            tail.setNext(newItem);
            SupermarketItemNode temp = tail;
            tail = tail.getNext();
            tail.setPrev(temp);
        }
        cap = cap + 1;

    }

    public void removeFromKart(int pos) {
        if (!isKartEmpty()) {
            if (pos == 0) {
                head = head.getNext();
                if (head != null) {
                    head.setPrev(null);
                }
                cap--;
                return;
            }
            SupermarketItemNode node = head;
            for (int i = 0; i < cap; i++) {
                if (i == pos) {
                    node.getPrev().setNext(node.getNext());
                    if (node.getNext() != null) {
                        node.getNext().setPrev(node.getPrev());
                    }
                    break;
                }
                node = node.getNext();
            }
            cap--;
        }

    }

    public T getItem(int pos) {
        SupermarketItemNode<T> node = head;
        for (int i = 0; i < pos; i++) {
            if (i >= cap) {
                return null;
            }
            node = node.getNext();
        }
        return node.getItem();
    }

    public ArrayList<T> toArray() {
        ArrayList<T> arr = new ArrayList<>();
        if (cap == 0) {
            return arr;
        }
        SupermarketItemNode<T> node = head;
        for (int i = 0; i < cap; i++) {
            arr.add(node.getItem());
            node = node.getNext();
        }
        return arr;
    }

    public void changeItemQuantity(int pos) {

    }

    public double calculateTotal() {
        return 0.00;
    }


    public boolean isKartEmpty() {
        return cap == 0;
    }

    public SupermarketItemNode<T> getHead() {
        return head;
    }

    public void setHead(SupermarketItemNode head) {
        this.head = head;
    }

    public SupermarketItemNode getTail() {
        return tail;
    }

    public void setTail(SupermarketItemNode tail) {
        this.tail = tail;
    }

    public int getCap() {
        return cap;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }


    public class SupermarketItemNode<E> {
        private E item;
        private SupermarketItemNode next;
        private SupermarketItemNode prev;

        public SupermarketItemNode(E item) {
            this.item = item;
        }

        public E getItem() {
            return item;
        }

        public void setItem(E item) {
            this.item = item;
        }

        public SupermarketItemNode getNext() {
            return next;
        }

        public void setNext(SupermarketItemNode next) {
            this.next = next;
        }

        public SupermarketItemNode getPrev() {
            return prev;
        }

        public void setPrev(SupermarketItemNode prev) {
            this.prev = prev;
        }


    }

}
