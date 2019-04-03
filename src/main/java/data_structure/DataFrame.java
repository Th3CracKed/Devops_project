package data_structure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataFrame{

    /**
     * List d'identifiant de lignes
     */
    private List<String> indexs = new ArrayList<>();

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
     * Comparaison de nombres d'indices  avec la plus grande colonne en nombre de cellule dans la liste de colonnes
     * Comparaison de nombres de labels avec le nombre de colonnes
     * @param mIndexs liste d'identifiants de lignes
     * @param mLabels liste de labels de colonnes
     * @param columnsCells liste de contenu des colonnes
     * @throws NullPointerException Si l'une des lists specifier est null
     * @throws IllegalArgumentException Si la taille de données n'est pas cohérante
     */
    public DataFrame(String[] mIndexs, String[] mLabels, List<?>... columnsCells){
        if(mLabels.length != columnsCells.length )
            throw new IllegalArgumentException("Le nombre de label doit-etre egale au nombre de columns");

        for (List<?> cells : columnsCells) {
            if(mIndexs.length != cells.size()) {
                throw new IllegalArgumentException("La nombre d'indices doit-etre egale a la taille de columns");
            }else{
                columns.add(new Column<>(cells));
            }
        }

        Collections.addAll(indexs, mIndexs);
        Collections.addAll(labels, mLabels);

    }

    public List<String> getIndexs() {
        return indexs;
    }

    public List<String> getLabels() {
        return labels;
    }

    public List<Column> getColumns() {
        return columns;
    }
}
