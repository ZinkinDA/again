package Lab_02;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("src/Lab_02/matrix.txt");// Файл с нашими исходными данными
        if(!file.exists()){
            file.createNewFile();
        }

        Scanner scanner = new Scanner(file);

        int LJ = scanner.nextLine().split(" ").length; // Макс столбцов
        int LI = 1; // Макс строк

        while (scanner.hasNextLine()) {
            LI++;
            scanner.nextLine();
        }

        scanner = new Scanner(file);
        Integer[][] matrix = new Integer[LJ][LI];// Создаем массив матрицы
        try {
            for (int i = 0; i < LJ; i++) {
                for (int j = 0; j < LI; j++) {
                    matrix[i][j] = scanner.nextInt(); // Записываем элементы
                }
            }
        } catch (InputMismatchException e){
            System.err.println("Проверьте данные в файле");
            System.exit(1);
        }
        //Cчитываем теперь с консоли
        scanner = new Scanner(System.in);
        SpaceMatrix spaceMatrix = new SpaceMatrix(matrix);//Создаем нашу разреженную матрицу
        System.out.println(spaceMatrix.printMatrix());//Вывод матрицы
        System.out.println(spaceMatrix.printSpaceMatrix());//Вывод этой же разреженной матрицы

        //ЛР
        System.out.println("Задание лабораторной работы (вариант 11)");
        System.out.print("Введите число B : ");
        try {

            int b = scanner.nextInt();
            SpaceMatrix result = spaceMatrix.labsMethod(b); // Все реализация решения задачи

            System.out.println(result.printMatrix()); //Вывод матрицы
            System.out.println(result.printSpaceMatrix());//Вывод этой же разреженной матрицы

            scanner.close();// закрываем сканер

        } catch (InputMismatchException e){
            System.err.println("Вы ввели с консоли не число!");
            System.exit(1);
        }
    }
}
