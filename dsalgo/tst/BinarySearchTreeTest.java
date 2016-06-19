package tst;

import org.junit.Before;
import src.BinarySearchTree;

import static org.junit.Assert.*;

public class BinarySearchTreeTest {

    // Example tree I'll be using:
    //       5
    //    3     7
    //  2  4   6  8
    // 1            10
    //             9  11
    private BinarySearchTree tree;

    @Before
    public void setup() {
        tree = new BinarySearchTree(5);

        for (int i = 1; i <= 11; i++) {
            // In the example tree I drew, 5 is the root value and all values in the tree are unique for now.
            // So we skip inserting an extra value of 5
            if (i != 5) {
                tree.insert(new BinarySearchTree(i));
            }
        }
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