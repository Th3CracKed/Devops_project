package data_structure;

import dependencies_injection.ColumnComponent;
import dependencies_injection.DaggerColumnComponent;
import dependencies_injection.DaggerDataFrameComponent;
import dependencies_injection.DataFrameComponent;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DependenciesTest {

    @Test
    public void DataFrameTest(){
        DataFrameComponent dataFrameComponent = DaggerDataFrameComponent.builder().fileName("rsc/csv_examples/test.csv").build();
        DataFrame dataFrame = dataFrameComponent.getDataFrame();
        assertNotEquals(null,dataFrame);
    }

    //TODO add illegalArgumentException

    @Test
    public void ColumnTest(){
        ColumnComponent columnComponent = DaggerColumnComponent.builder().setCells(Arrays.asList("Col1","Col2","Col3")).build();
        Column column = columnComponent.getColumn();
        assertEquals("Liste de cellules n'est pas correct",Arrays.asList("Col1","Col2","Col3"),column.getCells());
        assertEquals("Liste de cellules n'est pas correct",3,column.numberOfCells());
    }
}
