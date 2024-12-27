package krimeano.aoc2024.days.day21;

import krimeano.aoc2024.days.my_lib.SolveDay;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*

X^A
<v>

<vA<AA>>^AvAA<^A>A<v<A>>^AvA^A<vA>^A<v<A>^A>AAvA^A<v<A>A>^AAAvA<^A>A
<vA <A A >>^A vA A <^A >A <v<A >>^A vA ^A <vA >^A <v<A >^A >A A vA ^A <v<A >A >^A A A vA <^A >A
  v  < <    A  > >   ^  A    <    A  >  A   v   A    <   ^  A A  >  A    <  v   A A A  >   ^  A
            <           A         ^     A       >           ^ ^     A           v v v         A
                        0               2                           9                         A
 */

public class Day21x1 extends SolveDay {
    Pad keyPad = new KeyPad();
    Pad numPad = new NumPad();
    protected Map<String, Long> cached = new HashMap<>();
    protected int robots = 2;

    public Day21x1(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        long result = 0;
        for (String line : getLines(textInput)) {
            int numericCode = getNumericCode(line);
            long pathLength = countForCode(line);
            result += (long) numericCode * pathLength;
        }
        System.out.println("(long) " + result);
        return (int) result;
    }

    protected int getNumericCode(String code) {
        code = code.substring(0, code.length() - 1);
        while (code.startsWith("0")) {
            code = code.substring(1);
        }
        return code.isEmpty() ? 0 : Integer.parseInt(code);
    }

    protected long countForCode(String code) {
        char prevC = 'A';
        long result = 0;
        for (char c : code.toCharArray()) {
            if (verbose) {
                System.out.println(prevC + "->" + c + ": " + numPad.getPaths(c, prevC));
            }
            long minLength = f(robots, numPad.getPaths(c, prevC));
            prevC = c;
            result += minLength;
        }
        return result;
    }

    /**
     * F(0, 'any') = 1;
     * F(depth, "XYZ") = F(depth, 'X', 'A') + F(depth, 'Y', 'X') + F(depth, 'Z', 'Y');
     * G('X', 'W') = Pad.getPaths('X', 'W') = {"MNK", "OPQ"...};
     * F(depth, 'X', 'W') = F(depth-1, G('X', 'W')) = min(F(depth-1, "MNK"), F(depth-1, "OPQ")...);
     */
    protected long f(int depth, Set<String> paths) {
        long minResult = Long.MAX_VALUE;
        for (String path : paths) {
            minResult = Math.min(minResult, f(depth, path));
        }
        return minResult;
    }

    protected long f(int depth, String path) {
        if (depth == 0) {
            return path.length();
        }
        String key = depth + "-" + path;
        if (cached.containsKey(key)) {
            return cached.get(key);
        }
        char prevC = 'A';
        long result = 0;
        for (char c : path.toCharArray()) {
            result += f(depth - 1, keyPad.getPaths(c, prevC));
            prevC = c;
        }
        cached.put(key, result);
        return result;
    }
}
