
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fernando
 */
public class Action {

    private State stateBeforeAction;
    private Box newPosition;
    private int sandtoN;
    private int sandtoS;
    private int sandtoW;
    private int sandtoE;
    private int cost;

    public Action(State stateBeforeAction, Box newPosition, int sandtoN, int sandtoS, int sandtoW, int sandtoE) {

        this.stateBeforeAction = stateBeforeAction;
        this.newPosition = newPosition;
        this.sandtoN = sandtoN;
        this.sandtoS = sandtoS;
        this.sandtoW = sandtoW;
        this.sandtoE = sandtoE;
        this.cost=1;
    }

    public int getCost() {
        return cost;
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
    
    
    
    

    
    
    public State performAnAction(State previusState) throws IOException {
        Tractor t = previusState.getTractor();
              Box[][] newmatrix = new Box[previusState.getField().getC()][previusState.getField().getF()];
         
         for (int i = 0; i < previusState.getField().getC(); i++) {
            for (int j = 0; j < previusState.getField().getF(); j++) {
                    Box box = new Box(i, j);
                    box.setSandQuantity(previusState.getField().getBox(i,j).getSandQuantity());
                    newmatrix[i][j] = box;
                
            }
         }
         
         Field f=new Field(newmatrix, this.getNewPosition().getColumn(), this.getNewPosition().getRow(), previusState.getField().getMax(), previusState.getField().getC(), previusState.getField().getF(), previusState.getField().getK());


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
        f.setBox(t.getX(), t.getY(), (f.getBox(t.getX(), t.getY()).getSandQuantity() - (this.getSandtoE() + this.getSandtoN() + this.getSandtoS() + this.getSandtoW())));

        t = new Tractor(this.getNewPosition().getColumn(), this.getNewPosition().getRow());
        f.setX(t.getX());
        f.setY(t.getY());
        State newState = new State(f, t, this);

        return newState;
    }

}
