package team.techtigers.core.leddisplay;

import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * A class that represents a region in which the number 11 is displayed and moves up and down
 */
public class ExampleRegion extends DisplayRegion {
    private final Sprite[] sprites;
    private final ExampleSprite oneInTensDigit;
    private final ExampleSprite oneInOnesDigit;
    private final ElapsedTime timer;

    public ExampleRegion(int x, int y) {
        super(x, y, 4, 7);
        this.oneInTensDigit = new ExampleSprite(0, 0);
        this.oneInOnesDigit = new ExampleSprite(2, 0);
        oneInTensDigit.setColor(Color.PINK);
        oneInOnesDigit.setColor(Color.BLUE);
        this.timer = new ElapsedTime();
        this.sprites = new Sprite[]{oneInTensDigit, oneInOnesDigit};
    }

    @Override
    public void update() {
        if (timer.milliseconds() > 1000) {
            if (oneInTensDigit.getY() == 1 || oneInOnesDigit.getY() == 1) {
                oneInOnesDigit.setPosition(oneInOnesDigit.getX(), oneInOnesDigit.getY() - 1);
                oneInTensDigit.setPosition(oneInTensDigit.getX(), oneInTensDigit.getY() - 1);
            }
            oneInTensDigit.setPosition(oneInTensDigit.getX(), oneInTensDigit.getY() + 1);
            oneInOnesDigit.setPosition(oneInOnesDigit.getX(), oneInOnesDigit.getY() + 1);
            timer.reset();
        } if (timer.milliseconds() > 500) {
            oneInTensDigit.setColor(Color.ORANGE);
            oneInOnesDigit.setColor(Color.PURPLE);
        }
    }

    @Override
    protected Sprite[] getSprites() {
        return this.sprites;
    }
}
