
package leetcode.editor.cn;

import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;

public class CombinationSumIii {
    public static void main(String[] args) {
        Solution solution = new CombinationSumIii().new Solution();
        StopWatch watch = new StopWatch("总共耗时");
        watch.start("组合总和 III");
        //
        List<List<Integer>> lists = solution.combinationSum3(3, 9);
        watch.stop();
        System.out.println("lists = " + lists.toString());
        System.out.println(watch.prettyPrint());
    }

    /**
     * 组合总和 III
     * combination-sum-iii
     */
    // leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private final List<List<Integer>> result = new ArrayList<>();

        public List<List<Integer>> combinationSum3(int k, int n) {
            if (k > n) {
                return result;
            }
            dfs(n, k, new ArrayList<>(), 1, 0);
            return result;
        }

        private void dfs(int n, int k, List<Integer> res, int i, int sum) {
            if (res.size() > k || n < sum) {
                return;
            }
            if (res.size() == k) {
                if (n == sum) {
                    result.add(new ArrayList<>(res));
                }
                return;
            }
            for (int j = i; j <= n && j <= 9; j++) {
                res.add(j);
                dfs(n, k, res, j + 1, sum + j);
                res.remove(res.size() - 1);
            }
        }
    }
// leetcode submit region end(Prohibit modification and deletion)

}