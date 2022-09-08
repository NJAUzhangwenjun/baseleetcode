
package leetcode.editor.cn;

import org.springframework.util.StopWatch;

public class MinCostClimbingStairs {
    public static void main(String[] args) {
        Solution solution = new MinCostClimbingStairs().new Solution();
        StopWatch watch = new StopWatch("总共耗时");
        watch.start("使用最小花费爬楼梯");
        //
        solution.minCostClimbingStairs(new int[]{1,100,1,1,1,100,1,1,100,1});
        watch.stop();
        System.out.println(watch.prettyPrint());
    }

    /**
     * 使用最小花费爬楼梯
     * min-cost-climbing-stairs
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minCostClimbingStairs(int[] cost) {
            int h = cost.length;
            int[] f = new int[h + 1];
            //爬上第i阶台阶时最小话费
            // f[i] = Math.min(f[i - 1] + cost[i - 1], f[i - 2] + cost[i - 2]);
            f[0] = f[1] = 0;
            for (int i = 2; i <= h; i++) {
                f[i] = Math.min(f[i - 1] + cost[i - 1], f[i - 2] + cost[i - 2]);
                System.out.println("f[i] = " + f[i]);
            }
            return f[h];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}