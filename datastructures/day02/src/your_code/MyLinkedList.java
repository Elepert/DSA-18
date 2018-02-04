package your_code;

public class MyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        Chicken val;
        Node prev;
        Node next;

        private Node(Chicken d, Node prev, Node next) {
            this.val = d;
            this.prev = prev;
            this.next = next;
        }

        private Node(Chicken d) {
            this.val = d;
            prev = null;
            next = null;
        }
    }

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(Chicken c) {
        addLast(c);
    }

    public Chicken pop() {
        return removeLast();
    }

    public void addLast(Chicken c) {
        Node newN = new Node(c, null, null);
        if (size == 0) {
            head = newN;
            tail = newN;
        } else {
            Node prev_tail = tail;
            tail = newN;
            tail.prev = prev_tail;
            prev_tail.next = tail;
        }
        size ++;
    }

    public void addFirst(Chicken c) {
        Node newN = new Node(c, null, null);
        if (size == 0) {
            head = newN;
            tail = newN;
        } else {

            newN.next = head;
            newN.next.prev = newN;
            head = newN;

        }
        size ++;
    }

    public Chicken get(int index) {
        int location = 0;
        Node current_node = head;
        while (current_node.val != null){
            if (location == index){
                return current_node.val;
            }
            location ++;
            current_node = current_node.next;
        }
        return null;
    }

    public Chicken remove(int index) {
        Chicken found_chicken = null;
        Node current_node = head;
        int location = 0;
        if (index == 0) {
            found_chicken = removeFirst();
            return found_chicken;
        } else if (index == (size-1)){
            found_chicken = removeLast();
            return found_chicken;
        }
        while (current_node.next != null){
            if (location == index) {
                found_chicken = current_node.val;
                Node prev_node = current_node.prev;
                Node next_node = current_node.next;
                prev_node.next = next_node;
                next_node.prev = prev_node;
                size--;
                return found_chicken;
            }
            current_node = current_node.next;
            location++;
        }
        return null;

    }

    public Chicken removeFirst() {
        Chicken found_chicken = null;
        if (size == 0){
            return null;
        } else if (size == 1) {
            found_chicken = head.val;
            head = null;
            tail = null;
            size--;
            return found_chicken;
        }
        found_chicken = head.val;
        head = head.next;
        head.prev = null;
        size--;
        return found_chicken;
    }

    public Chicken removeLast() {
        Chicken found_chicken = null;
        if (size == 0) {
            return null;
        } else if (size == 1) {
            found_chicken = head.val;
            head = null;
            tail = null;
            size--;
            return found_chicken;
        }
        found_chicken = tail.val;

        tail = tail.prev;
        tail.next = null;
        size--;
        return found_chicken;
    }
}