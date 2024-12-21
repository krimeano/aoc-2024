package krimeano.aoc2024.days.day20;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day20x2Test extends Day20x1Test{
    protected static final int[][] TEST_CASES = {
            {32, 50},
            {31, 52},
            {29, 54},
            {39, 56},
            {25, 58},
            {23, 60},
            {20, 62},
            {19, 64},
            {12, 66},
            {14, 68},
            {12, 70},
            {22, 72},
            {4, 74},
            {3, 76}
    };

    @Test
    void solve() {
        int sum = 0;
        for (int[] testCase : TEST_CASES) {
            sum += testCase[0];
        }
        assertEquals(sum, new Day20x2(true).solve(TEST_INPUT, 50));
    }
}
