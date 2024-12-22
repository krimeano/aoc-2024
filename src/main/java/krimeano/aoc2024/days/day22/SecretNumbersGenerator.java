package krimeano.aoc2024.days.day22;

public class SecretNumbersGenerator implements Generator {
    private static final int PRUNE_SHIFT = 24;
    private static final int PRUNE = (1 << PRUNE_SHIFT) - 1;
    private static final int SHIFT_A = 6;
    private static final int SHIFT_B = 5;
    private static final int SHIFT_C = 11;

    private int secretNumber;

    SecretNumbersGenerator(int firstNumber) {
        secretNumber = firstNumber;
    }

    @Override
    public int next() {
        secretNumber = prune(mix(secretNumber << SHIFT_A)); /* x<2^24 -> x<2^30 -> x<2^24 */
        secretNumber = prune(mix(secretNumber >> SHIFT_B)); /* x<2^24 -> x<2^19 -> x<2^19 */
        secretNumber = prune(mix(secretNumber << SHIFT_C)); /* x<2^19 -> x<2^30 -> x<2^24 */
        return secretNumber;
    }

    private int mix(int number) {
        return number ^ secretNumber;
    }

    private int prune(int number) {
        return number & PRUNE;
    }
}
