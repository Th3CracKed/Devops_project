package utils;

import data_structure.DataFrame;
import org.junit.Test;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.*;

public class CsvParserTest {

    private String[] mIndexs = new String[]{"1", "2", "3"};

    private String[] mLabels = new String[]{"A", "B", "C"};

    private DataFrame dataFrameTest = new DataFrame(mIndexs, mLabels, Arrays.asList("Test1","Test2","test3"),Arrays.asList(2,5,6),Arrays.asList("2","5","6"));


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
        try {
            myParser = new CsvParser("rsc/csv_examples/test_basic.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        DataFrame dataframe = new DataFrame(myParser.getIndexes(), myParser.getLabels(), myParser.getColumns());
        assertEquals(dataframe.getLabels(),dataFrameTest.getLabels());
    }

    @Test
    public void getIndexes() {
        try {
            myParser = new CsvParser("rsc/csv_examples/test_basic.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        DataFrame dataframe = new DataFrame(myParser.getIndexes(), myParser.getLabels(), myParser.getColumns());
        assertEquals(dataframe.getIndexes(),dataFrameTest.getIndexes());
    }

    @Test
    public void getColumns() {
        try {
            myParser = new CsvParser("rsc/csv_examples/test_basic.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        DataFrame dataframe = new DataFrame(myParser.getIndexes(), myParser.getLabels(), myParser.getColumns());
        assertEquals(dataframe.getColumns().size(),dataFrameTest.getColumns().size());
    }
}