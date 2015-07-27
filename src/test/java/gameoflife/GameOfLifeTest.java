package gameoflife;

import org.junit.Test;

import static gameoflife.CellState.ALIVE;
import static gameoflife.CellState.DEAD;
import static org.assertj.core.api.Assertions.assertThat;

public class GameOfLifeTest {

    /**
     * The universe of the Game of Life is an infinite two-dimensional orthogonal grid of square cells,
     * each of which is in one of two possible states, alive or dead.
     * Every cell interacts with its eight neighbours, which are the cells that are
     * horizontally, vertically, or diagonally adjacent.
     * At each step in time, the following transitions occur:
     * <p>
     * Any live cell with fewer than two live neighbours dies, as if caused by under-population.
     * Any live cell with two or three live neighbours lives on to the next generation.
     * Any live cell with more than three live neighbours dies, as if by overcrowding.
     * Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
     */

    //Any live cell with fewer than two live neighbours dies, as if caused by under-population.
    @Test
    public void a_live_cell_with_0_neighbor_dies_on_next_generation() {
        assertThat(ALIVE.nextGeneration(0)).isEqualTo(DEAD);
    }

    @Test
    public void a_live_cell_with_1_neighbor_dies_on_next_generation() {
        assertThat(ALIVE.nextGeneration(1)).isEqualTo(DEAD);
    }

    //Any live cell with two or three live neighbours lives on to the next generation
    @Test
    public void a_live_cell_with_2_neighbour_is_alive_on_next_generation() {
        assertThat(ALIVE.nextGeneration(2)).isEqualTo(ALIVE);
    }

    @Test
    public void a_live_cell_with_3_neighbour_is_alive_on_next_generation() {
        assertThat(ALIVE.nextGeneration(3)).isEqualTo(ALIVE);
    }

    //Any live cell with more than three live neighbours dies, as if by overcrowding.
    @Test
    public void a_live_cell_with_4_neighbour_is_dead_on_next_generation() {
        assertThat(ALIVE.nextGeneration(4)).isEqualTo(DEAD);
    }

    @Test
    public void a_live_cell_with_5_neighbour_is_dead_on_next_generation() {
        assertThat(ALIVE.nextGeneration(5)).isEqualTo(DEAD);
    }

    //Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
    @Test
    public void a_dead_cell_with_3_neighbour_is_alive_on_next_generation() {
        assertThat(DEAD.nextGeneration(3)).isEqualTo(ALIVE);
    }

    //a dead cell with 2 neighbour stays dead
    @Test
    public void a_dead_cell_with_2_neighbour_is_dead_on_next_generation() {
        assertThat(DEAD.nextGeneration(2)).isEqualTo(DEAD);
    }

    //count neighbour of a cell in a grid
    @Test
    public void a_cell_in_an_empty_grid_has_0_neighbor() {
        assertThat(GameOfLifeGrid.emptyGrid(3).neighbourCount(1, 1)).isEqualTo(0);
    }

    @Test
    public void a_cell_with_horizontal_line_of_neighbor_has_2_neighbor() {
        GameOfLifeGrid grid = GameOfLifeGrid.emptyGrid(3);
        grid.makeAlive(0,1);
        grid.makeAlive(2,1);
        assertThat(grid.neighbourCount(1, 1)).isEqualTo(2);
    }

    @Test
    public void a_cell_with_vertical_line_of_neighbor_has_2_neighbor() {
        GameOfLifeGrid grid = GameOfLifeGrid.emptyGrid(3);
        grid.makeAlive(1,0);
        grid.makeAlive(1,2);
        assertThat(grid.neighbourCount(1, 1)).isEqualTo(2);
    }

    @Test
    public void a_cell_with_a_full_grid_has_8_neighbor() {
        GameOfLifeGrid grid = GameOfLifeGrid.fullGrid();
        assertThat(grid.neighbourCount(1, 1)).isEqualTo(8);
    }

    //passing generation
    @Test
    public void is_game_of_life_blink() {
        GameOfLifeGrid grid = GameOfLifeGrid.emptyGrid(5);
        grid.makeAlive(2,1);
        grid.makeAlive(2,2);
        grid.makeAlive(2, 3);

        GameOfLifeGrid newGen = grid.nextGeneration();

        assertThat(newGen.cellAt(2,1)).isEqualTo(DEAD);
        assertThat(newGen.cellAt(1,2)).isEqualTo(ALIVE);

        assertThat(newGen.cellAt(2,2)).isEqualTo(ALIVE);

        assertThat(newGen.cellAt(2,3)).isEqualTo(DEAD);
        assertThat(newGen.cellAt(3, 2)).isEqualTo(ALIVE);
    }

    @Test
    public void is_game_of_life_stay_the_same() {
        GameOfLifeGrid grid = GameOfLifeGrid.emptyGrid(5);
        grid.makeAlive(2,1);
        grid.makeAlive(1,2);
        grid.makeAlive(1,1);
        grid.makeAlive(2,2);

        GameOfLifeGrid newGen = grid.nextGeneration();

        assertThat(newGen.cellAt(2,1)).isEqualTo(ALIVE);
        assertThat(newGen.cellAt(1,2)).isEqualTo(ALIVE);

        assertThat(newGen.cellAt(1,1)).isEqualTo(ALIVE);
        assertThat(newGen.cellAt(2,2)).isEqualTo(ALIVE);
    }


}
