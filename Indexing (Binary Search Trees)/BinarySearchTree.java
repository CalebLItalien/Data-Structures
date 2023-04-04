package proj5;

/**
 * Models a binary search tree, wherein values must be comparable, values less than or equal to the root are placed in
 * the left subtree, else placed in the right subtree
 * 
 * @authors Chris Fernandes, Kristina Striegnitz, Caleb L'Italien
 * @version 11/13/2022
 *
 * Invariants:
 * Root is a pointer to the root node or null if there are no nodes.
 */

public class BinarySearchTree<T extends Comparable<T>> {
	private BSTNode<T> root;
	
	/**
	 * Default constructor
	 */
	public BinarySearchTree() {
		root = null;
	}


    /**
	 * Inserts a new value into this BST
	 *
	 * @param newValue value to insert
	 */
	public void insert(T newValue) {
		root = insert(root,newValue);
	}


	/**
	 * Inserts value into tree rooted at subroot
	 * 
	 * @param subroot subroot of tree to insert into
	 * @param value a value to insert
	 * @return root of the subtree inserted into
	 */
	private BSTNode<T> insert(BSTNode<T> subroot, T value) {
		if (subroot==null){
			return new BSTNode<T>(value);
		}
		else if (value.compareTo(subroot.key) > 0){
			subroot.rlink = insert(subroot.rlink,value);
			return subroot;
		}
		else {
			subroot.llink = insert(subroot.llink, value);
			return subroot;
		}
	}


	/**
	 * Deletes value from tree if in the tree
	 *
	 * @param value value to delete
	 */
	public void delete(T value) {
		root = delete(root, value);
	}


	/**
	 * Deletes value from tree rooted at subroot
	 *
	 * @param subroot root of tree to be deleted from
	 * @param value value to delete
	 * @return pointer to tree rooted at subroot that has value removed from it
	 */
	private BSTNode<T> delete(BSTNode<T> subroot, T value) {
		if (subroot == null) {
			return null;
		}
		else if (value.compareTo(subroot.key) > 0) {
			subroot.rlink = delete(subroot.rlink, value);
		}
		else if (value.compareTo(subroot.key) < 0) {
			subroot.llink = delete(subroot.llink, value);
		}
		else{
			if (subroot.isLeaf()){
				return null;
			}
			else if (subroot.hasRightChildOnly()) {
				return subroot.rlink;
			}
			else if (subroot.hasLeftChildOnly()) {
				return subroot.llink;
			}
			else{
				T maxValue = maxValue(subroot.llink);
				subroot.key = maxValue;
				subroot.llink = delete(subroot.llink, maxValue);
			}
		}
		return subroot;
	}


	/**
	 * @return the maximum value in the BST
	 */
	public T maxValue() {
		if (!isEmpty()) {
			return maxValue(root);
		}
		return null;
	}


	/**
	 * Helper method for finding the maximum value in the BST
	 *
	 * @param subroot root of tree to find maximum value from
	 * @return the maximum value within this BST
	 */
	private T maxValue(BSTNode<T> subroot) {
		if (subroot.rlink == null) {
			return subroot.key;
		}
		return maxValue(subroot.rlink);
	}


    /**
     * Checks if the target value is in the BST
	 *
	 * @param target value to find
     * @return true if the value is in the tree, else false
     */
    public boolean search(T target) {
		return search(root, target);
    }


	/**
	 * Helper method for checking if a target value is in the BST
	 *
	 * @param subroot root of tree to find target from
	 * @param target value to find
	 * @return true if the value is found, false if not, else a pointer to the tree, rooted at the subroot, to search
	 * for the value from
	 */
	private boolean search(BSTNode<T> subroot, T target) {
		if (subroot == null) {
			return false;
		}
		else if (target.compareTo(subroot.key) > 0) {
			return search(subroot.rlink, target);
		}
		else if (target.compareTo(subroot.key) < 0) {
			return search(subroot.llink, target);
		}
		else {
			return true;
		}
	}


	/**
	 * Finds equivalent data to a given target
	 *
	 * @param target value to find
	 * @return equivalent data if found (not the target), else null
	 */
	public T getData(T target) {
		return getData(root, target);
	}


	/**
	 * Helper method for finding equivalent data to a given target
	 *
	 * @param subroot root of tree to find target from
	 * @param target value to find
	 * @return equivalent data if found (not the target), null if not found, else a pointer to the tree, rooted at the
	 * subroot, to search for the value from
	 */
	private T getData(BSTNode<T> subroot, T target) {
		if (subroot == null){
			return null;
		}
		else if (target.compareTo(subroot.key) > 0){
			return getData(subroot.rlink, target);
		}
		else if (target.compareTo(subroot.key) < 0){
			return getData(subroot.llink, target);
		}
		else{
			return subroot.key;
		}
	}


	/**
	 * @return true if the tree is empty, else false
	 */
	public boolean isEmpty() {
		return root == null;
	}


	/**
	 * Prints the binary search tree in ascending order, line by line
	 */
	public void printInOrder() {
		printInOrder(root);
	}


	/**
	 * Helper method for printing the binary search tree in ascending order, line by line
	 *
	 * @param subroot root of tree to find target from
	 */
	private void printInOrder(BSTNode subroot) {
		if (subroot == null) {
			return;
		}
		printInOrder(subroot.llink);
		System.out.println(subroot.key);
		printInOrder(subroot.rlink);
	}


	/**
	 * Returns tree as printable string
	 *
	 * @return tree in string format with form (left subtree) value (right subtree)
	 */
	public String toString() {
		return toString(root);
	}


	/**
	 * Recursive helper method for toString()
	 *
	 * @param N root of subtree to make into a string
	 * @return string version of tree rooted at N
	 */
	private String toString(BSTNode<T> N) {
		String ret = "";
		if (N != null){
			ret += "(";
			ret += toString(N.llink);
			ret += "  " + N + "  ";
			ret += toString(N.rlink);
			ret += ")";
		}
		return ret;
	}
}