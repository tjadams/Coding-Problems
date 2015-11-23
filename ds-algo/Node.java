/**
 * Created by Tyler Adams on 20/04/2015.
 */

public class Node{
    private Node left;
    private Node right;
    private int value;

    public Node(){
        setLeft(null);
        setRight(null);
        setValue(-1);
    }

    public Node(int value){
        Node n = new Node();
        n.setValue(value);
    }

    public Node left() {
        return left;
    }

    public Node right() {
        return right;
    }

    public int value() {
        return value;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
