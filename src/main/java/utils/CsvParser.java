package utils;


import data_structure.Column;
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
    private ArrayList <Column> columns = new ArrayList<>();
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

        records.remove(records.size()-1); // dernier element qui est vide

        //System.out.println(records);

        /* Recuperation des labels*/
        labels= records.get(0);
        if(labels.get(0).equals("Index")){
            labels.remove(0);
            containsIndex= true;
        }
        records.remove(0);
        for(int i=0;i<labels.size();i++){
            columns.add(new Column(new ArrayList<String>()));
        }
        for(int i=0;i<records.size();i++){
            if(containsIndex){
                indexes.add(records.get(i).get(0));
                records.get(i).remove(0);
            }
            else{
                indexes.add(""+i);
            }
            for(int j=0;j<labels.size();j++){
                columns.get(j).add(records.get(i).get(j));
            }



            //columns.add()
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

    public ArrayList<Column> getColumns(){
        return columns;
    }

}
