package krimeano.aoc2024.days.day23;

import krimeano.aoc2024.days.my_lib.SolveDay;

import java.util.*;

public class Day23x1 extends SolveDay {
    Map<String, Set<String>> connections;

    public Day23x1(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        findConnections(getLines(textInput));
        return findTriangles().size();
    }

    protected void findConnections(List<String> lines) {
        connections = new HashMap<>();

        lines.sort(Comparator.naturalOrder());

        for (String line : lines) {
            String[] computers = line.split("-");
            String a = computers[0];
            String b = computers[1];

            connections.putIfAbsent(a, new HashSet<>());
            connections.get(a).add(b);

            connections.putIfAbsent(b, new HashSet<>());
            connections.get(b).add(a);
        }
    }

    protected Set<List<String>> findTriangles() {
        Set<List<String>> triangles = new HashSet<>();

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
                        triangles.add(abc);
                    }
                }
            }
        }

        return triangles;
    }
}
