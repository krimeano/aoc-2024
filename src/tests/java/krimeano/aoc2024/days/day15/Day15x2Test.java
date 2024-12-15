package krimeano.aoc2024.days.day15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day15x2Test extends Day15x1Test {

    @Test
    void solve() {
        assertEquals(9021, new Day15x2(true).solve(TEST_INPUT));
    }
}
