import java.util.*;
public class Boomerang {

    public static int numberOfBoomerangs(int[][] points) {
        int boomCount = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i <points.length;i++){

            for (int j = 0; j<points.length; j++){
                if (i == j){
                    continue;
                }
                int dx = points[i][0] - points[j][0];
                int dy = points[i][1] - points[j][1];
                int dis = dx*dx+dy*dy;

                if (!map.containsKey(dis)){
                    map.put(dis, 0);
                }
                map.put(dis, map.get(dis)+1);
            }

            for (int value: map.values()){
                boomCount += value*(value-1);
            }
            map.clear();

        }
        return boomCount;
    }
}

