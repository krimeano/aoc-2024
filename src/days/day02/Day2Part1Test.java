package days.day02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day2Part1Test {
    String TEST_INPUT = """
            7 6 4 2 1
            1 2 7 8 9
            9 7 6 2 1
            1 3 2 4 5
            8 6 4 4 1
            1 3 6 7 9
            """;

    @Test
    void solve() {
        assertEquals(2, new Day2Part1(true).solve(TEST_INPUT));
    }
}