package krimeano.aoc2024.days.day09;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day9x2Test extends Day9x1Test {

    @Test
    void solve() {
        assertEquals(2858, new Day9x2(true).solve(TEST_INPUT));
    }
}
