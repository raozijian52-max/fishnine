package deque;

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
        Deque = (T[]) new Object[Dequesize];
        nextFirst = Dequesize/2 - 1;
        nextLast = Dequesize/2;
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
}
