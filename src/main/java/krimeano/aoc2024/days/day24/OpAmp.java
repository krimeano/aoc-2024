package krimeano.aoc2024.days.day24;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class OpAmp {
    private static final String UNKNOWN = "<?>";
    private static final String EMPTY = "---";
    private static final String WIRE_FORMAT = "%s%02d";

    public int ix;

    /* x,y -> a, b */
    public final String x;

    /* x,y -> a, b */
    public final String y;

    /* a,u -> c, z */
    public String u = UNKNOWN;

    /* x,y -> a, b */
    /* a,u -> c, z */
    private String a = UNKNOWN;

    /* x,y -> a, b */
    /* b,c -> w */
    private String b = UNKNOWN;

    /* a,u -> c, z */
    /* b,c -> w */
    private String c = UNKNOWN;

    /* b,c -> w */
    public String w = UNKNOWN;

    public final String z;

    private Gate xyToA;
    private Gate xyToB;
    private final Gate auToZ;
    private Gate auToC;
    private Gate bcToW;

    public OpAmp(int ix, HashMap<String, Set<Gate>> wireToGate, HashMap<String, Gate> wireFromGate) {
        this.ix = ix;
        x = String.format(WIRE_FORMAT, 'x', ix);
        y = String.format(WIRE_FORMAT, 'y', ix);
        z = String.format(WIRE_FORMAT, 'z', ix);

        auToZ = wireFromGate.get(z);
        Set<Gate> xyToAOrB = new HashSet<>(wireToGate.get(x));

        if (ix == 0) {
            xyToA = auToZ;
            u = EMPTY;
            a = EMPTY;
            b = EMPTY;
            c = EMPTY;
            xyToAOrB.remove(auToZ);
            bcToW = xyToAOrB.iterator().next();
            xyToB = bcToW;
            w = bcToW.output;
        } else {
            for (Gate gate : xyToAOrB) {
                if (auToZ.inputs.contains(gate.output)) {
                    a = gate.output;
                    xyToA = gate;
                }
            }

            if (!a.equals(UNKNOWN)) {
                Set<String> tmp;
                Set<Gate> tmpGates;

                xyToAOrB.remove(xyToA);
                xyToB = xyToAOrB.iterator().next();
                b = xyToB.output;

                tmp = new HashSet<>(auToZ.inputs);
                tmp.remove(a);
                u = tmp.iterator().next();

                tmpGates = new HashSet<>(wireToGate.get(a));
                tmpGates.remove(auToZ);
                if (!tmpGates.isEmpty()) {
                    auToC = tmpGates.iterator().next();
                    c = auToC.output;
                }

                bcToW = wireToGate.get(b).iterator().next();
                w = bcToW.output;
            }

        }
    }

    @Override
    public String toString() {
        return String.format("            %s\n", u)
                + String.format("%s %s %s      %2s %s\n", x, formatGate(xyToA), a, formatGate(auToZ), z)
                + String.format("%s %s %s %s %s\n", y, formatGate(xyToB), b, formatGate(auToC), c)
                + String.format("          %s %s\n", formatGate(bcToW), w);
    }

    protected String formatGate(Gate gate) {
        return gate == null ? "?" : gate.toShortString();
    }
}
