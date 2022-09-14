
package leetcode.editor.cn;

import org.springframework.util.StopWatch;

public class HouseRobberIi {
    public static void main(String[] args) {
        Solution solution = new HouseRobberIi().new Solution();
        StopWatch watch = new StopWatch("总共耗时");
        watch.start("打家劫舍 II");
        // 
        watch.stop();
        System.out.println(watch.prettyPrint());
    }

    /**
     * 打家劫舍 II
     * house-robber-ii
     */
    // leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int rob(int[] nums) {
            if (null == nums || nums.length == 0) {
                return 0;
            }
            int n = nums.length;
            if (n == 1) {
                return nums[0];
            }
            if (nums.length == 2) {
                return Math.max(nums[0], nums[1]);
            }
            return Math.max(rob(nums, 0, n - 2), rob(nums, 1, n - 1));
        }

        /**
         * @param start 偷窃房屋的下标范围是偷窃房屋的开始下标
         * @param end   偷窃房屋的下标范围是偷窃房屋的结束下标
         * @return int
         */
        private int rob(int[] nums, int start, int end) {
            int first = nums[start], second = Math.max(nums[start], nums[start + 1]);
            for (int i = start + 2; i <= end; i++) {
                int temp = second;
                second = Math.max(first + nums[i], second);
                first = temp;
            }
            return second;
        }
    }
// leetcode submit region end(Prohibit modification and deletion)

}