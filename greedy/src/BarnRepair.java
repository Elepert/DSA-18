import java.util.*;

public class BarnRepair {

    public static Comparator<int[]> idComp = new Comparator<int[]>(){

        @Override
        //Runtime: O(1)
        public int compare(int[] a, int[] b) {
            if (a[2]<b[2]){
                return -1;
            } else if (b[2]<a[2]){
                return 1;
            } else {
                return 0;
            }
        }

    };

    public static void printStalls(List<int[]> boards){
        for (int i =0; i<boards.size(); i++){
            int[] thisBoard = boards.get(i);
            System.out.print("Beginning: ");
            System.out.print(thisBoard[0]);
            System.out.print(", End: ");
            System.out.print(thisBoard[1]);
            System.out.print(", Diff: ");
            System.out.println(thisBoard[2]);

        }
    }

    public static void printQueue(PriorityQueue<int[]> gaps){
        //Iterator<int[]> iter = gaps.iterator();
        while (gaps.peek()!=null){
            System.out.println(Arrays.toString(gaps.remove()));
        }
    }

    /*
    Greedy Algorithm: finds shortest gaps between stall groups
    merges those into one bigger stall
    fits back into the problem
     */

    public static int solve(int M, int[] occupied) {
        Arrays.sort(occupied);
        //List<Integer> gaps = new ArrayList<Integer>();
        PriorityQueue<int[]> gaps = new PriorityQueue<>(idComp);
        List<int[]> boards = new ArrayList<>();
        int[] stall = new int[3];

        int last_gap = occupied[0]-1;
        int beg_gap = occupied[0];
        for (int i = 0; i< occupied.length; i++) {
            if (last_gap + 1 == occupied[i]) {
                if (stall[0] == 0) {
                    beg_gap = occupied[i];
                    stall[0] = beg_gap;
                } else if (i == occupied.length-1){
                    stall[1] = occupied[i];
                    stall[2] = stall[1]-stall[0]+1;
                    boards.add(stall);
                }
            } else {
                if (stall[1] == 0) {
                    stall[1] = last_gap;
                    stall[2] = stall[1]-stall[0]+1;
                    boards.add(stall);
                    stall = new int[3];
                    beg_gap = occupied[i];
                    stall[0] = beg_gap;
                    if (i == occupied.length-1) {
                        stall[1] = occupied[i];
                        stall[2] = stall[1] - stall[0] + 1;
                        boards.add(stall);
                    }
                }
            }
            last_gap = occupied[i];
        }

        int last_stall = occupied[0]-1;
        for (int i = 0; i< occupied.length; i++){
            if (last_stall+1 != occupied[i]){
                beg_gap = occupied[i-1]+1;
                int end_gap = occupied[i]-1;
                int diff = end_gap-beg_gap+1;
                int[] gap = {beg_gap, end_gap, diff};
                gaps.add(gap);
            }
            last_stall = occupied[i];
        }

        return greedy(M, gaps, boards);

    }

    public static int greedy(int M, PriorityQueue<int[]> gaps, List<int[]> boards){
        if (M >= boards.size()){
            return minBoards(boards);
        }

        int[] gapToCover = gaps.remove();
        int begToFind = gapToCover[0]-1;
        int endToFind = gapToCover[1]+1;

        int end = 0;
        int beg = 0;

        for (int i = 0; i<boards.size();i++){
            if (boards.get(i)[1] == begToFind){
                beg = i;
            }
            if (boards.get(i)[0] == endToFind){
                end = i;
            }
        }
        int[] newBoard = boards.get(beg);
        newBoard[1] = boards.get(end)[1];
        newBoard[2] = newBoard[1]-newBoard[0]+1;
        boards.set(beg, newBoard);
        boards.remove(end);

        return greedy(M, gaps, boards);

    }

    public static int minBoards(List<int[]> boards){
        int minB = 0;
        for (int i = 0; i<boards.size();i++){
            minB += boards.get(i)[2];
        }
        return minB;
    }
}