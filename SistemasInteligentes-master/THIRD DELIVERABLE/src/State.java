
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author fernando
 */
public class State {

    private Field field;
    private Tractor tractor;
    private Action actionDone;

    public Action getActionDone() {
        return actionDone;
    }

    public State(Field field, Tractor tractor) {
        this.field = field;
        this.tractor = tractor;
    }

    public State(Field field, Tractor tractor, Action actionDone) {
        this.field = field;
        this.tractor = tractor;
        this.actionDone = actionDone;
    }

    public Field getField() {
        return field;
    }

    public Tractor getTractor() {
        return tractor;
    }

    public boolean isGoal() {
        for (int i = 0; i < field.getC(); i++) {
            for (int j = 0; j < field.getF(); j++) {
                if (field.getBox(i, j).getSandQuantity() != field.getK()) {
                    return false;
                }
            }
        }
        return true;
    }

    public ArrayList<State> Succesors(State currentState) throws IOException {
        ArrayList<State> listSuccessors = new ArrayList<State>();
        ArrayList<Box> adjacent = new ArrayList();
        List<Action> listActions = new ArrayList<>();
        int y = currentState.getField().getY();
        int x = currentState.getField().getX();
        int ymax = currentState.getField().getF() - 1;
        int xmax = currentState.getField().getC() - 1;
        adjacent = currentState.getTractor().adjacentBoxes(currentState, xmax, ymax);

        int sandToMove = (currentState.getField().getBox(currentState.getTractor().getX(), currentState.getTractor().getY()).getSandQuantity() - currentState.getField().getK());
        if (sandToMove > 0) {
            listActions = possibleActions(currentState, currentState.getTractor().possiblemovements(x, y, xmax, ymax), sandToMove, adjacent);
        }
        if (sandToMove == 0 || listActions.size() == 0) {
            //if the tractor dont have sant to move or there is not a adjacent to position to move the sand, then the only actions is moving
            for (int i = 0; i < adjacent.size(); i++) {
                listActions.add(new Action(currentState, adjacent.get(i), 0, 0, 0, 0));
            }
        }

        for (Action possibleAction : listActions) {
            State newState;
            newState= possibleAction.performAnAction(currentState);
                                                            System.out.println("ActionToDO");
                                                System.out.println(newState.getActionDone().toString());
                                                System.out.println("Tractor in positon ("+newState.getTractor().getX()+"),("+newState.getTractor().getY()+")");
                                                 newState.getField().printField(newState.getField().getMatrix());
            listSuccessors.add(new State(newState.getField(), newState.getTractor(), possibleAction));
        }
        return listSuccessors;
    }

    public List<Action> possibleActions(State state, ArrayList<String> myMovements, int sandToMove, ArrayList<Box> adjacent) {
        int[] distributions = new int[4];
        List<Action> listActions = new ArrayList<>();
        int x = state.getTractor().getX();
        int y = state.getTractor().getY();
        int max = state.getField().getMax();
        boolean existNorth = x > 0;
        boolean existSouth = x < state.getField().getC() - 1;
        boolean existWest = y > 0;
        boolean existEast = y < state.getField().getF() - 1;

        for (int n = 0; n <= sandToMove; n++) {
            for (int w = 0; w <= sandToMove; w++) {
                for (int e = 0; e <= sandToMove; e++) {
                    for (int s = 0; s <= sandToMove; s++) {

                        if ((n + w + e + s) == sandToMove) {
                            //deja solo las que la suma de la arena distribuida sea igual a la cantidad de arena para repartir
                            if ((!(existNorth) && n > 0) || (!(existWest) && w > 0) || (!(existEast) && e > 0) || (!(existSouth) && s > 0)) {

                                //quita las que se haya hecho alguna distribucion a algun vecino
                            } else if ((existNorth && (state.getField().getBox(x - 1, y).getSandQuantity() + n > max)) || (existWest && (state.getField().getBox(x, y - 1).getSandQuantity() + w > max)) || (existEast && (state.getField().getBox(x, y + 1).getSandQuantity() + e > max)) || (existSouth && (state.getField().getBox(x + 1, y).getSandQuantity() + s > max))) {
                                //quita las que añadiendo la arena a esa casilla la cantidad de arena sea mayor al maximo
                            } else {

                                distributions[0] = n;		//quantity of sand to move to the NORTH
                                distributions[1] = s;		//quantity of sand to move to the SOUTH
                                distributions[2] = w;		//quantity of sand to move to the WEST
                                distributions[3] = e;		//quantity of sand to move to the EAST
                                for (int i = 0; i < adjacent.size(); i++) {
                                    listActions.add(new Action(state, adjacent.get(i), distributions[0], distributions[1], distributions[2], distributions[3]));
                                }
                            }
                        }
                    }

                }
            }
        }

        return listActions;
    }
}
