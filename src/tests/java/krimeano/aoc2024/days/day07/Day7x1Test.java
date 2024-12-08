package krimeano.aoc2024.days.day07;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day7x1Test {
    protected static final String TEST_INPUT = """
            190: 10 19
            3267: 81 40 27
            83: 17 5
            156: 15 6
            7290: 6 8 6 15
            161011: 16 10 13
            192: 17 8 14
            21037: 9 7 18 13
            292: 11 6 16 20
            """;

    @Test
    void solve() {
        assertEquals(3749, new Day7x1(true).solve(TEST_INPUT));
    }
}
