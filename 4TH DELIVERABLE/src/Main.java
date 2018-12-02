
import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException, Exception {
        Main init = new Main();
        Scanner scan = new Scanner(System.in);
        boolean op = true;
        boolean generated = false;
        boolean rightfile;
        int opcion = 0;
        Field field = null;
        Tractor t = null;
        State initial = null;
        double timeF = 0, timeI = 0;
        String path = "OutputSolution.txt";
        File file = new File(path);
        BufferedWriter br;
        br = new BufferedWriter(new FileWriter(file));
        try{
        do {
           
            System.out.println("\n***** WELCOME TO TRACTOR MANIATIC *****");
            System.out.println("How do you want to introduce the values of the program?");
            System.out.println("1) Load values from a file.");
            if (generated == true) {
                System.out.println("2) Move the tractor.");
            }
            System.out.println("3) Exit.\n");
            opcion = scan.nextInt();
            switch (opcion) {
                
                case 1:
                    do {
                        
                        rightfile = true;
                        try {
                            System.out.println("\nEnter the path of the file:");
                            String ruta = scan.next();
                            field = new Field(ruta);

                        } catch (IOException e) {
                            rightfile = false;
                            System.out.println(e);
                        } catch (Exception p) {
                            rightfile = false;
                        }
                    } while (rightfile == false);
                    t = new Tractor(field.getX(), field.getY());
                    initial = new State(field, t);
                    generated = true;
                    if (!field.correctData()) {
                        System.out.println("Impossible to obtain a solution with the data introduced, change the data and execute the program again");
                        op = false;
                    }
                    break;

                case 2:
                    if (generated == true) {
                        boolean goodstrategy = true;
                        int maxDepth = Integer.MAX_VALUE;
                        int incrDepth = 1;
                        ArrayList<Node> solutionlist = new ArrayList();
                        boolean opt;
                        String strat;
                        Problem p;
                        
                        System.out.println("Do you want to use optimazcion seach? (Yes , No)");
                        if ("YES".equals(scan.next().toUpperCase())) {
                            opt = true;
                        } else {
                            opt = false;
                            System.out.println("No optimization its applied");
                        }
                        
                        do{
                        System.out.println("Which type of algorithm you want: BFS, DFS, DLS, IDS, UCS, A* ?");
                        
                            strat = scan.next().toUpperCase();

                        
                            p = new Problem(initial);

                        switch (strat.toUpperCase()) {
                            case "BFS":
                                System.out.println("Introduce the Max Depth:");
                                maxDepth = scan.nextInt();
                                timeI = System.currentTimeMillis();
                                solutionlist = p.search(strat, maxDepth, incrDepth, opt);
                                timeF = System.currentTimeMillis();
                                goodstrategy=true;
                                break;
                            case "DFS":
                                System.out.println("Introduce the Max Depth:");
                                maxDepth = scan.nextInt();
                                timeI = System.currentTimeMillis();
                                solutionlist = p.search(strat, maxDepth, incrDepth, opt);
                                timeF = System.currentTimeMillis();
                                goodstrategy=true;
                                break;
                            case "DLS":
                                System.out.println("Introduce the Max Depth:");
                                maxDepth = scan.nextInt();
                                timeI = System.currentTimeMillis();
                                solutionlist = p.search(strat, maxDepth, incrDepth, opt);
                                timeF = System.currentTimeMillis();
                                goodstrategy=true;
                                break;
                            case "IDS":
                                System.out.println("Introduce the Max Depth:");
                                maxDepth = scan.nextInt();
                                System.out.println("Introduce the incremental Depth:");
                                incrDepth = scan.nextInt();
                                timeI = System.currentTimeMillis();
                                solutionlist = p.search(strat, maxDepth, incrDepth, opt);
                                timeF = System.currentTimeMillis();
                                goodstrategy=true;
                                break;
                            case "UCS":
                                System.out.println("Introduce the Max Depth:");
                                maxDepth = scan.nextInt();
                                timeI = System.currentTimeMillis();
                                solutionlist = p.search(strat, maxDepth, incrDepth, opt);
                                timeF = System.currentTimeMillis();
                                goodstrategy=true;
                                break;
                            case "A*":
                                System.out.println("Introduce the Max Depth:");
                                maxDepth = scan.nextInt();
                                timeI = System.currentTimeMillis();
                                solutionlist = p.search(strat, maxDepth, incrDepth, opt);
                                timeF = System.currentTimeMillis();
                                goodstrategy=true;
                                break;
                            default:
                                goodstrategy=false;
                                System.out.println("Error, enter a valid strategy");
                                break;
                        }
                        }while(goodstrategy==false);
                        if (solutionlist == null) {
                            System.out.println("SOLUTION NOT FOUND");
                        } else {
                            Node in = new Node(p.getInitialState());
                            br.write("Strategy: " + strat);
                            br.newLine();
                            br.write("Initial State:");
                            br.newLine();
                            br.write("None. Cost: "+in.getCost()+" Depth: "+ in.getDepth());
                            br.newLine();
                            in.getState().getField().writeField(in.getState().getField().getMatrix(), br);
                            br.write("Solution:");
                            br.newLine();                                                       
                            for(int i= solutionlist.size()-1; i>=0; i--)  { 
                                br.write(solutionlist.get(i).getActionDone().toString());
                                br.write(" Cost: "+solutionlist.get(i).getCost()+" Depth: "+ solutionlist.get(i).getDepth());
                                br.newLine();
                                solutionlist.get(i).getState().getField().writeField(solutionlist.get(i).getState().getField().getMatrix(), br);                              
                                
                            }
                            br.write("Total Cost= " + solutionlist.get(0).getCost());
                            br.write("  |  Total Depth= " + (solutionlist.get(0).getDepth()+1));
                            br.newLine();
                            br.write("Temporal complexity= " + (timeF - timeI) + "ms");
                            br.newLine();
                            br.write("Spatial Complexity: " + p.getCreatednodes() + " nodes generated");
                            br.newLine();
                            br.write("Optimization= " + (opt ? "Yes" : "No"));
                            br.newLine();

                        }
                        br.close();
                        System.exit(0);

                    }
                    break;

                case 3:
                    op = false;
                    System.out.println("Bye!");
                    break;
            }


        } while (op == true);
                            }catch(Exception e){
                            System.out.println("Error: "+ e);
                            main(args); 
                            }
    }

}
