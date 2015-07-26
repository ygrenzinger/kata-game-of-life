package gameoflife;

public enum CellState {
    ALIVE, DEAD;

    public CellState nextGeneration(int nbOfNeighbors) {
        if (nbOfNeighbors == 2) {
            return this;
        }
        if (nbOfNeighbors == 3) {
            return ALIVE;
        }
        return DEAD;
    }
}
