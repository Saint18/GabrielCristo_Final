public class Main {
    public static void main(String[] args) {
        // Create the model
        WorkoutLogger workoutLogger = new WorkoutLogger();

        // Create the view
        // Pass it 'null' for now, the controller will be set later
        ConcreteGui gui = new ConcreteGui(null);

        // Create the controller and link it with the model and view
        Controller controller = new Controller(workoutLogger, gui);

        // Set the controller to the GUI after it's created
        gui.setController(controller);

        controller.initialize();
    }
}
