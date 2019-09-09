package linked_list;

import java.util.Objects;
import java.util.Scanner;

/**
 * 维护一个有序单链表，越靠近尾部的为越晚访问的，当有新数据被访问时，从链表头开始顺序遍历链表。
 *
 * 	1. 如果此数据之前已经被缓存了，删除之前的，放到头部
 * 	2. 如果没有，分为两种情况：
 *      缓存未满，直接插到头部。
 *     缓存已满，尾节点删除，插入头部。
 *     访问复杂度为O(n)
 * @author sun. on 9/9/2019.
 */
public class LRULinkedList<T> {
	private static final int DEFAULT_CAPACITY = 10;
	private ListNode<T> head;
	private int length;
	private int capacity;

	public LRULinkedList() {
		this.capacity = DEFAULT_CAPACITY;
		this.length = 0;
		this.head = new ListNode<>();
	}

	public LRULinkedList(int capacity) {
		this.capacity = capacity;
		this.length = 0;
		this.head = new ListNode<>();
	}

	public void add(T value) {
		ListNode<T> prevNode = findPrevNode(value);
		if (prevNode != null) {
			//			删除原来的
			delete(prevNode);
			//			加到头部
			addFirst(value);
		} else {
			if (length >= capacity) {
				//	删除尾节点
				deleteLast();
			}
			//	加到头部
			addFirst(value);
		}

	}

	private void addFirst(T value) {
		ListNode<T> curr = new ListNode<>(value);
		curr.next = head.next;
		head.next = curr;
		length++;
	}

	private void delete(ListNode<T> prevNode) {
		ListNode next = prevNode.next;
		prevNode.next = next.next;
		next = null;
		length--;
	}

	private void deleteLast() {
		ListNode<T> prev = head;
		if (prev == null) {
			return;
		}
		while (prev.next.next != null) {
			prev = prev.next;
		}

		ListNode<T> next = prev.next;
		prev.next = null;
		next = null;
		length--;
	}

	ListNode<T> findPrevNode(T value) {
		ListNode<T> node = head;
		while (node.next != null) {
			if (Objects.equals(value, node.next.val)) {
				return node;
			}
			node = node.next;
		}
		return null;
	}

	void print() {
		ListNode<T> node = head;
		while (node != null) {
			System.out.println(node.val);
			node = node.next;
		}
	}

	public static void main(String[] args) {
		LRULinkedList<String> lruLinkedList = new LRULinkedList<>();
		Scanner sc = new Scanner(System.in);
		while (true) {
			lruLinkedList.add(sc.nextLine());
			lruLinkedList.print();
		}
	}

}


class ListNode<T> {
	T val;
	ListNode next;

	ListNode() {
	}

	ListNode(T x) {
		val = x;
	}

}
