package View;

import Controller.Controller;
import Model.Exceptions.MyException;

public class RunExampleCommand extends Command {

    private final Controller controller;

    public RunExampleCommand(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute() {
        this.controller.allSteps();
    }
}
