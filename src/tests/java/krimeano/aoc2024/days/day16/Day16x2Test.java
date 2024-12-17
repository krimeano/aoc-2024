package krimeano.aoc2024.days.day16;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day16x2Test extends Day16x1Test{
    @Test
    void solve() {
        assertEquals(45, new Day16x2(true).solve(TEST_INPUT_1));
        assertEquals(64, new Day16x2(true).solve(TEST_INPUT_2));
    }
}
