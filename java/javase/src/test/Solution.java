package test;
import java.util.ArrayList;
/**
 * 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
 * @Description: <Function>
 * @author  karlieswift
 * @date 2020年5月25日
 * @version "13.0.1"
 */
public class Solution {
	public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
		ArrayList<Integer> list_1 = new ArrayList<Integer>();
		ArrayList<Integer> list_2 = new ArrayList<Integer>();
		ListNode p = listNode;
		while (p != null) {
			list_1.add(p.val);
			p = p.next;
		}
		for (int i = list_1.size() - 1; i >= 0; i--) {
			list_2.add(list_1.get(i));
		}
		return list_2;
	}
}
/*
//递归思路：
public class Solution {
    ArrayList<Integer> list_1=new ArrayList<Integer>();
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        while(listNode!=null){
            printListFromTailToHead(listNode.next);
            list_1.add(listNode.val);
        }
        return list_1;
    }
}
*/





 class ListNode {
       int val;
       ListNode next = null;

     ListNode(int val) {
          this.val = val;
      }
   }












