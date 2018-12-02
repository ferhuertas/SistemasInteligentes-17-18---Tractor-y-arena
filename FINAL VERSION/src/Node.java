
import java.util.*;

public class Node implements Comparable<Node> {

    private Node parent;
    private State state;
    private int cost;
    private Action actionDone;
    private int depth;
    private int value;
    private int heuristic;

    // to the folowing, we use the previus node ( the parent) and generate the action in him, then we get the new node
    public Node(Node parentNode, State thisState, Action actionDone, String strategy, int maxDepth) throws MdepthException {
        this.state = thisState;
        this.actionDone = actionDone;
        this.parent = parentNode;
        this.cost = parentNode.getCost() + actionDone.getCost(); /// parent cost + elemementsofsand+1
        this.depth = parentNode.getDepth() + 1;
        //System.out.println("PROFUNDIAD EN NODO: "+ this.depth);
        this.heuristic = calculateHeuristic(thisState);
        if (depth > maxDepth) {
		throw new MdepthException();
	}
        switch (strategy) {
            case "BFS":
                this.value = depth;
                break;
            case "DFS":
            case "DLS":
            case "IDS":
                this.value = -depth;
                break;
            case "UCS":
                this.value = cost;
                break;
            case "A*":
                this.value = cost + heuristic;
                break;
            default:
                break;
        }
    }

    //to the first node (root)
    public Node(State state) {
        this.state = state;
        this.cost= 0;
        this.depth=0;
        this.value=0;
    }

    public Node getParent() {
        return parent;
    }

    public State getState() {
        return state;
    }

    public int getCost() {
        return cost;
    }

    public int getDepth() {
        return depth;
    }

    public int getValue() {
        return value;
    }

    public Action getActionDone() {
        return actionDone;
    }
    @Override
    public int compareTo(Node other) {
        if (this.getValue() < other.getValue()) {
            return -1;
        }
        if (this.getValue() > other.getValue()) {
            return 1;
        }
        return 0;
    }

    private int calculateHeuristic(State thisState) {
        int heuristicvalue = 0;
        for (int i = 0; i < thisState.getField().getC(); i++) {
            for (int j = 0; j < thisState.getField().getF(); j++) {
                if (thisState.getField().getBox(i, j).getSandQuantity() != thisState.getField().getK()) {
                    heuristicvalue++;
                }
            }
        }

        return heuristicvalue;
    }

}
