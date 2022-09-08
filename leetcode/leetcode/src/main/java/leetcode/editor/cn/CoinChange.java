
package leetcode.editor.cn;

import org.springframework.util.StopWatch;

import java.util.*;

public class CoinChange {
    public static void main(String[] args) {
        Solution solution = new CoinChange().new Solution();
        StopWatch watch = new StopWatch("总共耗时");
        watch.start("零钱兑换");
        int i = solution.coinChange(new int[]{1,1,2}, 11);
        watch.stop();
        System.out.println("i = " + i);
        System.out.println(watch.prettyPrint());
    }

    /**
     * 零钱兑换
     * coin-change
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int coinChange(int[] coins, int amount) {
//       amounts[i] 表示 当凑成金额为i时，使用的最小硬币数量
//
            int[] amounts = new int[amount + 1];
            Arrays.fill(amounts, Integer.MAX_VALUE);
            amounts[0] = 0;
            //f(x) = min[f(x-c0),f(x-c1),...,f(x-cn)]
            //i 为凑成金额，amounts[i]为此时的硬币数量
            for (int money = 1; money < amounts.length; money++) {
//            选择硬币
                for (int coinIndex = 0; coinIndex < coins.length; coinIndex++) {
                    //选择比上一个数量更加小的硬币
                    if (money >= coins[coinIndex] && amounts[money - coins[coinIndex]] + 1 < amounts[money]) {
                        amounts[money] = amounts[money - coins[coinIndex]] + 1;
                    }
                }
            }
            return amounts[amount] == Integer.MAX_VALUE ? -1 : amounts[amount];
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}