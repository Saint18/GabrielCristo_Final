import java.util.List;

public interface InterfaceWorkoutLogger {
    void logWorkout(AbstractWorkout workout);  // Method to log a new workout
    List<AbstractWorkout> getLoggedWorkouts();  // Method to retrieve all logged workouts
    int getNumberOfWorkouts();

}

