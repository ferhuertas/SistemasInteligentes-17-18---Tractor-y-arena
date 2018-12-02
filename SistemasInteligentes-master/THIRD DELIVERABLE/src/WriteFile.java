
import java.io.FileWriter;

public class WriteFile {

    public static void generateNewField(int[] arrayData) {
        int random;
        FileWriter file = null;
        try {
            file = new FileWriter("C:\\Users\\fernando\\Documents\\NetBeansProjects\\First Deliverable\\src\\field.txt");
            for (int i = 0; i < arrayData.length; i++) {
                file.write(arrayData[i] + " ");
            }
            file.write("\r\n");
            for (int j = 0; j < arrayData[4]; j++) {
                for (int k = 0; k < arrayData[5]; k++) {
                    random = (int) Math.floor(Math.random() * arrayData[3] + 1);
                    file.write(" " + random);
                }
                file.write("\r\n");
            }
            file.close();
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }

}
