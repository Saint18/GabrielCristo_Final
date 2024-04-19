import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class WorkoutLogger implements InterfaceWorkoutLogger {
    private List<AbstractWorkout> workouts; // List to hold logged workouts

    /**
     * Constructs a WorkoutLogger with an empty list of workouts.
     */
    public WorkoutLogger() {
        this.workouts = new ArrayList<>();
    }

    /**
     * Logs a new workout, adding it to the list of workouts.
     *
     * @param workout The workout to be logged.
     */
    @Override
    public void logWorkout(AbstractWorkout workout) {
        if (workout == null) {
            throw new IllegalArgumentException("Workout cannot be null");
        }

        // Check if the workout is already logged
        for (AbstractWorkout currentWorkout : workouts) {
            if (currentWorkout.getWorkoutName().equals(workout.getWorkoutName())) {
                return;
            }
        }

        // if workout does not exist in ArrayList, add it
        workouts.add(workout);
    }

    /**
     * Retrieves all logged workouts.
     *
     * @return An unmodifiable list of logged workouts to ensure data integrity.
     */
    @Override
    public List<AbstractWorkout> getLoggedWorkouts() {
        return Collections.unmodifiableList(workouts); // Prevent external modifications. Make an immutable list for interface
    }

    /**
     * Optional method to remove a workout from the log.
     *
     * @param workout The workout to remove.
     * @return true if the workout was successfully removed, false otherwise.
     */
    public boolean removeWorkout(AbstractWorkout workout) {
        return workouts.remove(workout);
    }

    /**
     * Method to get the number of workouts.
     *
     * @return The number of logged workouts.
     */
    public int getNumberOfWorkouts() {
        return workouts.size();
    }
}
