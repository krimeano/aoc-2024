package krimeano.aoc2024.days.day09;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day9x1Test {
    protected static final String TEST_INPUT = """
            2333133121414131402
            """;

    @Test
    void solve() {
        assertEquals(1928, new Day9x1(true).solve(TEST_INPUT));
    }
}
