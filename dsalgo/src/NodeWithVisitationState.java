package src;

import java.util.LinkedList;

public class NodeWithVisitationState {
    NodeWithVisitationState left;
    NodeWithVisitationState right;
    int value;
    boolean hasBeenVisited = false;

    public NodeWithVisitationState(int value) {
        this.value = value;
    }

    public LinkedList<NodeWithVisitationState> getChildren() {
        LinkedList list = new LinkedList<NodeWithVisitationState>();
        list.add(left);
        list.add(right);
        return list;
    }
}
