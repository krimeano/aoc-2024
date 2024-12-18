package krimeano.aoc2024.days.day18;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day18x1Test {
    protected static final String TEST_INPUT = """
            5,4
            4,2
            4,5
            3,0
            2,1
            6,3
            2,4
            1,5
            0,6
            3,3
            2,6
            5,1
            """;
    @Test
    void solve() {
        assertEquals(22, new Day18x1(true).solve(TEST_INPUT));
    }
}
