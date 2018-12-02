import java.util.*;


public class Node implements Comparable<Node>{
	
	private Node parent;
	private State state;
	private int cost;
	private Action actionDone;
	private int depth;
	private int value;
	
	// to the folowing, we use the previus node ( the parent) and generate the action in him, then we get the new node
    public Node(Node parentNode, State thisState, Action actionDone, String strategy, int maxDepth) {
        this.state = thisState;
        this.actionDone = actionDone;
        this.parent = parentNode;
        this.cost = parentNode.getCost() + actionDone.getCost() + 1; /// parent cost + elemementsofsand+1
        this.depth = parentNode.getDepth() + 1;

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
                default:
                    break;
            }
    }

        //to the first node (root)
	public Node(State state) {
		this.state = state;
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
        
        
       public void getPath(Queue<String> q){
		if(!(this.parent == null)){
			this.parent.getPath(q);
		}
		if(this.actionDone.toString() != " ")
			q.add(this.actionDone.toString());
	}
	
	@Override
	public int compareTo(Node other) { 
		if(this.getValue()<other.getValue()){
			return -1;
		}
		if(this.getValue()>other.getValue()){
			return 1;
		}
		return 0;
	}
	
}