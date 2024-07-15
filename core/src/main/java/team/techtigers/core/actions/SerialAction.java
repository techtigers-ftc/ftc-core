package team.techtigers.core.actions;

/**
 * Allows multiple servo actions to be performed in series
 */
public class SerialAction implements IAction {
    private final IAction[] actions;
    private int actionIndex;
    private boolean isFinished;

    /**
     * Initializes all of the values
     * @param actions array of actions
     */
    public SerialAction(IAction[] actions) {
        if (actions == null){
            throw new IllegalArgumentException("Invalid actions (arg #1)");
        }

        for (IAction action:actions) {
            if (action == null){
                throw new IllegalArgumentException("Invalid");
            }
        }
        this.actions = actions;
        actionIndex = 0;
        isFinished = false;
    }

    @Override
    public void start(){
        actionIndex = 0;
        actions[actionIndex].start();
        isFinished = false;
    }

    /**
     * updates all of the values along with the position of the servo
     */
    @Override
    public void update(){
        if (actions[actionIndex].isFinished()) {
            actionIndex++;
            if (actionIndex >= actions.length) {
                isFinished = true;
                return;
            }
            actions[actionIndex].start();
        }

        actions[actionIndex].update();
    }

    /**
     * Returns if the servo has reached its final position
     * @return true if finished, false if not finished
     */
    @Override
    public boolean isFinished() {
        return isFinished;
    }
}