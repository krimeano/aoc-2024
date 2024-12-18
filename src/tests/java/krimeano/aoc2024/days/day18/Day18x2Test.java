package krimeano.aoc2024.days.day18;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day18x2Test {
    protected static final String TEST_INPUT = """
            5,4
            4,2
            4,5
            3,0
            2,1
            6,3
            2,4
            1,5
            0,6
            3,3
            2,6
            5,1
            1,2
            5,5
            2,5
            6,5
            1,4
            0,4
            6,4
            1,1
            6,1
            1,0
            0,5
            1,6
            2,0
            """;

    @Test
    void solve() {
        assertEquals(Arrays.asList((byte) 1, (byte) 6), new Day18x2(true).solveList(TEST_INPUT, 12));
    }
}
