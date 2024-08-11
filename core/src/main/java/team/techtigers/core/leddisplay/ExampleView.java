package team.techtigers.core.leddisplay;

/**
 * An example view period
 */
public class ExampleView extends DisplayView {

    /**
     * Creates a new view
     */
    public ExampleView() {
        super(new DisplayRegion[]{
                new ExampleRegion(0,0)
        });
    }
}
