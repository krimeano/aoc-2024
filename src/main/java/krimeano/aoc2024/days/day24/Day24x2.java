package krimeano.aoc2024.days.day24;

import krimeano.aoc2024.days.my_lib.SolveDay;

import java.util.*;

public class Day24x2 extends SolveDay {
    List<String> inputs;
    Map<String, Set<String>> parentsMap;
    Map<String, Set<String>> childrenMap;
    List<List<String>> tiers;

    public Day24x2(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        readData(textInput);
        sortGates();
//        for (String key : parentsMap.keySet()) {
//            System.out.println(key + " < " + parentsMap.get(key));
//        }
//        for (String key : childrenMap.keySet()) {
//            System.out.println(key + " > " + childrenMap.get(key));
//        }
        return -1;
    }

    protected void readData(String textInput) {
        inputs = new ArrayList<>();
        parentsMap = new HashMap<>();
        childrenMap = new HashMap<>();
        boolean isInitWires = true;

        for (String line : getLines(textInput, true)) {
            if (line.isEmpty()) {
                isInitWires = false;
                continue;
            }
            if (isInitWires) {
                inputs.add(line.split(": ")[0]);
            } else {
                String[] parts = line.split(" -> ");
                String[] tokens = parts[0].split(" ");
                String keyZ = parts[1];
                String keyX = tokens[0];
                String keyY = tokens[2];

                parentsMap.put(keyZ, new HashSet<>());
                parentsMap.get(keyZ).add(keyX);
                parentsMap.get(keyZ).add(keyY);

                childrenMap.putIfAbsent(keyX, new HashSet<>());
                childrenMap.get(keyX).add(keyZ);

                childrenMap.putIfAbsent(keyY, new HashSet<>());
                childrenMap.get(keyY).add(keyZ);
            }
        }

        inputs.sort(Comparator.comparing(x -> x.substring(1)));
    }

    protected void sortGates() {
        tiers = new ArrayList<>();
        int cursor = 0;
        List<String> lastTier = new ArrayList<>();
        Set<String> visitedWires = new HashSet<>();
        while (!lastTier.isEmpty() || cursor < inputs.size()) {
            List<String> newTier = new ArrayList<>();
            Set<String> newVisitedWires = new HashSet<>();
            if (lastTier.isEmpty()) {
                String x = inputs.get(cursor++);
                String y = inputs.get(cursor++);
                newTier.add(x);
                newVisitedWires.add(x);
                newTier.add(y);
                newVisitedWires.add(y);
            }
            for (String wire : lastTier) {
                if (childrenMap.containsKey(wire)) {
                    for (String child : childrenMap.get(wire)) {
                        if (!newTier.contains(child)) {
                            boolean bothParentsVisited = true;
                            if (parentsMap.containsKey(child)) {
                                for (String parent : parentsMap.get(child)) {
                                    bothParentsVisited = bothParentsVisited && visitedWires.contains(parent);
                                }
                            }
                            if (bothParentsVisited) {
                                newTier.add(child);
                                newVisitedWires.add(child);
                            }
                        }
                    }
                }
            }
            if (!newTier.isEmpty()) {
                tiers.add(newTier);
                visitedWires.addAll(newVisitedWires);
            }
            lastTier = newTier;
        }

        for (List<String> tier : tiers) {
            for (String wire : tier) {
                if (parentsMap.containsKey(wire)) {
                    System.out.print(parentsMap.get(wire) + " > ");
                } else {
                    System.out.print("             ");
                }
                System.out.print(wire + "        ");
            }
            System.out.println();
        }
    }
}
