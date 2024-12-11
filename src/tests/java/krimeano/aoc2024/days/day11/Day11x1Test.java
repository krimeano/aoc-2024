package krimeano.aoc2024.days.day11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day11x1Test {

    @Test
    void getNumberOfStones() {
        Day11x1 solution = new Day11x1(true);

        assertEquals(1, solution.getNumberOfStones("0", 1));
        assertEquals(1, solution.getNumberOfStones("1", 1));
        assertEquals(2, solution.getNumberOfStones("10", 1));
        assertEquals(2, solution.getNumberOfStones("99", 1));
        assertEquals(1, solution.getNumberOfStones("999", 1));


        assertEquals(2, solution.getNumberOfStones("17", 1));
        assertEquals(2, solution.getNumberOfStones("17", 2));
        assertEquals(3, solution.getNumberOfStones("17", 3));
        assertEquals(6, solution.getNumberOfStones("17", 4));
        assertEquals(8, solution.getNumberOfStones("17", 5));
        assertEquals(15, solution.getNumberOfStones("17", 6));

        assertEquals(1, solution.getNumberOfStones("125", 1));
        assertEquals(2, solution.getNumberOfStones("125", 2));
        assertEquals(2, solution.getNumberOfStones("125", 3));
        assertEquals(3, solution.getNumberOfStones("125", 4));
        assertEquals(5, solution.getNumberOfStones("125", 5));
        assertEquals(7, solution.getNumberOfStones("125", 6));

        assertEquals(55312, solution.getNumberOfStones("125", Day11x1.TIMES) + solution.getNumberOfStones("17", Day11x1.TIMES));
    }

    @Test
    void solve() {
        assertEquals(55312, new Day11x1(true).solve("""
                125 17
                """));
    }
}
