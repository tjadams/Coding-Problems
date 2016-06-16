/*
A class that represents the Binary Search Tree (BST) data structure.

TODO: - figure out a smart way to change equality in comparing BinarySearchTree.value. Ex: some people define BSTs to
        have equality on the right node (value >= this.value when comparing) while other people define BSTs as having
        equality on the left node. When talking about BSTs with people, clarify which comparison we'll use.

NOTE: - for now we'll use equality on the right node
NOTE: - int can't be null because it's a primitive data type. Only Objects can be null.
 */
public class BinarySearchTree {
    private BinarySearchTree left;
    private BinarySearchTree right;
    private int value;

    public int min() {
        // TODO
        return 0;
    }

    public int max() {
        // TODO
        return 0;
    }

    public BinarySearchTree successor() {
        // TODO
        return null;
    }

    /*
    Searches for a BinarySearchTree node with an integer value of "value".
    Returns true if it exists in the tree, returns false otherwise.
     */
    // TODO code this in a non-static way as well. That way, insteadof doing the static BianrySearchTree.search(root, value), I can do root.search(value) which looks better lol. Look into pros/cons of having a method about a data structure be static vs non-static
    // TODO I was thinking of this method as if I was searching in an in-order traversal type of way which is why I have the successor/predecessor parts. I should investigate the difference between doing it this way and doing it with using node.right and node.left. Also I should probably code it the standard way with node.left and node.right as in http://www.algolist.net/Data_structures/Binary_search_tree/Lookup
    public static boolean search(BinarySearchTree root, int value) {
        if (root == null) {
            return false;
        }

        if (value > root.value) {
            // Continue searching towards the right
            return search(root.successor(), value); // TODO what's the difference between just calling search again and calling search again but saying "return search(...)"? I'm pretty sure I have to put a "return search(...)" because otherwise if you just say "search(...)" then you can eventually get to a return true/false but that true/false is not assigned to anything and it isn't returned so the "search(...)" ends up having the same functionality as a void call
        } else if  (value < root.value) {
            // Continue searching towards the left
            return search(root.predecessor(), value); // TODO see above
        } else if (value == root.value) { // NOTE: could have made this "else" but this is more readable.
            return true;
        }

        return false; // TODO how come IntelliJ gets mad at me here telling me to put a return statement here. I thought I covered all possible branches with return statements?
    }

    /*
    Note that you can't just have setters for the left and right nodes
     */
    public void insert(BinarySearchTree node) {

    }

    public BinarySearchTree predecessor() {
        // TODO
        return null;
    }

    public BinarySearchTree getLeft() {
        return left;
    }

    public BinarySearchTree getRight() {
        return right;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
