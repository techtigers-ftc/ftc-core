package team.techtigers.core.display;

/**
 * An abstract class which represents a sprite with a position and size
 */
public abstract class Sprite {
    private final int height;
    private final int width;
    private int x;
    private int y;
    private boolean enabled;
    private Color color;

    /**
     * Constructs a sprite
     *
     * @param x the x coordinate of the bottom left corner of the sprite
     * @param y the y coordinate of the bottom left corner of the sprite
     * @param width the width of the sprite
     * @param height the height of the sprite
     */
    protected Sprite(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.enabled = true;
        this.color = Color.BLACK;
    }

    /**
     * Returns the x coordinate of the bottom left corner of the sprite within the region
     *
     * @return the x coordinate of the bottom left corner of the sprite within the region
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y coordinate of the bottom left corner of the sprite within the region
     *
     * @return the y coordinate of the bottom left corner of the sprite within the region
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the position of the sprite within the region
     *
     * @param x the x coordinate of the bottom left corner of the sprite within the region
     * @param y the y coordinate of the bottom left corner of the sprite within the region
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the color of the sprite
     *
     * @return gets the current color the sprite is set to
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the color of the number
     *
     * @param color the color to set the number to
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Enables the sprite
     */
    public void enable() {
        enabled = true;
    }

    /**
     * Disables the sprite
     */
    public void disable() {
        enabled = false;
    }

    /**
     * Returns whether or not the sprite is enabled
     *
     * @return whether or not the sprite is enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Shows the sprite on the LEDs
     *
     * @param leds and LED array for use in the visual subsystem
     */
    public void show(Color[][] leds) {
        if (enabled) {
            showSprite(leds);
        }
    }

    /**
     * Returns the width of the sprite
     *
     * @return the width of the sprite
     */
    protected int getWidth() {
        return width;
    }

    /**
     * Returns the height of the sprite
     *
     * @return the height of the sprite
     */
    protected int getHeight() {
        return height;
    }

    /**
     * Shows the sprite on the LEDs
     *
     * @param leds an LED array that represents all the LEDs
     */
    protected abstract void showSprite(Color[][] leds);

}
