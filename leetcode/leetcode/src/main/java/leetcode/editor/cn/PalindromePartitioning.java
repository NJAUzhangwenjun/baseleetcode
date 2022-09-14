
package leetcode.editor.cn;

import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {
    public static void main(String[] args) {
        Solution solution = new PalindromePartitioning().new Solution();
        StopWatch watch = new StopWatch("总共耗时");
        watch.start("分割回文串");
        //
        List<List<String>> aab = solution.partition("aab");
        watch.stop();
        System.out.println("aab = " + aab.toString());
        System.out.println(watch.prettyPrint());
    }

    /**
     * 分割回文串
     * palindrome-partitioning
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //声明一个全局变量ans
        List<List<String>> ans = new ArrayList<>();

        //主方法
        public List<List<String>> partition(String s) {
            backtrack1(s.toCharArray(), 0, new ArrayList<String>());
            return ans;
        }

        private void backtrack1(char[] c, int start, ArrayList<String> res) {
            int l = c.length;
            if (start >= l) {
                ans.add(new ArrayList<>(res));
                return;
            }
            for (int j = start; j < l; j++) {
                if (isPartition(c, start, j)) {
                    res.add(new String(c, start, j-start+1));
                    backtrack1(c, j+1, res);
                    res.remove(res.size() - 1);
                }
            }
        }

        private boolean isPartition(char[] c, int start, int end) {
            while (end >= start) {
                if (c[start++] != c[end--]) {
                    return false;
                }
            }
            return true;
        }

        //回溯方法
        void backtrack(char[] s, int startIndex, List<String> path) {
            //当startIndex越界时返回
            if (startIndex == s.length) {
                //将当前path添加到ans中，注意：需要新造一个ArrayList
                ans.add(new ArrayList(path));
                return;
            }
            //从startIndex开始枚举
            for (int i = startIndex; i < s.length; i++) {
                if (!isPalindrome(s, startIndex, i)) {
                    continue;
                }
                //从startIndex到i如果是回文串，则将此串加入path，并向下递归
                path.add(new String(s, startIndex, i - startIndex + 1));
                backtrack(s, i + 1, path);
                //递归完毕后，记得将此串从path中删除，这是回溯算法的关键步骤
                path.remove(path.size() - 1);

            }
        }

        //辅助方法，判断从start到end是否回文
        private boolean isPalindrome(char[] s, int start, int end) {
            while (start <= end) {
                if (s[start++] != s[end--]) {
                    return false;
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}