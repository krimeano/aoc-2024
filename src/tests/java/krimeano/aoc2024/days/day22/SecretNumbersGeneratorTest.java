package krimeano.aoc2024.days.day22;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SecretNumbersGeneratorTest {
    @Test
    void next() {
        Generator generator = new SecretNumbersGenerator(123);
        assertEquals(15887950, generator.next());
        assertEquals(16495136, generator.next());
        assertEquals(527345, generator.next());
        assertEquals(704524, generator.next());
        assertEquals(1553684, generator.next());
        assertEquals(12683156, generator.next());
        assertEquals(11100544, generator.next());
        assertEquals(12249484, generator.next());
        assertEquals(7753432, generator.next());
        assertEquals(5908254, generator.next());
    }
}
