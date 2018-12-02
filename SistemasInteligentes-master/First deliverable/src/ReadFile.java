import java.io.*;
import java.util.*;

public class ReadFile {

    public static List<Integer> read(String file) throws IOException {
        List<Integer> data = new ArrayList<Integer>();
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line);//con el tokenizer dividimos por espacios
                while (st.hasMoreTokens()) {

                    data.add(Integer.parseInt(st.nextToken()));
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error reading the file " + e);
        }

        return data;
    }
}
