package hw3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UtilsTest {
    @Test
    void evenOddNumberReturnsTrueForEvenNumbers() {
        assertTrue(Utils.evenOddNumber(-10));
        assertTrue(Utils.evenOddNumber(0));
        assertTrue(Utils.evenOddNumber(2));
    }

    @Test
    void evenOddNumberReturnsFalseForOddNumbers() {
        assertFalse(Utils.evenOddNumber(-11));
        assertFalse(Utils.evenOddNumber(3));
    }

    @Test // граничные негативные проверки
    void numberInIntervalReturnsFalseForNumbersBelowInterval() {
        assertFalse(Utils.numberInInterval(24));
        assertFalse(Utils.numberInInterval(25));
    }

    @Test // граничные негативные проверки
    void numberInIntervalReturnsFalseForNumbersAboveInterval() {
        assertFalse(Utils.numberInInterval(100));
        assertFalse(Utils.numberInInterval(101));
    }

    @Test // граничные позитивные проверки
    void numberInIntervalReturnsTrueForIntervalNumbers() {
        assertTrue(Utils.numberInInterval(26));
        assertTrue(Utils.numberInInterval(99));
        // проверка внутри интервала
        assertTrue(Utils.numberInInterval(63));
    }
}
