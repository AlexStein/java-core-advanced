package objects;

public class Main {

    public static void main(String[] args) {

        Moveable[] sportsmen = {
                new Cat("Барсик"),
                new Human("Иван"),
                new Robot("Р010101"),
                new Cat("Мурзик"),
                new Human("Михаил"),
                new Robot("Р010102"),
                new Cat("Рыжик"),
                new Human("Феофан"),
                new Robot("Р010103"),
                new Cat("Васька"),
                new Human("Кирилл"),
                new Robot("Р010104")
        };

        Vincible[] obstacles = {
                new Track(),
                new Wall(),
                new Track(),
                new Wall()
        };

        System.out.println("-- Старт --");

        for (Vincible obstacle : obstacles) {
            System.out.println(obstacle.toString());

            for (Moveable sportsman : sportsmen) {
                obstacle.pass(sportsman);
            }

            System.out.println();
        }

        System.out.println("-- Финиш --");

        for (Moveable sportsman : sportsmen) {
            if (sportsman.isActive()) {
                System.out.println(sportsman.toString() + " прошел испытание");
            }
        }
    }
}
