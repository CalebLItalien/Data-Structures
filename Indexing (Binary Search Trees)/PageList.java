package proj5;

/**
 * Models a page list, which holds a word and a list of pages
 *
 * @author Caleb L'Italien
 * @version 10/09/2022
 *
 * Invariants:
 * Word holds a string. List holds at most four page numbers.
 */

public class PageList implements Comparable<PageList> {
    private String word;
    private LinkedList list;
    private static final int MAX_IN_LIST = 4;

    public PageList() {
        word = null;
        list = new LinkedList();
    }


    /**
     * @return the word for this page list
     */
    public String getWord() {
        return word;
    }


    /**
     * Sets the word to a new word
     *
     * @param data a string
     */
    public void setWord(String data) {
        word = data;
    }


    /**
     * @param data an integer
     * @return true if the list contains the target data, else false
     */
    public boolean contains(int data) {
        return list.contains(data);
    }


    /**
     * Inserts data, if new, into the list, if not full
     *
     * @param data new data
     */
    public void addToList(int data) {
        if (!full() && !contains(data)) {
            list.insertAtEnd(data);
        }
    }


    /**
     * Compares a page list with another based on their words. Any word null is considered lowest precedence.
     *
     * @param other another page list
     * @return a positive integer if this word is greater in precedence (ASCII) than the other word, 0 if they are equal
     * precedence, else a negative number
     */
    public int compareTo(PageList other) {
        if (this.word == null && other.word == null) {
            return 0;
        }
        else if (other.word == null){
            return 1;
        }
        else if (this.word == null){
            return -1;
        }
        else {
            return this.word.compareTo(other.word);
        }
    }


    /**
     * @return true if the list is full, else false
     */
    public boolean full() {
        return list.getLength() == MAX_IN_LIST;
    }


    /**
     * Returns the page list as a printable string
     *
     * @return the word and, enclosed in curly brackets, a list of page numbers
     */
    public String toString() {
        String dataStr = "";
        if (word != null) {
            dataStr = word + " ";
        }
        String listStr = dataStr + "{";
        for (int counter = 0; counter < list.getLength(); counter ++){
            if (counter != list.getLength() - 1) {
                listStr = listStr + list.getDataAtIndex(counter) + ", ";
            }
            else{
                listStr = listStr + list.getDataAtIndex(counter);
            }
        }
        listStr = listStr + "}";
        return listStr;
    }
}