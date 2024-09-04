package team.techtigers.core.display;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ColorTest {
    @Nested
    @DisplayName("Color class")
    class ColorClass {
        @Test
        @DisplayName("Creates a color with RGBW values")
        void createsColorWithRGBWValues() {
            Color color = new Color((byte) 0x08, (byte) 0x00, (byte) 0x00, (byte) 0x00);
            assertEquals((byte) 0x08, color.red);
            assertEquals((byte) 0x00, color.green);
            assertEquals((byte) 0x00, color.blue);
            assertEquals((byte) 0x00, color.white);
        }

        @Test
        @DisplayName("Creates a color with RGB values")
        void createsColorWithRGBValues() {
            Color color = new Color((byte) 0x08, (byte) 0x00, (byte) 0x00);
            assertEquals((byte) 0x08, color.red);
            assertEquals((byte) 0x00, color.green);
            assertEquals((byte) 0x00, color.blue);
            assertEquals((byte) 0x00, color.white);
        }

        @Test
        @DisplayName("Colors with same RGBW values are equal")
        void colorsWithSameRGBWValuesAreEqual() {
            Color color1 = new Color((byte) 0x08, (byte) 0x00, (byte) 0x00, (byte) 0x00);
            Color color2 = new Color((byte) 0x08, (byte) 0x00, (byte) 0x00, (byte) 0x00);
            assertEquals(color1, color2);
        }

        @Test
        @DisplayName("Colors with different RGBW values are not equal")
        void colorsWithDifferentRGBWValuesAreNotEqual() {
            Color color1 = new Color((byte) 0x08, (byte) 0x00, (byte) 0x00, (byte) 0x00);
            Color color2 = new Color((byte) 0x00, (byte) 0x08, (byte) 0x00, (byte) 0x00);
            assertNotEquals(color1, color2);
        }

        @Test
        @DisplayName("toString returns correct string representation")
        void toStringReturnsCorrectStringRepresentation() {
            Color color = new Color((byte) 0x08, (byte) 0x00, (byte) 0x00, (byte) 0x00);
            assertEquals("Color(red=8, green=0, blue=0, white=0)", color.toString());
        }
    }
}