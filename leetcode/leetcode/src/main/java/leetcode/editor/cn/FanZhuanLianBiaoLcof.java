
package leetcode.editor.cn;

public class FanZhuanLianBiaoLcof {
    public static void main(String[] args) {
        Solution solution = new FanZhuanLianBiaoLcof().new Solution();
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        listNode1.next = listNode2;
        ListNode listNode3 = new ListNode(3);
        listNode2.next = listNode3;
        ListNode listNode4 = new ListNode(4);
        listNode3.next = listNode4;
        listNode4.next = new ListNode(5);

        ListNode listNode = solution.reverseList(listNode1);
        while (listNode != null) {
            System.out.println("listNode = " + listNode.val);
            listNode = listNode.next;
        }

    }
    /**
     * 反转链表
     * fan-zhuan-lian-biao-lcof
     */
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    class Solution {
        public ListNode reverseList(ListNode head) {

            ListNode pre = null;
            ListNode cur = head;
            while (null != cur) {
                ListNode next = cur.next;
                cur.next = pre;//反转

//            指针下移
                pre = cur;
                cur = next;
            }
            return pre;

        }
    }


//leetcode submit region end(Prohibit modification and deletion)

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}