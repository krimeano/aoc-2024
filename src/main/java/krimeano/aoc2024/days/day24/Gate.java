package krimeano.aoc2024.days.day24;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Gate {
    public class UnknownGateTypeException extends RuntimeException {
        public UnknownGateTypeException(String message) {
        }
    }

    private enum GateType {
        AND,
        OR,
        XOR,
        UNKNOWN
    }

    final Set<Wire> inputs;
    final GateType type;
    Wire output;

    public Gate(String initString, Wire.Factory wireFactory) {
        String[] parts = initString.split(" -> ");
        String[] tokens = parts[0].split(" ");

        inputs = new HashSet<>();
        inputs.add(wireFactory.getWire(tokens[0]));
        inputs.add(wireFactory.getWire(tokens[2]));

        type = switch (tokens[1]) {
            case "AND" -> GateType.AND;
            case "OR" -> GateType.OR;
            case "XOR" -> GateType.XOR;
            default -> GateType.UNKNOWN;
        };

        output = wireFactory.getWire(parts[1]);
    }

    public void connect() {
        Iterator<Wire> iterator = inputs.iterator();
        Wire wireX = iterator.next();
        Wire wireY = iterator.next();

        try {
            int x = wireX.getValue();
            int y = wireY.getValue();

            this.output.setValue(switch (type) {
                case GateType.AND -> x & y;
                case GateType.OR -> x | y;
                case GateType.XOR -> x ^ y;
                default -> throw new UnknownGateTypeException("Unknown gate type: " + type);
            });
        } catch (Wire.UndefinedException e) {
            /* do nothing */
        } catch (UnknownGateTypeException e) {
            /* do nothing */
            System.err.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        Iterator<Wire> iterator = inputs.iterator();
        return String.format("Gate[%s %s %s > %s]", iterator.next().key, toShortString(), iterator.next().key, output.key);
    }

    public String toVerboseString() {
        Iterator<Wire> iterator = inputs.iterator();
        return String.format("Gate[%s %s %s > %s]", iterator.next(), toShortString(), iterator.next(), output);
    }


    public String toShortString() {
        return switch (type) {
            case AND -> "&";
            case OR -> "|";
            case XOR -> "^";
            default -> "?";
        };
    }
}
