
package leetcode.editor.cn;

import org.springframework.util.StopWatch;

public class BestTimeToBuyAndSellStockIi {
    public static void main(String[] args) {
        Solution solution = new BestTimeToBuyAndSellStockIi().new Solution();
        StopWatch watch = new StopWatch("总共耗时");
        watch.start("买卖股票的最佳时机 II");
        int i = solution.maxProfit(new int[]{6, 1, 3, 2, 4, 7});
        watch.stop();
        System.out.println(watch.prettyPrint());
        System.out.println("i = " + i);
    }

    /**
     * 买卖股票的最佳时机 II
     * best-time-to-buy-and-sell-stock-ii
     */
    // leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxProfit(int[] prices) {
            if (prices.length < 2) {
                return 0;
            }
            int profit = 0;

            for (int i = 0; i < prices.length - 1; i++) {
                if (prices[i] < prices[i + 1]) {
                    profit += prices[i + 1] - prices[i];
                }
            }

            return profit;

        }

        public int maxProfit1(int[] prices) {
            if (prices.length < 2) {
                return 0;
            }
            int max = 0;
            Integer cur = null;
            for (int i = 1; i < prices.length; i++) {
                int j = i - 1;
                if (prices[j] < prices[i] && cur == null) {
                    cur = prices[j];
                }
                if (prices[j] > prices[i] && cur != null) {
                    max += prices[j] - cur;
                    cur = null;
                }
            }
            if (cur != null) {
                max += prices[prices.length - 1] - cur;
            }
            return max;


        }
    }
// leetcode submit region end(Prohibit modification and deletion)

}