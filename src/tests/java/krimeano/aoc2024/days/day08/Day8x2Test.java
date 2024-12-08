package krimeano.aoc2024.days.day08;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day8x2Test extends Day8x1Test{
    @Test
    void solve() {
        assertEquals(34, new Day8x2(true).solve(TEST_INPUT));
    }
}
