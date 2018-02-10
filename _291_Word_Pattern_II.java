package leetcode;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : WordPatternII
 * Creator : Edward
 * Description : 291. Word Pattern II
 */
public class WordPatternII {
    /**
     * Given a pattern and a string str, find if str follows the same pattern.
     Here follow means a full match, such that there is a bijection
     between a letter in pattern and a non-empty substring in str.
     Examples:
     pattern = "abab", str = "redblueredblue" should return true.
     pattern = "aaaa", str = "asdasdasdasd" should return true.
     pattern = "aabb", str = "xyzabcxzyabc" should return false.
     pattern = "abab", str = "redblueredblue" "redres
     
     题意：
         给定一个字符出现顺序的pattern, 判断其对应的str是不是遵守一致的字符或者单词出现模式（x,y双向映射）
         139. Word Break 和 290. Word Pattern I 的综合题
             
     思路：
         backtracking题，结合了HashMap和HashSet。
         1，从str的取出单词，判断 单词分割的位置在哪里, 用字符长度逐一扫遍所有的字符。
            => 相当于 hashset 逐一存储出现的字符：r re red
         2，判断映射（isMatch）是否成立 -> 若建立了映射，就把 pattern对应的 (char) c  和 (str) word 加入 hashmap.
         3, 注意边界条件 -> 判断str 和 pattern，对应映射长度相等
                 
     复杂度：
          time : O(2^n) 
          space : O(n)
     
         /* time : O(2^n) 不确定？ */       
         
     * @param pattern
     * @param str
     * @return
     */
    public boolean wordPatternMatch(String pattern, String str) {
        HashMap<Character, String> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        return isMatch(str, 0, pattern, 0, map, set);
    }

    private boolean isMatch(String str, int i, String pat, int j, HashMap<Character, String> map, HashSet<String> set) {
        if (i == str.length() && j == pat.length()) return true;
        if (i == str.length() || j == pat.length()) return false;

        char c = pat.charAt(j);
        if (map.containsKey(c)) {
            String s = map.get(c);
            if (!str.startsWith(s, i)) {
                return false;
            }
            return isMatch(str, i + s.length(), pat, j + 1, map, set);
        }

        for (int k = i; k < str.length(); k++) {
            String p = str.substring(i, k + 1);
            if (set.contains(p)) {
                continue;
            }
            map.put(c, p);
            set.add(p);
            if (isMatch(str, k + 1, pat, j + 1, map, set)) {
                return true;
            }
            map.remove(c);
            set.remove(p);
        }
        return false;
    }
}
