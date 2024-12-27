package krimeano.aoc2024.days.day21;

import krimeano.aoc2024.days.my_lib.SolveDay;

public class Day21x1 extends SolveDay {
    Pad keyPad = new KeyPad();
    Pad numPad = new NumPad();

    public Day21x1(boolean verbose) {
        super(verbose);
//        System.out.println(keyPad.positions);
//        System.out.println(numPad.positions);
    }

    @Override
    public int solve(String textInput) {
        int result = 0;
        for (String line : getLines(textInput)) {
            System.out.println(line);
            int numericCode = getNumericCode(line);
            System.out.println(numericCode);
            int pathLength = countForCode(line);
            System.out.println(pathLength);
            result += numericCode * pathLength;
        }
        return result;
    }

    protected int getNumericCode(String code) {
        code = code.substring(0, code.length() - 1);
        while (code.startsWith("0")) {
            code = code.substring(1);
        }
        return code.isEmpty() ? 0 : Integer.parseInt(code);
    }

    protected int countForCode(String code) {
        char prevC = 'A';
        int result = 0;
        for (char c : code.toCharArray()) {
//            System.out.println(prevC + "->" + c);
            result += f(3, numPad.getPaths(c, prevC).iterator().next());
        }
        return result;
    }

    /**
     * F(0, 'any') = 1;
     * F(depth, "XYZ") = F(depth, 'X', 'A') + F(depth, 'Y', 'X') + F(depth, 'Z', 'Y');
     * G('X', 'W') = Pad.getPaths('X', 'W') = {"MNK", "OPQ"...};
     * F(depth, 'X', 'W') = F(depth-1, G('X', 'W')) = min(F(depth-1, "MNK"), F(depth-1, "OPQ")...);
     */
    protected int f(int depth, String path) {
        char prevC = 'A';
        int result = 0;
        for (char c : path.toCharArray()) {
            result += f(depth, c, prevC);
            prevC = c;
        }
        return result;
    }

    protected int f(int depth, char step, char prevStep) {
        if (depth == 0) {
            return 1;
        }
        int minResult = Integer.MAX_VALUE;
        for (String newPath : keyPad.getPaths(step, prevStep)) {
            minResult = Math.min(minResult, f(depth - 1, newPath));
        }
        return minResult;
    }


}
