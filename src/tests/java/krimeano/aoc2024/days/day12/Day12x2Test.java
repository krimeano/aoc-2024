package krimeano.aoc2024.days.day12;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day12x2Test extends Day12x1Test {
    protected static final String TEST_INPUT_4 = """
            EEEEE
            EXXXX
            EEEEE
            EXXXX
            EEEEE
            """;

    protected static final String TEST_INPUT_5 = """
            AAAAAA
            AAABBA
            AAABBA
            ABBAAA
            ABBAAA
            AAAAAA
            """;

    @Test
    void solve() {
        Day12x1 solution = new Day12x2(true);
        assertEquals(80, solution.solve(TEST_INPUT_1));
        assertEquals(436, solution.solve(TEST_INPUT_2));
        assertEquals(236, solution.solve(TEST_INPUT_4));
        assertEquals(368, solution.solve(TEST_INPUT_5));
    }
}
