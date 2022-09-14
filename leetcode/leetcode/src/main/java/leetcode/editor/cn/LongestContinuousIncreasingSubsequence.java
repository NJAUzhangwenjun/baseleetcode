
package leetcode.editor.cn;

import org.springframework.util.StopWatch;

import java.util.Objects;

public class LongestContinuousIncreasingSubsequence {
    public static void main(String[] args) {
        Solution solution = new LongestContinuousIncreasingSubsequence().new Solution();
        StopWatch watch = new StopWatch("总共耗时");
        watch.start("最长连续递增序列");
        //
        int length = solution.findLengthOfLCIS(new int[]{1, 3, 5, 7});
        watch.stop();
        System.out.println("length = " + length);
        System.out.println(watch.prettyPrint());
    }

    /**
     * 最长连续递增序列
     * longest-continuous-increasing-subsequence
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findLengthOfLCIS(int[] nums) {
            if (null == nums || nums.length == 0) {
                return 0;
            }
            int length = nums.length;
            int maxLength = 1;
            // 第 i 个 元素时 的递增子序列长度
            int curLengthOfLCIS = 1;
            for (int i = 1; i < length; i++) {
                if (nums[i - 1] < nums[i]) {
                    curLengthOfLCIS += 1;
                } else {
                    curLengthOfLCIS = 1;
                }
                maxLength = Math.max(maxLength, curLengthOfLCIS);
            }
            return maxLength;
        }

        public int findLengthOfLCIS1(int[] nums) {

            if (Objects.isNull(nums) || nums.length == 0) {
                return 0;
            }
            int length = nums.length;
            if (length == 1) {
                return 1;
            }
            int result = 0;

            for (int i = 0; i < length; i++) {
                int j = i + 1;
                for (; j < length; j++) {
                    if (nums[j - 1] >= nums[j]) {
                        result = Math.max(result, j - i);
                        break;
                    }
                }
                if (j == length) {
                    result = Math.max(result, j - i);
                }
            }
            return result;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}