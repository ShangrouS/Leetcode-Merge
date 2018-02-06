题意：
    给一个正整数n, 找这个数的所有因子,输出所有的因式分解的组合
    Note: 因子(大于1，小于n)
    
思路：
    典型的backtracking题。
    1，进行helper函数backtracking调用
    2，helper考虑边界条件 -> 何时返回结果 -> for循环递归
    3，考虑特殊情况。
     3.1 质数不能因式分解，返回为“空”
     3.2 helper第一次调用，start的起始元素是2
     3.3 因为考虑因子可以重复的，helper每次调用的start都是i本身
     3.4 退出条件要同时满足，商为1 && 每个返回list结果的 size > 1 
        
复杂度：
     time : O(2^n)
     space : O(n)   

     
class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        helper(res, new ArrayList<>(), n, 2);
        return res;
    }
    public void helper(List<List<Integer>> res, List<Integer> list, int n, int start) {
        if (n == 1) {
            if (list.size() > 1) {
                res.add(new ArrayList<>(list));
                return;
            }
        }
        for (int i = start; i <= n; i++) {
            if (n % i == 0) {
                list.add(i);
                helper(res, list, n / i, i);
                list.remove(list.size() - 1);
            }
        }
    }
}
