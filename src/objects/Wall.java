package objects;

import java.util.Random;

public class Wall implements Vincible {

    private final int MIN_HEIGHT = 0;
    private final int MAX_HEIGHT = 5;

    private int height;

    public Wall() {
        this.height = new Random().nextInt(MAX_HEIGHT);
    }

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "!Препятствие: Стена высотой " + height;
    }

    @Override
    public void pass(Moveable sportsman) {
        if (sportsman.isActive()) {
            sportsman.jump(this.height);
        }
    }
}
