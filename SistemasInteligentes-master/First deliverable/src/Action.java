
public class Action {

    private State stateBeforeAction;
    private Box newPosition;
    private int sandtoN;
    private int sandtoS;
    private int sandtoW;
    private int sandtoE;
    private int cost = 1;

    public Action(State stateBeforeAction, Box newPosition, int sandtoN, int sandtoS, int sandtoW, int sandtoE) {

        this.stateBeforeAction = stateBeforeAction;
        this.newPosition = newPosition;
        this.sandtoN = sandtoN;
        this.sandtoS = sandtoS;
        this.sandtoW = sandtoW;
        this.sandtoE = sandtoE;
    }

    @Override
    public String toString() {
        int x = stateBeforeAction.getTractor().getX();
        int y = stateBeforeAction.getTractor().getY();
        return ("[" + "((" + newPosition.getColumn() + ", " + newPosition.getRow() + "), [(" + sandtoN + ", (" + (x - 1) + ", " + y + ")), (" + sandtoS + ", (" + (x + 1) + ", " + y + ")), (" + sandtoW + ", (" + x + ", " + (y - 1) + ")), (" + sandtoE + ", (" + x + ", " + (y + 1) + ")), " + cost + "]");
    }

    public Box getNewPosition() {
        return newPosition;
    }

    public int getSandtoN() {
        return sandtoN;
    }

    public int getSandtoS() {
        return sandtoS;
    }

    public int getSandtoW() {
        return sandtoW;
    }

    public int getSandtoE() {
        return sandtoE;
    }

    public State getStateBeforeAction() {
        return stateBeforeAction;
    }
    public State performAnAction(State previusState) {
        Tractor t = previusState.getTractor();
        Field f = this.getStateBeforeAction().getField();

        int ymax = f.getF() - 1;
        int xmax = f.getC() - 1;
        if ((t.getX() - 1) >= 0) {
            f.setBox(t.getX() - 1, t.getY(), (this.getSandtoN() + f.getBox(t.getX() - 1, t.getY()).getSandQuantity()));
        }
        if ((t.getX() + 1) <= xmax) {
            f.setBox(t.getX() + 1, t.getY(), (this.getSandtoS() + f.getBox(t.getX() + 1, t.getY()).getSandQuantity()));
        }
        if ((t.getY() - 1) >= 0) {
            f.setBox(t.getX(), t.getY() - 1, (this.getSandtoW() + f.getBox(t.getX(), t.getY() - 1).getSandQuantity()));
        }
        if ((t.getY() + 1) <= ymax) {
            f.setBox(t.getX(), t.getY() + 1, (this.getSandtoE() + f.getBox(t.getX(), t.getY() + 1).getSandQuantity()));
        }
        f.setBox(f.getX(), f.getY(), (f.getBox(f.getX(), f.getY()).getSandQuantity() - (this.getSandtoE() + this.getSandtoN() + this.getSandtoS() + this.getSandtoW())));

        t = new Tractor(this.getNewPosition().getColumn(), this.getNewPosition().getRow());
        f.setX(t.getX());
        f.setY(t.getY());
        State newState = new State(f, t);

        return newState;
    }

}
