package threads;

import java.util.Random;

public class Main {

    static final int size = 10000000;
    static final int h = size / 2;

    public static void main(String[] args) {

        float[] arr = new float[size];
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }

        long startTime, endTime;

        // Без потоков
        startTime = System.currentTimeMillis();
        straightCalculation(arr);
        endTime = System.currentTimeMillis();
        printSpentTime("Расчет в главном потоке ", startTime, endTime);

        // Проверка значения в случайной ячейке во второй половине массива
        int index = new Random().nextInt(h) + h;
        System.out.printf("Проверка значения %d элемента: %f\n\n", index, arr[index]);

        // Сбрасываем значения на единички
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }

        // С потоками
        float[] a1 = new float[h];
        float[] a2 = new float[h];

        startTime = System.currentTimeMillis();

        // Разбиваем
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        // Расчет
        multithreadCalculation(a1, a2);

        // Склеиваем
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        endTime = System.currentTimeMillis();
        printSpentTime("Расчет в двух фоновых потоках ", startTime, endTime);

        // Проверка значения в случайной ячейке
        System.out.printf("Проверка значения %d элемента: %f\n\n", index, arr[index]);
    }

    /**
     * Вычисление в значений в массиве
     */
    public static void straightCalculation(float[] arr) {
        for (int i = 0; i < size; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    /**
     * Вычисление в значений в массивах в двух потоках
     */
    public static void multithreadCalculation(float[] a1, float[] a2) {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < h; i++) {
                a1[i] = (float)(a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < h; i++) {
                a2[i] = (float)(a2[i] * Math.sin(0.2f + (i + h) / 5) * Math.cos(0.2f + (i + h) / 5) * Math.cos(0.4f + (i + h) / 2));
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Печать оценки выполнения кода с помощью System.nanoTime()
     *
     * @param taskDescription Описание анализируемого алгоритма
     * @param start           время начала, нс
     * @param end             время окончания, нс
     */
    public static void printSpentTime(String taskDescription, long start, long end) {
        System.out.println(taskDescription);
        System.out.printf("Время выполения: %.3f с\n\n", (end - start) * 0.001);
    }
}
