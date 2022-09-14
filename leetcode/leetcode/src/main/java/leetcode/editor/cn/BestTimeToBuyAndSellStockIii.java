
package leetcode.editor.cn;
import org.springframework.util.StopWatch;

public class BestTimeToBuyAndSellStockIii{
  public static void main(String[] args) {
        Solution solution = new BestTimeToBuyAndSellStockIii().new Solution();
        StopWatch watch = new StopWatch("总共耗时");
        watch.start("买卖股票的最佳时机 III");
        // 
        watch.stop();
        System.out.println(watch.prettyPrint());
  }
  /**
  * 买卖股票的最佳时机 III
  * best-time-to-buy-and-sell-stock-iii
  */
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int profit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i] < prices[i + 1]) {
                profit += prices[i + 1] - prices[i];
            } else {

            }
        }

        return profit;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}