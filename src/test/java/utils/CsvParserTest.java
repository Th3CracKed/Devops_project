package utils;

import data_structure.DataFrame;
import org.junit.Test;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.*;

public class CsvParserTest {

    private CsvParser myParser;
    @Test
    public void testAvecIndex() {
        try {
            myParser = new CsvParser("rsc/csv_examples/test.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        DataFrame dataframe = new DataFrame(myParser.getIndexes(), myParser.getLabels(), myParser.getColumns());
        assertNotEquals(null,dataframe);
    }

    @Test
    public void testSansIndex() {
        try {
            myParser = new CsvParser("rsc/csv_examples/test_sans_index.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        DataFrame dataframe = new DataFrame(myParser.getIndexes(), myParser.getLabels(), myParser.getColumns());
        assertNotEquals(null,dataframe);

    }

    @Test
    public void testDouble() {
        try {
            myParser = new CsvParser("rsc/csv_examples/test_avec_float.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        DataFrame dataframe = new DataFrame(myParser.getIndexes(), myParser.getLabels(), myParser.getColumns());
        assertNotEquals(null,dataframe);

    }

    @Test
    public void testInt() {
        try {
            myParser = new CsvParser("rsc/csv_examples/test_avec_int.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        DataFrame dataframe = new DataFrame(myParser.getIndexes(), myParser.getLabels(), myParser.getColumns());
        assertNotEquals(null,dataframe);

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_index_en_trop(){
        try {
            myParser = new CsvParser("rsc/csv_examples/test_erreur_index.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        DataFrame dataframe = new DataFrame(myParser.getIndexes(), myParser.getLabels(), myParser.getColumns());
        assertNotEquals(null,dataframe);
    }

    @Test
    public void getLabels() {
    }

    @Test
    public void getIndexes() {
    }

    @Test
    public void getColumns() {
    }
}