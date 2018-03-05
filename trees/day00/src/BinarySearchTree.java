import java.util.List;
import java.util.ArrayList;

public class BinarySearchTree<T extends Comparable<T>> {
    TreeNode<T> root;
    private int size;

    public int size() {
        return size;
    }

    public boolean contains(T key) {
        return find(root, key) != null;
    }

    /**
     * Add a node to the BST. Internally calls insert to recursively find the new node's place
     */
    public boolean add(T key) {
        if (find(root, key) != null) return false;
        root = insert(root, key);
        size++;
        return true;
    }

    public void addAll(T[] keys) {
        for (T k : keys)
            add(k);
    }

    public List<T> inOrderTraversal() {
        List<T> traversal = new ArrayList<T>();

        if (root == null){
            return traversal;
        }

        TreeNode<T> tempm = root;

        while(tempm.hasLeftChild()){
            tempm = tempm.leftChild;
        }

        traversal.add(tempm.key);

        T key = tempm.key;

        for (int i = 1; i<size(); i++){

            if (key != null) {
                key = findSuccessor(key);

                traversal.add(key);
            }

        }
        return traversal;
    }

    /**
     * Deletes a node from the BST using the following logic:
     * 1. If the node has a left child, replace it with its predecessor
     * 2. Else if it has a right child, replace it with its successor
     * 3. If it has no children, simply its parent's pointer to it
     */
    public boolean delete(T key) {
        TreeNode<T> toDelete = find(root, key);
        if (toDelete == null) {
            System.out.println("Key does not exist");
            return false;
        }
        TreeNode<T> deleted = delete(toDelete);
        size--;
        return true;
    }

    private TreeNode<T> delete(TreeNode<T> n) {
        // Recursive base case

        if (n == null) return null;

        TreeNode<T> replacement;

        if (n.isLeaf()) {
            // Case 1: no children
            replacement = null;
            if (root == n){
                root = null;
            }
        } else if (n.hasRightChild() != n.hasLeftChild()) {
            // Case 2: one child
            replacement = (n.hasRightChild()) ? n.rightChild : n.leftChild; // replacement is the non-null child
            if (root == n){
                root = replacement;
            }
        } else {
            // Case 3: two children
            replacement = findMin(n.rightChild);
            n.key = replacement.key;
            return delete(replacement);

        }

        // Put the replacement in its correct place, and set the parent.

        n.replaceWith(replacement);

        return replacement;
    }

    public T findPredecessor(T key) {
        TreeNode<T> n = find(root, key);
        if (n != null) {
            TreeNode<T> predecessor = findPredecessor(n);
            if (predecessor != null)
                return predecessor.key;
        }
        return null;
    }

    public T findSuccessor(T key) {
        TreeNode<T> n = find(root, key);

        if (n != null) {
            TreeNode<T> successor = findSuccessor(n);
            if (successor != null)
                return successor.key;
        }
        return null;
    }

    private TreeNode<T> findMin(TreeNode<T> n){
        while(n.hasLeftChild()){
            n = n.leftChild;
        }
        return n;
    }

    private TreeNode<T> findMax(TreeNode<T> n){
        while(n.hasRightChild()){
            n = n.rightChild;
        }
        return n;
    }

    private TreeNode<T> findPredecessor(TreeNode<T> n) {

        if (n.hasLeftChild()) {

            return findMax(n.leftChild);
        } else if (n.isRightChild()){
            return n.parent;
        } else if (n.isLeftChild()){
            TreeNode<T> ptemp = n;
            while(ptemp.isLeftChild()){
                ptemp = ptemp.parent;
            }
            if (ptemp.isRightChild()) {
                return ptemp.parent;
            }
        }
        return null;
    }

    private TreeNode<T> findSuccessor(TreeNode<T> n) {

        if (n.hasRightChild()){
            return findMin(n.rightChild);
        } else if (n.isLeftChild()){
            return n.parent;
        } else if (n.isRightChild()){
            TreeNode<T> ptemp = n;
            while(ptemp.isRightChild()){
                ptemp = ptemp.parent;
            }
            if (ptemp.isLeftChild()) {
                return ptemp.parent;
            }
        }
        return null;

    }

    /**
     * Returns a node with the given key in the BST, or null if it doesn't exist.
     */
    private TreeNode<T> find(TreeNode<T> currentNode, T key) {
        if (currentNode == null) {
            return null;
        }

        int cmp = key.compareTo(currentNode.key);
        if (cmp < 0)
            return find(currentNode.leftChild, key);
        else if (cmp > 0)
            return find(currentNode.rightChild, key);

        return currentNode;
    }

    /**
     * Recursively insert a new node into the BST
     */
    private TreeNode<T> insert(TreeNode<T> node, T key) {
        if (node == null) return new TreeNode<>(key);

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.leftChild = insert(node.leftChild, key);
            node.leftChild.parent = node;
        } else {
            node.rightChild = insert(node.rightChild, key);
            node.rightChild.parent = node;
        }
        return node;
    }
}
