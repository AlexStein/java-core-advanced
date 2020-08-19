package weekdays;

public enum DayOfWeek {

    MONDAY(5), TUESDAY(4), WEDNESDAY(3), THURSDAY(2), FRIDAY(1), SATURDAY(0), SUNDAY(0);

    private final int DAY_LENGTH = 8;
    private int restTime;

    DayOfWeek(int restTime) {
        this.restTime = restTime * DAY_LENGTH;
    }

    public int getRestTime() {
        return restTime;
    }
}
