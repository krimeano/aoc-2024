package days.day00;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day0Part1Test {
    @Test
    void solve() {
        assertEquals(-2, new Day0Part1(true).solve("1 2\n3 4\n"));
        assertEquals(100, new Day0Part1(true).solve("10 0\n0 10\n"));
        assertEquals(1, new Day0Part1(true).solve("1 1 1\n0 1 1\n0 0 1\n"));
    }
}
