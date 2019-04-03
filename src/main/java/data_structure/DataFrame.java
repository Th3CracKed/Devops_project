package data_structure;

import java.util.*;

public class DataFrame {

    /**
     * Map Keys c'est l'identifiant de DataFrame == DataFrame Indexs
     * List de colonne qui contient le l
     */
    private Map<String,List<Colonne>> dataFrameContent;

    /**
     * Permet de deleguer le travail
     */
    private DataFrame(){
        dataFrameContent = new LinkedHashMap<>();
    }

    //TODO add Exeception checking
    public DataFrame(ArrayList <String> mIndexs, ArrayList<String> mLabels, Map<Integer,List<Colonne>> content){
        this();
        //indexs = mIndexs;
        //labels = mLabels;
    }

}
