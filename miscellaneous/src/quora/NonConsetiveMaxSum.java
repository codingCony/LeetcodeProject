//package quora;
//
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// *
// * https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=450288
// *
// * 比如[1, 1, 2, 2, 2, 3, 3, 4, 4, 4, 4]
// 输出结果是 2+2+2+4+4+4+4 = 22
// 比如[1，1，1，1，2，2，2，2, 3，4，4，5，5，5] .
// [1...]  [1..., 3...] [1..., 3..., 5...] [1..., 4...] [1..., 5...]
// [2...] [2..., 4...] [2..., 5...]
// [3...] [3..., 5...]
// [4...]
// [5...]
// sum最大的是2和5的组合
// 结果是2+2+2+2+5+5+5 = 23
// * Created by yuegu on 10/22/18.
// */
//public class NonConsetiveMaxSum {
//
//  public static void main(String[] args) {
//
//  }
//
//  public static int NonConsetiveMaxSum(int[] array) {
//    Arrays.sort(array);
////    Map<Integer, Integer> map = new HashMap<>();
////    for(int i = 0; i < array.length; i++) {
////      if(map.containsKey(array[i]))
////        map.put(array[i], map.getOrDefault(array[i], 0) + array[i]);
////    }
//    int[] dp = new int[array.length];
//    for(int i = 0; i < array.length; i++) {
//
//    }
//  }
//}
