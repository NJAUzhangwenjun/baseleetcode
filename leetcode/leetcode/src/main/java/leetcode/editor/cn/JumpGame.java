
package leetcode.editor.cn;

import org.springframework.util.StopWatch;

public class JumpGame {
    public static void main(String[] args) {
        Solution solution = new JumpGame().new Solution();
        StopWatch watch = new StopWatch("总共耗时");
        watch.start("跳跃游戏");
        //
        boolean b = solution.canJump(new int[]{2, 3, 1, 1, 4});
        watch.stop();
        System.out.println("b = " + b);
        System.out.println(watch.prettyPrint());
    }

    /**
     * 跳跃游戏
     * jump-game
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean canJump(int[] nums) {
            // f(j) = f(i)&&nums[i]-(j-i)>=0&&j-i>=0
            // f(0) =  true,
            // f(1) = nums[0]-(1-0)>=0;
            // f(2) =
            int n = nums.length;
            boolean[] ints = new boolean[n];
            ints[0] = true;
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    ints[i] = ints[i] || (ints[j] && nums[j] >= (i - j));
                }
            }
            return ints[n - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}