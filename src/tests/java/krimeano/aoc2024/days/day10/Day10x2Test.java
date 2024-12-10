package krimeano.aoc2024.days.day10;

import krimeano.aoc2024.days.my_lib.SolveDay;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day10x2Test extends Day10x1Test {
    @Test
    void solve() {
        SolveDay solution = new Day10x2(true);
        assertEquals(81, solution.solve(TEST_DATA_4));
    }
}
