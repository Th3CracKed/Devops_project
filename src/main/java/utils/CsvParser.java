package utils;


import data_structure.Column;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * La classe permettant de parser un CSV, indépendante de Dataframe.
 */
public class CsvParser {

    private ArrayList<String> indexes = new ArrayList<>();
    private ArrayList<String> labels = new ArrayList<>();
    private ArrayList <Column> columns = new ArrayList<>();
    private boolean containsIndex = false;

    /**
     *
     * @param filename Le nom du fichier
     * @throws IOException
     */
    public CsvParser(String filename) throws IOException {
        ArrayList<ArrayList<String>> records = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null) {
                ArrayList<String> values = new ArrayList<>(Arrays.asList(line.split(",")));
                records.add(values);
            }

        records.remove(records.size()-1); // dernier element qui est vide

        /* Recuperation des labels*/
        labels = records.get(0);
        if(labels.get(0).equals("Index")){
            labels.remove(0);
            containsIndex= true;
        }
        records.remove(0);
        for(int i = 0; i < labels.size(); i++){
            columns.add(new Column(new ArrayList<String>()));
        }
        for(int i = 0;i < records.size(); i++){

            /* Recuperation des indexes (s'ils existent) */
            if(containsIndex){
                indexes.add(records.get(i).get(0));
                records.get(i).remove(0);
            }
            else{
                indexes.add(""+i);
            }
            /* ========================================== */

            /* Boucle d'ajout des colonnes */
            for(int j = 0; j < labels.size(); j++){
                double f;
                String val = records.get(i).get(j);
                try {
                    f = Double.valueOf(val.trim()).floatValue(); /* on vérifie si on peut caster en double*/
                    if(f-Math.round(f)<=0){ /* si la valeur est un entier on l'ajoute en tant qu'entier*/
                        columns.get(j).add(Math.round(f));
                    }
                    else{  /* sinon on l'enregistre en tant que double */
                        columns.get(j).add(f);
                    }
                } catch (NumberFormatException e) { /* si une erreur est détectée, c'est que la valeur est un string */
                    columns.get(j).add(val);
                }
            }
        }
    }

    /**
     * Get labels
     * @return La liste des labels
     */
    public ArrayList<String> getLabels(){

        return labels;
    }

    /**
     * Get indexes
     * @return La liste des indexes
     */
    public ArrayList<String> getIndexes(){
        return indexes;
    }

    /**
     * Get columns
     * @return La liste des colonnes
     */
    public ArrayList<Column> getColumns(){
        return columns;
    }

}
