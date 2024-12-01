package days.day01;

import days.my_lib.SolveDay;

import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class Day1Part1 extends SolveDay {
    public Day1Part1(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        int result = 0;
        /* heap helps to get the smallest element */
        PriorityQueue<Integer> minHeapLeft = new PriorityQueue<>();
        PriorityQueue<Integer> minHeapRight = new PriorityQueue<>();

        for (String line : getLines(textInput)) {
            String[] words = line.split(" +");
            minHeapLeft.add(Integer.parseInt(words[0]));
            minHeapRight.add(Integer.parseInt(words[1]));
        }

        try {
            while (!minHeapLeft.isEmpty() && !minHeapRight.isEmpty()) {
                result += Math.abs(minHeapLeft.remove() - minHeapRight.remove());
            }
        } catch (NoSuchElementException e) {
            System.err.println("Shouldn't happen");
            System.err.println(e.getMessage());
            result = -1;
        }

        return result;
    }
}
