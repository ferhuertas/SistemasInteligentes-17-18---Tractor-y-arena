
import java.util.ArrayList;

public class Tractor {

    int x;
    int y;
    int sand;
    int s;
    char nextMovement;
    int size;

    public Tractor(int x, int y, int sand, int size, int s, char nextMovement) {
        this.x = x;
        this.y = y;
        this.sand = 0;
        this.size = size;
        this.s = s;
        this.nextMovement = nextMovement;
    }

    public Tractor(int x, int y) {
        this.x = x;
        this.y = y;
        this.sand = 0;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSand() {
        return sand;
    }

    public void setSand(int sand) {
        this.sand = sand;
    }

    public ArrayList<Box> adjacentBoxes(State state, int xmax, int ymax) {

        ArrayList<Box> adjacent = new ArrayList();
        ArrayList<String> myMovements = new ArrayList<>();
        int x =state.getTractor().getX();
        int y= state.getTractor().getY();
        myMovements = possiblemovements(x,y, xmax, ymax);

        if (myMovements.contains("right")) {
            adjacent.add(state.getField().getBox(x, y + 1));

        }//move right
        if (myMovements.contains("down")) {
            adjacent.add(state.getField().getBox(x + 1, y));

        }// move down
        if (myMovements.contains("up")) {
            adjacent.add(state.getField().getBox(x - 1, y));

        }// move up
        if (myMovements.contains("left")) {
            adjacent.add(state.getField().getBox(x, y - 1));

        } // move left

        return adjacent;
    }

    public ArrayList possiblemovements(int x, int y, int xmax, int ymax) {
        ArrayList<String> myMovements = new ArrayList<>();
        myMovements.add("up");
        myMovements.add("down");
        myMovements.add("right");
        myMovements.add("left");
        if (x == 0) {
            myMovements.remove("up");
        }
        if (y == 0) {
            myMovements.remove("left");
        }
        if (x == xmax) {
            myMovements.remove("down");
        }
        if (y == ymax) {
            myMovements.remove("right");
        }
        return myMovements;
    }
}
