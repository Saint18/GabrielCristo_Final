import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private InterfaceWorkoutLogger workoutLogger; // Model to log workouts
    private InterfaceGui gui; // View to interact with the user

    /**
     * Constructor to initialize the Controller with its dependencies.
     */
    public Controller(InterfaceWorkoutLogger workoutLogger, InterfaceGui gui) {
        this.workoutLogger = workoutLogger;
        this.gui = gui;
    }

    /**
     * Initializes the GUI and sets up the initial state, including a default workout.
     */
    public void initialize() {
        gui.initialize();
        addWorkout("Daily Workout");
        gui.showMessage("Welcome to the Fitness Tracker App!");
    }

    /**
     * Adds a workout with the specified name.
     */
    public void addWorkout(String name) {
        try {
            ConcreteWorkout workout = new ConcreteWorkout(name);
            workoutLogger.logWorkout(workout);
            gui.update();
        } catch (Exception e) {
            gui.showMessage("Failed to add workout: " + e.getMessage());
        }
    }

    /**
     * Adds an exercise to the current workout.
     */
    public void addExercise(AbstractExercise exercise) {
        try {
            ConcreteWorkout workout = getCurrentWorkout();
            workout.addExercise(exercise);
            workoutLogger.logWorkout(workout);
            gui.update();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            gui.showMessage("Failed to add exercise: " + e.getMessage());
        }
    }

    /**
     * Retrieves the currently selected workout based on GUI selection.
     */
    private ConcreteWorkout getCurrentWorkout() {
        String workoutName = gui.getSelectedWorkout();

        for (AbstractWorkout workout : workoutLogger.getLoggedWorkouts()) {
            if (workout.getWorkoutName().equals(workoutName)) {
                return (ConcreteWorkout) workout;
            }
        }
        throw new IllegalArgumentException("Workout not found");
    }

    /**
     * Displays all logged workouts in the GUI.
     */
    public void displayWorkouts() {
        StringBuilder workoutsDisplay = new StringBuilder();
        for (AbstractWorkout workout : workoutLogger.getLoggedWorkouts()) {
            workoutsDisplay.append(workout.toString()).append("\n");
        }
        gui.showMessage(workoutsDisplay.toString());
    }

    /**
     * Exports all logged workout data to a text file.
     */
    public void exportWorkoutData() {
        File file = new File("WorkoutData.txt");
        try (PrintWriter out = new PrintWriter(file)) {
            workoutLogger.getLoggedWorkouts().forEach(workout -> out.println(workout.toString()));
            gui.showMessage("Data exported successfully to " + file.getAbsolutePath());
        } catch (FileNotFoundException e) {
            gui.showMessage("Failed to export data: " + e.getMessage());
        }
    }

    /**
     * Displays workout statistics in the GUI.
     */
    public void displayStatistics() {
        int totalWorkouts = workoutLogger.getNumberOfWorkouts();
        double totalVolume = workoutLogger.getLoggedWorkouts().stream()
            .flatMap(workout -> workout.getExercises().stream())
            .mapToDouble(AbstractExercise::calculateVolume)
            .sum();
        double averageDuration = workoutLogger.getLoggedWorkouts().stream()
            .mapToInt(AbstractWorkout::getDurationMinutes)
            .average()
            .orElse(0);
        String stats = String.format("Total Workouts: %d\nTotal Volume: %.2f kg\nAverage Workout Duration: %.2f minutes",
                                     totalWorkouts, totalVolume, averageDuration);
        gui.showMessage(stats);
    }

    /**
     * Compiles and returns a formatted string of all workouts and their exercises.
     */
    public String getWorkoutsDisplayText() {
        StringBuilder sb = new StringBuilder();
        List<AbstractWorkout> workouts = workoutLogger.getLoggedWorkouts();
        for (AbstractWorkout workout : workouts) {
            sb.append(workout.toString()).append("\n");
            for (AbstractExercise exercise : workout.getExercises()) {
                sb.append("   - ").append((ConcreteExercise)exercise).append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Returns a list of names of all logged workouts.
     */
    public List<String> getWorkoutNames() {
        List<String> workoutNames = new ArrayList<>();
        for (AbstractWorkout workout : workoutLogger.getLoggedWorkouts()) {
            workoutNames.add(workout.getWorkoutName());
        }
        return workoutNames;
    }
}
