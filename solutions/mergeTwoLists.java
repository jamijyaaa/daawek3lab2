import java.util.ArrayList;
import java.util.Collections;

class Solution {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ArrayList<Integer> values = new ArrayList<>();

        ListNode current = list1;

        while (current != null) {
            values.add(current.val);
            current = current.next;
        }

        current = list2;

        while (current != null) {
            values.add(current.val);
            current = current.next;
        }

        Collections.sort(values);

        ListNode dummy = new ListNode(0);
        current = dummy;

        for (int value : values) {
            current.next = new ListNode(value);
            current = current.next;
        }

        return dummy.next;
    }
}
