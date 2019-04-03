package data_structure;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ColonneTest {

    private Colonne<String> colonne;

    private String label = "Une colonne";

    private String d1 = "Donnees 1";

    private String d2 = "Donnees 2";

    @Test
    public void testConstructor1(){
        colonne = new Colonne<>(label,d1,d2);
        checkArrayElements();
    }

    @Test
    public void testContructor2(){
        colonne = new Colonne<>(label, new ArrayList<>(Arrays.asList(d1, d2)));
        checkArrayElements();
    }

    @Test
    public void getLabel() {
        colonne = new Colonne<>(label);
        assertEquals(label,colonne.getLabel());
    }

    private void checkArrayElements() {
        ArrayList cells = (ArrayList) colonne.getCells();
        assertEquals(d1,cells.get(0));
        assertEquals(d2,cells.get(1));
    }
}