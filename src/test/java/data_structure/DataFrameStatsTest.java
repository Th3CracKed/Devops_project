package data_structure;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DataFrameStatsTest {

    private String[] mIndexes = new String[]{"1", "2", "3","4"};

    private String[] mLabels = new String[]{"A", "B", "C"};

    private DataFrame dataFrame = new DataFrame(mIndexes, mLabels, Arrays.asList("23.5","54.2","51.2","A weird value"),Arrays.asList(2,5,6,5),Arrays.asList(245.2,12.2,12.1,122));

    @Test
    public void test_sum(){
        List<Double> expectedList = Arrays.asList(128.9,18.0,391.5);
        assertEquals(expectedList,dataFrame.sum());
    }

    @Test
    public void test_sum_axis1(){
        List<Double> expectedList = Arrays.asList(270.7, 71.4, 69.3, 127.0);
        assertEquals(expectedList,dataFrame.sum(1));
    }

    @Test
    public void test_sum_skipna_false(){
        List<Double> expectedList = Arrays.asList(270.7, 71.4, 69.3, Double.parseDouble("NaN"));
        assertEquals(expectedList,dataFrame.sum(1,false));
    }
}
