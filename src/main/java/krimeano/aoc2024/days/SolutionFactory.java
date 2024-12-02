package krimeano.aoc2024.days;

import krimeano.aoc2024.days.day00.Day0x1;
import krimeano.aoc2024.days.day00.Day0x2;
import krimeano.aoc2024.days.day01.Day1x1;
import krimeano.aoc2024.days.day01.Day1x2;
import krimeano.aoc2024.days.day02.Day2x1;
import krimeano.aoc2024.days.day02.Day2x2;
import krimeano.aoc2024.days.day03.Day3x1;
import krimeano.aoc2024.days.day03.Day3x2;
import krimeano.aoc2024.days.my_lib.NoDayException;
import krimeano.aoc2024.days.my_lib.SolveDay;

public class SolutionFactory {

    public static SolveDay[] getDay(int day) throws NoDayException {
        return getDay(day, false);
    }

    public static SolveDay[] getDay(int day, boolean verbose) throws NoDayException {
        switch (day) {
            case 0:
                return new SolveDay[]{new Day0x1(verbose), new Day0x2(verbose)};
            case 1:
                return new SolveDay[]{new Day1x1(verbose), new Day1x2(verbose)};
            case 2:
                return new SolveDay[]{new Day2x1(verbose), new Day2x2(verbose)};
            case 3:
                return new SolveDay[]{new Day3x1(verbose), new Day3x2(verbose)};
            default:
                throw new NoDayException("Day " + day + "not found");
        }

    }
}
