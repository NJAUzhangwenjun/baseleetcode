
package leetcode.editor.cn;

import org.springframework.util.StopWatch;

public class BestTimeToBuyAndSellStock {
    public static void main(String[] args) {
        Solution solution = new BestTimeToBuyAndSellStock().new Solution();
        StopWatch watch = new StopWatch("总共耗时");
        watch.start("买卖股票的最佳时机");
        //
        int i = solution.maxProfit(new int[]{2,1,2,0,1});
        watch.stop();
        System.out.println("i = " + i);
        System.out.println(watch.prettyPrint());
    }

    /**
     * 买卖股票的最佳时机
     * best-time-to-buy-and-sell-stock
     */
    // leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxProfit(int[] prices) {
            if (null == prices || prices.length < 2) {
                return 0;
            }
            int n = prices.length;
            int min = prices[0];
            int result = 0;
            for (int i = 1; i < n; i++) {
                result = Math.max(result, prices[i] - min);
                min = Math.min(min, prices[i]);
            }
            return  result;
        }
    }
// leetcode submit region end(Prohibit modification and deletion)

}