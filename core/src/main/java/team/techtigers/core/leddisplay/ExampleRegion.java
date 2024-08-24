package team.techtigers.core.leddisplay;


/**
 * A class that represents a region in which the number 11 is displayed and moves up and down
 */
public class ExampleRegion extends DisplayRegion {
    private final Sprite[] sprites;
    private final ExampleSprite oneInTensDigit;
    private final ExampleSprite oneInOnesDigit;
    private double lastTime;

    /**
     * Creates a new example region
     */
    public ExampleRegion(int x, int y) {
        super(x, y, 4, 7);
        this.oneInTensDigit = new ExampleSprite(0, 0);
        this.oneInOnesDigit = new ExampleSprite(2, 0);
        oneInTensDigit.setColor(Color.PINK);
        oneInOnesDigit.setColor(Color.BLUE);
        lastTime = System.currentTimeMillis();
        this.sprites = new Sprite[]{oneInTensDigit, oneInOnesDigit};
    }

    @Override
    public void update() {
        if (System.currentTimeMillis()-lastTime > 1000) {
            if (oneInTensDigit.getY() == 1 || oneInOnesDigit.getY() == 1) {
                oneInOnesDigit.setPosition(oneInOnesDigit.getX(), oneInOnesDigit.getY() - 1);
                oneInTensDigit.setPosition(oneInTensDigit.getX(), oneInTensDigit.getY() - 1);
            }
            oneInTensDigit.setPosition(oneInTensDigit.getX(), oneInTensDigit.getY() + 1);
            oneInOnesDigit.setPosition(oneInOnesDigit.getX(), oneInOnesDigit.getY() + 1);
            lastTime = System.currentTimeMillis();
        } if (System.currentTimeMillis()-lastTime > 500) {
            oneInTensDigit.setColor(Color.ORANGE);
            oneInOnesDigit.setColor(Color.PURPLE);
        }
    }

    @Override
    protected Sprite[] getSprites() {
        return this.sprites;
    }
}
