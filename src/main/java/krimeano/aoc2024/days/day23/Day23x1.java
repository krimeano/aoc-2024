package krimeano.aoc2024.days.day23;

import krimeano.aoc2024.days.my_lib.SolveDay;

import java.util.*;

public class Day23x1 extends SolveDay {
    public Day23x1(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        Map<String, Set<String>> connections = new HashMap<>();

        ArrayList<String> lines = getLines(textInput);
        lines.sort(Comparator.naturalOrder());

        for (String line : lines) {
//            System.out.println(line);
            String[] computers = line.split("-");
            String a = computers[0];
            String b = computers[1];

            connections.putIfAbsent(a, new HashSet<>());
            connections.get(a).add(b);

            connections.putIfAbsent(b, new HashSet<>());
            connections.get(b).add(a);
        }

        Set<List<String>> result = new HashSet<>();

        for (Map.Entry<String, Set<String>> entry : connections.entrySet()) {
            String a = entry.getKey();
            ArrayList<String> neighbors = new ArrayList<>(entry.getValue());
            for (int i = 0; i < neighbors.size() - 1; i++) {
                for (int j = i + 1; j < neighbors.size(); j++) {
                    String b = neighbors.get(i);
                    String c = neighbors.get(j);
                    if (a.indexOf('t') != 0 && b.indexOf('t') != 0 && c.indexOf('t') != 0) {
                        continue;
                    }
                    if (connections.get(b).contains(c)) {
                        List<String> abc = Arrays.asList(a, b, c);
                        abc.sort(Comparator.naturalOrder());
//                        System.out.println(abc);
                        result.add(abc);
                    }
                }
            }
        }

        return result.size();
    }
}
