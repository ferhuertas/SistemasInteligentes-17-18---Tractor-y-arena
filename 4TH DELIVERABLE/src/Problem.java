
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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

    public Problem(State initialState) {

        this.initialState = initialState;
    }

    public ArrayList<Node> acSolve(String strat, int maxdepth, boolean opt,HashMap<String, Integer> visitados) throws IOException {

        Frontier frontier = new Frontier();
        frontier.createFrontier();      //create a frontier with priority queue  

        Node initialNode = new Node(this.initialState); //create the initial node.
        ArrayList<State> successorsList;
        Node actualNode = null;
        boolean sol = false;
        Node newnode = null;
        frontier.insertNode(initialNode); //insert the initial Node

        while (!sol && !frontier.isEmpty()) { //while we dont have a solution and there is nodes in the frontier
            actualNode = frontier.removeFirst(); //we get the node of the frontier
            visitednodes++;
            if (actualNode.getState().isGoal()) { //look if this node is a solution
                System.out.println("SOLUTION FOUND!");
                actualNode.getState().getField().printField(actualNode.getState().getField().getMatrix());
                sol = true;
            } else { //if its not the solution
                successorsList = actualNode.getState().Succesors(actualNode.getState()); //we generate the successors of this node
                for (State succesor : successorsList) { //for all it successors
                    try {
                        newnode = new Node(actualNode, succesor, succesor.getActionDone(), strat, maxdepth); //we convert the successor into a node
                        this.creatednodes++;
                    } catch (MdepthException e) { //if the depth of this node is bigger that the max we put it as null
                        newnode = null;
                        continue;
                    }
                    if (newnode != null) {
                        if (opt) { //if we want an optimized way
                            if (visitados.containsKey(newnode.getState().toString())) { //if in the hashtable we have the same state
                                if (newnode.getCost() <= visitados.get(newnode.getState().toString())) { //if the cost is better than the one in the hashtable
                                    visitados.replace(newnode.getState().toString(), newnode.getCost()); //we replace the old cost with the new cost.
                                    frontier.insertNode(newnode); //and we insert that node in the frontier to repeat the process until we reach a solution
                                }
                            } else {// if its not in the hashtable
                                visitados.put(newnode.getState().toString(), newnode.getCost()); //we insert it in the hashtable
                                frontier.insertNode(newnode); //and we insert that node in the front to repeat the proccess until we reach a solution
                            }
                        } else {//if there its not optimization
                            frontier.insertNode(newnode); //we insert that node in the front to repeat the proccess until we reach a solution
                        }
                    }
                }
            }
        }
        if (sol) { //if we have a soltution
            return createSolution(actualNode); //we create it
        } else {
            return null;
        }
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

    public ArrayList<Node> search(String strat, int maxdepth, int incremdepth, boolean opt) throws IOException {

        int actualdepth = incremdepth;
        ArrayList<Node> solution = new ArrayList<Node>();
        solution = null;
        HashMap<String, Integer> visitados = new HashMap<>();
        while (solution == null && actualdepth <= maxdepth) {//only 1 time all strategies except iterative                
            solution = acSolve(strat, actualdepth, opt, visitados);
            actualdepth += incremdepth;//increment of the depth
        }
        return solution;
    }

    private static ArrayList<Node> createSolution(Node current_node) {
        System.out.println("Creating the solution in the Output.txt file...");
        ArrayList<Node> solution = new ArrayList<Node>();
        while (current_node.getParent() != null && solution != null) {
            solution.add(current_node);
            current_node = current_node.getParent();
        }
        return solution;
    }

}
