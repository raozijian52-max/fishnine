package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T>{
    private Comparator<T> defaultComparator;
    public MaxArrayDeque(Comparator<T> c){
        super();
        this.defaultComparator = c;
    }

    public T max(){
        T Max = this.get(0);
        for(int i = 0;i < size();i++){
            if(defaultComparator.compare(this.get(i),Max) > 0){
                Max = this.get(i);
            }
        }
        return Max;
    }

    public T max(Comparator<T> c){
        T Max = this.get(0);
        for(int i = 0;i < size();i++){
            if(c.compare(this.get(i),Max) > 0){
                Max = this.get(i);
            }
        }
        return Max;
    }


}

