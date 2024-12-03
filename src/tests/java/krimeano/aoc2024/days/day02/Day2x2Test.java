package krimeano.aoc2024.days.day02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day2x2Test {
    String TEST_INPUT = """
            1 2 3 4 5
            1 2 3 4 9
            1 2 3 9 5
            1 2 9 4 5
            1 9 3 4 5
            9 2 3 4 5
            1 2 3 8 9
            1 2 8 9 5
            1 9 0 4 5
            8 9 3 4 5
            1 2 8 4 9
            7 6 4 2 1
            1 2 7 8 9
            9 7 6 2 1
            1 3 2 4 5
            8 6 4 4 1
            1 3 6 7 9
            """;

    @Test
    void solve() {
        assertEquals(10, new Day2x2(true).solve(TEST_INPUT));
    }
}
