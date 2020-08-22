package objects;

import java.util.Random;

public class Track implements Vincible {

    private final int MIN_LENGTH = 30;
    private final int MAX_LENGTH = 300;

    private int length;

    public Track() {
        this.length = new Random().nextInt(MAX_LENGTH - MIN_LENGTH) + MIN_LENGTH;
    }

    public Track(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "!Препятствие: Дорожка длиной " + length;
    }

    @Override
    public void pass(Moveable sportsman) {
        if (sportsman.isActive()) {
            sportsman.run(this.length);
        }
    }
}
