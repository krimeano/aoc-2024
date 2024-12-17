package krimeano.aoc2024.days;

import krimeano.aoc2024.days.day00.Day0x1;
import krimeano.aoc2024.days.day00.Day0x2;
import krimeano.aoc2024.days.day01.Day1x1;
import krimeano.aoc2024.days.day01.Day1x2;
import krimeano.aoc2024.days.day02.Day2x1;
import krimeano.aoc2024.days.day02.Day2x2;
import krimeano.aoc2024.days.day03.Day3x1;
import krimeano.aoc2024.days.day03.Day3x2;
import krimeano.aoc2024.days.day04.Day4x1;
import krimeano.aoc2024.days.day04.Day4x2;
import krimeano.aoc2024.days.day05.Day5x1;
import krimeano.aoc2024.days.day05.Day5x2;
import krimeano.aoc2024.days.day06.Day6x1;
import krimeano.aoc2024.days.day06.Day6x2;
import krimeano.aoc2024.days.day07.Day7x1;
import krimeano.aoc2024.days.day07.Day7x2;
import krimeano.aoc2024.days.day08.Day8x1;
import krimeano.aoc2024.days.day08.Day8x2;
import krimeano.aoc2024.days.day09.Day9x1;
import krimeano.aoc2024.days.day09.Day9x2;
import krimeano.aoc2024.days.day10.Day10x1;
import krimeano.aoc2024.days.day10.Day10x2;
import krimeano.aoc2024.days.day11.Day11x1;
import krimeano.aoc2024.days.day11.Day11x2;
import krimeano.aoc2024.days.day12.Day12x1;
import krimeano.aoc2024.days.day12.Day12x2;
import krimeano.aoc2024.days.day13.Day13x1;
import krimeano.aoc2024.days.day13.Day13x2;
import krimeano.aoc2024.days.day14.Day14x1;
import krimeano.aoc2024.days.day14.Day14x2;
import krimeano.aoc2024.days.day15.Day15x1;
import krimeano.aoc2024.days.day15.Day15x2;
import krimeano.aoc2024.days.day16.Day16x1;
import krimeano.aoc2024.days.day16.Day16x2;
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
            case 4:
                return new SolveDay[]{new Day4x1(verbose), new Day4x2(verbose)};
            case 5:
                return new SolveDay[]{new Day5x1(verbose), new Day5x2(verbose)};
            case 6:
                return new SolveDay[]{new Day6x1(verbose), new Day6x2(verbose)};
            case 7:
                return new SolveDay[]{new Day7x1(verbose), new Day7x2(verbose)};
            case 8:
                return new SolveDay[]{new Day8x1(verbose), new Day8x2(verbose)};
            case 9:
                return new SolveDay[]{new Day9x1(verbose), new Day9x2(verbose)};
            case 10:
                return new SolveDay[]{new Day10x1(verbose), new Day10x2(verbose)};
            case 11:
                return new SolveDay[]{new Day11x1(verbose), new Day11x2(verbose)};
            case 12:
                return new SolveDay[]{new Day12x1(verbose), new Day12x2(verbose)};
            case 13:
                return new SolveDay[]{new Day13x1(verbose), new Day13x2(verbose)};
            case 14:
                return new SolveDay[]{new Day14x1(verbose), new Day14x2(verbose)};
            case 15:
                return new SolveDay[]{new Day15x1(verbose), new Day15x2(verbose)};
            case 16:
                return new SolveDay[]{new Day16x1(verbose), new Day16x2(verbose)};
            default:
                throw new NoDayException("Day " + day + "not found");
        }

    }
}
