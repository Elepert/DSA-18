import java.util.Arrays;

public class MyArrayList {
    private Cow[] elems;

    private int size;

    // Runtime: O(1)
    public MyArrayList() {
        size = 0;
        elems = new Cow[10];
    }

    // Runtime: O(1)
    public MyArrayList(int capacity) {
        size = 0;
        elems = new Cow[capacity];
    }

    // Runtime: O(1)*
    public void add(Cow c) {
        if (size == elems.length) {
            grow();
        }
        elems[size] = c;

        size++;

    }

    // Runtime: O(1)
    public int size() {
        return size;
    }

    // Runtime: O(1)
    public Cow get(int index) {
        if (index <= size && index >= 0){
            Cow searchCow = elems[index];
            return searchCow;
        } else {
            throw new IndexOutOfBoundsException("IndexOutOfBoundsException");
        }

    }

    // Runtime: O(n)
    public Cow remove(int index) {
        if (index <= size && index >= 0) {
            Cow removedCow = elems[index];
            for (int i = index; i < size-1; i++) {
                elems[i] = elems[i + 1];
            }
            elems[size-1]= null;
            size--;

            if (size <= 0.25*elems.length && size != 1){
                Cow[] tempelems = new Cow[elems.length/2];
                System.arraycopy(elems, 0, tempelems, 0, elems.length/2);
                elems = tempelems;
            }
            return removedCow;
        }  else {
            throw new IndexOutOfBoundsException("Index Out Of Bounds Exception");
        }
    }

    // Runtime: O(n)
    public void add(int index, Cow c) {
        if (index <= size && index >= 0) {
            if (size == elems.length){
                grow();
            }
            for (int i = size-1; i > index; i--) {
                elems[i] = elems[i - 1];
            }
            elems[index] = c;

            size++;
        }  else {
            throw new IndexOutOfBoundsException("Index Out Of Bounds Exception");
        }
    }

    // Runtime: O(n)
    public void grow(){
        Cow[] tempelems = new Cow[elems.length*2];
        System.arraycopy(elems, 0, tempelems, 0, elems.length);
        elems = tempelems;
    }
}
