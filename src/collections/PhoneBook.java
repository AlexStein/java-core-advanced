package collections;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Класс Телефонный справочник
 */
public class PhoneBook {

    private HashMap<String, HashSet<String>> storage;

    public PhoneBook() {
        this.storage = new HashMap<>();
    }

    /**
     * Добавить запись в телефонный справочник
     *
     * @param fullName    Фамилия абонента
     * @param phoneNumber Телефонный номер
     */
    public void add(String fullName, String phoneNumber) {

        HashSet<String> phoneSet = this.storage.getOrDefault(fullName,new HashSet<>());
        phoneSet.add(phoneNumber);

        this.storage.put(fullName, phoneSet);
    }

    /**
     * Получить телефонный номер по фамилии абонента
     *
     * @param fullName Фамилия абонента
     * @return Телефонный номер или null
     */
    public String get(String fullName) {
        HashSet<String> phoneSet = this.storage.get(fullName);

        if (phoneSet == null) {
            return null;
        }

        return String.join(", ", phoneSet);
    }
}
