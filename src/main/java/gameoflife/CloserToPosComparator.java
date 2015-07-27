package gameoflife;

import java.util.Comparator;

public class CloserToPosComparator implements Comparator<Position> {

    public final Position position;

    public CloserToPosComparator(Position position) {
        this.position = position;
    }

    @Override
    public int compare(Position o1, Position o2) {
        return o1.distanceTo(position) - o2.distanceTo(position);
    }
}
