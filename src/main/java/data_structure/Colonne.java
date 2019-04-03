package data_structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Permet à DataFrame de définir une liste de colonnes avec different type
 * @param <E> le type de cette colonne définit par l'utilisateur
 */
public class Colonne <E> {
    /**
     * L'identifiant de cette colonne
     */
    private String label;

    /**
     * La liste des cellules dans cette colonne avec le même type
     */
    private List<E> cells;

    /**
     * Permet de créer une colonne à l'aide d'une ArrayList de colonne
     * @param label l'identifiant de cette colonne
     * @param cells liste des objets de type 'E' a mettre dans cette colonne
     */
    public Colonne(String label, ArrayList<E> cells) {
        this.label = label;
        this.cells = cells;
    }

    /**
     * Permet de créer une colonne à l'aide des objets de type 'E' passer en paramètre
     * @param mCells
     */
    public Colonne(String label, E... mCells) {
        this.label = label;
        cells = new ArrayList<>();
        cells.addAll(Arrays.asList(mCells));
    }

    /**
     * Un getter
     * @return label de cette Colonne
     */
    public String getLabel() {
        return label;
    }

    /**
     * Un getter
     * @return liste des cellules de type 'E'
     */
    public List<E> getCells() {
        return cells;
    }
}
