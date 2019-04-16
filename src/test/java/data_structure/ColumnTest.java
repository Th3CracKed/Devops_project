package data_structure;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ColumnTest {

    private String d1 = "Donnees 1";

    private String d2 = "Donnees 2";

    private Column<String> column = new Column<>(new ArrayList<>(Arrays.asList(d1, d2)));

    @Test
    public void testContructor1(){
        assertNotEquals(null,column);
        assertEquals("Nombre de cellules n'est pas correct",2,column.numberOfCells());
    }

    @Test
    public void checkArrayElements() {
        ArrayList cells = (ArrayList) column.getCells();
        assertEquals("Premier celulle de colonne n'est pas correct",d1,cells.get(0));
        assertEquals("Deuxieme celulle de colonne n'est pas correct",d2,cells.get(1));
    }

    @Test
    public void equals(){
        Column col1 = new Column<>(Arrays.asList("1", "2"));
        Column col2 = new Column<>(Arrays.asList("1", "2"));
        assertEquals(col1,col1);
        assertNotEquals("Column ne doit pas equals a un object",col1, new Object());
        assertNotEquals("Column ne doit pas equals a null",col1, null);
        assertEquals("probleme dans la fonction equals,ces deux objets ont le meme contenu",col1,col2);
    }


}