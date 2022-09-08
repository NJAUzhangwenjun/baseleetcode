
package leetcode.editor.cn;

import org.springframework.util.StopWatch;

import java.util.Arrays;

public class UniquePaths {
    public static void main(String[] args) {
        Solution solution = new UniquePaths().new Solution();
        StopWatch watch = new StopWatch("总共耗时");
        watch.start("不同路径");
        // 
        watch.stop();
        System.out.println(watch.prettyPrint());
    }

    /**
     * 不同路径
     * unique-paths
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int uniquePaths(int m, int n) {
            int[][] ints = new int[m][n];
            for (int i = 0; i < m; i++) {
                ints[i][0] = 1;
            }
            for (int i = 0; i < n; i++) {
                ints[0][i] = 1;
            }
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    ints[i][j] = ints[i - 1][j] + ints[i][j - 1];
                }
            }
            return ints[m - 1][n - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}