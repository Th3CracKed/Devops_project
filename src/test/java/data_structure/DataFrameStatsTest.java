package data_structure;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DataFrameStatsTest {

    private String[] mIndexes = new String[]{"1", "2", "3","4"};

    private String[] mLabels = new String[]{"A", "B", "C"};

    private DataFrame dataFrame = new DataFrame(mIndexes, mLabels, Arrays.asList("23.5","54.2","NaN","A weird value"),Arrays.asList(2,-5,0,5),Arrays.asList(245.2,12.2,12.1,122));

    @Test
    public void test_sum(){
        List<Double> expectedList = Arrays.asList(77.7,2.0,391.5);//somme des colonnes
        assertEqualsDoublesList("La somme des valeurs de colonnes attendues n'est pas correcte",expectedList,dataFrame.sum());
    }

    @Test
    public void test_sum_axis1(){
        List<Double> expectedList = Arrays.asList(270.7, 61.4, 12.1, 127.0);//somme des lignes
        assertEqualsDoublesList("La somme des valeurs de lignes attendues n'est pas correcte",expectedList,dataFrame.sum(1));
    }

    @Test
    public void test_sum_skipna_false(){
        List<Double> expectedList = Arrays.asList(270.7, 61.4, Double.parseDouble("NaN"), Double.parseDouble("NaN"));//somme des lignes
        assertEqualsDoublesList("La somme des valeurs de lignes attendues n'est pas correcte (valeurs non numérique ne sont pas ignorer)",
                expectedList,dataFrame.sum(1,false));
    }

    @Test
    public void test_min(){
        List<Double> expectedList = Arrays.asList(23.5,-5.0,12.1);//min des colonnes
        assertEqualsDoublesList("Le minimum attendu pour chaque colonne n'est pas correcte",expectedList,dataFrame.min());
    }

    @Test
    public void test_min_axis1(){
        List<Double> expectedList = Arrays.asList(2.0,-5.0,0.0,5.0);//min des lignes
        assertEqualsDoublesList("Le minimum attendu pour chaque ligne n'est pas correcte",expectedList,dataFrame.min(1));
    }

    @Test
    public void test_min_skipna_false(){
        List<Double> expectedList = Arrays.asList(2.0, -5.0, Double.parseDouble("NaN"), Double.parseDouble("NaN"));//min des lignes
        assertEqualsDoublesList("Le minimum attendu pour chaque ligne n'est pas correcte",expectedList,dataFrame.min(1,false));
    }

    @Test
    public void test_max(){
        List<Double> expectedList = Arrays.asList(54.2,5.0,245.2);//max des colonnes
        assertEqualsDoublesList("Le maximum attendu pour chaque colonne n'est pas correcte",expectedList,dataFrame.max());
    }

    @Test
    public void test_max_axis1(){
        List<Double> expectedList = Arrays.asList(245.2, 54.2,12.1,122.0);//max des lignes
        assertEqualsDoublesList("Le maximum attendu pour chaque ligne n'est pas correcte",expectedList,dataFrame.max(1));
    }

    @Test
    public void test_max_skipna_false(){
        List<Double> expectedList = Arrays.asList(245.2, 54.2, Double.parseDouble("NaN"), Double.parseDouble("NaN"));//max des lignes
        assertEqualsDoublesList("Le maximum attendu pour chaque ligne n'est pas correcte",expectedList,dataFrame.max(1,false));
    }

    @Test
    public void test_avg(){
        List<Double> expectedList = Arrays.asList(19.425,0.5,97.875);//moyenne des colonnes
        assertEqualsDoublesList("La moyenne attendu pour chaque colonne n'est pas correcte",expectedList,dataFrame.avg());
    }

    @Test
    public void test_avg_axis1(){
        List<Double> expectedList = Arrays.asList(90.233, 20.466,4.033,42.333);//moyenne des lignes
        assertEqualsDoublesList("La moyenne attendu pour chaque ligne n'est pas correcte",expectedList,dataFrame.avg(1));
    }

    @Test
    public void test_avg_skipna_false(){
        List<Double> expectedList = Arrays.asList(90.233, 20.466, Double.parseDouble("NaN"), Double.parseDouble("NaN"));//moyenne des lignes
        assertEqualsDoublesList("La moyenne attendu pour chaque ligne n'est pas correcte",expectedList,dataFrame.avg(1,false));
    }

    private void assertEqualsDoublesList(String message, List<Double> expected, List<Double> actual){
        int i = 0;
        for (Double aDouble : actual) {
            assertEquals(message,expected.get(i),aDouble,0.001);//delta is the precision
            i++;
        }
    }
}
