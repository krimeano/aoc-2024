package krimeano.aoc2024.days.day24;

import krimeano.aoc2024.days.my_lib.SolveDay;

import java.util.*;

public class Day24x1 extends SolveDay {
    Wire.Factory wireFactory;

    Set<String> inputs;
    Set<String> outputs;

    HashMap<String, Set<Gate>> wireToGate;
    HashMap<String, Gate> wireFromGate;

    public Day24x1(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        readData(textInput);
        connectWires();

        if (verbose) {
            System.out.println(outputs);
        }


        try {
            long result = 0;
            for (String wire : outputs) {
                result |= wireFactory.getWire(wire).getBitValue();
            }
            System.out.println("Day24x1: " + result);
            return (int) result;
        } catch (Wire.UndefinedException e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }

    protected void readData(String textInput) {
        inputs = new HashSet<>();
        outputs = new HashSet<>();
        wireFactory = new Wire.Factory();
        wireToGate = new HashMap<>();
        wireFromGate = new HashMap<>();
        boolean isInitWires = true;

        for (String line : getLines(textInput, true)) {
            if (line.isEmpty()) {
                isInitWires = false;
                continue;
            }

            if (isInitWires) {
                Wire wire = wireFactory.getWire(line);
                inputs.add(wire.key);
                if (verbose) {
                    System.out.println(wire);
                }

            } else {
                Gate gate = new Gate(line, wireFactory);
                for (String key : gate.inputs) {
                    wireToGate.putIfAbsent(key, new HashSet<>());
                    wireToGate.get(key).add(gate);
                }
                wireFromGate.put(gate.output, gate);
                if (gate.output.startsWith("z")) {
                    outputs.add(gate.output);
                }
                if (verbose) {
                    System.out.println(gate);
                }
            }
        }
    }

    protected void connectWires() {
        List<String> wave = new ArrayList<>(inputs);
        while (!wave.isEmpty()) {
            List<String> newWave = new ArrayList<>();
            for (String key : wave) {
                if (wireToGate.containsKey(key)) {
                    for (Gate gate : wireToGate.get(key)) {
                        gate.connect();
                        if (wireFactory.getWire(gate.output).isDefined() && !newWave.contains(gate.output)) {
                            newWave.add(gate.output);
                        }
                    }
                }
            }
            wave = newWave;
        }
    }
}
