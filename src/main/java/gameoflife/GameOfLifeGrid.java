package gameoflife;

import java.util.ArrayList;
import java.util.List;

import static gameoflife.CellState.ALIVE;
import static gameoflife.CellState.DEAD;

public class GameOfLifeGrid {


    private final CellState[][] grid;

    private GameOfLifeGrid(CellState[][] grid) {
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

    public void makeAlive(Position position) {
        grid[position.getRow()][position.getColumn()] = ALIVE;
    }

    public CellState cellAt(Position position) {
        return grid[position.getRow()][position.getColumn()];
    }

    public static GameOfLifeGrid fullGrid() {
        return createGrid(ALIVE, 3);
    }



    public long neighbourCount(Position position) {
        return buildNeigbours(position.getRow(), position.getColumn()).stream().filter(cellState -> cellState == ALIVE).count();
    }

    private List<CellState> buildNeigbours(int row, int column) {
        List<CellState> cells = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (!(i == 0 && j == 0)) {
                    cells.add(grid[row+i][column+j]);
                }
            }
        }
        return cells;
    }

    public GameOfLifeGrid nextGeneration() {
        GameOfLifeGrid newGen = copy();
        for (int i = 1; i < grid.length-1; i++) {

            for (int j = 1; j < grid.length-1; j++) {
                long count =   this.neighbourCount(new Position(i, j));
                newGen.grid[i][j] = this.grid[i][j].nextGeneration(count);
            }
        }
        return newGen;
    }

    private GameOfLifeGrid copy() {
        GameOfLifeGrid copy = emptyGrid(grid.length);
        copyRows(copy);
        return copy;
    }

    private void copyRows(GameOfLifeGrid copy) {
        for (int i = 0; i < grid.length; i++) {
            copyColumns(copy, i);
        }
    }

    private void copyColumns(GameOfLifeGrid copy, int row) {
        System.arraycopy(grid[row], 0, copy.grid[row], 0, grid[row].length);
    }
}
