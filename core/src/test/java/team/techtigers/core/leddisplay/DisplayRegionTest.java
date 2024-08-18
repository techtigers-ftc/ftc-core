package team.techtigers.core.leddisplay;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DisplayRegionTest {

    @Test
    void getXReturnsCorrectXCoordinate() {
        DisplayRegion region = new TestDisplayRegion(5, 10, 20, 30);
        assertEquals(5, region.getX());
    }

    @Test
    void getYReturnsCorrectYCoordinate() {
        DisplayRegion region = new TestDisplayRegion(5, 10, 20, 30);
        assertEquals(10, region.getY());
    }

    @Test
    void setPosUpdatesCoordinates() {
        DisplayRegion region = new TestDisplayRegion(5, 10, 20, 30);
        region.setPos(15, 25);
        assertEquals(15, region.getX());
        assertEquals(25, region.getY());
    }

    @Test
    void getWidthReturnsCorrectWidth() {
        DisplayRegion region = new TestDisplayRegion(5, 10, 20, 30);
        assertEquals(20, region.getWidth());
    }

    @Test
    void getHeightReturnsCorrectHeight() {
        DisplayRegion region = new TestDisplayRegion(5, 10, 20, 30);
        assertEquals(30, region.getHeight());
    }

    @Test
    void renderResetsLedsToBlack() {
        DisplayRegion region = new TestDisplayRegion(5, 10, 20, 30);
        Color[][] leds = region.render();
        for (int y = 0; y < region.getHeight(); y++) {
            for (int x = 0; x < region.getWidth(); x++) {
                assertEquals(Color.BLACK, leds[x][y]);
            }
        }
    }

    private static class TestDisplayRegion extends DisplayRegion {
        private Sprite[] sprites;

        protected TestDisplayRegion(int x, int y, int width, int height) {
            super(x, y, width, height);
            this.sprites = new Sprite[0];
        }

        @Override
        public void update() {
            // No-op for testing
        }

        @Override
        protected Sprite[] getSprites() {
            return sprites;
        }

        public void addSprite(Sprite sprite) {
            this.sprites = new Sprite[]{sprite};
        }
    }
}