
package leetcode.editor.cn;

import org.springframework.util.StopWatch;

import java.util.*;

public class CombinationSumIi {
    public static void main(String[] args) {
        Solution solution = new CombinationSumIi().new Solution();
        StopWatch watch = new StopWatch("总共耗时");
        watch.start("组合总和 II");
        //
        List<List<Integer>> sum2 = solution.combinationSum2(new int[]{2, 5, 2, 1, 2}, 5);
        watch.stop();
        System.out.println("sum2 = " + sum2.toString());
        System.out.println(watch.prettyPrint());
    }

    /**
     * 组合总和 II
     * combination-sum-ii
     */
    // leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private final List<List<Integer>> result = new ArrayList<>();

        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            if (Objects.isNull(candidates) || candidates.length == 0 || target <= 0) {
                return Collections.emptyList();
            }
            Arrays.sort(candidates);
            dfs(candidates, target, 0, new ArrayList<>());
            return result;
        }

        private void dfs(int[] candidates, int target, int start, List<Integer> res) {
            if (target == 0) {
                result.add(new ArrayList<>(res));
            }
            for (int i = start; i < candidates.length && target >= candidates[i]; i++) {
                // 跳过同一树层使用过的元素
                if (i > start && candidates[i] == candidates[i - 1]) {
                    continue;
                }

                res.add(candidates[i]);
                dfs(candidates, target - candidates[i], i + 1, res);
                res.remove(res.size() - 1);
            }
        }
    }
// leetcode submit region end(Prohibit modification and deletion)

}