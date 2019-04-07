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
    private static String[] mIndexs; //= new String[]{"1", "2", "3"};
    private static String[] mLabels; // = new String[]{"A", "B", "C"};

    public static DataFrame parse(String filename) throws IOException {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                records.add(Arrays.asList(values));
            }
        }
        System.out.println(records);

        mLabels= records.get(0).toArray(new String[0]);
        DataFrame res = null;
        //DataFrame res = new DataFrame(mIndexs, mLabels, Arrays.asList("Test1","Test2","test3"),Arrays.asList(2,5,6),Arrays.asList("2","6"));
        return res;
    }
}
