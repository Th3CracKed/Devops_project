package data_structure;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DataFrameTest {

    private String[] mIndexs = new String[]{"1", "2", "3"};

    private String[] mLabels = new String[]{"A", "B", "C"};

    private DataFrame dataFrame = new DataFrame(mIndexs, mLabels, Arrays.asList("Test1","Test2","test3"),Arrays.asList(2,5,6),Arrays.asList("2","5","6"));

    @Test
    public void testContructor1(){
        assertNotEquals(null,dataFrame);
    }

    @Test
    public void testIndexs(){
        assertEquals("Les Indices passer en paramètre ne sont pas les mêmes dans le DataFrame",dataFrame.getIndexs(),Arrays.asList(mIndexs));
    }

    @Test
    public void testLabels(){
        assertEquals("Les labels passer en paramètre ne sont pas les mêmes dans le DataFrame",dataFrame.getLabels(),Arrays.asList(mLabels));
    }

    @Test
    public void testColumns() {
        List <Column> columns = new ArrayList<>();
        columns.add(new Column<>(Arrays.asList("Test1","Test2","test3")));
        columns.add(new Column<>(Arrays.asList(2,5,6)));
        columns.add(new Column<>(Arrays.asList("2","5","6")));
        assertEquals("",dataFrame.getColumns(),columns);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Illegal_Argument_Index_Contructor1(){
        DataFrame dataFrameIndex = new DataFrame(mIndexs, mLabels, Arrays.asList("Test1","Test2","test3"),Arrays.asList(2,5,6),Arrays.asList("2","6"));
        assertNotEquals(null,dataFrameIndex);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Illegal_Argument_Label_Contructor1(){
        DataFrame dataFrameLabel = new DataFrame(mIndexs, mLabels, Arrays.asList("Test1","Test2","test3"),Arrays.asList(2,5,6));
        assertNotEquals(null,dataFrameLabel);
    }

}