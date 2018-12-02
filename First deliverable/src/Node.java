import java.util.*;


public class Node implements Comparable<Node>{
	
	private Node parent;
	private State state;
	private int cost;
	private Action actionDone;
	private int depth;
	private int value;
	
	// to the folowing, we use the previus node ( the parent) and generate the action in him, then we get the new node
	public Node(Node parentNode, Action actionToPerform) {
		this.state =  actionToPerform.performAnAction(parentNode.getState());
		this.actionDone = actionToPerform;
		this.parent = parentNode;
		this.cost = parentNode.getCost()+1;
		this.depth = parentNode.getDepth()+1;
		this.value = Math.abs(1+(new Random()).nextInt()%100);
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
