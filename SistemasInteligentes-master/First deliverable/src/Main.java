
import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Main init = new Main();
        Scanner scan = new Scanner(System.in);
        boolean op = true;
        boolean generated = false;
        int opcion = 0;
        Field field = null;
        Tractor t= null;
        State initial=null;
        do {
            System.out.println("\n***** WELCOME TO TRACTOR MANIATIC *****");
            System.out.println("How do you want to introduce the values of the program?");
            System.out.println("1) Load values from a file.");
            System.out.println("2) Write values with the keyboard.");
            if (generated == true) {
                System.out.println("3) Move the tractor.");
            }
            System.out.println("4) Exit.");
            System.out.println("\n");
            opcion = scan.nextInt();

            switch (opcion) {

                case 1:
                    String ruta = "/field.txt";
                    field = new Field();
                    t = new Tractor(field.getX(), field.getY());
                    initial=new State(field, t);
                    generated = true;
                    break;

                case 2:
                    init.generateNewField();
                    field = new Field();
                    t = new Tractor(field.getX(), field.getY());
                    initial=new State(field, t);
                    generated = true;
                    break;

                case 3:
                    if (generated == true) {
                        ArrayList<Box> adjacent = new ArrayList();
                        List<Action> listActions = new ArrayList<>();
                        int y = initial.getField().getY();
                        int x = initial.getField().getX();
                        int ymax = initial.getField().getF() - 1;
                        int xmax = initial.getField().getC() - 1;
                        System.out.println("Movement testing.");
                        adjacent = initial.getTractor().adjacentBoxes(initial, xmax, ymax);

                        for (int i = 0; i < adjacent.size(); i++) {
                            System.out.println("Adjacent: [" + adjacent.get(i).getColumn() + "," + adjacent.get(i).getRow() + "] with sand of: " + adjacent.get(i).getSandQuantity());
                        }
                        int sandToMove = (initial.getField().getBox(initial.getTractor().getX(), initial.getTractor().getY()).getSandQuantity() - initial.getField().getK());
                        System.out.println("SANDTOMOVE= "+ sandToMove+" in position ("+initial.getTractor().getX()+", "+initial.getTractor().getY());
                        if (sandToMove > 0) {
                            listActions= init.possibleActions(initial, initial.getTractor().possiblemovements(x, y, xmax, ymax), sandToMove, adjacent);
                            System.out.println("Possible next movements:");
                            for (int i = 0; i<listActions.size();i++){     
                                System.out.println(listActions.get(i).toString());
                            }
                        } 
                        if (sandToMove==0 || listActions.size()==0){
                            
                            System.out.println("No sand to move");
                            for (int i= 0; i< adjacent.size();i++){                                   
                                    listActions.add(new Action(initial,adjacent.get(i),0,0,0,0));
                                    System.out.println(listActions.get(i).toString());
                                }
                        }
                        System.out.println("Example of execution of a random action of the list:");
                        Random rand = new Random();
                        int randomaction= rand.nextInt(listActions.size());
                        System.out.println(listActions.get(randomaction).toString());
                        initial= listActions.get(randomaction).performAnAction(initial);
                        System.out.println("New field:");
                        initial.getField().printField(initial.getField().getMatrix());
                        
                    }
                    //to check if it is doing the movement good, UNCOMPLETED

                    break;

                case 4:
                    op = false;
                    System.out.println("Bye!");
                    break;
            }

        } while (op == true);
    }

    public void generateNewField() throws IOException {
        Scanner scan = new Scanner(System.in);
        int x, y, k, MAX, C, F;
        boolean correct;
        do {
            correct = true;
            System.out.println("Introduce the x position of the tractor:");
            x = scan.nextInt();
            System.out.println("Introduce the y position of the tractor:");
            y = scan.nextInt();
            System.out.println("Introduce the desired quantity of sand in each box (k):");
            k = scan.nextInt();
            System.out.println("Introduce the maximum value of sand in each box:");
            MAX = scan.nextInt();
            System.out.println("Introduce the number of rows of the field: ");
            C = scan.nextInt();
            System.out.println("Introduce the number of columns of the field: ");
            F = scan.nextInt();
            if ((x - 1) > C || (y - 1) > F) {
                System.out.println("ERROR. The tractor cant be out of the field. Introduce the values again.\n");
                correct = false;
            }
        } while (!correct);

        int arrayData[] = {x, y, k, MAX, C, F};
        WriteFile.generateNewField(arrayData);

    }

    public List<Action> possibleActions(State state, ArrayList<String> myMovements, int sandToMove, ArrayList<Box> adjacent) {
        int[] distributions = new int[4];
        List<Action> listActions = new ArrayList<>();
        int x = state.getTractor().getX();
        int y = state.getTractor().getY();
        int max = state.getField().getMax();
        boolean existNorth= x>0;
        boolean existSouth= x<state.getField().getC() - 1;
        boolean existWest= y>0;
        boolean existEast= y<state.getField().getF() - 1;
        
        
        for (int n = 0; n <= sandToMove; n++) {
            for (int w = 0; w <= sandToMove; w++) {
                for (int e = 0; e <= sandToMove; e++) {
                    for (int s = 0; s <= sandToMove; s++) {
                        
                        if ((n + w + e + s) == sandToMove) {
                            //deja solo las que la suma de la arena distribuida sea igual a la cantidad de arena para repartir
                            if ((!(existNorth) && n > 0) || (!(existWest) && w > 0) || (!(existEast) && e > 0) || (!(existSouth) && s > 0)) {
                                
                             
                                //quita las que se haya hecho alguna distribucion a algun vecino
                            } else{ 
                                System.out.println("NORTH= "+(existNorth && (state.getField().getBox(x - 1, y).getSandQuantity() + n > max)));
                                System.out.println("WEST= "+(existWest && (state.getField().getBox(x, y - 1).getSandQuantity() + w > max)));
                                System.out.println("EAST= "+((existEast &&(state.getField().getBox(x, y + 1).getSandQuantity() + e > max))));
                                System.out.println("SOUTH= "+(existSouth && (state.getField().getBox(x + 1, y).getSandQuantity() + s > max)));
                                System.out.println("TOTAL= "+((  (existNorth && (state.getField().getBox(x - 1, y).getSandQuantity() + n > max)) ||(existWest && (state.getField().getBox(x, y - 1).getSandQuantity() + w > max)) || (existEast &&(state.getField().getBox(x, y + 1).getSandQuantity() + e > max)) || (existSouth && (state.getField().getBox(x + 1, y).getSandQuantity() + s > max)))));
                            
                                
                                if (  (existNorth && (state.getField().getBox(x - 1, y).getSandQuantity() + n > max)) ||(existWest && (state.getField().getBox(x, y - 1).getSandQuantity() + w > max)) || (existEast &&(state.getField().getBox(x, y + 1).getSandQuantity() + e > max)) || (existSouth && (state.getField().getBox(x + 1, y).getSandQuantity() + s > max))) {
                                //quita las que añadiendo la arena a esa casilla la cantidad de arena sea mayor al maximo
                            } else {

                                distributions[0] = n;		//quantity of sand to move to the NORTH
                                distributions[1] = s;		//quantity of sand to move to the SOUTH
                                distributions[2] = w;		//quantity of sand to move to the WEST
                                distributions[3] = e;		//quantity of sand to move to the EAST
                                System.out.println(distributions[0]+" "+distributions[1]+" "+distributions[2]+" "+distributions[3]);
                                for (int i= 0; i< adjacent.size();i++){                                   
                                    listActions.add(new Action(state,adjacent.get(i),distributions[0],distributions[1],distributions[2],distributions[3]));
                                }
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
