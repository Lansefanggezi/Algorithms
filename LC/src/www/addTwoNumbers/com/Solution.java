package www.addTwoNumbers.com;

public class Solution {
	public static void main(String[] args) {

	}
	
	/**
	 * LC上推荐的思路
	 * 
	 * */
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2){
		ListNode head = null;
		ListNode node = null;
		int val = 0;
		int flg = 0;
		
		while(l1 != null || l2 != null){
			int x = l1 != null ? l1.val : 0;
			int y = l2 != null ? l2.val : 0;
			val = (x + y + flg)%10;
			flg = (x + y + flg)/10;
			if (head == null) {
				head = new ListNode(val);
				node = head;
			}else{
				node.next = new ListNode(val);
				node = node.next;
			}
			if(l1 != null) l1 = l1.next;
			if(l2 != null) l2 = l2.next;
		}
		
		if (flg == 1) {
			node.next = new ListNode(1);
		}
		
		return head;
		
	}
}

