
package leetcode.editor.cn;

import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DecodeWays {
    public static void main(String[] args) {
        Solution solution = new DecodeWays().new Solution();
        StopWatch watch = new StopWatch("总共耗时");
        watch.start("解码方法");
        int i = solution.numDecodings("226");
        watch.stop();
        System.out.println("i = " + i);
        System.out.println(watch.prettyPrint());
    }

    /**
     * 解码方法
     * decode-ways
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int numDecodings(String s) {
            int n = s.length();
            if (n < 1) {
                return 0;
            }
            // 定义：dp[i] 表示 s[0..i-1] 的解码方式数量
            int[] dp = new int[n + 1];
            // base case: s 为空或者 s 只有一个字符的情况
            dp[0] = 1;
            dp[1] = s.charAt(0) == '0' ? 0 : 1;

            // 注意 dp 数组和 s 之间的索引偏移一位
            for (int i = 2; i <= n; i++) {
                char c = s.charAt(i - 1), d = s.charAt(i - 2);
                if ('1' <= c && c <= '9') {
                    // 1. s[i] 本身可以作为一个字母
                    dp[i] += dp[i - 1];
                }
                if (d == '1' || d == '2' && c <= '6') {
                    // 2. s[i] 和 s[i - 1] 结合起来表示一个字母
                    dp[i] += dp[i - 2];
                }
            }
            return dp[n];
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}