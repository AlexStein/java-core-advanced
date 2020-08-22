package objects;

import java.util.Random;

public class Cat implements Moveable {

    private final int MIN_RUN = 20;
    private final int MIN_JUMP = 8;

    private final int MAX_RUN = 200;
    private final int MAX_JUMP = 12;

    private String name;

    private int maxJumpHeight;
    private int maxRunLength;

    private boolean active;

    public Cat(String name) {
        this.name = name;

        Random rand = new Random();

        this.maxJumpHeight = rand.nextInt(MAX_JUMP - MIN_JUMP) + MIN_JUMP;
        this.maxRunLength = rand.nextInt(MAX_RUN - MIN_RUN) + MIN_RUN;

        this.active = true;
    }

    @Override
    public String toString() {
        return "Кот " + name;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public void jump(int height) {
        String not = "";
        if (height > this.maxJumpHeight) {
            this.active = false;
            not = " не";
        }
        System.out.printf("%s%s запрыгнул на стену в %d метров\n", toString(), not, height);
    }

    @Override
    public void run(int length) {
        String not = "";
        if (length > this.maxRunLength) {
            this.active = false;
            not = " не";
        }
        System.out.printf("%s%s пробежал %d метров\n", toString(), not, length);
    }
}
