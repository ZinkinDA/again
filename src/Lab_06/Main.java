package Lab_06;
/*
    Реализовать пирамидальную сортировку
 */

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        HeapSort heapSort = new HeapSort();
        Scanner scanner = new Scanner(System.in);
        int[] massInt = null;
        try {
            massInt = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        } catch (NumberFormatException e){
            System.err.println("Вы ввели неверный массив, массив указывать в форме { 1,2,3,4,5,6,7,8,.... }");
            System.exit(1);
        }

        heapSort.heapSort(massInt);

        for (int el : massInt) {
            if(el == massInt[massInt.length-1]){
                System.out.println(el);
            } else {
                System.out.print(el + ", ");
            }
        }
        System.out.println();
        scanner.close();
    }

}