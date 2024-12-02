package days;

import days.day00.Day0Part1;
import days.day00.Day0Part2;
import days.day01.Day1Part1;
import days.day01.Day1Part2;
import days.day02.Day2Part1;
import days.day02.Day2Part2;
import days.my_lib.NoDayException;
import days.my_lib.SolveDay;

public class SolutionFactory {

    public static SolveDay[] getDay(int day) throws NoDayException {
        return getDay(day, false);
    }

    public static SolveDay[] getDay(int day, boolean verbose) throws NoDayException {
        switch (day) {
            case 0:
                return new SolveDay[]{new Day0Part1(verbose), new Day0Part2(verbose)};
            case 1:
                return new SolveDay[]{new Day1Part1(verbose), new Day1Part2(verbose)};
            case 2:
                return new SolveDay[]{new Day2Part1(verbose), new Day2Part2(verbose)};
            default:
                throw new NoDayException("Day " + day + "not found");
        }

    }
}
