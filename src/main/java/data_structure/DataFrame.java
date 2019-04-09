package data_structure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
            throw new IllegalArgumentException("Le nombre de labels doit-etre egale au nombre de colonnes");

        for (List<?> cells : columnsCells) {
            if(mIndexes.length != cells.size()) {
                throw new IllegalArgumentException("Le nombre d'indices doit-etre egale a la taille de colonnes");
            }else{
                columns.add(new Column<>(cells));
            }
        }

        Collections.addAll(indexes, mIndexes);
        Collections.addAll(labels, mLabels);

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
     * Cree un nouveau DataFrame en selectionnant les indices passer en paramètre
     * @param selected_Indexes les lignes à retourner
     * @return un DataFrame des celulles des lignes passer en paramètre
     * @throws IndexOutOfBoundsException si l'une des indices passer en paramètre est en dehors du rang
     * @throws IllegalArgumentException si aucun paramètre n'est passer
     */
    public DataFrame iloc(int... selected_Indexes){
        if(selected_Indexes.length==0)
            throw new IllegalArgumentException("Il faut passer au moins un paramètre");

        List <String> mIndexes = new ArrayList<>();
        List[] columnsCells = new List[labels.size()];

        for(int index : selected_Indexes){
            mIndexes.add(indexes.get(index));

            int columnIndex = 0;
            for(Column column : columns) {
                if(columnsCells[columnIndex]==null){
                    columnsCells[columnIndex] = new ArrayList();
                }
                columnsCells[columnIndex].add(column.getCells().get(index));
                columnIndex++;
            }

        }

        return new DataFrame(mIndexes.toArray(new String[0]),labels.toArray(new String[0]), columnsCells);
    }

    /**
     * Cree un nouveau DataFrame en selectionnant les labels passer en paramètre
     * si un label n'est pas trouvé retourne des colonnes avec la valeur 'NaN'
     * @param selected_Labels les colonnes à retourner
     * @return un DataFrame à partir des colonnes passer en paramètre
     * @throws IllegalArgumentException si aucun paramètre n'est passer
     */
    public DataFrame loc(String... selected_Labels){
        if(selected_Labels.length==0)
            throw new IllegalArgumentException("Il faut passer au moins un paramètre");

        List <String> mIndexes = new ArrayList<>();
        List[] columnsCells = new List[labels.size()];

        for(String label : selected_Labels){
            mIndexes.add(label);
            int index = indexes.indexOf(label);
            int columnIndex = 0;
            for(Column column : columns) {
                if(columnsCells[columnIndex]==null){
                    columnsCells[columnIndex] = new ArrayList();
                }
                if(index!=-1) {
                    columnsCells[columnIndex].add(column.getCells().get(index));
                }else{
                    columnsCells[columnIndex].add("NaN");//label not found
                }
                columnIndex++;
            }
        }

        return new DataFrame(mIndexes.toArray(new String[0]),labels.toArray(new String[0]), columnsCells);
    }

    /**
     * Calcule la somme de chaque colonne (affichage par colonne)
     * @return liste des sommes
     */
    public List<Double> sum(){
        return sum(0);
    }

    /**
     * Calcule la somme de l'axis passer en paramètre
     * @param axis 0 Calcule la somme de chaque colonne(affichage par colonne), 1 Calcule la somme de chaque ligne (affichage par ligne)
     * @return liste des sommes
     */
    public List<Double> sum(int axis){
        return sum(axis,true);
    }

    /**
     * Calcule la somme de l'axis passer en paramètre
     *
     * @param axis 0 Calcule la somme de chaque colonne(affichage par colonne), 1 Calcule la somme de chaque ligne (affichage par ligne)
     * @param skipNa true eviter les valeurs NaN, false ne pas eviter la somme sera NaN
     * @return liste des sommes
     */
    public List <Double> sum(int axis, boolean skipNa){
        List <Double> results = new ArrayList<>();
        if(axis == 0){
            for (Column column : columns) {
                double  columnSum = 0;
                for (Object cell : column.getCells()) {
                    columnSum = sumCore(columnSum,cell,skipNa);
                }
                results.add(columnSum);
            }
        }else if(axis == 1){
            for(int i = 0; i < indexes.size(); i++) {
                double columnSum = 0;
                for (Column column : columns) {
                    Object cell = column.getCells().get(i);
                    columnSum = sumCore(columnSum,cell,skipNa);
                }
                results.add(columnSum);
            }
        }
        return results;
    }

    /**
     * calcule la somme courrante
     * @param columnSum la somme courrante
     * @param cell la valeur de la celulle courante
     * @param skipNa ignore les valeurs null ou non
     * @return la somme avec la cellule courrante
     */
    private double sumCore(double columnSum, Object cell, boolean skipNa) {
        try {
            //handle case when cell's value is 'NaN' which is treated as legit double value
            if(skipNa && (String.valueOf(cell).equals("NaN") || String.valueOf(cell).equals("+NaN") || String.valueOf(cell).equals("-NaN"))){
                return columnSum;
            }
            columnSum += Double.parseDouble(String.valueOf(cell));
        }catch (NumberFormatException n){
            if(!skipNa){
                columnSum += Double.parseDouble("NaN");//Sum of this column is NaN
            }
        }
        return columnSum;
    }

    /**
     * Trouve le minimum de chaque colonne (affichage par colonne)
     * @return liste des mins
     */
    public List<Double> min(){
        return min(0);
    }

    /**
     *
     * @param axis 0 trouve le min de chaque colonne(affichage par colonne), 1 trouve le min de chaque ligne (affichage par ligne)
     * @return liste des mins
     */
    public List<Double> min(int axis) {
        return min(axis,true);
    }

    /**
     *
     * @param axis 0 Calcule le min de chaque colonne(affichage par colonne), 1 trouve le min de chaque ligne (affichage par ligne)
     * @param skipNa true eviter les valeurs NaN, false ne pas eviter la somme sera NaN
     * @return liste des mins
     */
    public List<Double> min(int axis, boolean skipNa) {
        List <Double> results = new ArrayList<>();
        if(axis == 0){
            for (Column column : columns) {
                double currentMin = Double.MAX_VALUE;
                for (Object cell : column.getCells()) {
                    currentMin = minCore(currentMin,cell,skipNa);
                }
                results.add(currentMin);
            }
        }else if(axis == 1){
            for(int i = 0; i < indexes.size(); i++) {
                double currentMin = Double.MAX_VALUE;
                for (Column column : columns) {
                    Object cell = column.getCells().get(i);
                    currentMin = minCore(currentMin,cell,skipNa);
                }
                results.add(currentMin);
            }
        }
        return results;
    }

    /**
     *
     * @param currentMin le minimum courrant
     * @param cell la valeur de la celulle courante
     * @param skipNa ignore les valeurs null ou non
     * @return le min de toute les celulles precedente (la cellule courrante inclus)
     */
    private double minCore(double currentMin, Object cell, boolean skipNa) {
        try {
            //handle case when cell's value is 'NaN' which is treated as legit double value
            if(skipNa && (String.valueOf(cell).equals("NaN") || String.valueOf(cell).equals("+NaN") || String.valueOf(cell).equals("-NaN"))){
                return currentMin;
            }
            currentMin = Double.min(currentMin,Double.parseDouble(String.valueOf(cell)));
        }catch (NumberFormatException n){
            if(!skipNa){
                currentMin = Double.parseDouble("NaN");//Sum of this column is NaN
            }
        }
        return currentMin;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataFrame dataFrame = (DataFrame) o;

        if (!Objects.equals(indexes, dataFrame.indexes)) return false;
        if (!Objects.equals(labels, dataFrame.labels)) return false;
        return Objects.equals(columns, dataFrame.columns);

    }
}
