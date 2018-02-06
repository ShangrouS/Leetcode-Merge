package leetcode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : CombinationSumIV
 * Creator : Edward
 * Description : 377. Combination Sum IV
 */
public class CombinationSumIV {
    /**
     nums = [1, 2, 3]
     target = 4
     The possible combination ways are:
     (1, 1, 1, 1)
     (1, 1, 2)
     (1, 2, 1)
     (1, 3)
     (2, 1, 1)
     (2, 2)
     (3, 1)
     Note that different sequences are counted as different combinations.
     Therefore the output is 7.
     1, DP : res[i] += res[i - num];
     2, DFS + Memoization : HashMap<Integer, Integer>
 
     题意：
         输入：一组没有重复元素的正整数数组(int[] nums)，输出：和为Target的所有子集的个数。
         Note:子集元素顺序不同视为不同的组合，且子集内元素可以有重复。 
         Combination Sum 系列的最后一题，但是这里backtracking不是最优解，优先考虑DP.
         掌握程度：backtracking 和 DP 都要掌握。
     
     思路：
         DP方法：
         ？为什么会想到DP -> output:子集个数（个数问题是典型DP的输出）。
         ？DP的转移方程:  res[i] += res[i - num] -> i+1元素的结果建立在所有i+1前面元素的结果基础之上。
         DP-Case: 
         i = 1, num = 1, res[1] += res[0] = 1 
         i = 2, num = 1, res[2] += res[1] = 1
				        num = 2, res[2] += res[0] = 2
         i = 2, num = 1, res[3] += res[0] = 1
			        	num = 2, res[3] += res[1] = 2
                num = 3, res[3] += res[2] = 4
         i = 3, num = 1, res[3] += res[2] = 2
                num = 2, res[3] += res[1] = 3
                num = 3, res[3] += res[0] = 4
         i = 4, num = 1, res[4] += res[3] = 4
				        num = 2, res[4] += res[2] = 6
                num = 3, res[4] += res[1] = 7
                
     复杂度：
         time : O(n * k)
         space : O(k)
                 
     思路：
         backtracking方法: 
         1， DFS + Memoization : HashMap<Integer, Integer>  ( HashMap<Integer, Integer>记录中间结果，直接进行调用 )
         2， 注意helper边界条件 -> target调用会更新值 （ >0 , =0, <0 3种case）
         3， 相较其他combinationSum，这里 time compleity 小于 O(2^n)
         
     复杂度：
         time : < O(2^n) 
         space : O(n)     
     
     * @param nums
     * @param target
     * @return
     */

    // DP- Method : time : (n * k) space : O(k)
    public int combinationSum4(int[] nums, int target) {
        int[] res = new int[target + 1];
        res[0] = 1;
        for (int i = 1; i < res.length; i++) {
            for (int num : nums) {
                if (i - num >= 0) {
                    res[i] += res[i - num];
                }
            }
        }
        return res[target];
    }

    // backtracking: time : < O(2^n) space : O(n)
    public int combinationSum42(int[] nums, int target) {
        if (nums.length == 0) return 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        return helper(nums, target, map);
    }

    private int helper(int[] nums, int target, HashMap<Integer, Integer> map) {
        if (target == 0) return 1;
        if (target < 0) return 0;
        if (map.containsKey(target)) {
            return map.get(target);
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res += helper(nums, target - nums[i], map);
        }
        map.put(target, res);
        return res;
    }
}
