package krimeano.aoc2024.days.day12;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day12x1Test {

    protected static final String TEST_INPUT_1 = """
            AAAA
            BBCD
            BBCC
            EEEC
            """;

    protected static final String TEST_INPUT_2 = """
            OOOOO
            OXOXO
            OOOOO
            OXOXO
            OOOOO
            """;

    protected static final String TEST_INPUT_3 = """
            RRRRIICCFF
            RRRRIICCCF
            VVRRRCCFFF
            VVRCCCJFFF
            VVVVCJJCFE
            VVIVCCJJEE
            VVIIICJJEE
            MIIIIIJJEE
            MIIISIJEEE
            MMMISSJEEE
            """;

    @Test
    void solve() {
        Day12x1 solution = new Day12x1(true);
        assertEquals(140, solution.solve(TEST_INPUT_1));
        assertEquals(772, solution.solve(TEST_INPUT_2));
        assertEquals(1930, solution.solve(TEST_INPUT_3));
    }
}
