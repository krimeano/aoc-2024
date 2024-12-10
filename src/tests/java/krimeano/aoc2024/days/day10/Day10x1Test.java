package krimeano.aoc2024.days.day10;

import krimeano.aoc2024.days.my_lib.SolveDay;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day10x1Test {
    String TEST_DATA_0 = """
            0123
            1234
            8765
            9876
            """;

    String TEST_DATA_4 = """
            89010123
            78121874
            87430965
            96549874
            45678903
            32019012
            01329801
            10456732
            """;

    @Test
    void solve() {
        SolveDay solution = new Day10x1(true);
        assertEquals(1, solution.solve(TEST_DATA_0));
        assertEquals(36, solution.solve(TEST_DATA_4));
    }
}
