package leetcode.editor.cn.bytedance;

import org.junit.Test;

/**
 * 主要
 *
 * @author zhangwenjun
 * @date 2022/10/08
 */
public class Main {
    /**
     * 给定⼀个单链表的头节点 head,实现⼀个调整单链表的函
     * 数，使得每K个节点之间为⼀组进⾏逆序，并且从链表的尾部
     * 开始组起，头部剩余节点数量不够⼀组的不需要逆序。（不能
     * 使⽤队列或者栈作为辅助）
     *
     * 链表:1->2->3->4->5->6->7->8->null, K = 3。那么 6->7->8， 3->4->5，1->2各位⼀组。调整后：
     *     1->2->5->4->3->8->7->6->null。其中 1，2不调整，因为不够
     * ⼀组。
     */

    @Test
    public void reverseKGroupTest() {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);
        ListNode node5 = new ListNode(6);
        ListNode node6 = new ListNode(7);
        ListNode node7 = new ListNode(8);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        ListNode copy = ListNode.copy(head);
        System.out.println( copy);
        ListNode listNode = reverseKGroup(head, 3);
        ListNode copy1 = ListNode.copy(listNode);
        System.out.println(copy1);
    }

    //给定⼀个单链表的头节点 head,实现⼀个调整单链表的函
    //      * 数，使得每K个节点之间为⼀组进⾏逆序，并且从链表的尾部
    //      * 开始组起，头部剩余节点数量不够⼀组的不需要逆序。（不能
    //      * 使⽤队列或者栈作为辅助）
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode cur = head;
        ListNode pre = null;
        ListNode next = null;
        int count = 0;
        while (cur != null && count != k) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
            count++;
        }
        if (next != null) {
            head.next = reverseKGroup(next, k);
        }
        return pre;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            ListNode cur = this;
            stringBuilder.append(cur.val);
           while (cur.next != null) {
               stringBuilder.append("->");
               stringBuilder.append(cur.next.val);
               cur = cur.next;
              }
            return stringBuilder.toString();
        }

        //    深拷贝
        public static ListNode copy(ListNode head) {
            if (head == null) {
                return null;
            }
            ListNode newHead = new ListNode(head.val);
            ListNode cur = newHead;
            ListNode p = head.next;
            while (p != null) {
                cur.next = new ListNode(p.val);
                cur = cur.next;
                p = p.next;
            }
            return newHead;
        }
    }


}
