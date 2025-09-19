package team.techtigers.core.display;

import java.util.HashMap;


/**
 * Output subsystem that controls the visual feedback display
 */
public class VisualDisplay {
    private final AdafruitNeoPixel visualDisplay;
    private final HashMap<String, DisplayView> views;
    private DisplayView activeView;
    private int displayHeight;
    private int displayWidth;

    /**
     * Initializes a new visual feedback subsystem object. Obtains references to visual feedback
     *
     * @param visualDisplay the visual display object (adafruit neopixel)
     * @param defaultView   the default view to display
     */
    public VisualDisplay(AdafruitNeoPixel visualDisplay, DisplayView defaultView) {
        this.visualDisplay = visualDisplay;
        views = new HashMap<>();
        views.put("default", defaultView);
        activeView = defaultView;
    }

    /**
     * Adds a view to the list of views
     *
     * @param key  the key to access the view
     * @param view the view to add
     */
    public void addView(String key, DisplayView view) {
        views.put(key, view);
    }

    /**
     * Sets the active view
     *
     * @param key the key to access the view
     */
    public void setView(String key) {
        activeView = views.get(key);
    }

    /**
     * Finds the index of the LED in the array
     *
     * @param ledX the x coordinate of the LED
     * @param ledY the y coordinate of the LED
     * @return the index of the LED in the array
     */
    protected int findLedArrayIndex(int ledX, int ledY) {
        if (ledX < displayHeight) {
            ledX = displayHeight - ledX;
        } else {
            ledY = displayHeight - 1 - ledY;
        }

        if (ledX % 2 == 0) {
            return ledX * displayHeight + ledY;
        } else {
            return ledX * displayHeight + displayHeight - ledY;
        }
    }

    /**
     * Updates the color of the LEDs based on the current state of the robot
     */
    public void update() {
        visualDisplay.clearLeds();
        for (DisplayRegion region : activeView.getRegions()) {
            region.update();
        }

        for (DisplayRegion region : activeView.getRegions()) {
            Color[][] colors = region.render();
            for (int y = 0; y < region.getHeight(); y++) {
                for (int x = 0; x < region.getWidth(); x++) {
                    if (colors[x][y] == null) {
                        throw new RuntimeException("color is null:" + x + " " + y);
                    }
                    int ledIndex = findLedArrayIndex(region.getX() + x, region.getY() + y);
                    visualDisplay.setLeds(ledIndex, colors[x][y]);
                }
            }
        }
        visualDisplay.show();
    }
}
