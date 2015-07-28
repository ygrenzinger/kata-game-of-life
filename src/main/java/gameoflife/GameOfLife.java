package gameoflife;

import static gameoflife.CellState.ALIVE;

/**
 * Created by yannickgrenzinger on 28/07/2015.
 */
public class GameOfLife {

    private final CellState[][] grid;

    public GameOfLife(CellState[][] grid) {
        this.grid = grid;
    }

    public static GameOfLife emptyGrid(int size) {
        return createGrid(CellState.DEAD, size + 2);
    }


    public static GameOfLife fullGrid(int size) {
        return createGrid(CellState.ALIVE, size + 2);

    }

    private static GameOfLife createGrid(CellState state, int size) {
        CellState[][] newGrid = new CellState[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                newGrid[i][j] = state;
            }
        }
        return new GameOfLife(newGrid);
    }

    public void makeLive(int row, int column) {
        grid[row + 1][column + 1] = ALIVE;
    }

    public CellState cellAt(int row, int column) {
        return grid[row + 1][column + 1];
    }

    public int countNeighbours(int row, int column) {

        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (!(i == 0 && j == 0) && cellAt(row + i, column + j) == ALIVE) {
                    count++;
                }
            }
        }
        return count;
    }

    public GameOfLife nextGen() {

        GameOfLife newGrid = emptyGrid(grid.length - 2);
        for (int i = 0; i < grid.length - 2; i++) {
            for (int j = 0; j < grid.length - 2; j++) {
                int count = countNeighbours(i, j);
                if (cellAt(i, j).nextGeneration(count) == ALIVE) {
                    newGrid.makeLive(i, j);
                }
            }
        }

        return newGrid;
    }
}
