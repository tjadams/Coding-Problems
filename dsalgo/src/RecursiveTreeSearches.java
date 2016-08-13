package src;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class RecursiveTreeSearches {
    public static void main(String args[]) {
        /*
            5
          3   7
        1  2  6 8
        */

        /*
        Node root = generateRootNode();

        Queue<Node> bfsQueue = new LinkedList<Node>();
        bfsQueue.add(root);
        breadthFirstSearch(bfsQueue);

        Stack<Node> dfsStack = new Stack<Node>();
        dfsStack.push(root);
        depthFirstSearch(dfsStack);
        */

        NodeWithVisitationState root = generateRootNodeWithVisitationState();

        bft(root);
    }

    // Recursive BFS for Trees using a queue and using recursion like a while loop (see top and bottom of the method)
    public static void breadthFirstSearch(Queue queue) {
        if (queue.isEmpty()) {
            return;
        }

        Node node = (Node) queue.remove();

        System.out.println(node.value + " ");

        if (node.left != null) {
            queue.add(node.left);
        }

        if (node.right != null) {
            queue.add(node.right);
        }

        breadthFirstSearch(queue);
    }

    // Recursive DFS for Trees using a stack and using recursion like a while loop (see top and bottom of the method)
    public static void depthFirstSearch(Stack stack) {
        if (stack.isEmpty()) {
            return;
        }

        Node node = (Node) stack.pop();

        System.out.println(node.value + " ");

        if (node.right != null) {
            stack.push(node.right);
        }

        if (node.left != null) {
            stack.push(node.left);
        }

        depthFirstSearch(stack);
    }

    public static void visit(NodeWithVisitationState currentNode) {
        System.out.println(currentNode.value);
    }

    public static void conditionallyVisit(NodeWithVisitationState currentNode) {
        if (currentNode != null && !currentNode.hasBeenVisited) {
            visit(currentNode); // PRINTS YES OR NO
            currentNode.hasBeenVisited = true;
        }
    }

    // Breadth first traversal (I came up with this one on my own :D)
    public static void bft(NodeWithVisitationState currentNode) {
        if (currentNode == null) return;
        conditionallyVisit(currentNode);
        for (NodeWithVisitationState child : currentNode.getChildren()) {
            conditionallyVisit(child);
        }

        for (NodeWithVisitationState child : currentNode.getChildren()) {
            bft(child);
        }
    }

    private static Node generateRootNode() {
        Node root = new Node(5);
        Node a = new Node(3);
        Node b = new Node(7);
        root.left = a;
        root.right = b;
        Node c = new Node(1);
        Node d = new Node(2);
        a.left = c;
        a.right = d;
        Node e = new Node(6);
        Node f = new Node(8);
        b.left = e;
        b.right = f;

        return root;
    }

    private static NodeWithVisitationState generateRootNodeWithVisitationState() {
        NodeWithVisitationState root = new NodeWithVisitationState(5);
        NodeWithVisitationState a = new NodeWithVisitationState(3);
        NodeWithVisitationState b = new NodeWithVisitationState(7);
        root.left = a;
        root.right = b;
        NodeWithVisitationState c = new NodeWithVisitationState(1);
        NodeWithVisitationState d = new NodeWithVisitationState(2);
        a.left = c;
        a.right = d;
        NodeWithVisitationState e = new NodeWithVisitationState(6);
        NodeWithVisitationState f = new NodeWithVisitationState(8);
        b.left = e;
        b.right = f;

        return root;
    }
}
