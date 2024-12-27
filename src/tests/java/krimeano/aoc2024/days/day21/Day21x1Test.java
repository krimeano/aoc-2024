package krimeano.aoc2024.days.day21;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day21x1Test {
    protected static final String TEST_INPUT = """
            029A
            980A
            179A
            456A
            379A
            """;

    @Test
    void solve() {
        assertEquals(126384, new Day21x1(true).solve(TEST_INPUT));
    }
}
