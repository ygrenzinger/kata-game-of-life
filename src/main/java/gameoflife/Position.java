package gameoflife;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Position {
    private final int row;
    private final int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public List<Position> buildNeigbours() {
        List<Position> cells = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (!(i == 0 && j == 0)) {
                    cells.add(new Position(row + i, column + j));
                }
            }
        }
        return cells;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(row, position.row) &&
                Objects.equals(column, position.column);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public String toString() {
        return "Position{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }

    public int distanceTo(Position position) {
        int distColumn = Math.abs(this.column - position.column);
        int distRow = Math.abs(this.row - position.row);
        return Math.max(distColumn, distRow);
    }
}
