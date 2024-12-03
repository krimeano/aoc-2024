package krimeano.aoc2024.days.day03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day3x2Test {
    String TEST_INPUT = """
            xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))
            """;

    @Test
    void solve() {
        assertEquals(48, new Day3x2(true).solve(TEST_INPUT));
    }
}
