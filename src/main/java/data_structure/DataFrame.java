package data_structure;

import utils.CsvParser;

import javax.lang.model.element.Element;
import java.util.*;
import java.util.stream.Collectors;

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
     * @throws IllegalArgumentException Si la taille de données n'est pas cohérente
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
     * Constructeur prenant en paramètre le contenu de chaque colonne sous forme d’une List
     * Le nombres d'indices doit être égale aux nombres de cellules dans chaque colonnes
     * Le nombres de labels doit être égale aux nombres de colonnes
     * @param mIndexes liste d'identifiants de lignes
     * @param mLabels liste de labels de colonnes
     * @param columnsCells liste de colonnes
     * @throws NullPointerException Si l'une des lists specifiée vaut null
     * @throws IllegalArgumentException Si la taille de données n'est pas cohérente
     */
    public DataFrame(List<String> mIndexes, List<String> mLabels, List<Column> columnsCells){
        if(mLabels.size() != columnsCells.size())
            throw new IllegalArgumentException("Le nombre de label doit-etre egale au nombre de colonnes");

        for (Column col : columnsCells) {
            if(mIndexes.size() != col.numberOfCells()) {
                throw new IllegalArgumentException("Le nombre d'indices doit-etre egale à la taille de colonnes");
            }else{
                columns.add(col);
            }
        }

        indexes=mIndexes;
        labels=mLabels;

    }

    /**
     *Constructeur permettant de parser un fichier .csv en DataFrame
     * @param csv_filename Le nom du fichier CSV à transformer en dataframe
     */
    public DataFrame(String csv_filename){
        CsvParser myParser = new CsvParser(csv_filename);
        indexes= myParser.getIndexes();
        labels=myParser.getLabels();
        columns=myParser.getColumns();
        if(labels.size() != columns.size())
            throw new IllegalArgumentException("Le nombre de label doit-etre egale au nombre de colonnes");
        for (Column col : columns) {
            if(indexes.size() != col.numberOfCells()) {
                throw new IllegalArgumentException("Le nombre d'indices doit-etre egale à la taille de colonnes");
            }
        }
    }

    /**
     * Affiche tout le DataFrame
     */
    public String printAll(){
        String result = printCore(indexes.size(),true);
        System.out.println(result);
        return result;
    }

    /**
     * Affiche les premières lignes de DataFrame
     */
    public String printHead(){
        String result = printCore(indexes.size()-1,true);
        System.out.println(result);
        return result;
    }

    /**
     * Affiche les premières lignes de DataFrame
     * @param nbLines le nombre de lignes à afficher
     */
    public String printHead(int nbLines){
        String result = printCore(nbLines,true);
        System.out.println(result);
        return result;
    }

    /**
     * Affiche les dernières lignes de DataFrame
     */
    public String printTail(){
        String result = printCore(indexes.size()-1,false);
        System.out.println(result);
        return result;
    }

    /**
     * Affiche les dernières lignes de DataFrame
     * @param nbLines le nombre de lignes à afficher
     */
    public String printTail(int nbLines){
        String result = printCore(nbLines,false);
        System.out.println(result);
        return result;
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
     * Trouve le maximum entre l'entier et la liste passée en paramètre
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
     * Cree un nouveau DataFrame en selectionnant les indices passés en paramètre
     * @param selected_Indexes les lignes à retourner
     * @return un DataFrame des celulles des lignes passer en paramètre
     * @throws IndexOutOfBoundsException si l'une des indices passé en paramètre est en dehors du rang
     * @throws IllegalArgumentException si aucun paramètre n'est passé
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
     * Cree un nouveau DataFrame en selectionnant les labels passés en paramètre
     * si un label n'est pas trouvé, retourne des colonnes avec la valeur 'NaN'
     * @param selected_Labels les colonnes à retourner
     * @return un DataFrame à partir des colonnes passeés en paramètre
     * @throws IllegalArgumentException si aucun paramètre n'est passé
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
     * Calcule la somme de l'axis passé en paramètre
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
     * @throws IllegalArgumentException si le param passer est different de 0 ou 1
     */
    public List <Double> sum(int axis, boolean skipNa){
        if(axis != 1 && axis !=0 )
            throw new IllegalArgumentException("Il faut passer 0 ou 1 comme axis");

        List <Double> results = new ArrayList<>();
        if(axis == 0){
            for (Column column : columns) {
                double  columnSum = 0;
                for (Object cell : column.getCells()) {
                    columnSum = sumCore(columnSum,cell,skipNa);
                }
                results.add(columnSum);
            }
        }else {
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
            if(skipNa && Double.isNaN(Double.parseDouble(String.valueOf(cell)) )){
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
     * Calcule la moyenne de chaque colonne (affichage par colonne)
     * @return liste des moyens
     */
    public List<Double> avg(){
        return avg(0);
    }

    /**
     * Calcule la moyenne de l'axis passer en paramètre
     * @param axis 0 Calcule la moyenne de chaque colonne(affichage par colonne), 1 Calcule la moyenne de chaque ligne (affichage par ligne)
     * @return liste des moyens
     */
    public List<Double> avg(int axis){
        return avg(axis,true);
    }

    /**
     * Calcule la moyenne de l'axis passer en paramètre
     *
     * @param axis 0 Calcule la moyenne de chaque colonne(affichage par colonne), 1 Calcule la moyenne de chaque ligne (affichage par ligne)
     * @param skipNa true eviter les valeurs NaN, false ne pas eviter la somme sera NaN
     * @return liste des moyens
     * @throws IllegalArgumentException si le param passer est different de 0 ou 1
     */
    public List <Double> avg(int axis, boolean skipNa){
        if(axis != 1 && axis !=0 )
            throw new IllegalArgumentException("Il faut passer 0 ou 1 comme axis");

        List <Double> results = new ArrayList<>();
        if(axis == 0){
            for (Column column : columns) {
                double  columnSum = 0;
                for (Object cell : column.getCells()) {
                    columnSum = sumCore(columnSum,cell,skipNa);
                }
                results.add(columnSum / column.getCells().size());
            }
        }else {
            for(int i = 0; i < indexes.size(); i++) {
                double columnSum = 0;
                for (Column column : columns) {
                    Object cell = column.getCells().get(i);
                    columnSum = sumCore(columnSum,cell,skipNa);
                }
                results.add(columnSum / columns.size());
            }
        }
        return results;
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
     * @throws IllegalArgumentException si le param passer est different de 0 ou 1
     */
    public List<Double> min(int axis, boolean skipNa) {
        if(axis != 1 && axis !=0 )
            throw new IllegalArgumentException("Il faut passer 0 ou 1 comme axis");
        List <Double> results = new ArrayList<>();
        if(axis == 0){
            for (Column column : columns) {
                double currentMin = Double.MAX_VALUE;
                for (Object cell : column.getCells()) {
                    currentMin = minCore(currentMin,cell,skipNa);
                }
                results.add(currentMin);
            }
        }else {
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
     * @return le min de toutes les celulles precedentes (la cellule courrante inclus)
     */
    private double minCore(double currentMin, Object cell, boolean skipNa) {
        try {
            //handle case when cell's value is 'NaN' which is treated as legit double value
            if(skipNa && Double.isNaN(Double.parseDouble(String.valueOf(cell))) ){
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
     * Trouve le maximum de chaque colonne (affichage par colonne)
     * @return liste des maxs
     */
    public List<Double> max(){
        return max(0);
    }

    /**
     *
     * @param axis 0 trouve le max de chaque colonne(affichage par colonne), 1 trouve le max de chaque ligne (affichage par ligne)
     * @return liste des maxs
     */
    public List<Double> max(int axis) {
        return max(axis,true);
    }

    /**
     *
     * @param axis 0 Calcule le max de chaque colonne(affichage par colonne), 1 trouve le max de chaque ligne (affichage par ligne)
     * @param skipNa true eviter les valeurs NaN, false ne pas eviter la somme sera NaN
     * @return liste des maxs
     * @throws IllegalArgumentException si le param passer est different de 0 ou 1
     */
    public List<Double> max(int axis, boolean skipNa) {
        if(axis != 1 && axis !=0 )
            throw new IllegalArgumentException("Il faut passer 0 ou 1 comme axis");
        List <Double> results = new ArrayList<>();
        if(axis == 0){
            for (Column column : columns) {
                double currentMax = Double.MIN_VALUE;
                for (Object cell : column.getCells()) {
                    currentMax = maxCore(currentMax,cell,skipNa);
                }
                results.add(currentMax);
            }
        }else {
            for(int i = 0; i < indexes.size(); i++) {
                double currentMax = Double.MIN_VALUE;
                for (Column column : columns) {
                    Object cell = column.getCells().get(i);
                    currentMax = maxCore(currentMax,cell,skipNa);
                }
                results.add(currentMax);
            }
        }
        return results;
    }



    public DataFrame groupBy(String column_name){
        /* Detection de la colonne  */
        int nb =-1;
        for (int i=0;i<labels.size();i++) {
            if (labels.get(i).equals(column_name))
                nb = i;
        }
        if (nb==-1) return null;

        /* Debut du group by */
        ArrayList<Column> cols = new ArrayList<>(); // creation des nouvelles colonnes
        for (int i=0;i<columns.size();i++){
            cols.add(new Column(new ArrayList()));
        }

        Column the_col = columns.get(nb);
        ArrayList  list = new ArrayList(the_col.getCells());
        for (int i = 0; i < list.size(); i++) {
            System.out.println(the_col.findAll(list.get(i)));

        }
        return null;
    }




    /**
     *
     * @param currentMax le maximum courrant
     * @param cell la valeur de la celulle courante
     * @param skipNa ignore les valeurs null ou non
     * @return le max de toute les celulles precedente (la cellule courrante inclus)
     */
    private double maxCore(double currentMax, Object cell, boolean skipNa) {
        try {
            //handle case when cell's value is 'NaN' which is treated as legit double value
            if(skipNa && Double.isNaN(Double.parseDouble(String.valueOf(cell))) ){
                return currentMax;
            }
            currentMax = Double.max(currentMax,Double.parseDouble(String.valueOf(cell)));
        }catch (NumberFormatException n){
            if(!skipNa){
                currentMax = Double.parseDouble("NaN");//Sum of this column is NaN
            }
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
