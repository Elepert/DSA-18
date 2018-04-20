import java.util.Arrays;
import java.util.HashSet;

public class MCCR {

    public static int findMin(int[] keys, boolean[] inMST){
        // Initialize min value
        int min = Integer.MAX_VALUE;
        int min_index = -1;

        for (int v = 0; v < keys.length; v++)
            if (inMST[v] == false && keys[v] < min)
            {
                min = keys[v];
                min_index = v;
            }

        return min_index;
    }

    public static int MCCR(EdgeWeightedGraph G) {
        int numV = G.numberOfV();
        int[] keys = new int[numV];
        int minPath = 0;
        boolean[] inMST = new boolean[numV];

        for (int i = 0; i<numV;i++){
            keys[i]= Integer.MAX_VALUE;
            inMST[i] = false;
        }
        keys[0] = 0;;

        for (int j = 0; j<numV;j++){
            int minKey = findMin(keys, inMST);
            inMST[minKey] =true;

            minPath += keys[minKey];

            Iterable<Edge> neighbors = G.edges(minKey);
            for(Edge neigh: neighbors){
                int otherEdge = neigh.other(minKey);
                int weight = neigh.weight();
                if (otherEdge!=0 && inMST[otherEdge] == false && weight<keys[otherEdge]) {
                    keys[otherEdge] = weight;
                }
            }

        }
        return minPath;
    }

}

