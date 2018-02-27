import java.util.LinkedList;

public class Problems {

    private static int getNthDigit(int number, int base, int n) {
        return number / ((int) Math.pow(base, n)) % base;
    }


    /**
     * Use counting sort to sort the integer array according to a digit
     *
     * @param b The base used in radix sort
     * @param n The digit number (where 0 is the least significant digit)
     */
    static void countingSortByDigit(int[] A, int b, int n) {
        LinkedList<Integer>[] L = new LinkedList[b];
        for (int i = 0; i < b; i++)
            L[i] = new LinkedList<>();

        for (int i : A) {
            int intDigit = getNthDigit(i, b, n);
            L[intDigit].addLast(i);

        }


        int j = 0; // index in A to place numbers
        for (LinkedList<Integer> list : L) {
            for (Integer number: list){
                A[j] = number;
                j++;
            }
        }
    }

    /**
     * Runtime: TODO: Express your runtime in terms of n, b, and w
     *
     * n: length of array
     * w: word length of integers A in base b (equal to log base b of k (log_b k) )
     *
     * @param b The base to use for radix sort
     */
    static void radixSort(int[] A, int b) {

    }
    static void sortNumsBetween100s(int[] A) {
        for (int i=0; i<A.length; i++){
            A[i] += 100;
        }
        // Calculate the upper-bound for numbers in A
        int k = A[0] + 1;
        for (int i = 1; i < A.length; i++)
            k = (A[i] + 1 > k) ? A[i] + 1 : k;

        int b = A.length;
        int w = (int) Math.ceil(Math.log(k) / Math.log(b)); // w = log base b of k, word length of numbers
        for (int loop = 0; loop<w; loop++){
            countingSortByDigit(A, b, loop);
        }
        for (int i=0; i<A.length; i++){
            A[i] -= 100;
        }
    }

    /**
     * @param n the character number, 0 is the rightmost character
     * @return
     */
    private static int getNthCharacter(String s, int n) {
        return s.charAt(s.length() - 1 - n) - 'a';
    }


    /**
     * Use counting sort to sort the String array according to a character
     *
     * @param n The digit number (where 0 is the least significant digit)
     */
    static void countingSortByCharacter(String[] A, int n) {
        int b = 26;
        LinkedList<String>[] L = new LinkedList[b];
        for (int i = 0; i < b; i++)
            L[i] = new LinkedList<>();

        for (String i : A) {
            int intDigit = getNthCharacter(i, n);
            L[intDigit].addLast(i);

        }


        int j = 0; // index in A to place numbers
        for (LinkedList<String> list : L) {
            for (String word: list){
                A[j] = word;
                j++;
            }
        }
    }

    /**
     * @param stringLength The length of each of the strings in S
     */
    static void sortStrings(String[] S, int stringLength) {
        int w = stringLength;

        for (int loop = 0; loop<w; loop++){
            countingSortByCharacter(S, loop);
        }
    }

    /**
     * @param A The array to count swaps in
     */

    public static int countSwaps(int[] A) {
        // TODO
        return 0;
    }

}
