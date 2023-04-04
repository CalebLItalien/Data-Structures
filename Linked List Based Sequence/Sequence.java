package proj3;  // Gradescope needs this.

/**
 * Models a container, holds a list of strings. Has a default capacity of 10 or a capacity passed in.
 *
 * Author: Caleb L'Italien
 * Last edited: 10/18/2022
 *
 * Honor Code:
 * I affirm that I have carried out the attached academic endeavors with full academic honesty, in accordance with the
 * Union College Honor Code and the course syllabus.
 *
 * Invariants:
 * The contents are stored in sequence at indices 0 to size - 1. If there is no current index, current is -1.
 * The contents at indices larger than the size or at index = size are irrelevant.
 * If the size is zero, the contents of the sequence are irrelevant (null).
 * Size is always between zero and the length of the linked list.
 * The current index is either negative one if there is no current index, or somewhere between zero and size minus
 * one.
 */

public class Sequence {
    private int current;
    private int capacity;
    private LinkedList contents;

    private final int EMPTY = 0;
    private final int NO_CURRENT = -1;
    private final int CURRENT_INCREMENT = 1;
    private final int DEFAULT_CAPACITY = 10;


    /**
     * Creates a new sequence with initial capacity 10.
     */
    public Sequence() {
        this.current = NO_CURRENT;
        this.capacity = DEFAULT_CAPACITY;
        this.contents = new LinkedList();
    }
    

    /**
     * Creates a new sequence.
     * 
     * @param initialCapacity the initial capacity of the sequence.
     */
    public Sequence(int initialCapacity) {
        this.current = NO_CURRENT;
        if (validCapacity(initialCapacity)) {
            this.capacity = initialCapacity;
        }
        this.contents = new LinkedList();
    }
    

    /**
     * Adds a string to the sequence in the location before the
     * current element. If the sequence has no current element, the
     * string is added to the beginning of the sequence.
     *
     * The added element becomes the current element.
     *
     * If the sequences's capacity has been reached, the sequence will
     * expand to twice its current capacity plus 1.
     *
     * @param value the string to add.
     */
    public void addBefore(String value) {
        if (getCapacity() == contents.getLength()) {
            ensureCapacity(getCapacity() * 2 + 1 );
        }
        if (isCurrent()){
            contents.insertAtIndex(value, getCurrentIndex());
        }
        else{
            contents.insertAtHead(value);
            start();
        }
    }
    
    
    /**
     * Adds a string to the sequence in the location after the current
     * element. If the sequence has no current element, the string is
     * added to the end of the sequence.
     *
     * The added element becomes the current element.
     *
     * If the sequences's capacity has been reached, the sequence will
     * expand to twice its current capacity plus 1.
     *
     * @param value the string to add.
     */
    public void addAfter(String value) {
        if (getCapacity() == contents.getLength()){
            ensureCapacity(getCapacity() * 2 + 1);
        }
        if (isCurrent()){
            if (currentAtEnd()){
                contents.insertAtEnd(value);
                setCurrent(size() - 1);
            }
            else {
                contents.insertAtIndex(value, getCurrentIndex()+ 1);
                advance();
            }
        }
        else{
            contents.insertAtEnd(value);
            setCurrent(size() - 1);
        }
    }

    
    /**
     * @return true if and only if the sequence has a current element.
     */
    public boolean isCurrent() {
        return getCurrentIndex() != NO_CURRENT;
    }
    
    
    /**
     * @return the capacity of the sequence.
     */
    public int getCapacity() {
        return capacity;
    }

    
    /**
     * @return the element at the current location in the sequence, or
     * null if there is no current element.
     */
    public String getCurrent() {
        if (isCurrent()) {
            return contents.getDataAtIndex(getCurrentIndex());
        }
        return null;
    }
    
    
    /**
     * Increase the sequence's capacity to be
     * at least minCapacity.  Does nothing
     * if current capacity is already >= minCapacity.
     *
     * @param minCapacity the minimum capacity that the sequence
     * should now have.
     */
    public void ensureCapacity(int minCapacity) {
        if (getCapacity() < minCapacity){
            capacity = minCapacity;
        }
    }

    
    /**
     * Places the contents of another sequence at the end of this sequence.
     *
     * If adding all elements of the other sequence would exceed the
     * capacity of this sequence, the capacity is changed to make (just enough) room for
     * all of the elements to be added.
     * 
     * Postcondition: NO SIDE EFFECTS!  the other sequence should be left
     * unchanged.  The current element of both sequences should remain
     * where they are. (When this method ends, the current element
     * should refer to the same element that it did at the time this method
     * started.)
     *
     * @param another the sequence whose contents should be added.
     */
    public void addAll(Sequence another) {
        int sumSize = this.size() + another.size();
        if (sumSize > this.getCapacity()){
            ensureCapacity(sumSize);
        }
        for (int position = 0; position < another.size(); position ++){
            String data = another.contents.getDataAtIndex(position);
            contents.insertAtEnd(data);
        }
    }

    
    /**
     * Move forward in the sequence so that the current element is now
     * the next element in the sequence.
     *
     * If the current element was already the end of the sequence,
     * then advancing causes there to be no current element.
     *
     * If there is no current element to begin with, do nothing.
     */
    public void advance() {
        if (isCurrent()){
            if (currentAtEnd()){
                setCurrent(NO_CURRENT);
            }
            else{
                setCurrent(getCurrentIndex() + CURRENT_INCREMENT);
            }
        }
    }

    
    /**
     * Make a copy of this sequence.  Subsequence changes to the copy
     * do not affect the current sequence, and vice versa.
     * 
     * Postcondition: NO SIDE EFFECTS!  This sequence's current
     * element should remain unchanged.  The clone's current
     * element will correspond to the same place as in the original.
     *
     * @return the copy of this sequence.
     */
    public Sequence clone() {
        Sequence clone = new Sequence(this.getCapacity());

        for (int position = 0; position < size(); position++){
            String data = this.contents.getDataAtIndex(position);
            clone.contents.insertAtIndex(data, position);
        }
        clone.setCurrent(this.getCurrentIndex());
        return clone;
    }
   
    
    /**
     * Remove the current element from this sequence.  The following
     * element, if there was one, becomes the current element.  If
     * there was no following element (current was at the end of the
     * sequence), the sequence now has no current element.
     *
     * If there is no current element, does nothing.
     */
    public void removeCurrent() {
        if (isCurrent()) {
            if (currentAtEnd()) {
                contents.removeAtIndex(getCurrentIndex());
                setCurrent(NO_CURRENT);
            }
            else{
                contents.removeAtIndex(getCurrentIndex());
            }
        }
    }

    
    /**
     * @return the number of elements stored in the sequence.
     */
    public int size() {
        return contents.getLength();
    }

    
    /**
     * Sets the current element to the start of the sequence.  If the
     * sequence is empty, the sequence has no current element.
     */
    public void start() {
        if (!isEmpty()){
            setCurrent(EMPTY);
        }
        else{
            setCurrent(NO_CURRENT);
        }
    }

    
    /**
     * Reduce the current capacity to its actual size, so that it has
     * capacity to store only the elements currently stored.
     */
    public void trimToSize() {
        if (capacity != size()) {
            capacity = size();
        }
    }
    
    
    /**
     * Produce a string representation of this sequence.  The current
     * location is indicated by a >.  For example, a sequence with "A"
     * followed by "B", where "B" is the current element, and the
     * capacity is 5, would print as:
     * 
     *    {A, >B} (capacity = 5)
     * 
     * The string you create should be formatted like the above example,
     * with a comma following each element, no comma following the
     * last element, and all on a single line.  An empty sequence
     * should give back "{}" followed by its capacity.
     * 
     * @return a string representation of this sequence.
     */
    public String toString() {
        String sequenceStr = "{";
        for (int position = 0; position < size(); position++){
            if (position == getCurrentIndex()){
                sequenceStr = sequenceStr + ">";
                sequenceStr = sequenceStr + getCurrent();
            }
            else{
                sequenceStr = sequenceStr + contents.getDataAtIndex(position);
            }
            if (position + 1 != size()){
                sequenceStr = sequenceStr + ", ";
            }
        }
        sequenceStr = sequenceStr + "}";
        String capacityStr = " (capacity = " + getCapacity() + ")";
        sequenceStr = sequenceStr + capacityStr;

        return sequenceStr;
    }

    
    /**
     * Checks whether another sequence is equal to this one.  To be
     * considered equal, the other sequence must have the same size
     * as this sequence, have the same elements, in the same
     * order, and with the same element marked
     * current.  The capacity can differ.
     * 
     * Postcondition: NO SIDE EFFECTS!  this sequence and the
     * other sequence should remain unchanged, including the
     * current element.
     * 
     * @param other the other Sequence with which to compare
     * @return true iff the other sequence is equal to this one.
     */
    public boolean equals(Sequence other) {
        if (this.getCurrentIndex() != other.getCurrentIndex()){
            return false;
        }
        if (this.size() != other.size()){
            return false;
        }
        for (int position = 0; position < size(); position ++){
            String data = this.contents.getDataAtIndex(position);
            String otherData = other.contents.getDataAtIndex(position);

            if (!data.equals(otherData)){
                return false;
            }
        }
        return true;
    }
    
    
    /**
     * @return true if Sequence empty, else false
     */
    public boolean isEmpty() {
        return size() == EMPTY;
    }
    
    
    /**
     *  Empty the sequence.  There should be no current element.
     */
    public void clear() {
        setCurrent(NO_CURRENT);
        contents.clear();
    }


    /**
     * @return true if current at end of sequence, else false
     */
    private boolean currentAtEnd() {
        return getCurrentIndex() == (size() - 1);
    }


    /**
     * @param capacity the capacity of the sequence
     * @return true if capacity greater than or equal to zero, else false
     */
    private boolean validCapacity(int capacity) {
        return (capacity >= 0);
    }


    /**
     * @return returns the current index
     */
    private int getCurrentIndex() {
        return current;
    }


    /**
     * Sets the current to a new current
     */
    private void setCurrent(int newCurrent) {
        current = newCurrent;
    }
}