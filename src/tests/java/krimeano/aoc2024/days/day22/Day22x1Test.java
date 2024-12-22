package krimeano.aoc2024.days.day22;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day22x1Test {
    protected static final String TEST_INPUT = """
            1
            10
            100
            2024
            """;

    @Test
    void solve() {
        assertEquals(37327623, new Day22x1(true).solve(TEST_INPUT));
    }
}
