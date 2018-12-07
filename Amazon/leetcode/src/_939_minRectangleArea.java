//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Set;
//
///**
// * Created by yuegu on 11/13/18.
// */
//public class _939_minRectangleArea {
//
//  public class Coordinate {
//
//    int i;
//    int j;
//
//    public Coordinate(int i, int j) {
//      this.i = i;
//      this.j = j;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//      if (this == o) {
//        return true;
//      }
//      if (o == null || getClass() != o.getClass()) {
//        return false;
//      }
//
//      Coordinate that = (Coordinate) o;
//
//      if (i != that.i) {
//        return false;
//      }
//      return j == that.j;
//    }
//
//    @Override
//    public int hashCode() {
//      int result = i;
//      result = 31 * result + j;
//      return result;
//    }
//  }
//
//  public int getMin(int[][] input) {
//    Map<Integer, Set<Coordinate>> mapX = new HashMap<>();
//    Map<Integer, Set<Coordinate>> mapY = new HashMap<>();
//    for(int i = 0; i < input.length; i++) {
//      Coordinate cur = new Coordinate(input[i][0], input[i][1]);
//      if(!mapX.containsKey(input[i][0])) {
//        mapX.put(input[i][0], new HashSet<>());
//      }
//      mapX.get(input[i][0]).add(cur);
//
//      if(!mapY.containsKey(input[i][0])) {
//        mapY.put(input[i][1], new HashSet<>());
//      }
//      mapY.get(input[i][1]).add(cur);
//    }
//    int min = 0;
//    for(int i : mapX.keySet()) {
//      if(mapX.get(i).size() > 1) {
//        for(Coordinate c : mapX.get(i)) {
//
//        }
//      }
//    }
//
//  }
//}
