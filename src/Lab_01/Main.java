/*
    Задача 11.
    Пусть имеется набор действительных чисел и некоторое число C. Используя очередь, напечатать сначала все элементы,
     меньшие числа C, а затем большие числа C (если в наборе встретиться С, то его выводить С раз).

 */

package Lab_01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        /*
             Считываем из консоли число с
         */
        int c = 0;
        Scanner scanner = new Scanner(System.in);

        try {
            c = scanner.nextInt();
        } catch (InputMismatchException e){
            System.err.println("Вы ввели не число");
            System.exit(1);
        }

        String filename = "input.txt";
        File file = new File(filename);

        if(!file.exists()){

            /*
                Cкорее всего может быть какая-то ошибка в имени при создании,
                но мы его имя указали в переменной filename.
             */
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        scanner = new Scanner(file);
        String[] str = scanner.nextLine().split(" ");
        function(str,c);
        scanner.close();
    }


    private static void function(String[] input,int c){

        Queue queue = new Queue(input.length);

        for (String el:input) {
            try {
                queue.insert(Integer.parseInt(el));
            } catch (NumberFormatException e){
                System.err.println("Проверьте файл input.txt находящийся в корне приложения на ошибки { 1 2 3 4 5 6 7 8 ... }");
                System.exit(1);
            }
        }


        StringBuilder result = new StringBuilder("Все элементы : " + queue + "\n");
        StringBuilder stringBuilderMinC = new StringBuilder("Меньше c : ");
        StringBuilder stringBuilderC = new StringBuilder("c : ");
        StringBuilder stringBuilderMaxC = new StringBuilder("Больше с : ");

        while (!queue.isEmpty()){
            if(queue.getFront() == c){

                for (int i = 0; i < 5; i++) {
                    stringBuilderC.append(c).append(" ");
                }
                queue.remove();

            } else if(queue.getFront() < c) {

                stringBuilderMinC.append(queue.getFront()).append(" ");
                queue.remove();

            } else if(queue.getFront() > c){
                stringBuilderMaxC.append(queue.getFront()).append(" ");
                queue.remove();

            }
        }

        result.append(stringBuilderMinC).append("\n")
                .append(stringBuilderMaxC).append("\n");

        if(!(stringBuilderC.length() == "c : ".length())){
            result.append(stringBuilderC).append("\n");
        }

        System.out.println(result);
    }
}
