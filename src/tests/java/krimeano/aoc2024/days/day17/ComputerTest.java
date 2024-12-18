package krimeano.aoc2024.days.day17;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * If register C contains 9, the program 2,6 would set register B to 1.
 * If register A contains 10, the program 5,0,5,1,5,4 would output 0,1,2.
 * If register A contains 2024, the program 0,1,5,4,3,0 would output 4,2,5,6,7,7,7,7,3,1,0 and leave 0 in register A.
 * If register B contains 29, the program 1,7 would set register B to 26.
 * If register B contains 2024 and register C contains 43690, the program 4,0 would set register B to 44354.
 */
class ComputerTest {

    @Test
    void run() {
        Computer computer = new Computer(true);
        {
            computer.set(0, 0, 9)
                    .run("2,6");
            assertEquals(1, computer.registerB);
        }

        {
            String actual = computer
                    .set(10, 0, 0)
                    .run("5,0,5,1,5,4");
            assertEquals("0,1,2", actual);
        }

        {
            String actual = computer
                    .set(2024, 0, 9)
                    .run("0,1,5,4,3,0");
            assertEquals("4,2,5,6,7,7,7,7,3,1,0", actual);
            assertEquals(0, computer.registerA);
        }

        {
            computer.set(0, 29, 0)
                    .run("1,7");
            assertEquals(26, computer.registerB);
        }

        {
            computer.set(0, 2024, 43690)
                    .run("4,0");
            assertEquals(44354, computer.registerB);
        }

    }
}
