package krimeano.aoc2024.days.day17;

import java.util.ArrayList;

public class Computer {
    public int registerA = 0;
    public int registerB = 0;
    public int registerC = 0;
    public String program = "";
    protected ArrayList<String> output = new ArrayList<>();
    protected int pointer = 0;
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
        pointer = 0;
        steps = 0;

        if (verbose) {
            System.out.println();
            System.out.println("Program: " + program);
        }

        while (pointer < program.length()) {
            nextStep();
        }

        System.out.println();
        return printOutput();
    }

    protected void nextStep() {
        if (verbose) {
            System.out.println();
            System.out.println("Step: " + steps);
            System.out.println(program);
            for (int i = 0; i < pointer; i++) {
                System.out.print(" ");
            }
            System.out.println("^_^");
            System.out.println(" > " + Integer.toBinaryString(registerA) + ", " + Integer.toBinaryString(registerB) + ", " + Integer.toBinaryString(registerC));
        }
        char opcode = program.charAt(pointer);
        pointer += 2;
        int arg = Integer.parseInt(program.charAt(pointer) + "");
        pointer += 2;
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
        if (verbose) {
            System.out.println(" < " + Integer.toBinaryString(registerA) + ", " + Integer.toBinaryString(registerB) + ", " + Integer.toBinaryString(registerC));
        }
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

    protected int combo(int arg) {
        String debugString = "";
        int value = -1;
        assert 0 <= arg && arg < 7;
        switch (arg) {
            case 0, 1, 2, 3:
                debugString = "arg";
                value = arg;
                break;
            case 4:
                debugString = "A";
                value = registerA;
                break;
            case 5:
                debugString = "B";
                value = registerB;
                break;
            case 6:
                debugString = "C";
                value = registerC;
                break;
        }

        if (verbose) {
            System.out.println("combo(" + arg + ") : " + debugString + " : " + value + " : " + Integer.toBinaryString(value));
        }
        return value;
    }

    /* opcode 0 */
    protected void adv(int arg) {
        int value = combo(arg);

        if (verbose) {
            System.out.println("0 : adv(" + arg + ") : C = A >> combo(" + arg + ") : " + Integer.toBinaryString(registerA) + " >> " + value);
        }

        registerA = registerA >> value;
    }

    /* opcode 1 */
    protected void bxl(int arg) {
        if (verbose) {
            System.out.println("1 : bxl(" + arg + "): B = B ^ arg : " + Integer.toBinaryString(registerB) + " ^ " + Integer.toBinaryString(arg));
        }
        registerB = registerB ^ arg;
    }

    /* opcode 2 */
    protected void bst(int arg) {
        int value = combo(arg);
        if (verbose) {
            System.out.println("2 : bst(" + arg + ") : B = combo(" + arg + ") & 111 : " + Integer.toBinaryString(value) + " & " + Integer.toBinaryString(0b111));
        }
        registerB = value & 0b111;
    }

    /* opcode 3 */
    protected void jnz(int arg) {
        if (verbose) {
            System.out.print("3 : jnz(" + arg + ") : cursor = arg if A > 0 : A = " + registerA);
        }
        if (registerA == 0) {
            if (verbose) {
                System.out.println(" no move");
            }
            return;
        }
        if (verbose) {
            System.out.println(" move to " + arg);
        }
        pointer = arg;
    }

    /* opcode 4 */
    protected void bxc(int arg) {
        if (verbose) {
            System.out.println("4 : bxc(" + arg + ") : B = B ^ C : " + Integer.toBinaryString(registerB) + " ^ " + Integer.toBinaryString(registerC));
        }
        registerB = registerB ^ registerC;
    }

    /* opcode 5 */
    protected void out(int arg) {
        int value = combo(arg);
        int item = value & 0b111;
        if (verbose) {
            System.out.println("5 : out(" + arg + ") : output combo(" + arg + ") & 111 : " + Integer.toBinaryString(value) + " & " + Integer.toBinaryString(0b111) + " = " + Integer.toBinaryString(item) + " = " + item);
        }
        output.add(Integer.toString(item));
    }

    /* opcode 6 */
    protected void bdv(int arg) {
        int value = combo(arg);
        if (verbose) {
            System.out.println("6 : bdv(" + arg + "): B = A >> combo(" + arg + ") : " + Integer.toBinaryString(registerA) + " >> " + value);
        }
        registerA = registerA >> value;
    }

    /* opcode 7 */
    protected void cdv(int arg) {
        int value = combo(arg);
        if (verbose) {
            System.out.println("7 : cdv(" + arg + "): C = A >> combo(" + arg + ") : " + Integer.toBinaryString(registerA) + " >> " + value);
        }
        registerC = registerA >> value;
    }

}
