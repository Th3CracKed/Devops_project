package data_structure;

import java.util.List;

/**
 * Permet à DataFrame de définir une liste de colonnes avec different type
 * L'accès à cette class est dans le package seulement
 * @param <E> le type de cette colonne définit par l'utilisateur
 */
public class Column<E> {

    /**
     * La liste des cellules dans cette colonne avec le même type
     */
    private List<E> cells;

    /**
     * Permet de créer une colonne à l'aide d'une ArrayList de colonne
     * @param cells liste des objets de type 'E' a mettre dans cette colonne
     */
    public Column(List<E> cells) {
        this.cells = cells;
    }

    /**
     * Un getter
     * @return liste des cellules de type 'E'
     */
    public List<E> getCells() {
        return cells;
    }


    /**
     * @return nombre de cellules dans la colonne courante
     */
    public int numberOfCells(){
        return cells.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Column<?> column = (Column<?>) o;

        return cells.equals(column.cells);
    }

    /**
     * Ajout un élément à la liste
     * @param element L'élément de type E à ajouter, soit un entier, soit un float, soit un string
     */
    public void add(E element){
        cells.add(element);
    }

    @Override
    public String toString(){
        return cells.toString();
    }
}
