import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Problem {

	private State initialState;
	
	public State getInitialState() {
		return initialState;
	}

	public void setInitialState(State initialState) {
		this.initialState = initialState;
	}

	private int visitednodes = 0;
	private int creatednodes = 0;

	public Problem(State initialState){

		this.initialState=initialState;
	}

	public ArrayList<Node> acSolve(String strat, int maxdepth) throws IOException{
            
                Frontier frontier = new Frontier();
                frontier.createFrontier();

		Node initialNode = new Node(this.initialState);
		ArrayList<State> successorsList;
		Node actualNode = null;
		boolean sol = false;
		Node newnode =null;
                System.out.println("Estoy aquiENPROB");
		frontier.insertNode(initialNode);

		while(!sol && !frontier.isEmpty()){
			actualNode = frontier.removeFirst();
			visitednodes++;
			if(actualNode.getState().isGoal()){
                            System.out.println("SOLUTION FOUND!");
                            System.out.println("FINAL RESULT:");
                            actualNode.getState().getField().printField(actualNode.getState().getField().getMatrix());
                            System.out.println("NODES CREATED:"+ creatednodes);
                            System.out.println("NODES VISITED:"+ visitednodes);
				sol = true;
			}else{
				successorsList = actualNode.getState().Succesors(actualNode.getState());				
                                    System.out.println("Possible States;");
                                    for(State succesor: successorsList){
						newnode = new Node(actualNode,succesor, succesor.getActionDone(), strat, maxdepth);
                                                this.creatednodes++;
                                                frontier.insertNode(newnode);                                     
                                                  System.out.println(succesor.getActionDone().toString());
                                    }	
			}
		}
                if(sol)
		   return createSolution(actualNode);
		else return null;
	}


	public int getVisitednodes() {
		return visitednodes;
	}

	public void setVisitednodes(int visitednodes) {
		this.visitednodes = visitednodes;
	}

	public int getCreatednodes() {
		return creatednodes;
	}

	public void setCreatednodes(int creatednodes) {
		this.creatednodes = creatednodes;
	}

	public ArrayList<Node> search(String strat,int maxdepth, int incremdepth) throws IOException{

		int actualdepth = incremdepth;
                ArrayList<Node> solution = new ArrayList<Node>();
		while(actualdepth <= maxdepth){//only 1 time all strategies except iterative
			solution = acSolve(strat,actualdepth);
			actualdepth += incremdepth;//increment of the depth

		}
                return solution;
	}
        
     private static ArrayList<Node> createSolution(Node current_node) {
		ArrayList<Node> solution = new ArrayList<Node>();
		while(current_node.getParent()!=null && solution!= null) {
			solution.add(current_node);
			current_node = current_node.getParent();
		}
		return solution;
	}

}