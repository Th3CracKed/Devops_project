package utils;

import data_structure.DataFrame;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* ex
      A B C
1 Test1 2 2
2 Test2 5 5
3 test3 6 6
 */

public class CsvParser {
    /**
     * Liste d'identifiant de lignes
     */
    private static ArrayList<String> indexes = new ArrayList<>();

    /**
     * Liste d'identifiant de colonnes
     */
    private static ArrayList<String> labels = new ArrayList<>();

    public static DataFrame parse(String filename) throws IOException {
        ArrayList<ArrayList<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                ArrayList<String> values = new ArrayList<>(Arrays.asList(line.split(",")));
                records.add(values);
            }
        }
        System.out.println(records);

        labels= records.get(0);
        labels.remove(0);

        DataFrame res = null;
        //DataFrame res = new DataFrame(mIndexs, mLabels, Arrays.asList("Test1","Test2","test3"),Arrays.asList(2,5,6),Arrays.asList("2","6"));
        return res;
    }
}
