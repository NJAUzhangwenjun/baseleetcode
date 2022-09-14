package leetcode.editor.cn;

/**
 * 一排 n 栋房子，每栋房子都可以涂上k种颜色中的一种：红色、蓝色、绿色。。。。用某种颜色粉刷每个房子的成本是不同的。你必须给所有的房子涂漆，这样相邻的两个房子就没有相同的颜色。
 * <p>
 * 用某种颜色粉刷每栋房子的成本由  成本矩阵表示。例如，  用红色粉刷房子 0 的成本是costs[0][0]；
 * costs[1][2]是用绿色粉刷房子 1 的成本，依此类推... 找出粉刷所有房子的最低成本。
 * 注：
 * 所有成本均为正整数。
 * <p>
 * 例子：
 * <p>
 * 输入：[[17,2,17],[16,16,5],[14,3,19]]
 * 输出：10
 * 说明：将房屋 0 涂成蓝色，将房屋 1 涂成绿色，将房屋 2 涂成蓝色。
 * 最低成本：2 + 5 + 3 = 10。
 */
public class PaintHouse2 {
    /**
     * 最小成本二世
     *
     * @param costs 成本
     * @return int
     */
    public static int minCostII(int[][] costs) {
        //    f(i) = f(i-1)+min(costs[i][0],costs[i][1],costs[i][2])&&[(i-1)!=j]
        int row = costs.length;
        int col = costs[0].length;
        //粉刷第 i 个房子时 使用的总的最小花费
        int[] AllCost = new int[row];
        AllCost[0] = Integer.MAX_VALUE;
        //记录上一次使用的颜料类型
        int lastIndex = -1;
        //初始化第一个
        for (int i = 0; i < col; i++) {
            if (AllCost[0] > costs[0][i]) {
                AllCost[0] = costs[0][i];
                lastIndex = i;
            }
        }

        for (int i = 1; i < row; i++) {
            int curMinCost = Integer.MAX_VALUE;
            for (int j = 0; j < col; j++) {
                if (lastIndex == j || curMinCost <= costs[i][j]) {
                    continue;
                }
                curMinCost = costs[i][j];
                lastIndex = j;
            }
            AllCost[i] = curMinCost + AllCost[i - 1];
        }
        return AllCost[row - 1];
    }

    public static void main(String[] args) {
        int i = minCostII(new int[][]{
                {1, 5, 3}, {2, 9, 4}
        });
        System.out.println("i = " + i);
    }
}