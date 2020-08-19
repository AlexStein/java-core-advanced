package objects;

import java.util.Random;

public class Human implements Moveable {

    private static int MIN_RUN = 50;
    private static int MIN_JUMP = 1;

    private static int MAX_RUN = 500;
    private static int MAX_JUMP = 5;

    private String name;

    private int maxJumpHeight;
    private int maxRunLength;

    private boolean active;

    public Human(String name) {
        this.name = name;

        Random rand = new Random();

        this.maxJumpHeight = rand.nextInt(MAX_JUMP - MIN_JUMP) + MIN_JUMP;
        this.maxRunLength = rand.nextInt(MAX_RUN - MIN_RUN) + MIN_RUN;

        this.active = true;
    }

    @Override
    public String toString() {
        return "Человек " + name;
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

        if (length < this.maxRunLength) {
            this.active = false;
            not = " не";
        }
        System.out.printf("Человек %s%s пробежал %d метров\n", name, not, length);
    }
}
