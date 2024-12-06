package krimeano.aoc2024.days.day06;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day6x1Test {
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
        assertEquals(41, new Day6x1(true).solve(TEST_INPUT));
    }
}
