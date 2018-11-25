package www.addTwoNumbers.com;

public class AddTwoNum {

	public static void main(String[] args) {
		
	}
	/**
	 * 一开始自己的思路，特点是不清晰
	 * */
	public static ListNode addTwo(ListNode l1, ListNode l2){
		ListNode head = null;
		ListNode node = null;
		ListNode n1 = null;
		int flg = 0;
		int val = 0;
		while(l1 != null && l2 != null){
			val = (l1.val + l2.val + flg)%10;
			flg = (l1.val + l2.val + flg)/10;
			if (head == null) {
				head = new ListNode(val);
				node = head;
				n1 = node;
			}else {
				node = new ListNode(val);
				n1.next = node;
				n1 = n1.next;
			}
			l1 = l1.next;
			l2 = l2.next;
		}
		
		if (l1 != null) {
			node.next = l1;
			node = node.next;
		}else if(l2 != null){
			node.next = l2;
			node = node.next;
		}else{
			node.next = new ListNode(1);
			n1.next = node;
			return head;
		}
		while(flg != 0){
			if (node == null) {
				node = new ListNode(1);
				n1.next = node;
				flg = 0;
			}else{
				val = (node.val + flg)%10;
				flg = (node.val + flg)/10;
				node.val = val;
				node = node.next;
			}
		}
		return head;
	}
}
