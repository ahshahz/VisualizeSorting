import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class Model extends Observable {
    private int[][] twoDArray;
    private int width;
    private int height;

    public Model(int width, int height) {
        this.width = width;
        this.height = height;
        this.twoDArray = new int[this.width][this.height];
    }

    public int[][] getArray() {
        return this.twoDArray;
    }

    public int[] convert1Darray() {
        int[] oneDarray = new int[twoDArray.length * twoDArray[0].length];
        int value = 0;
        int f = 0; //tracks oneD index
        for (int r = 0; r < twoDArray.length; r++) {
            for (int c = 0; c < twoDArray[r].length; c++) {
                value = twoDArray[r][c];
                oneDarray[f] = value;
                f++;
            }
        }
        return oneDarray;
    }

    public void update2Darr(int arr[]) {
        int value = 0;
        int f = 0; //tracks oneD index
        for (int r = 0; r < twoDArray.length; r++) {
            for (int c = 0; c < twoDArray[r].length; c++) {
                value = arr[f];
                twoDArray[r][c] = value;
                f++;
            }
        }
        setChanged();
        notifyObservers();
    }

    public void quicksortControl() {
        int[] oneDarr = convert1Darray();
        quickSort(oneDarr, 0, oneDarr.length - 1);
        update2Darr(oneDarr);
    }

    public void quickSort(int arr[], int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public int partition(int arr[], int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        int tempI = 0;
        int tempJ = 0;
        for (int j = low; j <= high - 1; j++) {

            if (arr[j] < pivot) {
                i++;
                tempI = arr[i];
                tempJ = arr[j];
                arr[i] = tempJ;
                arr[j] = tempI;

            }
        }
        tempI = arr[i + 1];
        tempJ = arr[high];
        arr[high] = tempI;
        arr[i + 1] = tempJ;
        return i + 1;
    }

    public void randomize() {
        for (int r = 0; r < twoDArray.length; r++) {
            for (int c = 0; c < twoDArray[r].length; c++) {
                Random num = new Random();
                int value = num.nextInt(255 * 2) - 255;
                twoDArray[r][c] = value;
            }
        }
        setChanged();
        notifyObservers();
    }

    public void sortColumn() { //go column by column

        for (int k = 0; k < twoDArray[0].length; k++) {
            ArrayList<Integer> column = getColumn(k);

            int element = 0;

            int templarge = 0;
            int tempsmall = 0;
            for (int j = 1; j <= column.size() - 1; j++) {
                for (int i = j; i >= 1; i--) {
                    if (column.get(i - 1) > column.get(i)) {
                        templarge = column.get(i - 1);
                        tempsmall = column.get(i);
                        column.set(i, templarge);
                        column.set(i - 1, tempsmall);
                    }
                }
            }
            for (int h = 0; h < column.size(); h++) {
                element = column.get(h);
                set(element, h, k);
            }

        }
        setChanged();
        notifyObservers();


    }

    public ArrayList<Integer> getColumn(int col) {
        ArrayList<Integer> column = new ArrayList<>();
        for (int row = 0; row < twoDArray.length; row++) {
            int element = twoDArray[row][col];
            column.add(element);
        }
        return column;
    }

    public void sortRows() { //work with small sizes
        for (int k = 0; k < twoDArray.length; k++) {
            ArrayList<Integer> rows = getRow(k);

            int element = 0;


            int templarge = 0;
            int tempsmall = 0;
            for (int j = 1; j <= rows.size() - 1; j++) {
                for (int i = j; i >= 1; i--) {
                    if (rows.get(i - 1) > rows.get(i)) {
                        templarge = rows.get(i - 1);
                        tempsmall = rows.get(i);
                        rows.set(i, templarge);
                        rows.set(i - 1, tempsmall);
                    }
                }
            }
            for (int h = 0; h < rows.size(); h++) {
                element = rows.get(h);
                set(element, k, h);
            }

        }
        setChanged();
        notifyObservers();
    }


    public ArrayList<Integer> getRow(int row) {
        ArrayList<Integer> rows = new ArrayList<>();
        int element = 0;
        for (int c = 0; c < twoDArray[0].length; c++) {
            element = get(row, c);
            rows.add(element);
        }
        return rows;

    }

    public void set(int numOne, int row, int col) {
        twoDArray[row][col] = numOne;
    }

    void sortArray1() {
        long lTimeBefore = System.nanoTime();
        sortColumn();
        sortRows();
        long lTimeAfter = System.nanoTime();
        long lElapsedNanoSeconds = (lTimeAfter - lTimeBefore);
        System.out.println("sort Array 1: " + lElapsedNanoSeconds);
        setChanged();
        notifyObservers();
    }

    void sortArray2() {
        long lTimeBefore = System.nanoTime();
        sortRows();
        sortColumn();
        long lTimeAfter = System.nanoTime();
        long lElapsedNanoSeconds = (lTimeAfter - lTimeBefore);
        System.out.println("sort Array 2: " + lElapsedNanoSeconds);
        setChanged();
        notifyObservers();
    }

    public int get(int useRow, int useCol) {
        int element = 0;
        element = twoDArray[useRow][useCol];
        return element;
    }

    public String toString() { //here for testing purposes
        String element = "";
        for (int r = 0; r < twoDArray.length; r++) {
            element += "\n";
            for (int c = 0; c < twoDArray[r].length; c++) {
                element += " " + twoDArray[r][c];
            }
        }
        return element;
    }

    public static void main(String[] args) {
        Model test = new Model(4, 4);
        Model test2 = new Model(4, 4);
        Model test3 = new Model(4, 4);
        test.randomize();
        System.out.println("before quick sort");
        System.out.println(test);
        System.out.println("after quick sort");
        test.quicksortControl();
        System.out.println(test);


        //test.sortRows();

    /*
        System.out.println("after sort rows");
        System.out.println(test);
        //testing sort methods (bulk)
        test2.randomize();
        System.out.println("before sort");
        System.out.println(test2);
        System.out.println("after sort array 2 ");
        test2.sortArray2();
        System.out.println(test2);
        test3.randomize();
        System.out.println("before sort");
        System.out.println(test3);
        test3.sortArray1();
        System.out.println("after sort");
        System.out.println(test3);

        //Task A.6 observation
        System.out.println("A6");
        test.randomize();
        test2.randomize();
        System.out.println(test);
        test.sortArray1();
        test2.sortArray2();
        System.out.println("Array2");
        System.out.println(test);
        */

    }

}