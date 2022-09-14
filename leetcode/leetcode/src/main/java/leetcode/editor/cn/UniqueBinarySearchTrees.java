
package leetcode.editor.cn;

import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.Map;

public class UniqueBinarySearchTrees {
    public static void main(String[] args) {
        Solution solution = new UniqueBinarySearchTrees().new Solution();
        StopWatch watch = new StopWatch("总共耗时");
        watch.start("不同的二叉搜索树");
        // 
        watch.stop();
        System.out.println(watch.prettyPrint());
    }

    /**
     * 不同的二叉搜索树
     * unique-binary-search-trees
     */
    // leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private final Map<Integer, Integer> map = new HashMap<>();

        public int numTrees(int n) {
            if (n <= 1) {
                return 1;
            }
            if (map.containsKey(n)) {
                return map.get(n);
            }
            int count = 0;
            for (int i = 1; i <= n; i++) {
                int left = numTrees(i - 1);
                int right = numTrees(n - i);
                count += left * right;
            }
            if (!map.containsKey(n)) {
                map.put(n, count);
            }
            return count;
        }
    }
// leetcode submit region end(Prohibit modification and deletion)

}