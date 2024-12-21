package krimeano.aoc2024.days.day20;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day20x1Test {
    protected static final String TEST_INPUT = """
            ###############
            #...#...#.....#
            #.#.#.#.#.###.#
            #S#...#.#.#...#
            #######.#.#.###
            #######.#.#...#
            #######.#.###.#
            ###..E#...#...#
            ###.#######.###
            #...###...#...#
            #.#####.#.###.#
            #.#...#.#.#...#
            #.#.#.#.#.#.###
            #...#...#...###
            ###############
            """;
    protected static final int[][] TEST_CASES = {
            {14, 2},
            {14, 4},
            {2, 6},
            {4, 8},
            {2, 10},
            {3, 12},
            {1, 20},
            {1, 36},
            {1, 38},
            {1, 40},
            {1, 64},
    };

    @Test
    void solve() {
        int sum = 0;
        for (int[] testCase : TEST_CASES) {
            sum += testCase[0];
        }
        assertEquals(sum, new Day20x1(true).solve(TEST_INPUT, 2));
    }
}
