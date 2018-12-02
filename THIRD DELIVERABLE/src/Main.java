
import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException,Exception{
        Main init = new Main();
        Scanner scan = new Scanner(System.in);
        boolean op = true;
        boolean generated = false;
        int opcion = 0;
        Field field = null;
        Tractor t = null;
        State initial = null;
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
                    initial = new State(field, t);
                    generated = true;
                    break;

                case 2:
                    init.generateNewField();
                    field = new Field();
                    t = new Tractor(field.getX(), field.getY());
                    initial = new State(field, t);
                    generated = true;
                    break;

                case 3:
                    if (generated == true) {
                        int maxDepth=Integer.MAX_VALUE;
                        int incrDepth;
                       ArrayList<Node> solutionlist = new ArrayList();
                        
                        System.out.println("Which type of algorithm you want: BFS, DFS, DLS, IDS, UCS");
                        String strat=scan.next().toUpperCase();
                        
                        
                        Problem p = new Problem(initial);
                        
		
		switch(strat.toUpperCase()){
		case "BFS":
                       System.out.println("Estoy BFS");
			solutionlist= p.acSolve(strat, maxDepth);
                        
			break;
		case "DFS":
			solutionlist= p.acSolve(strat, maxDepth);
			break;
		case "DLS":
			System.out.println("Introduce the Max Depth:");
			maxDepth=scan.nextInt();
			solutionlist= p.acSolve(strat, maxDepth);
			break;
		case "IDS":
			System.out.println("Introduce the Max Depth:");
			maxDepth=scan.nextInt();
			System.out.println("Introduce the incremental Depth:");
			incrDepth=scan.nextInt();
			solutionlist= p.search(strat, maxDepth, incrDepth);
			break;
		case "UCS":
			solutionlist=p.acSolve(strat, maxDepth);
			break;
		}
                System.out.println("Steps to get solution");

                 for(Node node: solutionlist){
                     node.getState().getField().printField(node.getState().getField().getMatrix());
                     System.out.println();
                     //while(node.getParent()!=null){
                     //node.getParent().getState().getField().printField(node.getParent().getState().getField().getMatrix());
                     //System.out.println();
                     //node=node.getParent();
                     
                    // }
                 }

		
		System.exit(0);
                        
                        
                        
                    }
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
    
    
	

 

}
/*
		listTI = System.nanoTime();								
		for(int i = 0; i< successors.size(); i++) {
			frontier.insertNode(suc.get(i));
		}
		Collections.sort(frontier.getFrontier());
		listTF = System.nanoTime() - listTI;								
		for(int i = 0; i< successors.size(); i++) {
			System.out.println(frontier.getFrontier().get(i));
		}


		queueTI = System.nanoTime();												//-------------------------------------
		Frontier frontier2 = new Frontier();
		frontier2.createFrontierQ();
		for(int i = 0; i< successors.size(); i++) {
			frontier2.insertNodeQ(suc.get(i));
		}
		queueTF = System.nanoTime()- queueTI;										//-------------------------------------
		Iterator<Node> it = frontier2.getFrontierQ().iterator();
		while(it.hasNext()) 			
			System.out.println(frontier2.getFrontierQ().poll());
		}
*/
