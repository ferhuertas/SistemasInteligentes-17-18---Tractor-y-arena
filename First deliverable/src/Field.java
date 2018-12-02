
import java.util.List;
import java.io.IOException;
import java.util.*;

public class Field {

    private Box[][] matrix;


    private int x, y; //posicion en la que va a aparecer el tractor
    private int max, c, f;//c y f contienen la dimension de nuestro field
    private int k;
    private List<Integer> list = ReadFile.read("C:\\Users\\fernando\\Documents\\NetBeansProjects\\First Deliverable\\src\\field.txt");

    public Field() throws IOException {//aqui en el constructor vamos a tomar los datos del fichero, primero los guardamos en una lista y ya los vamos cogiendo de la lista
        System.out.println("\n ...Generating field... \n");
        this.x = list.get(0);
        list.remove(0);//eliminamos el valor 0 de la lista que contiene la x que acabamos de guardar, y repetimos el proceso
        this.y = list.get(0);
        list.remove(0);
        this.k = list.get(0);
        list.remove(0);
        this.max = list.get(0);
        list.remove(0);
        this.c = list.get(0);
        list.remove(0);
        this.f = list.get(0);
        list.remove(0);
        System.out.println(x + " " + y + " " + k + " " + max + " " + c + " " + f);

        matrix = createfield();
        printField(matrix);
    }

    public int getC() {
        return c;
    }

    public int getF() {
        return f;
    }
        public int getK() {
        return k;
    }
                public int getMax() {
        return max;
    }


    public Box[][] createfield() {//construir la matriz a partir de los valores leidos del archivo

        Box[][] matrix = new Box[c][getF()];
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < getF(); j++) {
                if (list.get(0) <= max) {
                    Box box = new Box(i, j);
                    box.setSandQuantity(list.get(0));
                    matrix[i][j] = box;

                } else {
                    System.out.println("The quantity of sand of a box cant be bigger than the MAX");
                }
                list.remove(0);
            }
        }

        return matrix;

    }

    public void printField(Box matrix[][]) {

        for (int i = 0; i < c; i++) {
            for (int j = 0; j < getF(); j++) {
                System.out.print(matrix[i][j].getSandQuantity() + " ");
            }
            System.out.println();
        }

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
        public Box getBox(int x, int y) {
        return matrix[x][y];
    }
    public void setBox(int x, int y, int quantity){
            matrix[x][y].setSandQuantity(quantity);
}
    public Box[][] getMatrix() {
        return matrix;
    }

}
