package gameoflife;

/**
 * Created by yannickgrenzinger on 27/07/2015.
 */
public enum CellState {

    DEAD, ALIVE;


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
