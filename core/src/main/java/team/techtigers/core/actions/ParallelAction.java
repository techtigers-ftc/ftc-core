package team.techtigers.core.actions;

/**
 * Allows multiple servo actions to be performed at once
 */
public class ParallelAction implements IAction {
    private final IAction[] actions;

    /**
     * Initializes all of the values
     * @param actions array of actions
     */
    public ParallelAction(IAction[] actions) {
        if (actions == null){
            throw new IllegalArgumentException("Invalid actions (arg #1)");
        }

        for (int i = 0; i < actions.length; i++) {
            IAction action = actions[i];
            if (action == null){
                throw new IllegalArgumentException("Invalid. Index: " + i);
            }
        }
        this.actions = actions;
    }

    @Override
    public void start(){
        for (IAction action: actions) {
            action.start();
        }
    }

    /**
     * updates all of the values along with the position of the servo
     */
    @Override
    public void update(){
        for (IAction action: actions) {
            if (!action.isFinished()) {
                action.update();
            }
        }
    }

    /**
     * Returns if the servo has reached its final position
     * @return true if finished, false if not finished
     */
    @Override
    public boolean isFinished() {
        boolean result = true;
        for (IAction action: actions) {
            result = result && action.isFinished();
        }

        return result;
    }
}