package krimeano.aoc2024.days.day22;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day22x2Test {
    protected static final String TEST_INPUT = """
            1
            2
            3
            2024
            """;

    @Test
    void solve() {
        assertEquals(23, new Day22x2(true).solve(TEST_INPUT));
    }
}
