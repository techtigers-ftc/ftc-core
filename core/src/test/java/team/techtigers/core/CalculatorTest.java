package team.techtigers.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    @Nested
    @DisplayName("add()")
    class AddMethod {
        @Test
        @DisplayName("1 + 2 = 3")
        public void testAdd() {
            Calculator calculator = new Calculator();
            int result = calculator.add(1, 2);
            assertEquals(3, result);
        }
    }
}
