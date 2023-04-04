package proj4;

/**
 * Models a stack of generic objects
 *
 * @param <T> a generic object
 * @author Caleb L'Italien
 * @version 10/28/2022
 *
 * Invariants:
 * The linked list stores all contents of the stack.
 */

public class Stack<T> {
    private LinkedList<T> linkedlist;
    private final int EMPTY = 0;

    /**
     * Creates a default stack
     */
    public Stack() {
       linkedlist = new LinkedList<>();
    }


    /**
     * @return true if the linked list is empty, else false
     */
    public boolean isEmpty() {  
    	return size() == EMPTY;
    }


    /**
     * Inserts a new object at the top of the stack
     * @param toPush a generic object
     */
    public void push(T toPush) {
        linkedlist.insertAtHead(toPush);
    }


    /**
     * Removes and returns the data of the object at the top of the stack if there is one, else null
     * @return the data of the first object on the stack
     */
    public T pop() {
        if (!isEmpty()) {
            T data = linkedlist.getFirstNode().data;
            linkedlist.removeHead();
            return data;
        }
        return null;
    }


    /**
     * @return the data of the object at the top of the stack if there is one, else null
     */
    public T peek() {
        if (!isEmpty()) {
            return linkedlist.getFirstNode().data;
        }
        return null;
    }


    /**
     * @return the length of the stack
     */
    public int size() {
    	return linkedlist.getLength();
    }


    /**
     * Creates a printable version of the stack. The top of the stack is indicated by a ">", with each element separated
     * by a comma, all enclosed in curly brackets.
     *
     * @return a string representation of the stack
     */
    public String toString() {
        String list = "{";
        if (!isEmpty()) {
            for (int position = 0; position < linkedlist.getLength(); position++) {
                T data = linkedlist.getDataAtIndex(position);
                String dataString = String.valueOf(data);

                if (position == 0){
                    dataString = ">" + dataString;
                }
                if (position != linkedlist.getLength() - 1){
                    dataString = dataString + ",";
                }
                list = list + dataString;
            }
        }
        else{
            list = list + ">";
        }
        list = list + "}";
        return list;
    }
}