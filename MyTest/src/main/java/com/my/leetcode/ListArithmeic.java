package com.my.leetcode;

import java.util.Stack;

public class ListArithmeic {

    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * <p>
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * <p>
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * <p>
     * 示例：
     * <p>
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/add-two-numbers
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        ListNode l1 = new ListNode(8);
        l1.next = new ListNode(9);
        l1.next.next = new ListNode(9);
        ListNode l2 = new ListNode(2);
        ListNode l3 = addTwoNumbers(l1, l2);
        ListNode l4 = addTwoNumbers(new ListNode(5), new ListNode(5));
        System.out.println();
    }


    /**
     * 利用了递归的思想,将本次相加结果的进位值加入下一个非null元素value上
     * <p>
     * 执行结果：通过 显示详情
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.89%的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了94.26%的用户
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l3 = new ListNode(0);
        int nextValue = 0;
        if (l1 != null && l2 != null) {
            // 当前两个都有值的情况
            nextValue = (l1.val + l2.val) / 10;
            l3.val = (l1.val + l2.val) % 10;
            //        进位加到l1或l2,如果遍历完成,有进位值则l3天假最新节点;
            if (l1.next != null) {
                l1.next.val += nextValue;
            } else if (l2.next != null) {
                l2.next.val += nextValue;
            } else {
                if (nextValue != 0) {
                    l3.next = new ListNode(nextValue);
                    return l3;
                }
            }
            l3.next = addTwoNumbers(l1.next, l2.next);
            return l3;
        } else if (l1 == null && l2 == null) {
            // 返回条件,两个链表都为null
            return null;
        } else {
            // 返回条件,l1!=null或l2==null
            ListNode other = l1 != null ? l1 : l2;
            if (other.val == 10) {
                other.val = 0;
                if (other.next != null) {
                    other.next.val++;
                    other.next = addTwoNumbers(l1 != null ? l1.next : null, l2 != null ? l2.next : l2);
                } else {
                    l3.val = 1;
                    other.next = l3;
                }
            }
            return other;
        }
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

//    public int reverse(int x) {
//        int a = Integer.valueOf(x).toHexString();
//    }


    /**
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
     *
     * 示例：
     *
     * 给定一个链表: 1->2->3->4->5, 和 n = 2.
     *
     * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
     * 说明：
     *
     * 给定的 n 保证是有效的。
     *
     * 进阶：
     *
     * 你能尝试使用一趟扫描实现吗？
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     * <p>
     * 该地方使用了栈的特性
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        Stack<ListNode> datas = new Stack<>();
        ListNode item = head;

        if (head == null) {
            return null;
        }
        while (item != null) {
            datas.add(item);
            item = item.next;
        }
        ListNode itemPre;
        while (n > 0) {
            item = datas.pop();
            n--;
        }
        if (item == head) {
            return head.next;
        } else {
            itemPre = datas.peek();
            itemPre.next = item.next;
        }
        return head;
    }
}
