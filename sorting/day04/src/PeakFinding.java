import java.util.Arrays;

public class PeakFinding {

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakOneD(int i, int[] nums) {
        if (i > 0 && nums[i] < nums[i - 1])
            return -1;
        if (i < nums.length - 1 && nums[i] < nums[i + 1])
            return 1;
        return 0;
    }

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakX(int x, int y, int[][] nums) {

        if (x > 0 && nums[y][x] < nums[y][x - 1])
            return -1;
        if (x < nums[0].length - 1 && nums[y][x] < nums[y][x + 1])
            return 1;
        return 0;
    }

    // Return -1 if up is higher, 1 if down is higher, 0 if peak
    private static int peakY(int x, int y, int[][] nums) {
        if (y > 0 && nums[y][x] < nums[y - 1][x])
            return -1;
        if (y < nums.length - 1 && nums[y][x] < nums[y + 1][x])
            return 1;
        return 0;
    }

    // These two functions return the index of the highest value along the X or Y axis, with the given
    // value for the other axis. Searches between hi (exclusive) and lo (inclusive)
    private static int maxXIndex(int y, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int x = lo; x < hi; x++) {
            if (maxIndex == -1 || nums[y][x] > nums[y][maxIndex])
                maxIndex = x;
        }
        return maxIndex;
    }

    private static int maxYIndex(int x, int lo, int hi, int[][] nums) {

        int maxIndex = -1;


        for (int y = lo; y < hi; y++) {

            if (maxIndex == -1 || nums[y][x] > nums[maxIndex][x])

                maxIndex = y;

        }
        return maxIndex;
    }


    public static int findOneDPeak(int[] nums) {
        int final_ind = -1;
        int start = 0;
        int end = nums.length-1;
        int mid_index = (end-start)/2;
        while (final_ind == -1){
            if (end-start==0){
                final_ind = end;
            } else {
                int analysis = peakOneD(mid_index, nums);
                if (analysis == -1) {
                    end = mid_index - 1;
                    mid_index = (end - start) / 2;
                } else if (analysis == 1) {
                    start = mid_index + 1;
                    mid_index = start + (end - start) / 2;
                } else if (analysis == 0) {
                    final_ind = mid_index;
                }
            }
        }

        return final_ind;
    }

    public static int[] findTwoDPeak(int[][] nums) {

        int final_ind_x = -1;
        int final_ind_y = -1;
        int start_x = 0;
        int end_x = nums.length-1;
        int mid_index_x = (end_x-start_x)/2;
        int maxY= -1;
        int[] answer = {-1,-1};
        while (final_ind_x == -1){

            maxY = maxYIndex(mid_index_x, 0, nums.length, nums);

            if (end_x==mid_index_x || start_x==mid_index_x){
                final_ind_x = mid_index_x;
                final_ind_y = maxY;

            } else {
                int analysis = peakX(mid_index_x, maxY, nums);
                if (analysis == -1) {
                    end_x = mid_index_x - 1;
                    mid_index_x = (end_x - start_x) / 2;
                } else if (analysis == 1) {
                    start_x = mid_index_x + 1;
                    mid_index_x = start_x + (end_x - start_x) / 2;
                } else if (analysis == 0) {
                    final_ind_x = mid_index_x;
                    final_ind_y = maxY;

                }
            }
        }
        answer[0] = final_ind_y;
        answer[1] = final_ind_x;
        return answer;
    }

}
