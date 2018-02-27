import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LargestSubArray {
    static int[] largestSubarray(int[] nums) {
        int count =0;
        int len = 0;
        int ending = -1;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,-1);
        for (int i = 0; i<nums.length;i++){
            if (nums[i] == 0) {
                count += -1;
            } else {
                count += nums[i];
            }

            if (map.containsKey(count)){
                if (len < i-map.get(count)){
                    len = i-map.get(count);
                    ending = i;
                }
            } else {
                map.put(count,i);
            }

        }
        int start = ending - len + 1;
        int[] subArray = {start, ending};
        return subArray;
    }
}
