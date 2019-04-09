package utils;

import data_structure.DataFrame;
import org.junit.Test;
import java.io.IOException;

import static org.junit.Assert.*;

public class CsvParserTest {

    private CsvParser myParser;
    @Test
    public void testConstructor() {
        try {
            myParser = new CsvParser("rsc/csv_examples/test.csv");
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
}