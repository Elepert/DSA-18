import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TextJustification {

    private static double cost(String[] words, int lo, int hi, int m) {
        if (hi <= lo)
            throw new IllegalArgumentException("Hi must be higher than Lo");
        int length = hi-lo-1; // account for spaces;
        for (int i = lo; i < hi; i++) {
            //System.out.println(words[i]);
            length += words[i].length();
        }
        if (length > m)
            return Double.POSITIVE_INFINITY;
        return Math.pow(m-length, 3);
    }

    public static int arrayMin(double[] arr1) {
        int i = 0;
        int min_Index = 0;
        double min = Integer.MAX_VALUE;
        if (arr1 == null) {
            return 0; // What if 0 is the minimum value? What do you want to do in this case?
        } else {
            while (i < arr1.length) {
                if (arr1[i] < min) {
                    min = arr1[i];
                    min_Index = i;
                }
                i++;
            }
        }
        return min_Index;
    }

    // A utility function to print the solution
    public static int printSolution (int[] p, int n)
    {
        int k;
        if (p[n] == 0)
            k = 0;
        else
            k = printSolution (p, p[n]-1) + 1;
        System.out.println("Line number" + " " + k + ": " +
                "From word no." +" "+ p[n] + " " + "to" + " " + n);
        return k;
    }



    public static List<Integer> justifyTextBU(String[] w, int m) {
        //justifyString(w,m);
        // this is really bottom up
        System.out.println(Arrays.toString(w));
        System.out.println(m);
        double[] DP= new double[w.length];
        int[] nextBreak = new int[w.length]; //ArrayList<>();
        nextBreak[0]=0;
        DP[0] = cost(w, 0,1,m);
        int last_In;
        int n = w.length;
        for (int i = 0; i < n; i++)
        {
            System.out.println(w[i]);
            //DP[j] = Double.POSITIVE_INFINITY;
            double[] tempMin = new double[i];
            double temMin = Double.POSITIVE_INFINITY;
            int temIn = 0;
            for (int j = 0; j <= i; j++)
            {
                double costT = cost(w, j, i + 1, m);
                if (j==0){
                    if (costT<temMin){
                        temMin = costT;
                        temIn = i;
                    }
                } else {
                    costT += DP[j-1];
                    if (costT<temMin){
                        temMin = costT;
                        temIn = i;
                    }
                }

                /*if (DP[i-1] != Integer.MAX_VALUE && cost(w, i, j+1, m) != Double.POSITIVE_INFINITY && ((DP[i-1] + cost(w, i, j+1, m)) < DP[j]))
                {
                    DP[j] = DP[i-1] + cost(w, i, j+1, m);
                    if (nextBreak.size() > j) {
                        nextBreak.set(j, i);
                    } else {
                        nextBreak.add(i);
                    }
                }*/
            }
            if (i>1 && (nextBreak[i-1] != nextBreak[i-2])) {
                nextBreak[i] = temIn;
            } else if (i == 1){
                nextBreak[i] = nextBreak[i-1];
            } else {
                nextBreak[i] = 0;
            }
            DP[i] = temMin;
        }

        /*
         for i in range(1, n + 1):
        sums = dict()
        k = i
        while (length(wl, k, i) <= L and k > 0):
            sums[(L - length(wl, k, i))**3 + m[k - 1]] = k
            k -= 1
        m[i] = min(sums)
        s[i] = sums[min(sums)]
         */
        printSolution(nextBreak, w.length-1);
        System.out.println(Arrays.toString(nextBreak));
        System.out.println(Arrays.toString(DP));
        /*for (int i = 1; i<w.length; i++){
            System.out.println("NEW ***********************");
            System.out.print("Current I: ");
            System.out.println(i);
            double min_val = Integer.MAX_VALUE;
            last_In = nextBreak.get(nextBreak.size()-1);

            System.out.print("I+1: ");
            System.out.println(i+1);
            System.out.print("Last_In: ");
            System.out.println(last_In);

            System.out.println("RESULTS -----------");
            double all = cost(w,last_In,i+1,m);
            System.out.print("ALL: ");
            System.out.println(all);


            double sep = DP[i-1]+cost(w,i,i+1,m);
            System.out.print("Sep: ");
            System.out.println(sep);

            if (all < sep){
                min_val = all;
            } else {
                min_val = sep;
                nextBreak.add(i);
                System.out.println(nextBreak);
            }
            DP[i] = min_val;
        }*/
        List<Integer> finalB = new ArrayList<Integer>();
        for(int intValue : nextBreak) {
            finalB.add(intValue);
        }
        return finalB;
    }

    public static List<Integer> justifyTextTD(String[] w, int m) {
        double[] DP = new double[w.length];
        for (int l=1;l<w.length;l++){
            DP[l] = Double.POSITIVE_INFINITY;
        }
        DP[0] = cost(w, 0, 1, m);
        int[] nextBreak = new int[w.length];
        recurseText(w, nextBreak, DP, m, w.length-1);
        System.out.println(Arrays.toString(nextBreak));
        List<Integer> finalB = new ArrayList<Integer>();
        for(int intValue : nextBreak) {
            finalB.add(intValue);
        }
        return finalB;
    }

    public static double recurseText(String[] w, int[] nextBreak, double[] DP, int m, int i){
        System.out.println("New loop");
        if (i == -1){
            return Double.POSITIVE_INFINITY;
        } else if (DP[i] != 0) {
            return DP[i];
        } else {
            double[] tempCosts = new double[i+1];
            for (int j = 0; j<=i; j++){
                if (j==0) {
                    tempCosts[j] =cost(w, j, i+1, m);
                } else {
                    tempCosts[j] = cost(w, j, i+1, m) + recurseText(w, nextBreak, DP, m, i-(j+1));
                }
            }
            int min_In = arrayMin(tempCosts);
            DP[i] = tempCosts[min_In];
            nextBreak[i] =  min_In;

            return DP[i];
        }
    }

}