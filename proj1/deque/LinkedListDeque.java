package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T> {

    private static class Node<T> {
        public Node<T> prev;
        public T item;
        public Node<T> next;

        public Node(T item, Node<T> prev, Node<T> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node<T> sentinel;
    private int size;

    // 创建空双端队列
    public LinkedListDeque() {
        sentinel = new Node<T>(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        Node<T> newNode = new Node<>(item, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        Node<T> newNode = new Node<>(item, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node<T> current = sentinel.next;
        while (current != sentinel) {
            System.out.print(current.item + " ");
            current = current.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node<T> firstNode = sentinel.next;
        sentinel.next = firstNode.next;
        firstNode.next.prev = sentinel;
        size -= 1;
        return firstNode.item;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node<T> lastNode = sentinel.prev;
        sentinel.prev = lastNode.prev;
        lastNode.prev.next = sentinel;
        size -= 1;
        return lastNode.item;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node<T> current = sentinel.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.item;
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(index, sentinel.next);
    }

    private T getRecursiveHelper(int index, Node<T> node) {
        if (index == 0) {
            return node.item;
        }
        return getRecursiveHelper(index - 1, node.next);
    }

    private class LinkedListDequeIterator implements Iterator<T>{
        private int Pos;
        public LinkedListDequeIterator(){
            Pos = 0;
        }
        public boolean hasNext(){
            return Pos < size;
        }
        public T next(){
            T returnitem = get(Pos);
            Pos += 1;
            return returnitem;
        }
    }
    @Override
    public Iterator<T> iterator(){
        return new LinkedListDeque.LinkedListDequeIterator();
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Deque) || ((Deque<T>) o).size() != this.size){
            return false;
        }
        if(o == this) {
            return true;
        }
        for(int i = 0;i < this.size;i++){
            if(((Deque<T>) o).get(i) != this.get(i)){
                return false;
            }
        }
        return true;
    }
}