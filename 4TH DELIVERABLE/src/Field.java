
import java.io.BufferedWriter;
import java.util.List;
import java.io.IOException;
import java.util.*;

public class Field {

    private Box[][] matrix;

    private int x, y; //posicion en la que va a aparecer el tractor
    private int max, c, f;//c y f contienen la dimension de nuestro field
    private int k;
    private List<Integer> list;

    public Field(String ruta) throws IOException {//aqui en el constructor vamos a tomar los datos del fichero, primero los guardamos en una lista y ya los vamos cogiendo de la lista
        this.list= ReadFile.read(ruta);
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
        System.out.println("\n ...Generating field... \n");
        System.out.println(x + " " + y + " " + k + " " + max + " " + c + " " + f);

        matrix = createfield();
        System.out.println(printField(matrix));
    }
    
           public Field(Box[][] matrix, int x, int y, int max, int c, int f, int k)  throws IOException{
        this.matrix = matrix;
        this.x = x;
        this.y = y;
        this.max = max;
        this.c = c;
        this.f = f;
        this.k = k;
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

        Box[][] matrix = new Box[getF()][c];
        for (int i = 0; i < getF(); i++) {
            for (int j = 0; j < c; j++) {
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

    public boolean correctData() {
        boolean correct = false;
        int quantity = 0;
        for (int i = 0; i < getF(); i++) {
            for (int j = 0; j < getC(); j++) {
                quantity += matrix[i][j].getSandQuantity();//total sand of the matrix
            }
        }
        if (((quantity / (getF() * getC())) == this.k)) {
            correct = true;
        }
        return correct;
    }

    public String printField(Box matrix[][]) {
        String smatrix = "";
        for (int i = 0; i < getF(); i++) {
            for (int j = 0; j < c; j++) {
                smatrix += matrix[i][j].getSandQuantity() + " ";
            }
            smatrix+= "\n";
        }
        return smatrix;
    }
    
    public void writeField(Box matrix[][], BufferedWriter br) throws IOException{
          for (int i = 0; i < getF(); i++) {
            for (int j = 0; j < c; j++) {
                br.write(matrix[i][j].getSandQuantity() + " ");
            }
            br.newLine();
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

    public void setBox(int x, int y, int quantity) {
        matrix[x][y].setSandQuantity(quantity);
    }

    public Box[][] getMatrix() {
        return matrix;
    }

}
