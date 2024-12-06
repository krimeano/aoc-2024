package krimeano.aoc2024.days.day06;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day6x2Test {
    String TEST_INPUT = """
            ....#.....
            .........#
            ..........
            ..#.......
            .......#..
            ..........
            .#..^.....
            ........#.
            #.........
            ......#...
            """;

    @Test
    void solve() {
        assertEquals(6, new Day6x2(true).solve(TEST_INPUT));
    }
}
