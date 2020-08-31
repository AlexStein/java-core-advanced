package collections;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        /* Найти и вывести список уникальных слов, из которых состоит массив.
         * Посчитать, сколько раз встречается каждое слово.
         */

        ArrayList<String> words = new ArrayList<>();
        words.addAll(Arrays.asList("Арбуз", "Дыня", "Тыква", "Картофель", "Морковь", "Свекла",
                "Редька", "Редис", "Хрен", "Сельдерей", "Арбуз", "Репа", "Тыква",
                "Картофель", "Арбуз", "Репа", "Тыква", "Апельсин", "Арбуз", "Малина",
                "Дыня", "Тыква", "Картофель", "Виноград", "Вишня"));

        // Чтобы отсортировалось TreeMap
        Map<String, Integer> mapCount = new TreeMap<>();
        for (String w : words) {
            Integer value = mapCount.getOrDefault(w, 0);
            mapCount.put(w, value + 1);
        }

        // Список уникальных слов
        System.out.println("Список уникальных слов:");
        System.out.println(String.join(", ", mapCount.keySet()));

        // Количество повторений слов
        System.out.println("\nКоличество повторений слов:");
        mapCount.forEach((k, v) -> {
            System.out.printf("%s: %d\n", k, v);
        });

        /* ТелефонныйСправочник, хранит в себе список фамилий и телефонных номеров.
         * В этот телефонный справочник с помощью метода add() можно добавлять записи.
         * С помощью метода get() искать номер телефона по фамилии. Следует учесть, что под одной фамилией может
         * быть несколько телефонов (в случае однофамильцев), тогда при запросе такой фамилии должны выводиться
         * все телефоны.
         */

        String[] names = {
                "Васильев", "Фомин", "Капустин", "Воронов", "Горбунов", "Селиверстов",
                "Лаврентьев", "Родионов", "Буров", "Зуев", "Зимин", "Некрасов"
        };

        Random random = new Random();
        int size = 20;

        // Телефонный справочник
        PhoneBook phoneBook = new PhoneBook();

        String name;
        String number;
        for (int i = 0; i < size; i++) {
            name = names[random.nextInt(names.length)];
            number = String.format("+7(495) %03d-%02d-%02d",
                    random.nextInt(950) + 50,
                    random.nextInt(100),
                    random.nextInt(100));

            phoneBook.add(name, number);
        }

        System.out.println("\nТелефонный справочник");
        for (int i = 0; i < 4; i++) {
            name = names[random.nextInt(names.length)];
            number = phoneBook.get(name);
            if (number == null) {
                number = "Телефон не найден";
            }

            System.out.printf("%s: %s\n", name, number);
        }
    }
}
