package krimeano.aoc2024.days.day08;

import krimeano.aoc2024.days.my_lib.SolveDay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Day8x1 extends SolveDay {
    protected int height;
    protected int width;
    protected HashMap<Character, ArrayList<int[]>> antennas = new HashMap<>();
    protected HashSet<String> antinodes = new HashSet<>();

    public Day8x1(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        ArrayList<String> lines = getLines(textInput);
        height = lines.size();
        if (height == 0) {
            return 0;
        }
        width = lines.getFirst().length();
        if (width == 0) {
            return 0;
        }
        antennas = new HashMap<>();
        antinodes = new HashSet<>();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                char antenna = lines.get(i).charAt(j);
                if (antenna == '.') {
                    continue;
                }
                if (antennas.containsKey(antenna)) {
                    for (int[] xy : antennas.get(antenna)) {
                        findAntinodes(xy[0], xy[1], i, j, antenna);
                    }
                } else {
                    antennas.put(antenna, new ArrayList<>());
                }
                antennas.get(antenna).add(new int[]{i, j});
            }
        }
        if (verbose) {
            System.out.println(antinodes);
        }
        return antinodes.size();
    }

    protected void findAntinodes(int x0, int y0, int x1, int y1, char antenna) {
        addAntinode(2 * x0 - x1, 2 * y0 - y1, antenna);
        addAntinode(2 * x1 - x0, 2 * y1 - y0, antenna);
    }

    protected void addAntinode(int x, int y, char antenna) {
        if (0 <= x && x < height && 0 <= y && y < width) {
            antinodes.add(x + "-" + y);
            if (verbose) {
                System.out.println("Adding antinode " + antenna + ": " + x + ", " + y);
            }
        } else {
            if (verbose) {
                System.out.println("Skipping antinode " + antenna + ": " + x + ", " + y);
            }
        }
    }

}
