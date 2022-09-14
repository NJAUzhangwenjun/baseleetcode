
package leetcode.editor.cn;

import org.springframework.util.StopWatch;

public class MinimumPathSum {
    public static void main(String[] args) {
        Solution solution = new MinimumPathSum().new Solution();
        StopWatch watch = new StopWatch("总共耗时");
        watch.start("最小路径和");
        // 
        watch.stop();
        System.out.println(watch.prettyPrint());
    }

    /**
     * 最小路径和
     * minimum-path-sum
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minPathSum(int[][] grid) {
            // f(i,j) = min[f(i-1,j),f(i,j-1)]
            int row = grid.length;
            int col = grid[0].length;
            //位置时 i，j时的最小路径和是 ints[i][j]
            int[][] ints = new int[row][col];
            ints[0][0] = grid[0][0];
            for (int i = 1; i < row; i++) {
                ints[i][0] = ints[i-1][0]+grid[i][0];
            }
            for (int i = 1; i < col; i++) {
                ints[0][i] = ints[0][i-1]+grid[0][i];
            }
            for (int i = 1; i < row; i++) {
                for (int j = 1; j < col; j++) {
                    ints[i][j] = grid[i][j] +Math.min(ints[i-1][j] ,ints[i][j-1] );
                }
            }
            return ints[row-1][col-1];
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}