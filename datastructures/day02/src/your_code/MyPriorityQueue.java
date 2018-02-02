package your_code;

import java.util.LinkedList;

/**
 * An implementation of a priority Queue
 */
public class MyPriorityQueue {

    private LinkedList<Integer> ll;
    private LinkedList<Integer> maxes;

    public MyPriorityQueue() {
        ll = new LinkedList<>();
        maxes = new LinkedList<>();
    }

    public Integer dequeue() {
        Integer pop = ll.removeFirst();
        if (pop == maxes.getFirst()){
            pop = maxes.removeFirst();
        }
        return pop;
    }

    public boolean isEmpty() {
        return ll.isEmpty();
    }

    public Integer front() {
        return ll.getFirst();
    }

    public void enqueue(int item) {
        if (ll.isEmpty()){
            ll.add(item);
        } else {
            for (int i = 0; i < ll.size(); i++) {
                Integer currentmax = ll.get(i);
                if (item > currentmax) {
                    ll.add(i, item);
                    i = ll.size();
                }

            }
        }
    }

    /**
     * Return and remove the largest item on the queue.
     */
    public int dequeueMax() {
        if (ll.isEmpty() == false) {
            Integer maxi = ll.removeFirst();
            return maxi;
        }
        return -1;
    }

}