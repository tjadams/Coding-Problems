package tst;

import org.junit.After;
import org.junit.Before;
import src.BinarySearchTree;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class BinarySearchTreeTest {

    // Example tree I'll be using:
    //       5
    //    3     7
    //  2  4   6  8
    // 1            10
    //             9  11
    private BinarySearchTree tree;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setup() {
        tree = new BinarySearchTree(5);

        // construct left half of tree
        tree.insert(new BinarySearchTree(3));
        tree.insert(new BinarySearchTree(4));
        tree.insert(new BinarySearchTree(2));
        tree.insert(new BinarySearchTree(1));

        // construct right half of tree
        tree.insert(new BinarySearchTree(7));
        tree.insert(new BinarySearchTree(6));
        tree.insert(new BinarySearchTree(8));
        tree.insert(new BinarySearchTree(10));
        tree.insert(new BinarySearchTree(9));
        tree.insert(new BinarySearchTree(11));
    }

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @org.junit.Test
    public void minTest() throws Exception {
        assertEquals(1, tree.min().getValue());
    }

    @org.junit.Test
    public void maxTest() throws Exception {
        assertEquals(11, tree.max().getValue());
    }

    @org.junit.Test
    public void successorTest() throws Exception {
        // TODO
    }

    @org.junit.Test
    public void inorderTraversalTest() throws Exception {
        // Expect an inorder expression for the constructed tree
        tree.inorderTraversal();
        assertEquals("1\n2\n3\n4\n5\n6\n7\n8\n9\n10\n11\n", outContent.toString());

        outContent.reset();

        BinarySearchTree.staticInorderTraversal(tree);
        assertEquals("1\n2\n3\n4\n5\n6\n7\n8\n9\n10\n11\n", outContent.toString());
    }

    @org.junit.Test
    public void preorderTraversalTest() throws Exception {
        // Expect a preorder expression for the constructed tree
        tree.preorderTraversal();
        assertEquals("5\n3\n2\n1\n4\n7\n6\n8\n10\n9\n11\n", outContent.toString());

        outContent.reset();

        BinarySearchTree.staticPreorderTraversal(tree);
        assertEquals("5\n3\n2\n1\n4\n7\n6\n8\n10\n9\n11\n", outContent.toString());
    }

    @org.junit.Test
    public void postorderTraversalTest() throws Exception {
        // Expect a postorder expression for the constructed tree
        tree.postorderTraversal();
        assertEquals("1\n2\n4\n3\n6\n9\n11\n10\n8\n7\n5\n", outContent.toString());

        outContent.reset();

        BinarySearchTree.staticPostorderTraversal(tree);
        assertEquals("1\n2\n4\n3\n6\n9\n11\n10\n8\n7\n5\n", outContent.toString());
    }

    @org.junit.Test
    public void searchTest() throws Exception {
        boolean result;
        for (int i = 1; i <= 11; i++) {
            result = tree.search(i);
            assertEquals(true, result);
        }

        result = tree.search(12);
        assertEquals(false, result);
    }

    @org.junit.Test
    public void insertTest() throws Exception {
        tree.insert(new BinarySearchTree(12));
        assertEquals(12, tree.max().getValue());

        tree.insert(new BinarySearchTree(5));
        assertEquals(5, tree.getRight().min().getValue());
    }

    @org.junit.Test
    public void predecessorTest() throws Exception {
        // TODO
    }
}