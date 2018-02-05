import java.util.*;

public class Problems {

    public static class Node {
        int val;
        Node next;

        Node(int d) {
            this.val = d;
            next = null;
        }
    }

    public static List<Integer> removeKDigits(int[] A, int k) {
        List<Integer> l = new LinkedList<>();
        if (A == null) {
            return l;
        }
        while (k > 0) {
            if (A.length == k){
                return l;
            }
            int min_value = 10;
            int min_index = 0;
            for (int i = 0; i<k+1; i++){
                if (A[i] < min_value){
                    min_value = A[i];
                    min_index = i;
                }
            }
            l.add(min_value);
            if (min_index != 0) {
                k = k-min_index;
            }
            A = Arrays.copyOfRange(A, min_index+1, A.length);

        }
        for (int i = 0; i < A.length; i++) {
            l.add(A[i]);
        }
        return l;
    }

    public static boolean isPalindrome(Node n) {
        if (n == null) {
            return true;
        }
        Node heads = n;
        Node headb = new Node(heads.val);
        while (heads.next != null){
            Node temp = headb;
            headb = new Node(heads.next.val);

            headb.next = temp;

            heads = heads.next;

        }
        heads = n;
        while (heads.next != null) {
            if (heads.val != headb.val) {
                return false;
            }
            heads = heads.next;
            headb = headb.next;
        }
        return true;
    }

    public static String infixToPostfix(String s) {
        Stack<Character> symbols = new Stack<Character>();
        char[] operation = s.toCharArray();
        String output = "";
        for (int i=0; i< operation.length; i++) {
            if (operation[i] == '(' || operation[i] == '*' || operation[i] == '+' || operation[i] == '-' || operation[i] == '/'){
                symbols.push(operation[i]);
            } else if (operation[i] == ')'){
                char operator = operation[i];

                operator = symbols.pop();

                output += operator;
                if (i < operation.length-1) {
                    output += ' ';
                }
                symbols.pop();
            } else if (operation[i] != ' '){
                output += operation[i];
                output += ' ';
            }
        }
        return output;
    }

}
