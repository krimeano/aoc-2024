package krimeano.aoc2024.days.day21;

import krimeano.aoc2024.days.my_lib.SolveDay;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
            System.out.print(numericCode);
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
//            System.out.println(prevC + "->" + c + ": " + numPad.getPaths(c, prevC));
//            result += f(3, numPad.getPaths(c, prevC));
            Set<String> paths = findAllPaths(2, numPad.getPaths(c, prevC));
//            System.out.println(paths.size());
            int minLength = Integer.MAX_VALUE;
            String minPath = "";
            for (String path : paths) {
                if (path.length() < minLength) {
                    minLength = path.length();
                    minPath = path;
                }
            }
//            System.out.println(prevC + "->" + c + ": " + minLength + ":" + minPath);
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
    protected int f(int depth, Set<String> paths) {
        int minResult = Integer.MAX_VALUE;
        for (String path : paths) {
            minResult = Math.min(minResult, f(depth, path));
        }
        return minResult;
    }

    protected int f(int depth, String path) {
        if (depth == 0) {
            return path.length();
        }
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
        return f(depth - 1, keyPad.getPaths(step, prevStep));
    }

    protected Set<String> findAllPaths(int depth, Set<String> paths) {
//        System.out.println("findAllPaths_0 " + depth + ", " + paths);
        if (depth == 0) {
            return new HashSet<>(paths);
        }

        Set<String> result = new HashSet<>();
        for (String path : paths) {
            result.addAll(findAllPaths(depth, path));
        }
        return result;
    }

    protected Set<String> findAllPaths(int depth, String path) {
//        System.out.println("findAllPaths_1 " + depth + ", " + path);
        Set<String> result = new HashSet<>();

        if (depth == 0) {
            result.add(path);
            return result;
        }
        char prevC = 'A';

        List<String> foundPaths = new ArrayList<>();

        foundPaths.add("");

        for (char c : path.toCharArray()) {
            List<String> prefixes = new ArrayList<>();
            for (String suffix : findAllPaths(depth, c, prevC)) {
                for (String prefix : foundPaths) {
                    prefixes.add(prefix + suffix);
                }
            }
            foundPaths = prefixes;
            prevC = c;
        }

        result.addAll(foundPaths);

        return result;
    }


    protected Set<String> findAllPaths(int depth, char step, char prevStep) {
//        System.out.println("findAllPaths_2 " + depth + ", " + step + ", " + prevStep);
        if (depth == 0) {
            Set<String> result = new HashSet<>();
            result.add(String.valueOf(step));
            return result;
        }
        return findAllPaths(depth - 1, keyPad.getPaths(step, prevStep));
    }

}
