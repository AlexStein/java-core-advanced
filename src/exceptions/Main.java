package exceptions;

public class Main {

    public static void main(String[] args) {

        // Массив некорректного размера
        String[][] badSizeArray1 = {
                {"1"}
        };

        String[][] badSizeArray2 = {
                {"1", "21", "3.5", "0"},
                {"1", "21", "3.5", "0"},
                {"1", "21", "3.5", "0", "1"},
                {"1", "21", "3.5", "0"},
        };

        // Массив с плохими данными
        String[][] badValuesArray = {
                {"1", "21", "3.5", "0"},
                {"1", "21", "3.5", "A"},
                {"--1", "21", "3.5", "0"},
                {"1", "21", "3.5", "0"}
        };

        // Корректный массив для подстчета суммы
        String[][] goodArray = {
                {"1", "21", "42", "63"},
                {"17", "169", "67", "80"},
                {"19", "121", "53", "1000"},
                {"91", "35", "11", "0145"}
        };

        System.out.println("badSizeArray1");
        printResult(badSizeArray1);

        System.out.println("badSizeArray2");
        printResult(badSizeArray2);

        System.out.println("badValuesArray");
        printResult(badValuesArray);

        System.out.println("goodArray");
        printResult(goodArray);

        System.out.println("End");
    }

    /**
     * Метод проходит по всем элементам массива, преобразовывает в int, и суммирует.
     *
     * @param arr Исходный массив размерностью 4х4
     * @return Сумма элементов массива
     * @throws MyArraySizeException Исключение при подаче массива другого размера
     * @throws MyArrayDataException Исключение если в элементе массива преобразование не удалось
     */
    public static long getArray(String[][] arr) throws MyArraySizeException, MyArrayDataException {
        long sum = 0;

        if (arr.length != 4) {
            throw new MyArraySizeException(
                    "Неверное количество строк в массиве: " + arr.length, arr.length);
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length != 4) {
                throw new MyArraySizeException(
                        "Неверное количество элементов в строке массива: " + arr[i].length, arr[i].length);
            }
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {

                try {
                    sum += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(String.format("Неверные данные в ячейке [%d][%d]", i, j), i, j);
                }
            }
        }

        return sum;
    }

    public static void printResult(String[][] arr) {
        try {
            long sum = getArray(arr);
            System.out.println("Сумма элементов массива: " + sum);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e);
        } finally {
            System.out.println();
        }
    }
}
