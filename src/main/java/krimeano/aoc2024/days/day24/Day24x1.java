package krimeano.aoc2024.days.day24;

import krimeano.aoc2024.days.my_lib.SolveDay;

import java.util.*;

public class Day24x1 extends SolveDay {
    Map<String, Integer> wires = new HashMap<>();
    Map<String, List<String>> gatesMap;
    List<String> sortedGateWires;

    public Day24x1(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        readData(textInput);
        sortGates();
        connectWires();
        if (verbose) {
            System.out.println("wires" + wires);
        }

        long result = 0;

        for (String wire : wires.keySet()) {
            if (wire.startsWith("z")) {
                result |= getWireBit(wire);
            }
        }
        System.out.println("Day24x1: " + result);
        return (int) result;
    }

    protected void readData(String textInput) {
        wires = new HashMap<>();
        gatesMap = new HashMap<>();
        boolean isInitWires = true;
        for (String line : getLines(textInput, true)) {
            if (line.isEmpty()) {
                isInitWires = false;
                continue;
            }
            if (isInitWires) {
                String[] parts = line.split(": ");
                wires.put(parts[0], Integer.parseInt(parts[1]));
            } else {
                String[] parts = line.split(" -> ");
                gatesMap.put(parts[1], Arrays.asList(parts[0].split(" ")));
            }
        }
    }

    protected long getWireBit(String key) {
        int shift;
        long value = wires.getOrDefault(key, 0);
        try {
            shift = Integer.parseInt(key.substring(1));
            return value << shift;
        } catch (NumberFormatException e) {
            return value;
        }
    }

    protected void sortGates() {

        Map<String, Set<String>> children = new HashMap<>();
        Map<String, Integer> dependsOnCount = new HashMap<>();
        List<String> zeroes = new ArrayList<>();

        for (String keyZ : gatesMap.keySet()) {
            List<String> gate = gatesMap.get(keyZ);
            String keyX = gate.get(0);
            String keyY = gate.get(2);

            children.putIfAbsent(keyX, new HashSet<>());
            children.get(keyX).add(keyZ);

            children.putIfAbsent(keyY, new HashSet<>());
            children.get(keyY).add(keyZ);

            int parents = wires.containsKey(keyX) ? 0 : 1;
            parents += wires.containsKey(keyY) ? 0 : 1;

            dependsOnCount.put(keyZ, parents);

            if (parents == 0) {
                zeroes.add(keyZ);
            }
        }

        sortedGateWires = new ArrayList<>();

        while (!zeroes.isEmpty()) {
            String keyZ = zeroes.removeFirst();

            sortedGateWires.add(keyZ);

            if (children.containsKey(keyZ)) {
                for (String child : children.get(keyZ)) {
                    int newCount = dependsOnCount.get(child) - 1;
                    dependsOnCount.put(child, newCount);
                    if (newCount == 0) {
                        zeroes.add(child);
                    }
                }
            }
        }
    }

    protected void connectWires() {
        for (String keyZ : sortedGateWires) {
            List<String> gate = gatesMap.get(keyZ);
            String keyX = gate.get(0);
            String operation = gate.get(1);
            String keyY = gate.get(2);

            int x = wires.getOrDefault(keyX, 0);
            int y = wires.getOrDefault(keyY, 0);

            int value = switch (operation) {
                case "AND" -> x & y;
                case "OR" -> x | y;
                case "XOR" -> x ^ y;
                default -> 0;
            };

            if (verbose) {
                System.out.print(gate);
                System.out.println(" = " + value + " <- (" + x + " " + operation + " " + y + ")");
            }
            wires.put(keyZ, value);
        }
    }
}
