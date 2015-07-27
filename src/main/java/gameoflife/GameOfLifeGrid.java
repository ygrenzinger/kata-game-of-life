package gameoflife;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static gameoflife.CellState.ALIVE;
import static gameoflife.CellState.DEAD;

public class GameOfLifeGrid {


    private final HashSet<Position> aliveCells;

    private GameOfLifeGrid(HashSet<Position> aliveCells) {
        this.aliveCells = aliveCells;
    }

    public void makeAlive(Position position) {
        aliveCells.add(position);
    }

    public CellState cellStateAt(Position position) {
        return aliveCells.contains(position) ? ALIVE : DEAD;
    }

    public static GameOfLifeGrid emptyGrid() {
        return new GameOfLifeGrid(new HashSet<>());
    }

    public static GameOfLifeGrid fullGrid(int size) {
        HashSet<Position> cells = new HashSet<>();
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                cells.add(new Position(row, column));
            }
        }
        return new GameOfLifeGrid(cells);
    }

    public long neighbourCount(Position position) {
        return aliveCells.stream().sorted(new CloserToPosComparator(position))
                .filter(p -> !p.equals(position))
                .filter(p -> p.distanceTo(position) == 1).count();
    }

    public GameOfLifeGrid nextGeneration() {
        GameOfLifeGrid newGen = emptyGrid();

        for (Position position : aliveCells) {
            long count = neighbourCount(position);
            if (ALIVE.nextGeneration(count) == ALIVE) {
                newGen.makeAlive(position);
            }
            if (count == 2) {
                List<Position> ressurectedCells = position.buildNeigbours().stream()
                        .filter(p -> !aliveCells.contains(p))
                        .filter(p -> {
                            long nb = neighbourCount(p);
                            return nb == 3;
                        }).collect(Collectors.toList());
                ressurectedCells.stream().forEach(newGen::makeAlive);
            }
        }

        return newGen;
    }
}
