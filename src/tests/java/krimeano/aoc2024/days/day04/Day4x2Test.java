package krimeano.aoc2024.days.day04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day4x2Test {
    String TEST_INPUT = """
            MMMSXXMASM
            MSAMXMSMSA
            AMXSXMAAMM
            MSAMASMSMX
            XMASAMXAMM
            XXAMMXXAMA
            SMSMSASXSS
            SAXAMASAAA
            MAMMMXMMMM
            MXMXAXMASX
            """;

    @Test
    void solve() {
        assertEquals(9, new Day4x2(true).solve(TEST_INPUT));
    }

}
