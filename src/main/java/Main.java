import data_structure.DataFrame;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println("Demonstration des fonctionalitees du DataFrame");
        System.out.println("** DataFrame(new String[]{\"Indice 1\", \"Indice 2\", \"Indice 3\"}, new String[]{\"Label A\", \"Label B\", \"Label C\"}, Arrays.asList(\"Valeur A_1\",\"Valeur A_2\",\"Valeur A_3\"),Arrays.asList(2,5.5,6.4),Arrays.asList(\"2\",5.78,5) **");
        DataFrame dataFrame = new DataFrame(new String[]{"Indice 1", "Indice 2", "Indice 3"}, new String[]{"Label A", "Label B", "Label C"}, Arrays.asList("Valeur A_1","Valeur A_2","Valeur A_3"),Arrays.asList(2,5.5,6.4),Arrays.asList("2",5.78,5));
        System.out.println("");
        System.out.println("** GetColumns() **");
        System.out.println(dataFrame.getColumns());
        System.out.println("** GetIndexes() **");
        System.out.println(dataFrame.getIndexes());
        System.out.println("** GetLabels() **");
        System.out.println(dataFrame.getLabels());
        System.out.println("");
        System.out.println("** PrintAll **");
        System.out.println(dataFrame.printAll());
        System.out.println("** PrintHead **");
        System.out.println(dataFrame.printHead());
        System.out.println("** PrintHead with param **");
        System.out.println(dataFrame.printHead(1));
        System.out.println("** PrintTail **");
        System.out.println(dataFrame.printTail());
        System.out.println("** PrintTail with param **");
        System.out.println(dataFrame.printTail(1));
        System.out.println("");
        System.out.println("** dataFrame.iloc(0,2) Cree un nouveau DataFrame en selectionnant les indices passer en parametre **");
        System.out.println(dataFrame.iloc(0,2).printAll());
        System.out.println("");
        System.out.println("** dataFrame.loc(\"Indice 1\",\"Indice 3\") Cree un nouveau DataFrame en selectionnant les labels passer en parametre **");
        System.out.println(dataFrame.loc("Indice 1","Indice 3").printAll());
        System.out.println("");
        System.out.println("** Sum de chaque colonne **");
        System.out.println(dataFrame.sum());
        System.out.println("** Sum de chaque ligne **");
        System.out.println(dataFrame.sum(1));
        System.out.println("** Sum de chaque colonne (ne pas eviter les valeurs differente de String) **");
        System.out.println(dataFrame.sum(0,false));
        System.out.println("");
        System.out.println("** Average de chaque colonne **");
        System.out.println(dataFrame.avg());
        System.out.println("** Average de chaque ligne **");
        System.out.println(dataFrame.avg(1));
        System.out.println("** Average de chaque colonne (ne pas eviter les valeurs differente de String) **");
        System.out.println(dataFrame.avg(0,false));
        System.out.println("");
        System.out.println("** Min de chaque colonne **");
        System.out.println(dataFrame.min());
        System.out.println("** Min de chaque ligne **");
        System.out.println(dataFrame.min(1));
        System.out.println("** Min de chaque colonne (ne pas eviter les valeurs differente de String) **");
        System.out.println(dataFrame.min(0,false));
        System.out.println("");
        System.out.println("** Max de chaque colonne **");
        System.out.println(dataFrame.max());
        System.out.println("** Max de chaque ligne **");
        System.out.println(dataFrame.max(1));
        System.out.println("** Max de chaque colonne (ne pas eviter les valeurs differente de String) **");
        System.out.println(dataFrame.min(0,false));
        System.out.println("");
    }

}
