import java.util.ArrayList;
import java.util.List;

public class State {
    
    private Field field;
    private Tractor tractor;

    public State(Field field, Tractor tractor) {
        this.field = field;
        this.tractor = tractor;
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

    public List<Node> Succesors(State currentState, ArrayList<Action> possibleActions) {
        List<Node> listSuccessors = new ArrayList<>();
        Node parentNode = new Node(currentState);
        for (Action possibleAction : possibleActions) {
            listSuccessors.add(new Node(parentNode, possibleAction));
        }
        return listSuccessors;
    }
}

    
    
}
