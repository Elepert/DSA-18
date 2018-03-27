package divide_and_conquer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.max;

public class Skyline {

    public static class Point {
        public int x;
        public int y;
        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "\nPoint {" +
                    "x: " + x +
                    ", y: " + y +
                    '}';
        }
    }

    public static class Building {
        private int l, r, h;
        public Building(int l, int r, int h) {
            this.l = l;
            this.r = r;
            this.h = h;
        }
    }

    // Given an array of buildings, return a list of points representing the skyline
    public static List<Point> skyline(Building[] B) {

        return skyRecur(B,0, B.length-1);
    }

    public static ArrayList<Point> skyRecur(Building[] B, int lo, int hi){
        int mid = lo+ (hi-lo)/2;
        ArrayList<Point> templine = new ArrayList<>();

        if (lo > hi){
            return templine;
        } else if (lo == hi){
            Point point1 = new Point(B[lo].l, B[lo].h);

            Point point2 = new Point(B[lo].r, 0);

            templine.add(point1);
            templine.add(point2);

            return templine;
        } else {
            ArrayList<Point> line1 = skyRecur(B, lo, mid);
            ArrayList<Point> line2 = skyRecur(B, mid+1, hi);
            return mergeSkyline(line1, line2);
        }


    }

    public static ArrayList<Point> mergeSkyline(ArrayList<Point> line1, ArrayList<Point> line2){
        ArrayList<Point> mergedSkyLine = new ArrayList<>();
        int lastHeightSkyScraper1 = 0;
        int lastHeightSkyScraper2 = 0;
        int maxHeight = 0;
        while( !(line1.isEmpty() || line2.isEmpty()) ){

            if(line1.get(0).x < line2.get(0).x){
                lastHeightSkyScraper1 = line1.get(0).y;

                maxHeight = max(lastHeightSkyScraper1, lastHeightSkyScraper2);

                if (mergedSkyLine.size() == 0 || mergedSkyLine.get(mergedSkyLine.size()-1).y != maxHeight) {
                    Point newP = new Point(line1.get(0).x, maxHeight);

                    mergedSkyLine.add(newP);
                }

                line1.remove(0);
            }else if(line1.get(0).x > line2.get(0).x){
                lastHeightSkyScraper2 = line2.get(0).y;
                maxHeight = max(lastHeightSkyScraper1, lastHeightSkyScraper2);

                if (mergedSkyLine.size() == 0 || mergedSkyLine.get(mergedSkyLine.size()-1).y != maxHeight) {
                    Point newP = new Point(line2.get(0).x, maxHeight);

                    mergedSkyLine.add(newP);
                }

                line2.remove(0);
            }else{
                maxHeight = max(line1.get(0).y, line2.get(0).y);

                if (mergedSkyLine.size() == 0 || mergedSkyLine.get(mergedSkyLine.size()-1).y != maxHeight) {
                    Point newP = new Point(line2.get(0).x, maxHeight);
                    mergedSkyLine.add(newP);
                }

                lastHeightSkyScraper1 = line1.get(0).y;
                lastHeightSkyScraper2 = line2.get(0).y;

                line1.remove(0);
                line2.remove(0);
            }
        }

        while(!line1.isEmpty()){
            mergedSkyLine.add( new Point(line1.get(0).x, line1.get(0).y));
            line1.remove(0);
        }
        while(!line2.isEmpty()){
            mergedSkyLine.add( new Point(line2.get(0).x, line2.get(0).y));
            line2.remove(0);
        }

        return mergedSkyLine;
    }

}
