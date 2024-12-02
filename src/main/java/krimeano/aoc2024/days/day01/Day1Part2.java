package krimeano.aoc2024.days.day01;

import krimeano.aoc2024.days.my_lib.SolveDay;

import java.util.HashMap;
import java.util.PriorityQueue;


public class Day1Part2 extends SolveDay {
    public Day1Part2(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        int result = 0;
        /* heap helps to get the smallest element */
        PriorityQueue<Integer> minHeapLeft = new PriorityQueue<>();
        /* count how many times each number is met in the right list */
        HashMap<Integer, Integer> hashMapRight = new HashMap<>();

        for (String line : getLines(textInput)) {
            String[] words = line.split(" +");
            minHeapLeft.add(Integer.parseInt(words[0]));
            int right = Integer.parseInt(words[1]);
            hashMapRight.put(right, hashMapRight.getOrDefault(right, 0) + 1);
        }

        while (!minHeapLeft.isEmpty()) {
            int left = minHeapLeft.poll();
            result += left * hashMapRight.getOrDefault(left, 0);
        }

        return result;
    }
}
