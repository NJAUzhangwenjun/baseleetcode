
package leetcode.editor.cn;

import java.util.Arrays;

public class FindPivotIndex {
    /**
     * 主要
     *
     * @param args arg游戏
     */
    public static void main(String[] args) {
        Solution solution = new FindPivotIndex().new Solution();
        final int pivotIndex = solution.pivotIndex(new int[]{1, 7, 3, 6, 5, 6});
        System.out.println("pivotIndex = " + pivotIndex);
    }

    /**
     * 寻找数组的中心下标
     * find-pivot-index
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 主索引
         *
         * @param nums 全国矿工工会
         * @return int
         */
        public int pivotIndex(int[] nums) {
            //斯多葛
            int sum = Arrays.stream(nums).sum();
            int curSum = 0;
            //数组风格
            for (int i = 0; i < nums.length; i++) {
                sum -= nums[i];
                if (curSum == sum) {
                    return i;
                }
                curSum += nums[i];
            }
            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}