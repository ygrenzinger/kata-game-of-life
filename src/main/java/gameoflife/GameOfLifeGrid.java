package gameoflife;

import java.util.Arrays;

import static gameoflife.CellState.*;

/**
 * Created by yannickgrenzinger on 27/07/2015.
 */
public class GameOfLifeGrid {

    public final CellState[][] grid;

    private GameOfLifeGrid(CellState[][] grid) {
        this.grid = grid;
    }

    public static GameOfLifeGrid emptyGrid() {
        return createGrid(DEAD);
    }

    public static GameOfLifeGrid fullGrid() {
        return createGrid(ALIVE);
    }

    private static GameOfLifeGrid createGrid(CellState state) {
        CellState[][] grid = new CellState[3][3];
        for (int column = 0; column < 3; column++) {
            Arrays.fill(grid[column], state);
        }
        return new GameOfLifeGrid(grid);
    }

    public CellState cellAt(int column, int row) {
        return grid[column][row];
    }

    public void makeAlive(int column, int row) {
        grid[column][row] = ALIVE;
    }

    public int countNeighbour(Position position) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (!(i == 0 && j== 0) && cellAt(position.getColumn() +i, position.getRow() +j) == ALIVE) {
                    count++;
                }
            }
        }
        return count;
    }
}
