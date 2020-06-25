package life;

import java.util.Arrays;

class Universe {
    private CellState[][] state;
    static int generation = 0;
    static long numOfAlive;

    Universe() {
        int width = 20;
        this.state = new CellState[width][width];
    }

    public int getWidth() {
        return state.length;
    }

    public void calculateNumOfAlive() {
        numOfAlive = Arrays.stream(state)
                .flatMap(Arrays::stream)
                .filter(c -> c == CellState.ALIVE)
                .count();
    }

    public void incrementGeneration() {
        generation += 1;
    }

    public CellState getCell(int y, int x) {
        return state[y][x];
    }

    public void setCell(int y, int x) {
        state[y][x] = CellState.ALIVE;
    }

    public void unsetCell(int y, int x) {
        state[y][x] = CellState.DEAD;
    }

    public boolean isSet(int y, int x) {
        return state[y][x] == CellState.ALIVE ? true : false;
    }

    public CellState[][] getState() {
        return state;
    }

    public long getNumOfNeighbours(int y, int x) {
        int width = state.length;
        int left = (x == 0) ? width - 1 : x - 1;
        int right = (x == width - 1) ? 0 : x + 1;
        int up = (y == 0) ? width - 1 : y - 1;
        int down = (y == width - 1) ? 0 : y + 1;

        CellState[] neighbours = {
                state[up][left],   state[up][x],   state[up][right],
                state[y][left], /* state[y][x] */  state[y][right],
                state[down][left], state[down][x], state[down][right]};

        return Arrays.stream(neighbours)
                .filter(c -> c == CellState.ALIVE)
                .count();
    }
}
