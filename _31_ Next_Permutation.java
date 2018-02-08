package leetcode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : NextPermutation
 * Creator : Edward
 * Description : 31. Next Permutation
 */
public class NextPermutation {
    /**
     
     Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
     If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
     
     Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
     1,2,3 → 1,3,2
     3,2,1 → 1,2,3
     1,1,5 → 1,5,1
     // 1　　2　　7　　4　　3　　1
             ^
     // 1　　2　　7　　4　　3　　1
                          ^
     // 1　　3　　7　　4　　2　　1
             ^            ^
     // 1　　3　　1　　2　　4　　7
                 ^   ^    ^   ^
     7 4 3 2 1 1
     
     题意：
         对于一个数组 (int[])，从其Permutation全排列中选取一个，将排列中的数字重新排列，满足顺序为字典序，并且下一个数更大的排列。
         若无法满足这样的重新排列，返回其升序排列。
         Note: 重要程度:熟练掌握。
               和 46.Permutation & 47.Permutation II ，虽然都有 Permutation，但不是一个思路，注意区分。
               理解: 什么是字典序最大（ lexicographically next greater numbers）
                     Eg: 316. Remove Duplicate Letters(帮助理解按字典序排列的题)
             
     思路：
         1，从
       
            backtracking        :  一维数组： boole 
            backtracking        :  一维数组： boolean int[] 
            
         
     复杂度：
           time : O(n);
           space : O(1);

     * @param nums
     */
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) return;

        int firstSmall = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                firstSmall = i;
                break;
            }
        }

        if (firstSmall == -1) {
            reverse(nums, 0, nums.length - 1);
            return;
        }

        int firstLarge = -1;
        for (int i = nums.length - 1; i > firstSmall; i--) {
            if (nums[i] > nums[firstSmall]) {
                firstLarge = i;
                break;
            }
        }
        swap(nums, firstSmall, firstLarge);
        reverse(nums, firstSmall + 1, nums.length - 1);
        return;
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i++] = nums[j];
        nums[j--] = temp;
    }

    public void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i++, j--);
        }
    }
}
