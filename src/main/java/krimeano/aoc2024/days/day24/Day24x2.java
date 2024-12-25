package krimeano.aoc2024.days.day24;

import krimeano.aoc2024.days.my_lib.SolveDay;

import java.util.*;

public class Day24x2 extends SolveDay {
    List<String> inputs;
    Map<String, Set<String>> parentsMap;
    Map<String, Set<String>> childrenMap;
    List<List<String>> tiers;
    List<OpAmp> chips;

    public Day24x2(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        readData(textInput);
        swapGates("z12", "kth");
        swapGates("z26", "gsd");

        chips = new ArrayList<>();
        OpAmp prevChip = null;
        for (int i = 0; i < inputs.size() / 2; i++) {

            OpAmp chip = new OpAmp(i, childrenMap, parentsMap);
            chips.add(chip);
            if (prevChip != null) {
                if (!chip.u.equals(prevChip.w)) {
                    System.out.println("WRONG INPUT OUTPUT!");
                }
            }
            System.out.println(chip);
            chip.printVerbose(childrenMap, parentsMap);
            prevChip = chip;
        }
        printGrandparents();
//        sortGates();
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

    protected void swapGates(String a, String b) {
        Set<String> tmpParents = parentsMap.get(a);
        parentsMap.put(a, parentsMap.get(b));
        parentsMap.put(b, tmpParents);

        for (String parent : parentsMap.get(a)) {
            childrenMap.get(parent).remove(b);
            childrenMap.get(parent).add(a);
        }

        for (String parent : parentsMap.get(b)) {
            childrenMap.get(parent).remove(a);
            childrenMap.get(parent).add(b);
        }
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

    protected void printGrandparents() {
        Map<String, Set<String>> grandParentsMap = new HashMap<>();
        List<String> wave = new ArrayList<>();

        for (String inputKey : inputs) {
            for (String child : childrenMap.get(inputKey)) {
                if (!wave.contains(child)) {
                    wave.add(child);
                }
                grandParentsMap.putIfAbsent(child, new HashSet<>());
                grandParentsMap.get(child).add(inputKey);
            }
        }
//        System.out.println(wave);
        while (!wave.isEmpty()) {
            String key = wave.removeFirst();
//            System.out.println(key);
            if (childrenMap.containsKey(key)) {
                for (String child : childrenMap.get(key)) {
                    if (!wave.contains(child)) {
                        wave.add(child);
                    }
                    grandParentsMap.putIfAbsent(child, new HashSet<>());
                    grandParentsMap.get(child).addAll(grandParentsMap.get(key));
                }
            }
        }

        List<String> keys = new ArrayList(grandParentsMap.keySet());
        keys.sort(Comparator.naturalOrder());
        for (String key : keys) {
            if (!key.startsWith("z")) {
                continue;
            }
            List<String> gp = new ArrayList<>(grandParentsMap.get(key));
            gp.sort(Comparator.naturalOrder());
            System.out.println(key + " > " + gp.size() + " : " + gp);
        }
    }

}
