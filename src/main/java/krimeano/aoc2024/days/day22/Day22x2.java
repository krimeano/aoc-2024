package krimeano.aoc2024.days.day22;

import java.util.*;

public class Day22x2 extends Day22x1 {
    public Day22x2(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        Map<List<Integer>, Integer> prices = new HashMap<>();

        for (int firstNumber : getLinesAsNumbers(textInput)) {
            Generator generator = new SecretNumbersGenerator(firstNumber);
            int prevPrice = firstNumber % 10;
            Set<List<Integer>> metSequences = new HashSet<>();
            List<Integer> sequence = new ArrayList<>();
            for (int i = 0; i < 2000; i++) {
                int price = generator.next() % 10;
                sequence.add(price - prevPrice);
                prevPrice = price;
                if (sequence.size() < 4) {
                    continue;
                }
                while (sequence.size() > 4) {
                    sequence.removeFirst();
                }
                if (!metSequences.contains(sequence)) {
                    List<Integer> abcd = Arrays.asList(sequence.get(0), sequence.get(1), sequence.get(2), sequence.get(3));
                    prices.put(abcd, prices.getOrDefault(abcd, 0) + price);
                    metSequences.add(sequence);
                }
            }
        }
        int maxBananas = 0;
        for (Integer bananas : prices.values()) {
            maxBananas = Math.max(maxBananas, bananas);
        }
        return maxBananas;
    }
}
