package gameoflife;

import static gameoflife.CellState.ALIVE;
import static gameoflife.CellState.DEAD;

public class GameOfLifeGrid {


    private final CellState[][] grid;

    public GameOfLifeGrid(CellState[][] grid) {
        this.grid = grid;
    }

    public static GameOfLifeGrid emptyGrid(int size) {
        return createGrid(DEAD, size);
    }

    private static GameOfLifeGrid createGrid(CellState state, int size) {
        CellState[][] newGrid = new CellState[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                newGrid[i][j] = state;
            }
        }
        return new GameOfLifeGrid(newGrid);
    }

    public void makeAlive(int x, int y) {
        grid[x][y] = ALIVE;
    }

    public CellState cellAt(int x, int y) {
        return grid[x][y];
    }

    public static GameOfLifeGrid fullGrid() {
        return createGrid(ALIVE, 3);
    }



    public int neighbourCount(int x, int y) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (!(i == 0 && j == 0) && grid[x+i][y+j] == ALIVE) {
                    count++;
                }
            }
        }
        return count;
    }

    public GameOfLifeGrid nextGeneration() {
        GameOfLifeGrid newGen = copy();
        for (int i = 1; i < grid.length-1; i++) {

            for (int j = 1; j < grid.length-1; j++) {
                int count =   this.neighbourCount(i, j);
                newGen.grid[i][j] = this.grid[i][j].nextGeneration(count);
            }
        }
        return newGen;
    }

    private GameOfLifeGrid copy() {
        GameOfLifeGrid copy = emptyGrid(grid.length);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                    copy.grid[i][j] = grid[i][j];
            }
        }
        return copy;
    }
}
