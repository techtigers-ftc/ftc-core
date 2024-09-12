package team.techtigers.core.display;

/**
 * Class to represent the bytes in an RGB or RGBW color.
 */
public class Color {

    /**
     * RGB for Black (LEDs off)
     */
    public static final Color BLACK = new Color((byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00);

    /**
     * RGBW for White
     */
    public static final Color WHITE = new Color((byte) 0x0F, (byte) 0x0F, (byte) 0x0F, (byte) 0x08);

    /**
     * RGB for Red
     */
    public static final Color RED = new Color((byte) 0x08, (byte) 0x00, (byte) 0x00, (byte) 0x00);

    /**
     * RGB for Orange
     */
    public static final Color ORANGE = new Color((byte) 0x06, (byte) 0x01, (byte) 0x00, (byte) 0x00);

    /**
     * RGB for Yellow
     */
    public static final Color YELLOW = new Color((byte) 0x0B, (byte) 0x08, (byte) 0x00, (byte) 0x00);

    /**
     * RGB for Green
     */
    public static final Color GREEN = new Color((byte) 0x00, (byte) 0x08, (byte) 0x00, (byte) 0x00);

    /**
     * RGB for Blue
     */
    public static final Color BLUE = new Color((byte) 0x00, (byte) 0x00, (byte) 0x08, (byte) 0x00);

    /**
     * RGB for Purple
     */
    public static final Color PURPLE = new Color((byte) 0x08, (byte) 0x00, (byte) 0x08, (byte) 0x00);

    /**
     * RGB for Pink
     */
    public static final Color PINK = new Color((byte) 0x08, (byte) 0x00, (byte) 0x03, (byte) 0x00);

    /**
     * The red value of the color
     */
    public final byte red;

    /**
     * The green value of the color
     */
    public final byte green;

    /**
     * The blue value of the color
     */
    public final byte blue;

    /**
     * The white value of the color
     */
    public final byte white;

    /**
     * Initializes a Color instance with RGBW values.
     *
     * @param red   The red value
     * @param green The green value
     * @param blue  The blue value
     * @param white The white value
     */
    public Color(byte red, byte green, byte blue, byte white) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.white = white;
    }

    /**
     * Overloaded constructor to only take RGB values.
     *
     * @param red   The red value
     * @param green The green value
     * @param blue  The blue value
     */
    public Color(byte red, byte blue, byte green) {
        this(red, green, blue, (byte) 0x00);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Color) {
            Color other = (Color) obj;
            return red == other.red
                    && green == other.green
                    && blue == other.blue
                    && white == other.white;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Color(red=%d, green=%d, blue=%d, white=%d)", red, green, blue, white);
    }

}
