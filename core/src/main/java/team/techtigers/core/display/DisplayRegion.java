package team.techtigers.core.display;


/**
 * An interface for a display region on the LEDs
 * Will be able to define a region within the LED arrays to be lit up in specific patterns
 */
public abstract class DisplayRegion {
    private final int height;
    private final int width;
    private final Color[][] leds;
    private int x;
    private int y;

    /**
     * Constructs a DisplayRegion
     *
     * @param x the x coordinate of the bottom left corner of the region
     * @param y the y coordinate of the bottom left corner of the region
     * @param width the width of the region
     * @param height the height of the region
     */
    protected DisplayRegion(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.leds = new Color[width][height];
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
    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the width of the sprite
     *
     * @return the width of the sprite
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of the sprite
     *
     * @return the height of the sprite
     */
    public int getHeight() {
        return height;
    }

    /**
     * A method which returns a two dimensional array of colors to be displayed on the LEDs
     *
     * @return a two dimensional array of colors to be displayed on the LEDs
     */
    public Color[][] render() {
        resetLeds();
        for (Sprite sprite : getSprites()) {
            sprite.show(leds);
        }
        return leds;
    }

    /**
     * Resets the LEDs in a region to black
     */
    private void resetLeds() {
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                leds[x][y] = Color.BLACK;
            }
        }
    }

    /**
     * An abstract method implemented by child classes which updates the LEDs
     */
    public abstract void update();

    /**
     * Returns an array of sprites that the region uses
     *
     * @return an array of sprites that the region uses
     */
    protected abstract Sprite[] getSprites();
}