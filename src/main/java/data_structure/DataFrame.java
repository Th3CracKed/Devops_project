package data_structure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataFrame{

    /**
     * List d'identifiant de lignes
     */
    private List<String> indexes = new ArrayList<>();

    /**
     * Liste d'identifiant de colonnes
     */
    private List<String> labels = new ArrayList<>();

    /**
     * List de colonnes, la classe contient un label pour identifier
     *
     */
    private List <Column> columns = new ArrayList<>();

    /**
     * Constructeur prenant en paramètre le contenu de chaque colonne sous forme d’une List
     * Le nombres d'indices doit être égale aux nombres de cellules dans chaque colonnes
     * Le nombres de labels doit être égale aux nombres de colonnes
     * @param mIndexes liste d'identifiants de lignes
     * @param mLabels liste de labels de colonnes
     * @param columnsCells liste de contenu des colonnes
     * @throws NullPointerException Si l'une des lists specifier est null
     * @throws IllegalArgumentException Si la taille de données n'est pas cohérante
     */
    public DataFrame(String[] mIndexes, String[] mLabels, List<?>... columnsCells){
        if(mLabels.length != columnsCells.length )
            throw new IllegalArgumentException("Le nombre de label doit-etre egale au nombre de columns");

        for (List<?> cells : columnsCells) {
            if(mIndexes.length != cells.size()) {
                throw new IllegalArgumentException("La nombre d'indices doit-etre egale a la taille de columns");
            }else{
                columns.add(new Column<>(cells));
            }
        }

        Collections.addAll(indexes, mIndexes);
        Collections.addAll(labels, mLabels);

    }

    public DataFrame(List<String> mIndexes, List<String> mLabels, List<Column> columnsCells){
        if(mLabels.size() != columnsCells.size())
            throw new IllegalArgumentException("Le nombre de label doit-etre egale au nombre de colonnes");

        for (Column col : columnsCells) {
            if(mIndexes.size() != col.numberOfCells()) {
                throw new IllegalArgumentException("Le nombre d'indices doit-etre égale à la taille de colonnes");
            }else{
                //columns.add(new Column<>(cells));
            }
        }

        indexes=mIndexes;
        labels=mLabels;

    }

    /**
     * Affiche tout le DataFrame
     */
    public void printAll(){
        System.out.println(printCore(indexes.size(),true));
    }

    /**
     * Affiche les premières lignes de DataFrame
     */
    public void printHead(){
        System.out.println(printCore(indexes.size()-1,true));
    }

    /**
     * Affiche les premières lignes de DataFrame
     * @param nbLines le nombre de lignes à afficher
     */
    public void printHead(int nbLines){
        System.out.println(printCore(nbLines,true));
    }

    /**
     * Affiche les dernières lignes de DataFrame
     */
    public void printTail(){
        System.out.println(printCore(indexes.size()-1,false));
    }

    /**
     * Affiche les dernières lignes de DataFrame
     * @param nbLines le nombre de lignes à afficher
     */
    public void printTail(int nbLines){
        System.out.println(printCore(nbLines,false));
    }

    /**
     * Prepare une String qui contient le nombre de lignes passer en paramètres
     * Si le nbLines dépasse la taille de tableau, tout le tableau sera afficher
     * @param nbLines Nombre de lines à afficher
     * @param head Direction de l'affichage, true les premières lignes , false les dernières lignes
     * @return L'affichage de tout le DataFrame
     */
    private String printCore(int nbLines, boolean head){

        if(nbLines <= 0)    throw new IllegalArgumentException("Nombres de lignes doit être positif");

        if(nbLines > indexes.size())    nbLines = indexes.size();

        List <String> filteredIndexes;
        if(head){
            filteredIndexes = indexes.subList(0,nbLines);
        }else{
            filteredIndexes = indexes.subList(indexes.size()-nbLines, indexes.size());
        }

        int indexPadding = findMax(1,filteredIndexes); //Find max length of indexes String

        StringBuilder result = new StringBuilder();

        //Table header
        result.append(String.format("%-"+indexPadding+"s",""));//add spacing for printing labels
        // For each (label + column ) find max length, print all labels using as minimum width the max length of (current label, cells list) +1
        List <Integer> paddingTables = new ArrayList<>();//stores column padding to print the table
        for(int i = 0; i < labels.size(); i++){
            int maxPadding = labels.get(i).length();
            if(head){
                maxPadding = findMax(maxPadding,columns.get(i).getCells().subList(0,nbLines));
            }else{
                int nbCells = columns.get(i).getCells().size();
                maxPadding = findMax(maxPadding,columns.get(i).getCells().subList(nbCells-nbLines,nbCells));
            }
            result.append(String.format("%" + (maxPadding+1) + "s",labels.get(i)));
            paddingTables.add(maxPadding+1);
        }
        result.append(String.format("%n"));// an empty line

        //Table content
        //print indexes using as padding the max length of indexes & print all Columns using as minimum width the max length of (current label, cells list) +1
        for(int i = 0; i < filteredIndexes.size(); i++) {
            result.append(String.format("%-"+indexPadding+"s",filteredIndexes.get(i)));
            for(int j = 0; j < columns.size(); j++) {
                Column column = columns.get(j);
                Object cell;// get only the cell with current index
                if(head) {
                    cell = column.getCells().get(i);
                }else{
                    int nbCells = columns.get(i).getCells().size();
                    cell = column.getCells().subList(nbCells-nbLines,nbCells).get(i);
                }
                result.append(String.format("%"+paddingTables.get(j)+"s",cell.toString()));//formatted with max width padding
            }
            result.append(String.format("%n"));// an empty line
        }

        return result.toString();
    }

    /**
     * Trouve le maximum entre l'entier et la liste passer en paramètre
     * @param currentMax la valeur maximum precèdement trouver
     * @param objects la liste d'objets à parcourir
     */
    private int findMax(int currentMax,List objects){
        for(Object object : objects){
            if(currentMax < object.toString().length())
                currentMax = object.toString().length();
        }
        return currentMax;
    }

    /**
     * Getter
     * @return Liste des indices
     */
    public List<String> getIndexes() {
        return indexes;
    }

    /**
     * Getter
     * @return Liste de labels
     */
    public List<String> getLabels() {
        return labels;
    }

    /**
     * Getter
     * @return Liste de colonnes
     */
    public List<Column> getColumns() {
        return columns;
    }
}
