
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
        this.cost=sandtoN+sandtoE+sandtoS+sandtoW+1;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        int x = stateBeforeAction.getTractor().getX();
        int y = stateBeforeAction.getTractor().getY();
        
        String action = "[ ((" + newPosition.getRow() + ", " + newPosition.getColumn() + "), [";
        if (x - 1 >= 0) {
            action += " (" + sandtoN + ", (" + (x - 1) + ", " + y + ")),";
        }
        if (x + 1 < stateBeforeAction.getField().getF()) {
            action += " (" + sandtoS + ", (" + (x + 1) + ", " + y + ")),";
        }
        if (y - 1 >= 0) {
            action += " (" + sandtoW + ", (" + x + ", " + (y - 1) + ")),";
        }
        if (y + 1 < stateBeforeAction.getField().getC()) {
            action += " (" + sandtoE + ", (" + x + ", " + (y + 1) + ")),";
        }
        action += " " + cost + "]";
        
        return action;
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
              Box[][] newmatrix = new Box[previusState.getField().getF()][previusState.getField().getC()];
         
         for (int i = 0; i < previusState.getField().getF(); i++) {
            for (int j = 0; j < previusState.getField().getC(); j++) {
                    Box box = new Box(i, j);
                    box.setSandQuantity(previusState.getField().getBox(i,j).getSandQuantity());
                    newmatrix[i][j] = box;
                
            }
         }
         
         Field f=new Field(newmatrix, this.getNewPosition().getRow(), this.getNewPosition().getColumn(), previusState.getField().getMax(), previusState.getField().getF(), previusState.getField().getC(), previusState.getField().getK());


        int ymax = f.getC() - 1;
        int xmax = f.getF() - 1;
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

        t = new Tractor(this.getNewPosition().getRow(), this.getNewPosition().getColumn());
        f.setX(t.getX());
        f.setY(t.getY());
        State newState = new State(f, t, this);

        return newState;
    }

}
