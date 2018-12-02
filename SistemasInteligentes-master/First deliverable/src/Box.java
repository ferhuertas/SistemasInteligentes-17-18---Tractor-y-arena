
/**
 *
 * @author fernando
 */
public class Box {

    private int row, column;
    private boolean visited;
    private int sand_quantity;
    private int MAX;

    // Constructor
    public Box(int row, int column) {
        this.row = row;
        this.column = column;
        this.visited = false;
        this.MAX = 8;
        
    }

    // return a row
    public int getRow() {
        return row;
    }

    // set a row
    public void setRow(int row) {
        this.row = row;
    }

    // return a colum
    public int getColumn() {
        return column;
    }

    public int getSandQuantity() {
        return sand_quantity;
    }

    public void setSandQuantity(int sand_Quantity) {
        this.sand_quantity = sand_Quantity;
    }

    // set a newcolumn value
    public void setColumn(int column) {
        this.column = column;
    }

    // check if its visited
    public boolean isVisited() {
        return visited;
    }

    //put a box as visited
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    // to compare two boxes
    @Override
    public boolean equals(Object obj) {
        Box b = (Box) obj;
        if (getColumn() == b.getColumn() && getRow() == b.getRow()) {
            return true;
        }
        return false;
    }

    // SHOW THE COORDINATES
    @Override
    public String toString() {
        return "(" + getRow() + "," + getColumn() + ')';
    }
}
