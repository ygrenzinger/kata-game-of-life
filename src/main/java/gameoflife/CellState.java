package gameoflife;

public enum CellState {
    ALIVE, DEAD;

    public CellState nextGeneration(int nbOfNeighbor) {
        if (nbOfNeighbor == 2) {
            return this;
        }
        if (nbOfNeighbor == 3) {
            return ALIVE;
        }
        return DEAD;
    }
}
