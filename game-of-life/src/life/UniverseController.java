package life;

import java.util.Random;

class UniverseController extends Thread {
    private Universe universe;
    private UniverseView universeView;

    UniverseController(Universe universe, UniverseView gameOfLife) {
        this.universe = universe;
        this.universeView = gameOfLife;
    }

    @Override
    public void run() {
        populateUniverse();

        while (true) {
            universeView.updateView(universe.getState());
            tick();
        }
    }

    void populateUniverse() {
        int width = universe.getWidth();
        Random random = new Random();

        for (int y = 0; y < width; y++) {
            for (int x = 0; x < width; x++) {
                if (random.nextBoolean()) {
                    universe.setCell(y, x);
                } else {
                    universe.unsetCell(y, x);
                }
            }
        }
        universe.calculateNumOfAlive();
    }

    private void tick() {
        int width = universe.getWidth();
        Universe nextGeneration = new Universe();

        for (int y = 0; y < width; y++) {
            for (int x = 0; x < width; x++) {
                long aliveNeighbours = universe.getNumOfNeighbours(y, x);

                if (aliveNeighbours == 3) {
                    nextGeneration.setCell(y, x);
                } else if (universe.isSet(y, x) && aliveNeighbours == 2) {
                    nextGeneration.setCell(y, x);
                } else {
                    nextGeneration.unsetCell(y, x);
                }
            }
        }
        universe = nextGeneration;
        universe.incrementGeneration();
        universe.calculateNumOfAlive();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
