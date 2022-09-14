package leetcode.editor.cn;

import org.springframework.util.StopWatch;

/**
 * Bomb Enemy 炸弹人
 *
 *
 * 361. Bomb Enemy 炸弹人
 *
 *
 * 给定一个 2D 网格，每个单元格要么是一堵墙'W'，要么是一个敌人'E,或'空'0'的（数字 0），
 * 返回你可以使用一枚炸弹杀死的最大敌人。
 *
 * 炸弹会从种植点杀死同一行和列中的所有敌人，直到它击中墙壁，因为墙壁太坚固而无法被摧毁。
 * 请注意，您只能将炸弹放在空单元格中。
 *
 * @author zhangwenjun
 * @date 2022/09/09
 */
public class BombEnemy {
    public static void main(String[] args) {
        BombEnemy.Solution solution = new BombEnemy().new Solution();
        StopWatch watch = new StopWatch("总共耗时");
        watch.start("炸弹人");
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
        public int maxKilledEnemies(char[][] grid) {

            //假设 敌人的位置可以放置炸弹 敌人的位置炸死敌人数是 炸弹方向上一个位置 炸死人数+1，墙的位置 炸死人数是 0
            // f(i,j) = f(i+1,j) + f(i-1,j) + f(i,j-1) + f(i,j+1);

            int m = grid.length;
            int n = grid[0].length;
            // 在 位置 i，j 的格子上放置炸弹 可以炸死敌人数是 ints[i][j]
            int[][] ints = new int[m][n];

            return 0;
        }
//leetcode submit region end(Prohibit modification and deletion)
}}
