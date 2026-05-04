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
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((l1, l2) -> Integer.compare(l1.val, l2.val));

        for (ListNode node: lists) {
            if (node != null)
                pq.offer(node);
        }
        ListNode head = new ListNode(-1);
        ListNode curr = head;

        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            curr.next = node;

            if (node.next != null) {
                pq.offer(node.next);
            }

            curr = curr.next;
        }


        return head.next;
    }
}