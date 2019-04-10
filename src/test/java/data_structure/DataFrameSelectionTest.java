package data_structure;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DataFrameSelectionTest {

    private String[] mIndexes = new String[]{"1", "2", "3"};

    private String[] mLabels = new String[]{"A", "B", "C"};

    private DataFrame dataFrame = new DataFrame(mIndexes, mLabels, Arrays.asList("Test1","Test2","test3"),Arrays.asList(2,5,6),Arrays.asList("2","5","6"));

    @Test
    public void test_iloc(){
        DataFrame df1 = new DataFrame(new String[]{"1", "3"}, mLabels, Arrays.asList("Test1","test3"),Arrays.asList(2,6),Arrays.asList("2","6"));
        DataFrame df2 = dataFrame.iloc(0,2);
        assertEquals("DataFrame est cense etre egale puisqu'ils sont composer du même contenu",df1,df2);
    }

    @Test
    public void test_iloc_failed(){
        DataFrame df2 = dataFrame.iloc(0,2);
        assertNotEquals("DataFrame n'est pas cense etre egale puisqu'ils ne sont pas composer du même contenu",dataFrame,df2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_Index_Out_iloc(){
        dataFrame.iloc(3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test__Illegal_Argument_iloc(){
        dataFrame.iloc();
    }

    @Test
    public void test_loc(){
        DataFrame df1 = new DataFrame(new String[]{"1", "3"}, mLabels, Arrays.asList("Test1","test3"),Arrays.asList(2,6),Arrays.asList("2","6"));
        DataFrame df2 = dataFrame.loc("1","3");
        assertEquals("DataFrame est cense etre egale puisqu'ils sont composer du même contenu",df1,df2);
    }

    @Test
    public void test_loc_unknown_label(){
        DataFrame df1 = new DataFrame(new String[]{"1", "3","7"}, mLabels, Arrays.asList("Test1","test3","NaN"),Arrays.asList(2,6,"NaN"),Arrays.asList("2","6","NaN"));
        DataFrame df2 = dataFrame.loc("1","3","7");
        assertEquals("DataFrame est cense etre egale puisqu'ils sont composer du même contenu",df1,df2);
    }

    @Test
    public void test_loc_failed(){
        DataFrame df2 = dataFrame.loc("1","2");
        assertNotEquals("DataFrame n'est pas cense etre egale puisqu'ils ne sont pas composer du même contenu",dataFrame,df2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test__Illegal_Argument_loc(){
        dataFrame.loc();
    }
}
