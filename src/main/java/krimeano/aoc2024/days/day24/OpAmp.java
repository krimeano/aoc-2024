package krimeano.aoc2024.days.day24;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OpAmp {
    public static final String UNKNOWN = "<?>";
    public static final String EMPTY = "---";
    public static final String WIRE_FORMAT = "%s%02d";
    public int ix;

    /* x,y -> a, b */
    public String x;

    /* x,y -> a, b */
    public String y;

    /* a,u -> c, z */
    public String u = UNKNOWN;

    /* x,y -> a, b */
    /* a,u -> c, z */
    public String a = UNKNOWN;

    /* x,y -> a, b */
    /* b,c -> w */
    public String b = UNKNOWN;

    /* a,u -> c, z */
    /* b,c -> w */
    public String c = UNKNOWN;

    /* b,c -> w */
    public String w = UNKNOWN;

    public String z;

    public Set<String> xyTo;
    public Set<String> zFrom;

    public OpAmp(int ix, Map<String, Set<String>> childrenMap, Map<String, Set<String>> parentsMap) {
        this.ix = ix;
        x = String.format(WIRE_FORMAT, 'x', ix);
        y = String.format(WIRE_FORMAT, 'y', ix);
        z = String.format(WIRE_FORMAT, 'z', ix);
        xyTo = new HashSet<>(childrenMap.get(x));
        zFrom = new HashSet<>(parentsMap.get(z));

        if (ix == 0) {
            u = EMPTY;
            a = EMPTY;
            b = EMPTY;
            c = EMPTY;

            for (String m : xyTo) {
                if (!m.equals(z)) {
                    w = m;
                }
            }
        } else {
            for (String m : xyTo) {
                if (zFrom.contains(m)) {
                    a = m;
                }
            }

            if (!a.equals(UNKNOWN)) {
                Set<String> tmp;

                tmp = new HashSet<>(zFrom);
                tmp.remove(a);
                u = tmp.iterator().next();

                tmp = new HashSet<>(xyTo);
                tmp.remove(a);
                b = tmp.iterator().next();

                w = childrenMap.get(b).iterator().next();

                tmp = new HashSet<>(parentsMap.get(w));
                tmp.remove(b);
                c = tmp.iterator().next();
            }

            if (childrenMap.getOrDefault(b, new HashSet<>()).size() != 1) {
                System.out.println("!!! " + b + " SHOULD HAVE ONLY 1 CHILD");
            }
            if (childrenMap.getOrDefault(c, new HashSet<>()).size() != 1) {
                System.out.println("!!! " + c + " SHOULD HAVE ONLY 1 CHILD");
            }
        }

    }

    @Override
    public String toString() {
        return String.format("          %s\n%s > %s  v  %s\n%s > %s %s\n          %s\n", u, x, a, z, y, b, c, w);
    }

    public void printVerbose(Map<String, Set<String>> childrenMap, Map<String, Set<String>> parentsMap) {
        printItem(x, childrenMap, parentsMap);
        printItem(y, childrenMap, parentsMap);
        printItem(a, childrenMap, parentsMap);
        printItem(b, childrenMap, parentsMap);
        printItem(u, childrenMap, parentsMap);
        printItem(z, childrenMap, parentsMap);
        printItem(c, childrenMap, parentsMap);
        printItem(w, childrenMap, parentsMap);
        System.out.println();
    }

    private void printItem(String item, Map<String, Set<String>> childrenMap, Map<String, Set<String>> parentsMap) {
        if (!item.equals(EMPTY) && !item.equals(UNKNOWN)) {
            System.out.println(parentsMap.getOrDefault(item, new HashSet<>()) + " > " + item + " > " + childrenMap.getOrDefault(item, new HashSet<>()));
        }
    }

}
