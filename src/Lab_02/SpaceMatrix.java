package Lab_02;

import java.util.ArrayList;
import java.util.List;

public class SpaceMatrix {
    private Integer size;
    private Integer[] LI;
    private Integer[] LJ;
    private Integer[] A;
    private Integer nnz;

    /*
        Конструктор для внесения значений из матрицы
     */
    public SpaceMatrix(Integer[][] matrix) {

        final int maxLI = matrix.length;
        final int maxLJ = matrix[0].length;

        if(maxLJ != maxLI){
            System.err.println("Неверная матрица");
            System.exit(1);
        }
        size = maxLI * maxLJ;
        nnz = countNotNullElement(matrix,maxLJ,maxLI);
        init(matrix,maxLJ,maxLI);
    }
    /*
        Конструктор для инициализации
     */
    public SpaceMatrix(Integer[] LI,Integer[] LJ,Integer[] A,Integer nnz,Integer size){
        this.size = size;
        this.LI = LI;
        this.LJ = LJ;
        this.nnz = nnz;
        this.A = A;
    }
    /*
        Инициализация оставшихся полей spaceMatrix
     */
    private void init(Integer[][] matrix,int cols,int rows){
        LJ = new Integer[nnz];
        LI = new Integer[nnz];
        A = new Integer[nnz];
        int countLI = 0;
        int countLJ = 0;
        int countA = 0;
        for (int i = 0;i < cols;i++){
            for (int j = 0; j < rows; j++) {
                if(matrix[j][i] != 0){
                    LJ[countLJ] = j;
                    countLJ++;
                    LI[countLI] = i;
                    countLI++;
                    A[countA] = matrix[j][i];
                    countA++;
                }
            }
        }
    }
    /*
        Проверка на количество не нулевых элементов.
     */
    private int countNotNullElement(Integer[][] matrix,int cols,int rows){
        int NNZ = 0;

        for (int i = 0;i < cols;i++){
            for (int j = 0; j < rows; j++) {
                if(matrix[j][i] != 0){
                    NNZ++;
                }
            }
        }
        return NNZ;
    }
    /*
        Вывод разреженной матрицы
     */
    public String printSpaceMatrix(){

        StringBuilder stringBuilder = new StringBuilder("\nLI : | ");
        for (int el:LJ) {
            stringBuilder.append(el).append( " | "); // Добавление в строку ID строк
        }
        stringBuilder.append("\nLJ : | ");
        for (int el:LI) {
            stringBuilder.append(el).append( " | ");//Добавление в строку ID колонок
        }
        stringBuilder.append("\nA : | ");
        for (int el:A) {
            stringBuilder.append(el).append( " | ");//Добавление в строку значений функции A(LI,LJ)
        }
        return stringBuilder.toString();

    }
    /*
        Вывод матрицы
     */
    public String printMatrix(){
        StringBuilder stringBuilder = new StringBuilder("\nМатрица : \n\t");//Билдер строки

        int size = (int) Math.sqrt(this.size); // Мы уверены что матрица согласована.
        int[][] matrix = new int[size][size];

        /*
            Записываем не нулевые элементы по индексу
         */
        int count = 0;
        while (count < nnz){
            matrix[this.LJ[count]][this.LI[count]] = this.A[count];
            count++;
        }
        /*
            Записываем матрицу в строку
         */
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                stringBuilder.append(matrix[i][j]).append(" ");
            }
            stringBuilder.append("\n\t");
        }

        return stringBuilder.toString();
    }
    /*
        Метод нашей лабораторной
     */
    public SpaceMatrix labsMethod(int b){
        StringBuilder stringBuilder = new StringBuilder("\n Полученная матрица равна : \n\t");
        List<Integer> minB = new ArrayList<>();// Элементы меньше
        List<Integer> maxB = new ArrayList<>();// Элементы больше

        //Находим элементы
        for(int el:A){
            if(el > b){
                maxB.add(el);
            } else {
                minB.add(el);
            }
        }
        // Добавляем к большим меньшие
        maxB.addAll(minB);

        // Создаем новую разреженную матрицу
        Integer[] LI = new Integer[nnz];
        Integer[] LJ = new Integer[nnz];
        Integer[] A = maxB.toArray(Integer[]::new);

        int count = 0;
        int r = 0;
        // Ставим нужные индексы
        for (int i = 0;i < nnz; i++) {
            if(r == (int) Math.sqrt(this.size)){
                count++;
                r = 0;
            }
            LJ[i] = count;
            LI[i] = r;
            r++;
        }
        // Возвращаем новую матрицу.
        return new SpaceMatrix(LI,LJ,A,this.nnz,this.size);
    }
}
