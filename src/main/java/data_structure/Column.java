package data_structure;

import java.util.List;

/**
 * Permet à DataFrame de définir une liste de colonnes avec different type
 * @param <E> le type de cette colonne définit par l'utilisateur
 */
class Column<E> {

    /**
     * La liste des cellules dans cette colonne avec le même type
     */
    private List<E> cells;

    /**
     * Permet de créer une colonne à l'aide d'une ArrayList de colonne
     * @param cells liste des objets de type 'E' a mettre dans cette colonne
     */
    Column(List<E> cells) {
        this.cells = cells;
    }

    /**
     * Un getter
     * @return liste des cellules de type 'E'
     */
    List<E> getCells() {
        return cells;
    }


    /**
     * @return nombre de cellules dans la colonne courante
     */
    int numberOfCells(){
        return cells.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Column<?> column = (Column<?>) o;

        return cells.equals(column.cells);
    }
}