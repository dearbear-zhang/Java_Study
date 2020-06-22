package com.my.leetcode;

public class ListArithmeic {

    public static void main(String[] args) {
        addTwoNumbers(new ListNode(5), new ListNode(5));
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l3 = new ListNode(0);
        int nextValue = 0;
        if (l1 != null && l2 != null) {
            nextValue = (l1.val + l2.val) / 10;
            l3.val = (l1.val + l2.val) % 10;
        } else if (l1 == null && l2 == null) {
            // 返回条件,两个链表都为null
            return null;
        } else if (l1 != null) {
            // 返回条件,l1!=null, l2==null
            if (l1.val == 10){
                l3.val = 1;
                l1.val--;
                
            }
        } else if (l2 != null) {
            // 返回条件,l1==null, l2!=null
            return l2;
        }
//        进位加到l1或l2,如果遍历完成,有进位值则l3天假最新节点;
        if (l1.next != null) {
            l1.next.val += nextValue;
        } else if (l2.next != null) {
            l2.next.val += nextValue;
        } else {
            if (nextValue != 0) {
                l3.next = new ListNode(nextValue);
            }
        }
        l3.next = addTwoNumbers(l1.next, l2.next);
        return l3;
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
