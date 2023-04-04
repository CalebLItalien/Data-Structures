package proj5;

/**
 * A node in a BinarySearchTree
 * 
 * @author Chris Fernandes, Kristina Striegnitz
 * @version Fall 2022
 *
 * Invariants:
 * The key is a comparable object T. The llink and rlink are pointers to the left and right nodes (in relation to this
 * node), or null for either if the respective link has no node.
 */

public class BSTNode<T> {
    public T key;
    public BSTNode<T> llink;
    public BSTNode<T> rlink;

    /**
     * Constructor
     *
     * @param data a generic value
     */
    public BSTNode(T data) {
    	key=data;
    	llink=null;
    	rlink=null;
    }


    /**
     * @return true if the node has null left link and right link, else false
     */
    public boolean isLeaf() {
    	return this.llink == null && this.rlink == null;
    }


    /**
     * @return true if the node has null left link and right link not null, else false
     */
    public boolean hasRightChildOnly() {
    	return this.llink == null && this.rlink != null;
    }


    /**
     * @return true if the node has null right link and left link not null, else false
     */
    public boolean hasLeftChildOnly() {
    	return this.llink != null && this.rlink == null;
    }


    /**
     * Returns the node as a printable string
     *
     * @return the data as a string
     */
    public String toString() {
        return "" + key;
    }
}