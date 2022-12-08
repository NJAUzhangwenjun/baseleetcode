/**
 * 题目Id：88
 * 题目：合并两个有序数组
 * 日期：2022-11-17 01:26:02
 */
package leetcode.editor.cn;

import org.springframework.util.StopWatch;

import java.util.Arrays;

public class MergeSortedArray {
    public static void main(String[] args) {
        Solution solution = new MergeSortedArray().new Solution();
        StopWatch watch = new StopWatch("合并两个有序数组");
        watch.start("merge-sorted-array");
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        solution.merge(nums1, 3, nums2, 3);
        watch.stop();
        System.out.println("nums1 = " + Arrays.toString(nums1));
        System.out.println(watch.prettyPrint());
    }

    // leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int mergeIndex = m + n - 1;
            m--;
            n--;
            while (mergeIndex >= 0) {
                if (m < 0 || (n >= 0 && nums1[m] <= nums2[n])) {
                    nums1[mergeIndex--] = nums2[n--];
                    continue;
                }
                nums1[mergeIndex--] = nums1[m--];
            }
        }
    }
// leetcode submit region end(Prohibit modification and deletion)

} 