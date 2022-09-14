
package leetcode.editor.cn;

import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LetterCombinationsOfAPhoneNumber {
    public static void main(String[] args) {
        Solution solution = new LetterCombinationsOfAPhoneNumber().new Solution();
        StopWatch watch = new StopWatch("总共耗时");
        watch.start("电话号码的字母组合");
        //
        List<String> strings = solution.letterCombinations("23");
        watch.stop();
        System.out.println("strings = " + strings.toString());
        System.out.println(watch.prettyPrint());

    }

    /**
     * 电话号码的字母组合
     * letter-combinations-of-a-phone-number
     */
    // leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private final List<String> result = new ArrayList<>();
        String[] numString = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        public List<String> letterCombinations(String digits) {
            if (Objects.isNull(digits) || digits.length() == 0) {
                return result;
            }
            dfs(digits.toCharArray(), 0, "");
            return result;
        }

        private void dfs(char[] chars, int i, String s) {
            if (s.length() == chars.length) {
                result.add(s);
                return;
            }
            for (int j = i; j < chars.length; j++) {
                int index = Integer.parseInt(Character.toString(chars[j]));
                String c = numString[index];
                for (int k = 0; k < c.length(); k++) {
                    dfs(chars, j + 1, s + c.charAt(k));
                }
            }
        }
    }
// leetcode submit region end(Prohibit modification and deletion)

}