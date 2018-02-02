package your_code;
import ADTs.StackADT;

import java.util.LinkedList;

/**
 * An implementation of the Stack interface.
 */
public class MyStack implements StackADT<Integer> {

    private LinkedList<Integer> ll;
    private LinkedList<Integer> maxes;

    public MyStack() {
        maxes = new LinkedList<>();
        ll = new LinkedList<>();
    }

    @Override
    public void push(Integer e) {
        ll.addFirst(e);
        if (maxes.isEmpty() == true || e > peekm()) {
            maxes.addFirst(e);
        }
    }

    @Override
    public Integer pop() {
        Integer pop = ll.removeFirst();
        if (pop == peekm()){
            pop = maxes.removeFirst();
        }
        return pop;
    }

    @Override
    public boolean isEmpty() {
        return ll.isEmpty();
    }

    @Override
    public Integer peek() {
        return ll.getFirst();
    }

    public Integer peekm() {
        return maxes.getFirst();
    }

    public Integer maxElement() {
        Integer maxE = peekm();
        return maxE;
    }
}
