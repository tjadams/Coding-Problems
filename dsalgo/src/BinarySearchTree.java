package src;

/*
A class that represents the Binary Search Tree (BST) data structure.

TODO: - figure out a smart way to change equality in comparing src.BinarySearchTree.value. Ex: some people define BSTs to
        have equality on the right node (value >= this.value when comparing) while other people define BSTs as having
        equality on the left node. When talking about BSTs with people, clarify which comparison we'll use.

NOTE: - for now we'll use equality on the right node. Ex: if inserting 5 at the root but the root is already 5, the new 5 node will go on the leftmost right of 5.
NOTE: - int can't be null because it's a primitive data type. Only Objects can be null.
 */
public class BinarySearchTree {

    private BinarySearchTree left;
    private BinarySearchTree right;
    private int value;

    public BinarySearchTree(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    /*
    Note that you can't just have setters for the left and right nodes
     */
    public void insert(BinarySearchTree node) {
        if (node.getValue() > this.getValue()) {
            if (this.getRight() != null) {
                this.getRight().insert(node);
            } else {
                this.right = node;
            }
        } else if (node.getValue() < this.getValue()) {
            if (this.getLeft() != null) {
                this.getLeft().insert(node);
            } else {
                this.left = node;
            }
        } else {
            // TODO remove this section if duplicates are not allowed within the tree
            // If the node values are equal, insert on the leftmost right side of this root
            BinarySearchTree oneNodeRight = this.getRight();
            oneNodeRight.min().left = node;
        }
    }

    // Try to delete a specific item from a BST
    // TODO how does this work when you don't have unique values? do you just delete on a given node without searching for it? Perhaps I won't search for that value then
    public boolean delete (BinarySearchTree node) {
        // TODO
        return false;
    }

    // TODO can I do this without having a "parent" BinarySearchTree field?
    public BinarySearchTree predecessor() {
        // TODO
        return null;
    }

    // TODO can I do this without having a "parent" BinarySearchTree field?
    public BinarySearchTree successor() {
        // TODO
        return null;
    }

    public void inorderTraversal() {
        if (this.getLeft() != null) {
            this.getLeft().inorderTraversal();
        }

        System.out.println(this.getValue());

        if (this.getRight() != null) {
            this.getRight().inorderTraversal();
        }
    }

    public static void staticInorderTraversal(BinarySearchTree tree) {
        if (tree == null) return;
        staticInorderTraversal(tree.getLeft());
        System.out.println(tree.getValue());
        staticInorderTraversal(tree.getRight());
    }


    public void preorderTraversal() {
        System.out.println(this.getValue());

        if (this.getLeft() != null) {
            this.getLeft().preorderTraversal();
        }

        if (this.getRight() != null) {
            this.getRight().preorderTraversal();
        }
    }

    public static void staticPreorderTraversal(BinarySearchTree tree) {
        if (tree == null) return;
        System.out.println(tree.getValue());
        staticPreorderTraversal(tree.getLeft());
        staticPreorderTraversal(tree.getRight());
    }

    public void postorderTraversal() {
        if (this.getLeft() != null) {
            this.getLeft().postorderTraversal();
        }

        if (this.getRight() != null) {
            this.getRight().postorderTraversal();
        }

        System.out.println(this.getValue());
    }

    public static void staticPostorderTraversal(BinarySearchTree tree) {
        if (tree == null) return;
        staticPostorderTraversal(tree.getLeft());
        staticPostorderTraversal(tree.getRight());
        System.out.println(tree.getValue());
    }

    /*
    Searches for a src.BinarySearchTree node with an integer value of "value".
    Returns true if it exists in the tree, returns false otherwise.
     */
    // TODO Look into pros/cons of having a method about a data structure be static vs non-static. I.e., search() vs staticSearch()
    // TODO I was thinking of this method as if I was searching in an in-order traversal type of way which is why I have the commented out successor/predecessor parts. I should investigate the difference between doing it this way and doing it with using node.right and node.left
    public boolean search(int value) {
        if (value == this.value) {
            return true;
        } else if (value > this.value) {
            if (this.getRight() != null) {
                return this.getRight().search(value); // TODO what's the difference between just calling search again and calling search again but saying "return search(...)"? I'm pretty sure I have to put a "return search(...)" because otherwise if you just say "search(...)" then you can eventually get to a return true/false but that true/false is not assigned to anything and it isn't returned so the "search(...)" ends up having the same functionality as a void call
            }
        } else if (value < this.value) {
            if (this.getLeft() != null) {
                return this.getLeft().search(value); // TODO see above
            }
        }

        return false;
    }

    // This method is the BST search method done statically
    public static boolean staticSearch(BinarySearchTree root, int value) {
        if (root == null) {
            return false;
        }

        if (value > root.value) {
            return staticSearch(root.getRight(), value);
        } else if  (value < root.value) {
            return staticSearch(root.getLeft(), value);
        } else if (value == root.value) { // NOTE: could have made this "else" but this is more readable.
            return true;
        }

        return false; // TODO how come IntelliJ gets mad at me here telling me to put a return statement here. I thought I covered all possible branches with return statements?
    }

    // Returns the node that contains the minimum value of the whole tree. This is the leftmost node of the tree.
    public BinarySearchTree min() {
        if (this.getLeft() != null) {
            return this.getLeft().min();
        } else {
            return this;
        }
    }

    // Returns the node that contains the maximum value of the whole tree. This is the rightmost node of the tree.
    public BinarySearchTree max() {
        if (this.getRight() != null) {
            return this.getRight().max();
        } else {
            return this;
        }
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
}
