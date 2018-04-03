import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Permutations {

    private static void backtrack(LinkedList<Integer> curr, Set<Integer> unused, List<List<Integer>> subsets) {
        if (unused.isEmpty())
            subsets.add(new LinkedList<>(curr));
        for (int u : new LinkedList<>(unused)) {
            curr.addLast(u);
            unused.remove(u);
            backtrack(curr, unused, subsets);
            unused.add(u);
            curr.removeLast();
        }
    }

    public static List<List<Integer>> permutations(List<Integer> A) {
        if (A == null){
            return null;
        }
        List<List<Integer>> permutations = new LinkedList<>();
        LinkedList<Integer> curr = new LinkedList<>();
        Set<Integer> ASet = new HashSet<Integer>(A);
        backtrack(curr, ASet, permutations);
        return permutations;
    }

}
