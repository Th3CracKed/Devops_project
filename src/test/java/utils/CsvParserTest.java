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
        DataFrame dataframe = new DataFrame("rsc/csv_examples/test.csv");
        assertNotEquals(null,dataframe);
    }

    @Test
    public void testSansIndex() {
        DataFrame dataframe = new DataFrame("rsc/csv_examples/test_sans_index.csv");
        assertNotEquals(null,dataframe);

    }

    @Test
    public void testDouble() {
        DataFrame dataframe = new DataFrame("rsc/csv_examples/test_avec_float.csv");
        assertNotEquals(null,dataframe);

    }

    @Test
    public void testInt() {
        DataFrame dataframe = new DataFrame("rsc/csv_examples/test_avec_int.csv");
        assertNotEquals(null,dataframe);

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_index_en_trop(){
        DataFrame dataframe = new DataFrame("rsc/csv_examples/test_erreur_index.csv");
        assertNotEquals(null,dataframe);
    }

    @Test
    public void getLabels() {

        DataFrame dataframe = new DataFrame("rsc/csv_examples/test_basic.csv");
        assertEquals(dataframe.getLabels(),dataFrameTest.getLabels());
    }

    @Test
    public void getIndexes() {
        DataFrame dataframe = new DataFrame("rsc/csv_examples/test_basic.csv");
        assertEquals(dataframe.getIndexes(),dataFrameTest.getIndexes());
    }

    @Test
    public void getColumns() {
        DataFrame dataframe = new DataFrame("rsc/csv_examples/test_basic.csv");
        assertEquals(dataframe.getColumns().size(),dataFrameTest.getColumns().size());
    }


}