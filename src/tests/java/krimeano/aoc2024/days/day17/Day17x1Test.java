package krimeano.aoc2024.days.day17;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day17x1Test {
    protected static final String TEST_INPUT = """
            Register A: 729
            Register B: 0
            Register C: 0
                        
            Program: 0,1,5,4,3,0
            """;

    @Test
    void solveString() {
        assertEquals("4,6,3,5,6,3,5,2,1,0", new Day17x1(true).solveString(TEST_INPUT));
    }
}
