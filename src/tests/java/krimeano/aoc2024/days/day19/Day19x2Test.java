package krimeano.aoc2024.days.day19;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day19x2Test {
    protected static final String TEST_INPUT = """
            r, wr, b, g, bwu, rb, gb, br

            brwrr
            bggr
            gbbr
            rrbgbr
            ubwu
            bwurrg
            brgr
            bbrgwb
            """;

    @Test
    void solve() {
        assertEquals(16, new Day19x2(true).solve(TEST_INPUT));
    }
}
