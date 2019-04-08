package utils;


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

    private ArrayList<String> indexes = new ArrayList<>();
    private ArrayList<String> labels = new ArrayList<>();
    private boolean containsIndex = false;

    public CsvParser(String filename) throws IOException {
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
        if(labels.get(0).equals("Index")){
            labels.remove(0);
            containsIndex= true;
        }


        //DataFrame res = null;
        //DataFrame res = new DataFrame(mIndexs, mLabels, Arrays.asList("Test1","Test2","test3"),Arrays.asList(2,5,6),Arrays.asList("2","6"));
    }

    public ArrayList<String> getLabels(){
        return labels;
    }

    public ArrayList<String> getIndexes(){
        return indexes;
    }
}
