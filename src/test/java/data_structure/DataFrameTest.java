package data_structure;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DataFrameTest {

    private String[] mIndexes = new String[]{"1", "2", "3"};

    private String[] mLabels = new String[]{"A", "B", "C"};

    private DataFrame dataFrame = new DataFrame(mIndexes, mLabels, Arrays.asList("Test1","Test2","test3"),Arrays.asList(2,5,6),Arrays.asList("2","5","6"));

    @Test
    public void testContructor1(){
        assertNotEquals(null,dataFrame);
    }

    @Test
    public void testIndexs(){
        assertEquals("Les Indices passer en paramètre ne sont pas les mêmes dans le DataFrame",dataFrame.getIndexes(),Arrays.asList(mIndexes));
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

    @Test(expected = NullPointerException.class)
    public void test_NullPointer_Contructor1(){
        new DataFrame(null,null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Illegal_Argument_Index_Contructor1(){
        DataFrame dataFrameIndex = new DataFrame(mIndexes, mLabels, Arrays.asList("Test1","Test2","test3"),Arrays.asList(2,5,6),Arrays.asList("2","6"));
        assertNotEquals(null,dataFrameIndex);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Illegal_Argument_Label_Contructor1(){
        DataFrame dataFrameLabel = new DataFrame(mIndexes, mLabels, Arrays.asList("Test1","Test2","test3"),Arrays.asList(2,5,6));
        assertNotEquals(null,dataFrameLabel);
    }

    @Test
    public void test_Print_All(){
        System.out.println("test_Print_All");
        dataFrame.printAll();
    }

    @Test
    public void test_Print_Head(){
        System.out.println("test_Print_Head");
        dataFrame.printHead();
    }

    @Test
    public void test_Print_Head_With_Param(){
        System.out.println("test_Print_Head_With_Param");
        dataFrame.printHead(1);
    }

    @Test
    public void test_Print_Head_With_Param_With_Exceed_In_NbLines(){
        System.out.println("test_Print_Head_With_Param_With_Exceed_In_NbLines");
        dataFrame.printHead(10);
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_Illegal_Argument_Print_Head_With_Param(){
        dataFrame.printHead(0);
    }

    @Test
    public void test_Print_Tail(){
        System.out.println("test_Print_Tail");
        dataFrame.printTail();
    }

    @Test
    public void test_Print_Tail_With_Param(){
        System.out.println("test_Print_Tail_With_Param");
        dataFrame.printTail(1);
    }

    @Test
    public void test_Print_tail_With_Param_With_Exceed_In_NbLines(){
        System.out.println("test_Print_tail_With_Param_With_Exceed_In_NbLines");
        dataFrame.printHead(10);
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_Illegal_Argument_Print_Tail_With_Param(){
        dataFrame.printTail(0);
    }

    @Test
    public void test_Find_Max_1() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method m = dataFrame.getClass().getDeclaredMethod("findMax", int.class, List.class);
        m.setAccessible(true);
        int max = (int) m.invoke(dataFrame,10,Arrays.asList("","4444","","55555"));
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
        DataFrame df2 = dataFrame.iloc(3);
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
    public void test_loc_failed(){
        DataFrame df2 = dataFrame.loc("1","2");
        assertNotEquals("DataFrame n'est pas cense etre egale puisqu'ils ne sont pas composer du même contenu",dataFrame,df2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test__Illegal_Argument_loc(){
        dataFrame.loc();
    }

    @Test
    public void equals(){
        DataFrame df1 = new DataFrame(new String[]{"1", "2", "3"}, new String[]{"A", "B", "C"}, Arrays.asList("Test1","Test2","test3"),Arrays.asList(2,5,6),Arrays.asList("2","5","6"));
        DataFrame df2 = new DataFrame(new String[]{"1", "2", "3"}, new String[]{"A", "B", "C"}, Arrays.asList("Test1","Test2","test3"),Arrays.asList(2,5,6),Arrays.asList("2","5","6"));
        assertEquals(df1,df1);
        assertNotEquals("DataFrame ne doit pas equals a un object",df1, new Object());
        assertNotEquals("DataFrame ne doit pas equals a null",df1, null);
        assertEquals("probleme dans la fonction equals,ces deux objets ont le meme contenu",df1,df2);
    }

}