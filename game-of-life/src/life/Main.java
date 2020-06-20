package life;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class Universe {
    String[][] universe;

    Universe(int size, long seed) {
        this.universe = new String[size][size];
        populateUniverse(seed);
    }

    void populateUniverse(long seed) {
        Random random = new Random(seed);
        for (int y = 0; y < universe.length; y++) {
            for (int x = 0; x < universe.length; x++) {
                if (random.nextBoolean()) {
                    universe[y][x] = "O";
                } else {
                    universe[y][x] = " ";
                }
            }
        }
    }

    public void tick() {
        String[][] newUniverse = Generator.nextGen(universe);
        universe = newUniverse;
    }

    public void printUniverse() {
        for (String[] line : universe) {
            System.out.println(String.join("", line));
        }
    }
}

class Generator {
    public static String[][] nextGen(String[][] universe) {
        String[][] newUniverse = new String[universe.length][universe.length];
        for (int y = 0; y < universe.length; y++) {
            for (int x = 0; x < universe.length; x++) {
                int left = (x == 0) ? universe.length - 1 : x - 1;
                int right = (x == universe.length - 1) ? 0 : x + 1;
                int up = (y == 0) ? universe.length - 1 : y - 1;
                int down = (y == universe.length - 1) ? 0 : y + 1;

                String[] neighbours = {
                        universe[up][left], universe[up][x], universe[up][right],
                        universe[y][left], universe[y][right],
                        universe[down][left], universe[down][x], universe[down][right] };

                long aliveNeighbours = Arrays.stream(neighbours)
                        .filter(c -> c == "O")
                        .count();

                if (aliveNeighbours == 3) {
                    newUniverse[y][x] = "O";
                } else if (universe[y][x] == "O" && aliveNeighbours == 2 ){
                    newUniverse[y][x] = "O";
                } else {
                    newUniverse[y][x] = " ";
                }
            }
        }
        return newUniverse;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int size = scan.nextInt();
        long seed = scan.nextInt();
        int generations = scan.nextInt();

        Universe universe = new Universe(size, seed);

        for (int i = 0; i < generations; i++) {
            universe.tick();
        }
        universe.printUniverse();
    }
}