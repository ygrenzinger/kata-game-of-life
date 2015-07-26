package gameoflife;

import java.util.Arrays;

import static gameoflife.CellState.ALIVE;
import static gameoflife.CellState.DEAD;

public class GameOfLifeGrid {

    private final CellState[][] grid;
    private final int size;

    private GameOfLifeGrid(CellState[][] grid, int size) {
        this.size = size;
        this.grid = grid;
    }

    public void makeAlive(int x, int y) {
        grid[x+1][y+1] = ALIVE;
    }

    public int numberOfNeighbour(int x, int y) {
        return internalNeighbourCount(x+1, y+1);
    }

    private int internalNeighbourCount(int x, int y) {
        int count = 0;
        for (int i = -1; i <= +1; i++) {
            for (int j = -1; j <= +1; j++) {
                if (!(i == 0 && j == 0) && grid[x + i][y + j] == ALIVE) {
                    count++;
                }
            }
        }
        return count;
    }

    public static GameOfLifeGrid emptyGrid(int size) {
        return new GameOfLifeGrid(createGrid(DEAD, size+2), size);
    }

    public static GameOfLifeGrid fullGrid(int size) {
        return new GameOfLifeGrid(createGrid(ALIVE, size+2), size);
    }

    private static CellState[][] createGrid(CellState state, int size) {
        CellState[][] grid = new CellState[size][size];
        for (int row = 0; row < size; row++) {
            Arrays.fill(grid[row], state);
        }
        return grid;
    }

    public GameOfLifeGrid nextGeneration() {
        GameOfLifeGrid nextGameOfLifeGeneration = emptyGrid(size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int count = numberOfNeighbour(i, j);
                nextGameOfLifeGeneration.grid[i+1][j+1] = this.grid[i+1][j+1].nextGeneration(count);
            }
        }
        return nextGameOfLifeGeneration;
    }

    public CellState cellAt(int x, int y) {
        return grid[x+1][y+1];
    }
}
