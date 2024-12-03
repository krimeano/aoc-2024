package krimeano.aoc2024.days.day03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day3x1Test {
    String TEST_INPUT = """
            xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))
            """;

    @Test
    void solve() {
        assertEquals(161, new Day3x1(true).solve(TEST_INPUT));
    }
}
