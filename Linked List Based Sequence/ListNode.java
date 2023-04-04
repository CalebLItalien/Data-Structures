package proj3;

/**
 * The ListNode class is more data-specific than the LinkedList class.  It
 * details what a single node looks like.  This node has one data field,
 * holding a pointer to a String object. 
 *
 * Author: Caleb L'Italien
 * Last edited: 10/18/2022
 *
 * Invariants:
 * Next in each node points to the next node. Data stored is always a string.
 */

public class ListNode {
    public String data;
    public ListNode next;

    /**
     * Creates a new ListNode.
     *
     * @param new_data a string
     */
    public ListNode(String new_data) {
        data = new_data;
        next = null;
    }


    /**
     * Creates a new ListNode with a reference pointer to another ListNode
     *
     * @param new_data a string
     * @param nextNode a reference pointer to another ListNode
     */
    public ListNode(String new_data, ListNode nextNode) {
        data = new_data;
        next = nextNode;
    }


    /**
     * @return returns the string data
     */
    public String toString() {
    	return data;
    }
}
