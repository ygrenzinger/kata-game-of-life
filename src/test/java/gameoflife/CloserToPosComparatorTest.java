package gameoflife;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.StrictAssertions.assertThat;

public class CloserToPosComparatorTest {

    @Test
    public void sort_positions_relative_to_a_position() {
        List<Position> positions = new ArrayList<>();
        positions.add(new Position(-3, 1));
        positions.add(new Position(0, 0));
        positions.add(new Position(0, 2));

        positions.sort(new CloserToPosComparator(new Position(0, 0)));

        assertThat(positions.get(0)).isEqualTo(new Position(0, 0));
        assertThat(positions.get(1)).isEqualTo(new Position(0, 2));
        assertThat(positions.get(2)).isEqualTo(new Position(-3, 1));
    }

}