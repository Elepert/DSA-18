import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TripleSum {

    static int tripleSum(int arr[], int sum) {
        Arrays.sort(arr);
        int count = 0;

        for (int i = 0; i<arr.length-1; i++){

            Map<Integer, Integer> tracker = new HashMap<Integer, Integer>();
            int sumCheck = 0;
            for (int j = i+1; j<arr.length; j++){

                sumCheck = sum-(arr[i]+arr[j]);
                if (tracker.containsValue(sumCheck)){
                    count += 1;

                } else {
                    tracker.put(j, arr[j]);
                }
            }
        }
        return count;
    }
}
