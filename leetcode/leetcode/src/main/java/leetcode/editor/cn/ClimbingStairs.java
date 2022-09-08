
package leetcode.editor.cn;
import org.springframework.util.StopWatch;

public class ClimbingStairs{
  public static void main(String[] args) {
        Solution solution = new ClimbingStairs().new Solution();
        StopWatch watch = new StopWatch("总共耗时");
        watch.start("爬楼梯");
        // 
        watch.stop();
        System.out.println(watch.prettyPrint());
  }
  /**
  * 爬楼梯
  * climbing-stairs
  */
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int climbStairs(int n) {
//        f（n） = f（n-1）+ f（n-2）
        return 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}