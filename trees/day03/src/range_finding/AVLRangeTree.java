package range_finding;

import java.util.LinkedList;
import java.util.List;

public class AVLRangeTree extends BinarySearchTree<Integer> {

    /**
     * Delete a key from the tree rooted at the given node.
     */
    @Override
    RangeNode<Integer> delete(RangeNode<Integer> n, Integer key) {
        n = super.delete(n, key);
        if (n != null) {
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            return balance(n);
        }
        return null;
    }

    /**
     * Insert a key into the tree rooted at the given node.
     */
    @Override
    RangeNode<Integer> insert(RangeNode<Integer> n, Integer key) {
        n = super.insert(n, key);
        if (n != null) {
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            return balance(n);
        }
        return null;
    }

    /**
     * Delete the minimum descendant of the given node.
     */
    @Override
    RangeNode<Integer> deleteMin(RangeNode<Integer> n) {
        n = super.deleteMin(n);
        if (n != null) {
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            return balance(n);
        }
        return null;
    }

    // Return the height of the given node. Return -1 if null.
    private int height(RangeNode x) {
        if (x == null) return -1;
        return x.height;
    }

    public int height() {
        return Math.max(height(root), 0);
    }

    // Restores the AVL tree property of the subtree.
    RangeNode<Integer> balance(RangeNode<Integer> x) {
        if (balanceFactor(x) > 1) {
            if (balanceFactor(x.rightChild) < 0) {
                //System.out.println("rotating right child node before: ");
                //System.out.println(x.rightChild);
                x.rightChild = rotateRight(x.rightChild);
                //System.out.println("rotating right child node after: ");
                //System.out.println(x.rightChild);
            }
            //System.out.println("rotating left node before: ");
            //System.out.println(x);
            x = rotateLeft(x);
            //System.out.println("rotating left node after: ");
            //System.out.println(x);
        } else if (balanceFactor(x) < -1) {
            if (balanceFactor(x.leftChild) > 0) {
                //System.out.println("rotating left child node before: ");
                //System.out.println(x.leftChild);
                x.leftChild = rotateLeft(x.leftChild);
                //System.out.println("rotating left child node after: ");
                //System.out.println(x.leftChild);
            }
            //System.out.println("rotating right node befre: ");
            //System.out.println(x);
            x = rotateRight(x);
            //System.out.println("rotating right node after: ");
            //System.out.println(x);
        }
        updateSubtree(x);
        return x;
    }

    // Return all keys that are between [lo, hi] (inclusive).
    // TODO: runtime = O(?)
    public List<Integer> rangeIndex(int lo, int hi) {
        List<Integer> l = new LinkedList<>();
        return inOrderRange(root, l, lo, hi);
    }

    public List<Integer> inOrderRange(RangeNode<Integer> node, List<Integer> list, int lo, int hi) {
        if (node != null) {
            inOrderRange(node.leftChild, list, lo, hi);
            if (node.key <= hi && node.key >= lo) {
                list.add(node.key);
            }
            inOrderRange(node.rightChild, list, lo, hi);
        }
        return list;
    }

    private RangeNode<Integer> minTree(RangeNode<Integer> x) {
        if (x.leftChild == null) return x;
        return minTree(x.leftChild);
    }

    private RangeNode<Integer> maxTree(RangeNode<Integer> x) {
        if (x.rightChild == null) return x;
        return maxTree(x.rightChild);
    }

    // return the number of keys between [lo, hi], inclusive
    // TODO: runtime = O(?)
    public int rangeCount(int lo, int hi) {
        /*if (root == null) return 0;
        RangeNode<Integer> treemax = maxTree(root);

        RangeNode<Integer> treemin = minTree(root);
        System.out.print("lo: ");
        System.out.println(lo);
        System.out.print("hi: ");
        System.out.println(hi);
        if (hi < treemin.key || lo > treemax.key){
            return 0;
        }
        int hiR = rank(root, hi); //, equals);
        int loR = rank(root,lo); //, equals);
        System.out.print("Got lo: ");
        System.out.println(loR);
        System.out.print("Got hi: ");
        System.out.println(hiR);
        /*if (hiR > 0 && equals == true){
            return 0;
        }
        return  (hiR - loR); //rangeCountRecur(lo, hi, root);*/
        //RangeNode<Integer> treemax = maxTree(root);

        //RangeNode<Integer> treemin = minTree(root);
        System.out.print("lo: ");
        System.out.println(lo);
        System.out.print("hi: ");
        System.out.println(hi);
        /*if (hi < treemin.key || lo > treemax.key){
            return 0;
        }*/

        int hiR = rank(root, hi); //, equals);
        int loR = rank(root,lo); //, equals);
        System.out.print("Got lo: ");
        System.out.println(loR);
        System.out.print("Got hi: ");
        System.out.println(hiR);
        if (contains(hi)) {
            System.out.println("Contains hi");
            if (loR != 0) {
                System.out.println("lor not 0");
                return hiR - loR;
            } else {
                System.out.println("lor 0");
                return hiR - loR;
            }
        } else if (contains(lo)) {
            System.out.println("Contains lo");
            return hiR-loR +1;
        }
        else {
            System.out.println("Normal");
            return hiR - loR;
        }
    }



    public int rank(RangeNode<Integer> node, int key){//, boolean high) {// , boolean equals) {
        /*int sum = 0;
        if (node == null) return 0;
        if (node.key < k) {
            if (node.hasLeftChild()) {
                sum += node.leftChild.Subtree;
            }
            sum += rank(node.rightChild, k, high) + 1;
        } else if (node.key == k){
            System.out.println("Key is in Tree *************");
            if (high == true) {
                System.out.println("Is high key");
                sum += 1;
            }//else {
              //  sum
            //}
            if (node.hasLeftChild()) sum += node.leftChild.Subtree;
        } else if (node.key > k && node.hasLeftChild()){
            sum += rank(node.leftChild, k, high);
        }
        return sum;*/
        //int cmp = key.compareTo( node.key);
        //System.out.println("Node we're looking at");
        //System.out.println(node);
        if (node == null) return 0;
        //System.out.println("passed null check");
        if (key < node.key) {
            return rank(node.leftChild, key);
        } else if (key > node.key){
            if (node.hasLeftChild()) {
                return 1 + node.leftChild.Subtree + rank(node.rightChild, key);
            } else {
                return 1 + rank(node.rightChild, key);
            }
        }
        else    {
            if (node.hasLeftChild()) {
                return node.leftChild.Subtree + 1;
            } else {
                return 1;
            }
        }
    }

    /**
     * Returns the balance factor of the subtree. The balance factor is defined
     * as the difference in height of the left subtree and right subtree, in
     * this order. Therefore, a subtree with a balance factor of -1, 0 or 1 has
     * the AVL property since the heights of the two child subtrees differ by at
     * most one.
     */
    private int balanceFactor(RangeNode x) {
        return height(x.rightChild) - height(x.leftChild);
    }

    /**
     * Perform a right rotation on node `n`. Return the head of the rotated tree.
     */
    private RangeNode<Integer> rotateRight(RangeNode<Integer> x) {
        RangeNode<Integer> y = x.leftChild;
        int yRS = 0;
        if (y.hasRightChild()){
            yRS += y.rightChild.Subtree;
        }
        x.leftChild = y.rightChild;
        y.rightChild = x;
        x.height = 1 + Math.max(height(x.leftChild), height(x.rightChild));
        y.height = 1 + Math.max(height(y.leftChild), height(y.rightChild));
        x.Subtree -= y.Subtree ;
        y.Subtree += x.Subtree ;
        x.Subtree += yRS;

        return y;
    }

    /**
     * Perform a left rotation on node `n`. Return the head of the rotated tree.
     */
    private RangeNode<Integer> rotateLeft(RangeNode<Integer> x) {
        RangeNode<Integer> y = x.rightChild;
        int yLS =0;
        if (y.hasLeftChild()){
            yLS += y.leftChild.Subtree;
        }
        x.rightChild = y.leftChild;

        y.leftChild = x;
        x.height = 1 + Math.max(height(x.leftChild), height(x.rightChild));
        y.height = 1 + Math.max(height(y.leftChild), height(y.rightChild));
        x.Subtree -= y.Subtree;
        y.Subtree += x.Subtree;
        x.Subtree += yLS;
        return y;
    }

    private int updateSubtree(RangeNode<Integer> n){
        if (n == null ) return 0;
        n.Subtree = updateSubtree(n.leftChild) + updateSubtree (n.rightChild) +1;
        return n.Subtree;
    }
}
