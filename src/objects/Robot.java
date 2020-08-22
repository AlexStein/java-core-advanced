package objects;

public class Robot implements Moveable {

    // Одна модель одни параметры =)
    private final int MAX_RUN = 150;

    private String name;

    private boolean active;

    public Robot(String name) {
        this.name = name;

        this.active = true;
    }

    @Override
    public String toString() {
        return "Робот " + name;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public void jump(int height) {
        this.active = false;
        System.out.println(toString() + " не умеет прыгать");
    }

    @Override
    public void run(int length) {
        String not = "";
        if (length > MAX_RUN) {
            this.active = false;
            not = " не";
        }
        System.out.printf("%s%s пробежал %d метров\n", toString(), not, length);
    }
}
