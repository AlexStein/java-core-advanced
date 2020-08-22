package exceptions;

/**
 * Класс исключения выбрасываемого при некорректном размере массива.
 *
 * @author AlexS
 */
public class MyArraySizeException extends Exception {

    private int size;

    public MyArraySizeException(String message, int size) {
        super(message);
        this.size = size;
    }

    public int getSize() {
        return size;
    }


}
