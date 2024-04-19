import java.util.List;

public interface InterfaceWorkout {
    // Add an exercise to the workout
    void addExercise(AbstractExercise exercise);

    // Remove an exercise from the workout
    boolean removeExercise(AbstractExercise exercise);

    // Get a list of all exercises in the workout
    List<AbstractExercise> getExercises();

    // Get the total duration of the workout in minutes
    int getDurationMinutes();

    // Get the name of the workout
    String getWorkoutName();

   //Set the name of the workout
    void setWorkoutName(String workoutName);
}
