package gameoflife;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PositionTest {

    @Test
    public void position_distance_to_another_position() {
        Position pos = new Position(0, 0);
        assertThat(pos.distanceTo(new Position(0, 0))).isEqualTo(0);
        assertThat(pos.distanceTo(new Position(0, 1))).isEqualTo(1);
        assertThat(pos.distanceTo(new Position(1, 1))).isEqualTo(1);
        assertThat(pos.distanceTo(new Position(1, 0))).isEqualTo(1);
        assertThat(pos.distanceTo(new Position(3, 1))).isEqualTo(3);
    }


}