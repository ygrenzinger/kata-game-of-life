package gameoflife;

import org.junit.Test;

import static gameoflife.CellState.ALIVE;
import static gameoflife.CellState.DEAD;
import static gameoflife.GameOfLifeGrid.emptyGrid;
import static gameoflife.GameOfLifeGrid.fullGrid;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by yannickgrenzinger on 25/07/2015.
 */
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
    public void a_cell_with_0_neighbor_is_dead_on_next_generation() {
        assertThat(ALIVE.nextGeneration(0)).isEqualTo(DEAD);
    }

    @Test
    public void a_cell_with_1_neighbor_is_dead_on_next_generation() {
        assertThat(ALIVE.nextGeneration(1)).isEqualTo(DEAD);
    }

    //Any live cell with two or three live neighbours lives on to the next generation.
    @Test
    public void a_cell_with_2_neighbour_is_alive_on_next_generation() {
        assertThat(ALIVE.nextGeneration(2)).isEqualTo(ALIVE);
    }

    @Test
    public void a_cell_with_3_neighbour_is_alive_on_next_generation() {
        assertThat(ALIVE.nextGeneration(3)).isEqualTo(ALIVE);
    }

    //Any live cell with more than three live neighbours dies, as if by overcrowding.
    @Test
    public void a_cell_with_4_neighbour_is_dead_on_next_generation() {
        assertThat(ALIVE.nextGeneration(4)).isEqualTo(DEAD);
    }

    //Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction
    @Test
    public void a_dead_cell_with_3_neighbour_is_alive_on_next_generation() {
        assertThat(DEAD.nextGeneration(3)).isEqualTo(ALIVE);
    }

    //Any dead cell with two neighbours stays dead on to the next generation
    @Test
    public void a_dead_cell_with_2_neighbour_is_dead_on_next_generation() {
        assertThat(DEAD.nextGeneration(2)).isEqualTo(DEAD);
    }

    //Count the number of neighbor of a cell
    @Test
    public void a_cell_in_an_empty_grid_has_no_neighbor() {
        assertThat(emptyGrid(3).numberOfNeighbour(1, 1)).isEqualTo(0);
    }

    @Test
    public void a_cell_with_a_horizontal_line_of_neighbour_has_2_neighbour() {
        GameOfLifeGrid gameOfLifeGrid = emptyGrid(3);
        gameOfLifeGrid.makeAlive(1, 0);
        gameOfLifeGrid.makeAlive(1, 2);
        assertThat(gameOfLifeGrid.numberOfNeighbour(1, 1)).isEqualTo(2);
    }

    @Test
    public void a_cell_with_a_vertical_line_of_neighbour_has_2_neighbour() {
        GameOfLifeGrid gameOfLifeGrid = emptyGrid(3);
        gameOfLifeGrid.makeAlive(0, 1);
        gameOfLifeGrid.makeAlive(2, 1);
        assertThat(gameOfLifeGrid.numberOfNeighbour(1, 1)).isEqualTo(2);
    }

    @Test
    public void a_cell_with_a_diagonal_line_of_neighbour_has_2_neighbour() {
        GameOfLifeGrid gameOfLifeGrid = emptyGrid(3);
        gameOfLifeGrid.makeAlive(0, 0);
        gameOfLifeGrid.makeAlive(2, 2);
        assertThat(gameOfLifeGrid.numberOfNeighbour(1, 1)).isEqualTo(2);
    }

    @Test
    public void a_cell_with_full_grid_has_8_neighbour() {
        GameOfLifeGrid gameOfLifeGrid = fullGrid(3);
        assertThat(gameOfLifeGrid.numberOfNeighbour(1, 1)).isEqualTo(8);
    }

    //move on generation with block
    @Test
    public void a_block_of_cells_stays_the_same_on_next_generation() {
        GameOfLifeGrid gameOfLifeGrid = emptyGrid(6);
        gameOfLifeGrid.makeAlive(2, 2);
        gameOfLifeGrid.makeAlive(2, 3);
        gameOfLifeGrid.makeAlive(3, 3);
        gameOfLifeGrid.makeAlive(3, 2);

        gameOfLifeGrid.nextGeneration();

        assertThat(gameOfLifeGrid.cellAt(2, 2)).isEqualTo(ALIVE);
        assertThat(gameOfLifeGrid.cellAt(2, 3)).isEqualTo(ALIVE);
        assertThat(gameOfLifeGrid.cellAt(3, 3)).isEqualTo(ALIVE);
        assertThat(gameOfLifeGrid.cellAt(3, 2)).isEqualTo(ALIVE);

    }
    @Test
    public void a_block_of_3_cells_in_line_blink() {
        GameOfLifeGrid gameOfLifeGrid = emptyGrid(3);
        gameOfLifeGrid.makeAlive(1, 0);
        gameOfLifeGrid.makeAlive(1, 1);
        gameOfLifeGrid.makeAlive(1, 2);

        GameOfLifeGrid nextGeneration = gameOfLifeGrid.nextGeneration();

        assertThat(nextGeneration.cellAt(1, 0)).isEqualTo(DEAD);
        assertThat(nextGeneration.cellAt(1, 2)).isEqualTo(DEAD);

        assertThat(nextGeneration.cellAt(1, 1)).isEqualTo(ALIVE);

        assertThat(nextGeneration.cellAt(0, 1)).isEqualTo(ALIVE);
        assertThat(nextGeneration.cellAt(2, 1)).isEqualTo(ALIVE);

    }

}
