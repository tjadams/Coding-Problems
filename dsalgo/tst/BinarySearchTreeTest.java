package tst;

import org.junit.Before;
import src.BinarySearchTree;

import static org.junit.Assert.*;
import static src.BinarySearchTree.search;

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

    }

    @org.junit.Test
    public void maxTest() throws Exception {

    }

    @org.junit.Test
    public void successorTest() throws Exception {

    }

    @org.junit.Test
    public void searchTest() throws Exception {
        boolean result;
        for (int i = 1; i <= 11; i++) {
            result = search(tree, i);
            assertEquals(true, result);
        }

        result = search(tree, 12);
        assertEquals(false, result);
    }

    @org.junit.Test
    public void insertTest() throws Exception {

    }

    @org.junit.Test
    public void predecessorTest() throws Exception {

    }
}