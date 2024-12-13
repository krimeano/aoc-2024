package krimeano.aoc2024.days.day13;

import krimeano.aoc2024.days.my_lib.SolveDay;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class Day13x2Test {
    protected static final String TEST_INPUT = """
            Button A: X+94, Y+34
            Button B: X+22, Y+67
            Prize: X=8400, Y=5400

            Button A: X+26, Y+66
            Button B: X+67, Y+21
            Prize: X=12748, Y=12176

            Button A: X+17, Y+86
            Button B: X+84, Y+37
            Prize: X=7870, Y=6450

            Button A: X+69, Y+23
            Button B: X+27, Y+71
            Prize: X=18641, Y=10279
            """;

    @Test
    void solve() {
        SolveDay solution = new Day13x2(true);
        assertEquals(0, solution.solve("""
                Button A: X+94, Y+34
                Button B: X+22, Y+67
                Prize: X=8400, Y=5400
                """));

        assertNotEquals(0, solution.solve("""
                Button A: X+26, Y+66
                Button B: X+67, Y+21
                Prize: X=12748, Y=12176
                """));

        assertEquals(0, solution.solve("""
                Button A: X+17, Y+86
                Button B: X+84, Y+37
                Prize: X=7870, Y=6450
                """));

        assertNotEquals(0, solution.solve("""
                Button A: X+69, Y+23
                Button B: X+27, Y+71
                Prize: X=18641, Y=10279
                """));

    }
}
