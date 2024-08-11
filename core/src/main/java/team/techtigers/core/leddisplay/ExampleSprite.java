package team.techtigers.core.leddisplay;

/**
 * A class which represents a sprite shaped like the number 1
 */
public class ExampleSprite extends Sprite {

    /**
     * Creates a new example sprite
     *
     * @param x     the x coordinate of the bottom left corner of the sprite within the region
     * @param y     the y coordinate of the bottom left corner of the sprite within the region
     */
    public ExampleSprite(int x, int y) {
        super(x, y, 2,5);
    }


    @Override
    protected void showSprite(Color[][] leds) {
        leds[1][0] = getColor();
        leds[1][1] = getColor();
        leds[1][2] = getColor();
        leds[1][3] = getColor();
        leds[1][4] = getColor();
        leds[0][3] = getColor();
    }
}