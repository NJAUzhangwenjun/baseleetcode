
package leetcode.editor.cn;

import org.springframework.util.StopWatch;

public class HouseRobber {
    public static void main(String[] args) {
        Solution solution = new HouseRobber().new Solution();
        StopWatch watch = new StopWatch("总共耗时");
        watch.start("打家劫舍");
        int rob = solution.rob(new int[]{1,2,3,1});
        watch.stop();
        System.out.println("rob = " + rob);
        System.out.println(watch.prettyPrint());
    }

    /**
     * 打家劫舍
     * house-robber
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int rob(int[] nums) {
            //    f(i)=max[f(i-2)+nums[i-1],f(i-1)]
            if (null == nums || nums.length == 0) {
                return 0;
            }
            int n = nums.length;
            if (n == 1) {
                return nums[0];
            }
            //ints[i] 前i个房子 最多偷多少硬币
            int[] ints = new int[2];
            ints[1] = nums[0];
            for (int i = 2; i <= n; i++) {
                int cur = ints[1];
                ints[1] = Math.max(ints[1], ints[0] + nums[i - 1]);
                ints[0] = cur;
            }
            return ints[1];
        }

        public int rob1(int[] nums) {
            //     前 i-1 个房子如果偷了 则 f(i-2)+nums[i-1]

            //    如果没偷 f(i-1)
            //    f(i)=max[f(i-2)+nums[i-1],f(i-1)]
            if (null == nums || nums.length == 0) {
                return 0;
            }
            int n = nums.length;
            if (n == 1) {
                return nums[0];
            }
            //ints[i] 前i个房子 最多偷多少硬币
            int[] ints = new int[n + 1];
            ints[0] = 0;
            ints[1] = nums[0];
            for (int i = 2; i <= n; i++) {
                ints[i] = Math.max(ints[i - 1], ints[i - 2] + nums[i - 1]);
            }
            return ints[n];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}