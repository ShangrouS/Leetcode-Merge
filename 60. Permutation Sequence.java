题意：
    给定一个数字n(1...9), 输出对应的第k个全排列组合
    Note: 和 46.PermutationI & 47.Permutation II 方法不一样，不是backtracking。用backtracking时间复杂度过高，不考虑，掌握特殊方法。
             
思路：   
    1，第K个元素的存储和查找，类似于Page（页）存储的结构，在第几个区块的第几行
    2，理解阶乘factorial的递归调用：
       当前元素（k）全排列的个数，取决于 = (k-1)元素的全排列个数 * 再乘以当前元素k的个数; k-1类似，直到 k = 0, 初始化为1(factorial[0]=1)
    3，/ 和 % 作用：
       假设一共有n个数，先固定首位(第一位为n)，那么剩下的n-1位里，所有的可能组合的个数一共是（n-1)! (记做:factorial[n - 1])
       k / factorial[n - 1] -> 访问到了当前的第几个区块（每个区块的首位，依次从n,n-1,到1）
      （假设 x = k / factorial[n - 1]意味着:第k各元素位于x - 1为头部元素的区间内，但不足以遍历完整的(x - 1)区间）
       k % factorial[n - 1] -> k 位于 k / factorial[n - 1] 的下一个区块内，然后更新k值。
      （相当于 在 (x - 1)区间内，从第二位开始，依次从n,n-1,到1，重新调用，不断更新k值，直到找到所有的元素）        
     4，初始化的时候，根据n的大小构建list, 当元素从最高位向右移一位的时候，删除list里对应的最高位元素。
     
复杂度：
      time : O(n^2)
      space : O(n)     
         
        
   /*注：视频的时间复杂度有错，但已经在评论处更正*/ 
  
public class PermutationSequence {
  
  public static String getPermutation(int n, int k) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            res.add(i);
        }
        int[] fact = new int[n];
        fact[0] = 1;
        for (int i = 1; i < n; i++) {
            fact[i] = i * fact[i - 1];
        }
        k = k - 1;
        StringBuilder sb = new StringBuilder();
        for (int i = n; i > 0; i--) {
            int index = k / fact[i - 1];
            k = k % fact[i - 1];
            sb.append(res.get(index));
            res.remove(index);
        }
        return sb.toString();
    }
}
