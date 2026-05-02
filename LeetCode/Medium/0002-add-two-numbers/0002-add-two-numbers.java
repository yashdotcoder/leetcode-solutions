/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode curr = head;
        int carry = 0;

        while (l1 != null || l2 != null) {
            int n1 = l1 == null ? 0 : l1.val;
            int n2 = l2 == null ? 0 : l2.val;
            int res = n1 + n2 + carry;

            carry = res / 10;
            int units = res % 10;

            ListNode next = new ListNode(units);

            if (head == null) {
                head = new ListNode(units);
                curr = head;
            } else {
                curr.next = next;
                curr = next;
            }

            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }

        if (carry != 0) {
            curr.next = new ListNode(carry);
        }

        return head;
    }
}