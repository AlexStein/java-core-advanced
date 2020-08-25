package exceptions;

/**
 * Класс исключения вырбасываемого при некорректных данных в ячейке
 *
 * @author AlexS
 */
public class MyArrayDataException extends Exception {

    private int row;
    private int element;

    public MyArrayDataException(String message, int row, int element) {
        super(message);
        this.row = row;
        this.element = element;
    }

    public int getElement() {
        return element;
    }

    public int getRow() {
        return row;
    }
}
