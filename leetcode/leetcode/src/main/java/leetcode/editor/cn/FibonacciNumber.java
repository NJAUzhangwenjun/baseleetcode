
package leetcode.editor.cn;

import org.springframework.util.StopWatch;

public class FibonacciNumber {
    public static void main(String[] args) {
        Solution solution = new FibonacciNumber().new Solution();
        StopWatch watch = new StopWatch("总共耗时");
        watch.start("斐波那契数");
        // 
        watch.stop();
        System.out.println(watch.prettyPrint());
    }

    /**
     * 斐波那契数
     * fibonacci-number
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int fib(int n) {
            if (n <= 1) {
                return n;
            }
            int[] ints = new int[n + 1];
            ints[0] = 0;
            ints[1] = 1;
            return fib(ints);
        }

        private int fib(int[] ints) {
            for (int i = 2; i < ints.length; i++) {
                ints[i] = ints[i - 1] + ints[i - 2];
            }
            return ints[ints.length - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}