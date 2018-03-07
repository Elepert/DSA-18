import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    /**
     * Delete a key from the tree rooted at the given node.
     */
    @Override
    TreeNode<T> delete(TreeNode<T> n, T key) {

        n = super.delete(n, key);

        if (n != null) {

            n = balance(n);
            recursiveHeight(n);

            return n;
        }
        return null;
    }

    /**
     * Insert a key into the tree rooted at the given node.
     */
    @Override
    TreeNode<T> insert(TreeNode<T> n, T key) {

        n = super.insert(n, key);
        if (n != null) {

            n = balance(n);
            recursiveHeight(n);
            return n;
        }

        return null;
    }

    /**
     * Delete the minimum descendant of the given node.
     */
    @Override
    TreeNode<T> deleteMin(TreeNode<T> n) {
        n = super.deleteMin(n);
        if (n != null) {
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            return balance(n);
        }
        return null;
    }

    private int recursiveHeight(TreeNode<T> n) {
        if (n == null){
            return -1;
        }
        n.height = 1 + Math.max(recursiveHeight(n.leftChild), recursiveHeight(n.rightChild));
        return n.height;
    }

    // Return the height of the given node. Return -1 if null.
    private int height(TreeNode<T> n) {
        if (n == null){
            return -1;
        }
        return n.height;
    }

    public int height() {
        return Math.max(height(root), 0);
    }

    // Restores the AVL tree property of the subtree. Return the head of the new subtree
    TreeNode<T> balance(TreeNode<T> n) {
        if (n == null){
            return null;
        }
        recursiveHeight(n);
        int bfn = balanceFactor(n);
        int bfnRC = balanceFactor(n.rightChild);
        int bfnLC = balanceFactor(n.leftChild);

        // right, right
        if ((bfn >= 2) && (bfnRC >= 0)){
            n = rotateLeft(n);

            return n;
        // left, left
        } else if ((bfn <= -2) && (bfnLC <= 0)){
            n = rotateRight(n);
            return n;
        // right, left
        } else if ((bfn >= 2) && (bfnRC < 0)){
            n.rightChild = rotateRight(n.rightChild);

            return balance(n);
        // left, right
        } else if ((bfn <= -2) && (bfnLC > 0)){
            n.leftChild = rotateLeft(n.leftChild);

            return balance(n);
        }
        return n;
    }

    /**
     * Returns the balance factor of the subtree. The balance factor is defined
     * as the difference in height of the left subtree and right subtree, in
     * this order. Therefore, a subtree with a balance factor of -1, 0 or 1 has
     * the AVL property since the heights of the two child subtrees differ by at
     * most one.
     */
    private int balanceFactor(TreeNode<T> n) {
        if (n == null)
            return 0;
        return height(n.rightChild) - height(n.leftChild);
    }

    /**
     * Perform a right rotation on node `n`. Return the head of the rotated tree.
     */
    private TreeNode<T> rotateRight(TreeNode<T> n) {

        TreeNode<T> x = n.leftChild;
        TreeNode<T> b = x.rightChild;
        x.rightChild = n;
        n.leftChild = b;
        return x;
    }

    /**
     * Perform a left rotation on node `n`. Return the head of the rotated tree.
     */
    private TreeNode<T> rotateLeft(TreeNode<T> n) {

        TreeNode<T> x = n.rightChild;
        TreeNode<T> b = x.leftChild;
        x.leftChild = n;
        n.rightChild = b;
        return x;
    }
}
