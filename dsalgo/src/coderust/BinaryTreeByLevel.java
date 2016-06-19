package src.coderust;
/*
    * Created by Tyler Adams on 20/04/2015.
    * A class that has a simple method that allows the height of a node in a binary tree to be calculated.
*/

public class BinaryTreeByLevel {
    public static void main(String args[]){
        Node root = new Node(1);
        Node a = new Node(2);
        Node b = new Node(3);
        Node c = new Node(4);
        Node d = new Node(5);
        Node e = new Node(6);
        Node f = new Node(7);
        root.setLeft(a);
        root.setRight(b);
        a.setLeft(c);
        a.setRight(d);
        b.setLeft(e);
        b.setRight(f);
        System.out.println("Height of tree: "+getHeightRecursively(root));
    }

    // The base case is when you reach a leaf node.
    public static int getHeightRecursively(Node root) {
        if(root.left() == null && root.right() == null) {
            return 0;
        }
        return Math.max(getHeightRecursively(root.left()) + 1, getHeightRecursively(root.right()) + 1);
    }
}
