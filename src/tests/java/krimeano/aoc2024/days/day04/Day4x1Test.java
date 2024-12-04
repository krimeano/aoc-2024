package krimeano.aoc2024.days.day04;

import krimeano.aoc2024.days.day03.Day3x2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day4x1Test {
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
        assertEquals(18, new Day4x1(true).solve(TEST_INPUT));
    }

}
