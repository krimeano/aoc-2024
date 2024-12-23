package krimeano.aoc2024.days.day23;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day23x2Test extends Day23x1Test {
    @Test
    void solve() {
        assertEquals("co,de,ka,ta", new Day23x2(true).solveSting(TEST_INPUT));
    }
}
