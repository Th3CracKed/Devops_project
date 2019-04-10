package data_structure;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DataFramePrintTest {

    private String[] mIndexes = new String[]{"1", "2", "3"};

    private String[] mLabels = new String[]{"A", "B", "C"};

    private DataFrame dataFrame = new DataFrame(mIndexes, mLabels, Arrays.asList("Test1","Test2","test3"),Arrays.asList(2,5,6),Arrays.asList("2","5","6"));

    @Test
    public void test_Print_All(){
        String expectedString = String.format("%-1s", "") +
                String.format("%6s", "A") +
                String.format("%2s", "B") +
                String.format("%2s", "C") +
                String.format("%n") +// an empty line
                String.format("%-1s", "1") +
                String.format("%6s", "Test1") +
                String.format("%2s", "2") +
                String.format("%2s", "2") +
                String.format("%n") +// an empty line
                String.format("%-1s", "2") +
                String.format("%6s", "Test2") +
                String.format("%2s", "5") +
                String.format("%2s", "5") +
                String.format("%n") +// an empty line
                String.format("%-1s", "3") +
                String.format("%6s", "test3") +
                String.format("%2s", "6") +
                String.format("%2s", "6") +
                String.format("%n");// an empty line
        assertEquals("PrintAll expected n'est pas celle afficher", expectedString, dataFrame.printAll());
    }

    @Test
    public void test_Print_Head(){
        String expectedString = String.format("%-1s", "") +
                String.format("%6s", "A") +
                String.format("%2s", "B") +
                String.format("%2s", "C") +
                String.format("%n") +// an empty line
                String.format("%-1s", "1") +
                String.format("%6s", "Test1") +
                String.format("%2s", "2") +
                String.format("%2s", "2") +
                String.format("%n") +// an empty line
                String.format("%-1s", "2") +
                String.format("%6s", "Test2") +
                String.format("%2s", "5") +
                String.format("%2s", "5") +
                String.format("%n");// an empty line
        assertEquals("PrintAll expected n'est pas celle afficher", expectedString, dataFrame.printHead());
    }

    @Test
    public void test_Print_Head_With_Param(){
        String expectedString = String.format("%-1s", "") +
                String.format("%6s", "A") +
                String.format("%2s", "B") +
                String.format("%2s", "C") +
                String.format("%n") +// an empty line
                String.format("%-1s", "1") +
                String.format("%6s", "Test1") +
                String.format("%2s", "2") +
                String.format("%2s", "2") +
                String.format("%n");// an empty line
        assertEquals("PrintAll expected n'est pas celle afficher", expectedString, dataFrame.printHead(1));
    }

    @Test
    public void test_Print_Head_With_Param_With_Exceed_In_NbLines(){
        String expectedString = String.format("%-1s", "") +
                String.format("%6s", "A") +
                String.format("%2s", "B") +
                String.format("%2s", "C") +
                String.format("%n") +// an empty line
                String.format("%-1s", "1") +
                String.format("%6s", "Test1") +
                String.format("%2s", "2") +
                String.format("%2s", "2") +
                String.format("%n") +// an empty line
                String.format("%-1s", "2") +
                String.format("%6s", "Test2") +
                String.format("%2s", "5") +
                String.format("%2s", "5") +
                String.format("%n") +// an empty line
                String.format("%-1s", "3") +
                String.format("%6s", "test3") +
                String.format("%2s", "6") +
                String.format("%2s", "6") +
                String.format("%n");// an empty line
        assertEquals("PrintAll expected n'est pas celle afficher", expectedString, dataFrame.printHead(10));
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_Illegal_Argument_Print_Head_With_Param(){
        dataFrame.printHead(0);
    }

    @Test
    public void test_Print_Tail(){
        String expectedString = String.format("%-1s", "") +
                String.format("%6s", "A") +
                String.format("%2s", "B") +
                String.format("%2s", "C") +
                String.format("%n") +// an empty line
                String.format("%-1s", "2") +
                String.format("%6s", "Test2") +
                String.format("%2s", "5") +
                String.format("%2s", "5") +
                String.format("%n") +// an empty line
                String.format("%-1s", "3") +
                String.format("%6s", "test3") +
                String.format("%2s", "6") +
                String.format("%2s", "6") +
                String.format("%n");// an empty line
        assertEquals("PrintAll expected n'est pas celle afficher", expectedString, dataFrame.printTail());
    }

    @Test
    public void test_Print_Tail_With_Param(){
        String expectedString = String.format("%-1s", "") +
                String.format("%6s", "A") +
                String.format("%2s", "B") +
                String.format("%2s", "C") +
                String.format("%n") +// an empty line
                String.format("%-1s", "3") +
                String.format("%6s", "test3") +
                String.format("%2s", "6") +
                String.format("%2s", "6") +
                String.format("%n");// an empty line
        assertEquals("PrintAll expected n'est pas celle afficher", expectedString, dataFrame.printTail(1));
    }

    @Test
    public void test_Print_tail_With_Param_With_Exceed_In_NbLines(){
        String expectedString = String.format("%-1s", "") +
                String.format("%6s", "A") +
                String.format("%2s", "B") +
                String.format("%2s", "C") +
                String.format("%n") +// an empty line
                String.format("%-1s", "1") +
                String.format("%6s", "Test1") +
                String.format("%2s", "2") +
                String.format("%2s", "2") +
                String.format("%n") +// an empty line
                String.format("%-1s", "2") +
                String.format("%6s", "Test2") +
                String.format("%2s", "5") +
                String.format("%2s", "5") +
                String.format("%n") +// an empty line
                String.format("%-1s", "3") +
                String.format("%6s", "test3") +
                String.format("%2s", "6") +
                String.format("%2s", "6") +
                String.format("%n");// an empty line
        assertEquals("PrintAll expected n'est pas celle afficher", expectedString, dataFrame.printTail(10));
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_Illegal_Argument_Print_Tail_With_Param(){
        dataFrame.printTail(0);
    }

    @Test
    public void test_Find_Max_1() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method m = dataFrame.getClass().getDeclaredMethod("findMax", int.class, List.class);
        m.setAccessible(true);
        int max = (int) m.invoke(dataFrame,10, Arrays.asList("","4444","","55555"));
        assertEquals(10,max);
        m.setAccessible(false);
    }

    @Test
    public void test_Find_Max_2() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method m = dataFrame.getClass().getDeclaredMethod("findMax", int.class, List.class);
        m.setAccessible(true);
        int max = (int) m.invoke(dataFrame,6,Arrays.asList("","465","9498","88888888"));
        assertEquals(8,max);
        m.setAccessible(false);
    }


}
