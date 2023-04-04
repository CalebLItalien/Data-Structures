package proj4;

/**
 * Models a list of nodes
 *
 * Author: Caleb L'Italien
 * Last edited: 10/28/2022
 *
 * Invariants:
 * The firstNode points to the first node in the linked list or null if there are no nodes. The length is the number of
 * nodes in the list or zero if there are no nodes. If the length is zero, the contents of the linked list are
 * irrelevant. The last node in the list points to null. 
 */

public class LinkedList<T> {
    private final int EMPTY = 0;

    private int length;
    private ListNode<T> firstNode;

    /**
     * Creates an empty linked list
     */
    public LinkedList() {
        length = 0;
        firstNode = null;
    }


    /**
     * @return returns the length of the linked list
     */
    public int getLength() {
        return length;
    }


    /**
     * @return returns the first node reference pointer
     */
    public ListNode<T> getFirstNode() {
        return firstNode;
    }


    /**
     * Inserts new data at the beginning of the linked list
     *
     * @param data a string
     */
    public void insertAtHead(T data) {
    	ListNode<T> newNode = new ListNode<T>(data);
        if (isEmpty()) {
            firstNode = newNode;
        }
        else {
            newNode.next = firstNode;
            firstNode = newNode;
        }
        length++;
    }


    /**
     * Removes the first node from the list
     */
    public void removeHead() {
        if (!isEmpty()) {
            firstNode = firstNode.next;
            length--;
        }
    }


    /**
     * Returns the data at a given index, or null if there is no data
     *
     * @param index an integer position
     * @return data if there is one, else null
     */
    public T getDataAtIndex(int index) {
        if (index < getLength()){
            ListNode<T> runner = getFirstNode();
            for (int position = 0; position < index && runner != null; position++){
                runner = runner.next;
            }
            return runner.data;
        }
        return null;
    }


    /**
     * Removes the data at a given index
     *
     * @param index an integer position
     */
    public void removeAtIndex(int index) {
        if (validRemoveIndex(index) && !isEmpty()) {
            if (index == 0) {
                removeHead();
            }
            else {
                int position = 0;
                ListNode<T> runner = getFirstNode();
                ListNode<T> prev = null;

                while (runner != null && position != index) {
                    prev = runner;
                    runner = runner.next;
                    position++;
                }

                runner = runner.next;
                prev.next = runner;
                length--;
            }
        }
    }


    /**
     * Inserts new data into the list at a given index
     *
     * @param data a string
     * @param index an integer position
     */
    public void insertAtIndex(T data, int index) {
        if (validInsertIndex(index)) {
            if (index == 0 || isEmpty()) {
                insertAtHead(data);
            }
            else {
                int position = 0;
                ListNode<T> runner = getFirstNode();
                ListNode<T> prev = null;

                while (runner != null && position != index) {
                    prev = runner;
                    runner = runner.next;
                    position++;
                }
                ListNode<T> newNode = new ListNode(data);
                prev.next = newNode;
                newNode.next = runner;
                length++;
            }
        }
    }


    /**
     * Inserts new data at the end of the list
     *
     * @param data a string
     */
    public void insertAtEnd(T data) {
        if (isEmpty()){
            insertAtHead(data);
        }
        else {
            ListNode<T> runner = firstNode;
            int position = 0;
            while (runner != null) {
                runner = runner.next;
                position ++;
            }
            insertAtIndex(data, position);
        }
    }


    /**
     * Sets the length to zero
     */
    public void clear() {
        length = EMPTY;
    }


    /**
     * Checks if the given index is within the bounds of the list or one more than the upper bound
     *
     * @param index an integer position
     * @return a boolean
     */
    private boolean validInsertIndex(int index) {
        return index <= getLength();
    }


    /**
     * Checks if the list is empty
     *
     * @return a boolean
     */
    private boolean isEmpty() {
        return getLength() == EMPTY;
    }


    /**
     * Checks if the given index is within the bounds of the list
     *
     * @param index an integer position
     * @return a boolean
     */
    private boolean validRemoveIndex(int index) {
        return index < getLength();
    }


    /**
     * Produces a string representation of the linked list. Data is listed with commas, enclosed in parentheses.
     *
     * @return a string representation of the linked list
     */
	public String toString() {
		String toReturn = "(";
		ListNode<T> runner = firstNode;
		while (runner != null){
			toReturn = toReturn + runner;
			runner = runner.next;
			if(runner != null){
				toReturn = toReturn + ", ";
			}
		}
		toReturn = toReturn + ")";
		return toReturn;
	}
}