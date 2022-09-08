
package leetcode.editor.cn;

import org.springframework.util.StopWatch;

public class IntegerBreak {
    public static void main(String[] args) {
        Solution solution = new IntegerBreak().new Solution();
        StopWatch watch = new StopWatch("总共耗时");
        watch.start("整数拆分");
        int i = solution.integerBreak(10);
        watch.stop();
        System.out.println("i = " + i);
        System.out.println(watch.prettyPrint());
    }

    /**
     * 整数拆分
     * todo
     * integer-break
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int integerBreak(int n) {
            if (n <= 2) {
                return 1;
            }
            if (n == 3) {
                return 2;
            }
            //整数k被切割后，最大乘积是dp[k]
            int[] dp = new int[n + 1];
            dp[1] = 1;
            dp[2] = 2;
            dp[3] = 3;
            for (int i = 4; i <= n; i++) {
                //切的方式
                for (int j = 1; j <= i / 2; j++) {
                    dp[i] = Math.max(dp[i], dp[j] * dp[i - j]);
                }
            }
            return dp[n];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}