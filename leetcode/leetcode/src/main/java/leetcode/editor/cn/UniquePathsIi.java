
package leetcode.editor.cn;

import org.springframework.util.StopWatch;

public class UniquePathsIi {
    public static void main(String[] args) {
        Solution solution = new UniquePathsIi().new Solution();
        StopWatch watch = new StopWatch("总共耗时");
        watch.start("不同路径 II");
        // 
        watch.stop();
        System.out.println(watch.prettyPrint());
    }

    /**
     * 不同路径 II
     * unique-paths-ii
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            // f(i,j) = f(i-1,j)+f(i,j-1);
            int row = obstacleGrid.length;
            int con = obstacleGrid[0].length;
            //在i行j列时候的路径数是 ints[i,j]
            int[][] ints = new int[row][con];
            for (int i = 0; i < row && obstacleGrid[i][0] == 0; i++) {
                ints[i][0] = 1;
            }
            for (int j = 0; j < con && obstacleGrid[0][j] == 0; j++) {
                ints[0][j] = 1;
            }
            for (int i = 1; i < row; i++) {
                for (int j = 1; j < con; j++) {
                    if (obstacleGrid[i][j] == 1) {
                        continue;
                    }
                    ints[i][j] = ints[i - 1][j] + ints[i][j - 1];
                }
            }
            return ints[row - 1][con - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}