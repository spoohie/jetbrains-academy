package life;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class Universe {
    String[][] universe;
    static int generation = 0;

    Universe(int size) {
        this.universe = new String[size][size];
        populateUniverse();
    }

    void populateUniverse() {
        Random random = new Random();
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

    public void tick(int numOfTicks) {
        for (int i = 0; i < numOfTicks; i++ ) {
            tick();
        }
    }

    public void tick() {
        String[][] newUniverse = Generator.nextGen(universe);
        universe = newUniverse;
        generation++;
    }

    public long getNumOfAlive() {
        return Arrays.stream(universe)
                .flatMap(Arrays::stream)
                .filter(c -> c == "O")
                .count();
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

        Universe universe = new Universe(size);

        try {
            while (Universe.generation < 11) {
                if (System.getProperty("os.name").contains("Windows"))
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                else
                    Runtime.getRuntime().exec("clear");

                universe.tick();
                System.out.println("Generation #" + Universe.generation);
                System.out.println("Alive: " + universe.getNumOfAlive());
                universe.printUniverse();
                Thread.sleep(1000);
            }
        } catch (InterruptedException | IOException e) {}

    }
}