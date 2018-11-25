package www.addTwoNumbers.com;

/**
 * % ȡ���� 432% 10 = 2
 * / ȡģ 432/10 = 43
 * Math.pow ��ƽ��
 * ����һ��Ҫ������(int) (lNode.val * Math.pow(10 , flg))���ٺ��� lNode = lNode.next
 * */
public class AddTwoNumbers {
	public static void main(String [] args){
		System.out.print(AddTwoNumbers.intToListNode(0));
	}
	
	public static int listNodeToInt(ListNode lNode) {
		int flg = 0;
		int count = 0;
		while (lNode != null) {
			count += (int) (lNode.val * Math.pow(10 , flg));
			lNode = lNode.next;
			flg ++;
		}
		return count;
	}
	
	public static ListNode intToListNode(int count){
		ListNode head = null;
		ListNode node = null;
		ListNode flg = null;
		if (count == 0) {
			return head = new ListNode(0);
		}
		while(count != 0){
			if (head == null) {
				head = new ListNode(count %10);
				flg = head;
			}else{
				node = new ListNode(count % 10);
				flg.next = node;
				flg = flg.next;
			}
			count = count /10;
		}
		return head;
	}
}
