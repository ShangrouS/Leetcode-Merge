package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : Permutations
 * Creator : Edward
 * Description : 46. Permutations
 */


public class Permutations {
    /**
    
     Given a collection of distinct numbers, return all possible permutations.
     For example,
     [1,2,3] have the following permutations:
     [
     [1,2,3],
     [1,3,2],
     [2,1,3],
     [2,3,1],
     [3,1,2],
     [3,2,1]
     ]
     time : O(n!)
     space : O(n);
     reference : http://www.1point3acres.com/bbs/thread-117602-1-1.html
     The number of recursive calls, T(n) satisfies the recurrence T(n) = T(n - 1) + T(n - 2) + ... + T(1) + T(0),
     which solves to T(n) = O(2^n). Since we spend O(n) time within a call, the time complexity is O(n2^n);
     
     题意：
         给定一个不重复数字的集合, 输出对应的所有全排列的组合
         
     整体思路：
         典型的backtracking题。
         1，进行helper函数backtracking调用
         2，着重理解：递归调用的时间复杂度分析（单次调用: O(n) = n!，典型的例子）
         3，方法2的helper调用swap()函数，实现字符间交换。backtracking传统方法的优化。
         4. 排列问题，用swap()实现，记思路。
     
     思路异同点：
         1，方法1-优化的点：每次进行遍历判断时，均从从i = 0 起始，逐一检查list是否包含从头开始的元素 (list.contains(nums[i]))，
                         当数组长度很大的时候，不适用。
         2，基于方法1问题，方法2进行 helper时，swap函数把数组进行重排 
         3，方法2注意: 返回条件判断（start == nums.length）-> 2次swap()交换
         
         Method2-Case:
         list: 1,2,3 -> swap[index: 1,index: 2]: 1,3,2
         res{(1,2,3), (1,3,2)}
         list: 1,2,3 -> swap[index: 1,index: 0]: 2,1,3                
         res{(1,2,3), (1,3,2), (2,1,3)}
         list: 2,1,3 -> swap[index: 1,index: 2]: 2,3,1
         res{(1,2,3), (1,3,2), (2,1,3), (2,3,1)}
         list: 1,2,3 -> swap[index: 2,index: 0]: 3,2,1
         res{(1,2,3), (1,3,2), (2,1,3), (2,3,1), (3,2,1)}
         list: 3,2,1 -> swap[index: 1,index: 2]: 3,1,2
         res{(1,2,3), (1,3,2), (2,1,3),(2,3,1), (3,2,1),(3,1,2)}
         
       /* 给楼主: 麻烦帮忙看一下case的逻辑和结果，double-check 是对的 。 O(∩_∩)O谢谢 */
        
     复杂度：
         backtracking:
         time : O(n! * n)
         space : O(n)   
         backtracking + swap:
         time : O(n!)
         space : O(n)   
     
     
     * @param nums
     * @return
     */
     
    // time : O(n! * n) space : O(n);
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        helper(res, new ArrayList<>(), nums);
        return res;
    }

    public static void helper(List<List<Integer>> res, List<Integer> list, int[] nums) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (list.contains(nums[i])) continue;  // O(n)
            list.add(nums[i]);
            helper(res, list, nums);
            list.remove(list.size() - 1);
        }
    }

    // time : O(n!) space : O(n);
    public static List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        helper2(res, 0, nums);
        return res;
    }
    public static void helper2(List<List<Integer>> res, int start, int[] nums) {
        if (start == nums.length) {
            List<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            swap(nums, start, i);
            helper2(res, start + 1, nums);
            swap(nums, start, i);
        }
    }
    public static void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }
}
