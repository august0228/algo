package linked_list;

/**
 *
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 *
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 *
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author sun. on 9/8/2019.
 */
public class PalindromeLinkedList {

	public boolean isPalindrome(ListNode head) {

		if (head == null || head.next == null) {
			return true;
		}

		ListNode prev = null;
		ListNode fast = head;
		ListNode slow = head;

		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			ListNode next = slow.next;
			slow.next = prev;
			prev = slow;
			slow = next;
		}
		/* 奇数个链表 */
		if (fast != null) {
			slow = slow.next;
		}

		while (slow != null) {
			if (slow.val != prev.val) {
				return false;
			}
			slow = slow.next;
			prev = prev.next;
		}
		return true;
	}

	public static void main(String[] args) {
		ListNode a = new ListNode(1);
		ListNode b = new ListNode(2);
		ListNode c = new ListNode(3);
		ListNode c1 = new ListNode(4);
		ListNode d = new ListNode(3);
		ListNode e = new ListNode(2);
		ListNode f = new ListNode(1);
		a.next = b;
		b.next = c;
		c.next = c1;
		c1.next = d;
		d.next = e;
		e.next = f;
		System.out.println(new PalindromeLinkedList().isPalindrome(a));
	}
}


