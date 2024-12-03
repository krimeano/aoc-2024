package krimeano.aoc2024.days.day01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day1x1Test {
    String TEST_INPUT = """
            3   4
            4   3
            2   5
            1   3
            3   9
            3   3
            """;

    @Test
    void solve() {
        assertEquals(11, new Day1x1(true).solve(TEST_INPUT));
    }
}
