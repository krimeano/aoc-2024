package krimeano.aoc2024.days.day07;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day7x2Test extends Day7x1Test {
    @Test
    void solve() {
        assertEquals(11387, new Day7x2(true).solve(TEST_INPUT));
    }
}
