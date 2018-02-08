题意：
     给定一个包含重复数字的集合, 输出对应的所有全排列的组合
     和 46.Permutations异同
     1.1（异）  input元素可能是有重复的，output的结果是Unique的
     2.1（同）  都可以用swap()实现（ Note: 47.PermutationsII 两种实现方法的时间复杂度一样 ）
             
思路：
     典型的backtracking题。
     1，进行helper函数backtracking调用
     2，去重判断 -> input含有重复的元素，首先要进行sort排序 （规律: input元素有重复的典型做法,sort去重）
     3，helper函数，去重处理 + 边界判断-> 记录 当前每一位元素有没有被用过 (通用方法)->
        backtracking        :  一维数组： boolean int[] 
        backtracking  + swap:  boolean isUsed 函数 -> 记录 当前每一位元素有没有被用过。
       （Note：boolean used[] = true / boolean used[] = false 不同位置的理解 -> 一次赋值，一次更新值）
                    
复杂度：
     time : O(n!)
     space : O(n)   


public class PermutationsII {

    // time : O(n!) space : O(n);
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return  res;
        Arrays.sort(nums);
        helper(res, new ArrayList<>(), nums, new boolean[nums.length]);
        return res;
    }

    public void helper(List<List<Integer>> res, List<Integer> list, int[] nums, boolean[] used) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i] || i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
            used[i] = true;
            list.add(nums[i]);
            helper(res, list, nums, used);
            used[i] = false;
            list.remove(list.size() - 1);
        }
    }

    // time : O(n!) space : O(n);
    public List<List<Integer>> permuteUnique2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return  res;
        Arrays.sort(nums);
        helper2(res, nums, 0);
        return res;
    }
    
    public void helper2(List<List<Integer>> res, int[] nums, int start) {
        if (start == nums.length) {
            List<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }
            res.add(list);
            return;
        }

        for (int i = start; i < nums.length; i++) {
            if (isUsed(nums,start, i)) continue;
            swap(nums, start, i);
            helper2(res, nums, start + 1);
            swap(nums, start, i);
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public boolean isUsed(int[] nums, int i, int j) {
        for (int x = i; x < j; x++) {
            if (nums[x] == nums[j]) return true;
        }
        return false;
    }
}
