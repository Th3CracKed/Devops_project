package utils;

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
        assertNotEquals(null,myParser);
    }

    @Test
    public void getLabels() {
    }

    @Test
    public void getIndexes() {
    }
}