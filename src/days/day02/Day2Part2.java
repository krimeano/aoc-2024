package days.day02;

import days.my_lib.SolveDay;

import java.util.Vector;

public class Day2Part2 extends SolveDay {

    private static final int[] DOWN_LIMITS = {-3, -1};
    private static final int[] UP_LIMITS = {1, 3};

    public Day2Part2(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        int result = 0;
        for (String line : getLines(textInput)) {
            if (verbose) {
                System.out.println();
                System.out.println(line);
            }

            String[] words = line.split(" ");

            Vector<Integer> changes = new Vector<>();

            int prev = Integer.parseInt(words[0]);

            int trend = 0;

            for (int i = 1; i < words.length; i++) {
                int current = Integer.parseInt(words[i]);
                int diff = current - prev;
                changes.add(diff);

                if ((diff >= DOWN_LIMITS[0]) && (diff <= DOWN_LIMITS[1])) {
                    trend--;
                } else if ((diff >= UP_LIMITS[0]) && (diff <= UP_LIMITS[1])) {
                    trend++;
                }

                prev = current;
            }
            if (verbose) {
                System.out.println("    changes: " + changes);
                System.out.println("    trend: " + trend);
            }


            boolean isSafe = true;

            int[] current_limits = trend < 0 ? DOWN_LIMITS : UP_LIMITS;
            boolean onceFixed = false;
            Vector<Integer> fixedChanges = new Vector<>();

            for (int i = 0; i < changes.size(); i++) {
                int change = changes.get(i);

                /* this is a good change */
                if ((change >= current_limits[0]) && (change <= current_limits[1])) {
                    if (verbose) {
                        System.out.println("    " + change + " is good");
                    }
                    fixedChanges.add(change);
                    continue;
                }

                if (onceFixed) {
                    if (verbose) {
                        System.out.println("    " + change + "; it was already fixed, fail");
                    }
                    isSafe = false;
                    break;
                }

                /* wrong change can be discarded if it is the last one */
                if (i == changes.size() - 1) {
                    if (verbose) {
                        System.out.println("    " + change + " is removed from the end of the list");
                    }
                    onceFixed = true;
                    continue;
                }

                /* try to discard the current item */
                int fixedChange = change + changes.get(i + 1);
                if ((fixedChange >= current_limits[0]) && (fixedChange <= current_limits[1])) {
                    if (verbose) {
                        System.out.println("    " + change + " can be fixed to value " + fixedChange);
                    }
                    onceFixed = true;
                    fixedChanges.add(fixedChange);
                    i++;
                    continue;
                }

                /* discard first item */
                if (i == 0) {
                    if (verbose) {
                        System.out.println("    " + change + " is removed frome the beginning");
                    }
                    onceFixed = true;
                    continue;
                }
                if (verbose) {
                    System.out.println("    " + change + " is merged to value " + fixedChange + "; unsafe!");
                }

                isSafe = false;
                break;
            }

            if (verbose) {
                if (isSafe) {
                    System.out.println("    fixed changes: " + fixedChanges);
                }
                System.out.println("    SAFE: " + isSafe);
            }

            if (isSafe) {
                result++;
            }

        }
        return result;
    }

}
