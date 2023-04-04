package proj2;
/**
 * Caleb L'Italien
 * CSC-151
 * I affirm that I have carried out the attached academic endeavors with full
 * academic honesty, in accordance with the Union College Honor Code and the
 * course syllabus.
 *
 * Creates a new sequence with default capacity 10 or a capacity passed in.
 */
public class Sequence
{
    /**
     * The contents are stored in sequence at indices 0 to size - 1. If there is no current index, current is -1.
     * The contents at indices larger than the size are irrelevant (null).
     * If the size is zero, the contents of the sequence are irrelevant (null).
     * Size is always between zero and the length of the sequence.
     * The current index is either negative one if there is no current index, or somewhere between zero and size minus
     * one.
     */
    private int size;
    private int current;
    private String[] holder;

    private final int DEFAULT_CAPACITY = 10;
    private final int NO_CURRENT = -1;
    private final int CURRENT_INCREMENT = 1;
    private final int EMPTY = 0;

    public Sequence() {
        this.size = EMPTY;
        this.current = NO_CURRENT;
        this.holder = new String[DEFAULT_CAPACITY];
    }
    

    /**
     * Creates a new sequence.
     * 
     * @param initialCapacity the initial capacity of the sequence.
     */
    public Sequence(int initialCapacity) {
        this.size = EMPTY;
        this.current = NO_CURRENT;
        if (validCapacity(initialCapacity)){
            this.holder = new String[initialCapacity];
        }
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
        int capacity = getCapacity();
        if (size() == capacity) {
            int newCapacity = (capacity * 2) + 1;
            ensureCapacity(newCapacity);
        }
        if (isCurrent()){
            for (int position = size; position > current; position--){
                holder[position] = holder[position - 1];
            }
        }
        else{
            current = 0;
            for (int position = size; position != current; position--){
                holder[position] = holder[position - 1];
            }
        }
        holder[current] = value;
        size ++;
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
        int capacity = getCapacity();

        if (size() == capacity) {
            int newCapacity = (capacity * 2) + 1;
            ensureCapacity(newCapacity);
        }
        if (isCurrent()){
            for (int position = size; position > current + 1; position--){
                holder[position] = holder[position-1];
            }
            holder[++current] = value;
        }
        else{
            current = size;
            holder[current] = value;
        }
        size ++;
    }


    /**
     * @return true if and only if the sequence has a current element.
     */
    public boolean isCurrent() {
        return this.current != NO_CURRENT;
    }
    
    
    /**
     * @return the capacity of the sequence.
     */
    public int getCapacity() {
        return holder.length;
    }

    
    /**
     * @return the element at the current location in the sequence, or
     * null if there is no current element.
     */
    public String getCurrent() {
        if (isCurrent()){
            return getElement(current);
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
        if (validCapacity(minCapacity)){
            int position = 0;
            String[] newHolder = new String[minCapacity];

            if (getCapacity() < minCapacity){
                for (String element: holder){
                    newHolder[position] = element;
                    position ++;
                }
                holder = newHolder;
            }
        }
    }

    
    /**
     * Places the contents of another sequence at the end of this sequence.
     *
     * If adding all elements of the other sequence would exceed the
     * capacity of this sequence, the capacity is changed to make room for
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
       int currentCopy = this.current;
       int totalSize = another.size() + this.size();

       if (totalSize > this.getCapacity()){
           this.ensureCapacity(totalSize);
       }
       this.current = size() - 1;
       int anotherPosition = 0;

       for (int thisPosition = this.size(); thisPosition < totalSize; thisPosition++){
           this.addAfter(another.holder[anotherPosition]);
           anotherPosition++;
       }
       this.current = currentCopy;
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
                current = NO_CURRENT;
            }
            else{
                current += CURRENT_INCREMENT;
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
       Sequence newSequence = new Sequence(this.getCapacity());
       newSequence.current = this.current;
       newSequence.size = this.size;

       for (int position = 0; position < this.getCapacity(); position++){
           newSequence.holder[position] = this.holder[position];
       }
       return newSequence;
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
        if (this.isCurrent()){
            for (int position = this.current; position < this.size(); position++){
                this.holder[position] = this.holder[position + 1];
            }
            if (this.currentAtEnd()){
                this.current = NO_CURRENT;
            }
            size --;
        }
    }

    
    /**
     * @return the number of elements stored in the sequence.
     */
    public int size() {
        return this.size;
    }

    
    /**
     * Sets the current element to the start of the sequence.  If the
     * sequence is empty, the sequence has no current element.
     */
    public void start() {
        if (!isEmpty()){
            current = 0;
        }
        else{
            current = NO_CURRENT;
        }
    }

    
    /**
     * Reduce the current capacity to its actual size, so that it has
     * capacity to store only the elements currently stored.
     */
    public void trimToSize() {
        String[] newHolder = new String[size()];
        for (int position = 0; position < this.size(); position++){
            newHolder[position] = this.holder[position];
        }
        this.holder = newHolder;
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
        String sequenceStr;
        String element;
        String capacityStr;

        if (!isEmpty()){
            sequenceStr = "{";
            for (int position = 0; position < size(); position ++){
                element = getElement(position);

                if (position == current){
                    element = ">" + element;
                }
                if (position != (size() - 1)){
                    element = element + ",";
                }
                if (position != 0){
                    element = " " + element;
                }
                sequenceStr = sequenceStr + element;
            }
            sequenceStr = sequenceStr + "}";
        }
        else{
            sequenceStr = "{}";
        }
        capacityStr = " (capacity = " + getCapacity() + ")";
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
        if (this.current != other.current){
            return false;
        }
        if (this.size() != other.size()){
            return false;
        }
        for (int position = 0; position < this.size(); position++){
            if (!this.holder[position].equals(other.holder[position])){
                return false;
            }
        }
        return true;
    }
    
    
    /**
     * 
     * @return true if Sequence empty, else false
     */
    public boolean isEmpty() {
        return size() == EMPTY;
    }
    
    
    /**
     *  empty the sequence.  There should be no current element.
     */
    public void clear()
    {
        for (int position = 0; position < this.size(); position++){
            this.holder[position] = null;
        }
        current = NO_CURRENT;
        size = EMPTY;
    }

    private boolean currentAtEnd() {
        return current == (size - 1);
    }


    private String getElement(int index) {
        return holder[index];
    }

    private boolean validCapacity(int capacity){
        if (capacity >= 0){
            return true;
        }
        return false;
    }
}