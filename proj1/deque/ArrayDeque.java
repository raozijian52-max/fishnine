package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>{
    private T[] Deque ;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque(){
        Deque = (T[]) new Object[8];
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }

    public void resize(int Dequesize){
        T[] newDeque = (T[]) new Object[Dequesize];

        for (int i = 0; i < size; i++) {
            int oldIndex = ((nextFirst + 1) % Deque.length + i) % Deque.length;
            newDeque[i] = Deque[oldIndex];
        }

        Deque = newDeque;
        nextFirst = Dequesize - 1;
        nextLast = size;
    }

    @Override
    public void addFirst(T item){
        if(size == Deque.length){
            resize(Deque.length * 2);
        }
        Deque[nextFirst] = item;
        nextFirst = (nextFirst - 1 + Deque.length) % Deque.length;
        size++;
    }

    @Override
    public void addLast(T item){
        if(size == Deque.length){
            resize(Deque.length * 2);
        }
        Deque[nextLast] = item;
        nextLast = (nextLast + 1) % Deque.length;
        size++;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public T get(int index){
        if (index < 0 || index >= size) {
            return null;
        }
        int realindex = ((nextFirst + 1) % Deque.length + index) % Deque.length;
        return Deque[realindex];
    }

    @Override
    public void printDeque(){
        for(int i = 0;i < size;i++){
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst(){
        if (isEmpty()) {
            return null;
        }
        int firstIndex = (nextFirst + 1) % Deque.length;
        T item = Deque[firstIndex];
        Deque[firstIndex] = null;
        nextFirst = firstIndex;
        size--;
        if(size < Deque.length / 4){
            resize(Deque.length / 2);
        }
        return item;
    }

    @Override
    public T removeLast(){
        if (isEmpty()) {
            return null;
        }
        int LastIndex = (nextLast - 1 + Deque.length) % Deque.length;
        T item = Deque[LastIndex];
        Deque[LastIndex] = null;
        nextLast = LastIndex;
        size--;
        if(size < Deque.length / 4){
            resize(Deque.length / 2);
        }
        return item;
    }
    private class ArrayDequeIterator implements Iterator<T>{
        private int Pos;
        public ArrayDequeIterator(){
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
        return new ArrayDequeIterator();
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