package weekdays;

public class Main {

    public static void main(String[] args) {
        System.out.println(getWorkingHours(DayOfWeek.MONDAY));
        System.out.println(getWorkingHours(DayOfWeek.FRIDAY));
        System.out.println(getWorkingHours(DayOfWeek.SUNDAY));
    }

    public static String getWorkingHours(DayOfWeek dow) {
        int restTime = dow.getRestTime();

        if (restTime == 0) {
            return "Выходной";
        }

        return String.valueOf(restTime);
    }

}
