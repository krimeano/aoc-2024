package krimeano.aoc2024.days.day17;

import java.util.ArrayList;

public class Computer {
    public long registerA = 0;
    public long registerB = 0;
    public long registerC = 0;
    public String program = "";
    protected ArrayList<Long> output = new ArrayList<>();
    protected byte cursor = 0;
    protected boolean verbose = false;
    protected int steps = 0;

    public Computer() {
    }

    public Computer(boolean verbose) {
        this.verbose = verbose;
    }

    public Computer set(int registerA, int registerB, int registerC) {
        if (verbose) {
            System.out.println();
            System.out.println("Setting: " + registerA + ", " + registerB + ", " + registerC);
        }
        this.registerA = registerA;
        this.registerB = registerB;
        this.registerC = registerC;
        return this;
    }


    public String run(String input) {
        program = input;
        output = new ArrayList<>();
        cursor = 0;
        steps = 0;

        if (verbose) {
            System.out.println();
            System.out.println("Program: " + program);
        }

        while (cursor < program.length()) {
            nextStep();
        }

        System.out.println();
        return printOutput();
    }

    protected void nextStep() {
        if (verbose) {
            System.out.println("Step: " + steps);
            System.out.println(program);
            for (int i = 0; i < cursor; i++) {
                System.out.print(" ");
            }
            System.out.println("^_^");
            System.out.println("abc: " + registerA + ", " + registerB + ", " + registerC);
        }
        char opcode = program.charAt(cursor);
        cursor += 2;
        byte arg = Byte.parseByte(program.charAt(cursor) + "");
        cursor += 2;
        if (verbose) {
            System.out.println("optcode = " + opcode + "; arg = " + arg);
        }
        switch (opcode) {
            case '0' -> adv(arg);
            case '1' -> bxl(arg);
            case '2' -> bst(arg);
            case '3' -> jnz(arg);
            case '4' -> bxc(arg);
            case '5' -> out(arg);
            case '6' -> bdv(arg);
            case '7' -> cdv(arg);
        }
        System.out.println("abc: " + registerA + ", " + registerB + ", " + registerC);
        steps++;
    }

    protected String printOutput() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < output.size(); i++) {
            if (i > 0) {
                result.append(',');
            }
            result.append(output.get(i));
        }

        return result.toString();
    }

    protected long combo(byte arg) {
        long value = switch (arg) {
            case 0, 1, 2, 3 -> arg;
            case 4 -> registerA;
            case 5 -> registerB;
            case 6 -> registerC;
            default -> 0;
        };

        if (verbose) {
            System.out.println("        combo(" + arg + ") = " + value);
        }
        return value;
    }

    /* opcode 0 */
    protected void adv(byte arg) {
        if (verbose) {
            System.out.println("    adv(" + arg + ")");
        }
        long denominator = 1L << combo(arg);
        if (verbose) {
            System.out.println("            denominator = " + denominator);
        }
        registerA = registerA / denominator;
    }

    /* opcode 1 */
    protected void bxl(byte arg) {
        if (verbose) {
            System.out.println("    bxl(" + arg + ")");
        }
        registerB = registerB ^ (long) arg;
    }

    /* opcode 2 */
    protected void bst(byte arg) {
        if (verbose) {
            System.out.println("    bst(" + arg + ")");
        }
        registerB = combo(arg) % 8;
    }

    /* opcode 3 */
    protected void jnz(byte arg) {
        if (verbose) {
            System.out.println("    jnz(" + arg + ")");
        }
        if (registerA == 0) {
            return;
        }
        cursor = arg;
    }

    /* opcode 4 */
    protected void bxc(byte arg) {
        if (verbose) {
            System.out.println("    bxc(" + arg + ")");
        }
        registerB = registerB ^ registerC;
    }

    /* opcode 5 */
    protected void out(byte arg) {
        if (verbose) {
            System.out.println("    out(" + arg + ")");
        }
        output.add(combo(arg) % 8);
    }

    /* opcode 6 */
    protected void bdv(byte arg) {
        if (verbose) {
            System.out.println("    bdv(" + arg + ")");
        }
        long denominator = 1L << combo(arg);
        if (verbose) {
            System.out.println("            denominator = " + denominator);
        }
        registerB = registerB / denominator;
    }

    /* opcode 7 */
    protected void cdv(byte arg) {
        if (verbose) {
            System.out.println("    cdv(" + arg + ")");
        }
        long denominator = 1L << combo(arg);
        if (verbose) {
            System.out.println("            denominator = " + denominator);
        }
        registerC = registerC / denominator;
    }
}
