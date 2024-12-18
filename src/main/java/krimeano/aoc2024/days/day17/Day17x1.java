package krimeano.aoc2024.days.day17;

import krimeano.aoc2024.days.my_lib.SolveDay;

public class Day17x1 extends SolveDay {
    public Day17x1(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        System.out.println(this.solveString(textInput));
        return 0;
    }

    public String solveString(String textInput) {
        int registerA = 0;
        int registerB = 0;
        int registerC = 0;
        String program = "";
        for (String line : getLines(textInput)) {
            String[] parts = line.split(": ");
            switch (parts[0]) {
                case "Register A":
                    registerA = Integer.parseInt(parts[1]);
                    break;
                case "Register B":
                    registerB = Integer.parseInt(parts[1]);
                    break;
                case "Register C":
                    registerC = Integer.parseInt(parts[1]);
                    break;
                case "Program":
                    program = parts[1];
            }
        }
        return new Computer(verbose).set(registerA, registerB, registerC).run(program);
    }
}
