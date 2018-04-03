import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Permutations {

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
