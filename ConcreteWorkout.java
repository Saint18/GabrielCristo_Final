import java.util.List;

public class ConcreteWorkout extends AbstractWorkout implements InterfaceWorkout {

    public ConcreteWorkout(String workoutName) {
        super(workoutName);
    }

    /**
     * Adds an exercise to the workout. Overrides to include specific behavior if needed.
     *
     * @param exercise The exercise to add.
     */
    @Override
    public void addExercise(AbstractExercise exercise) {
        super.addExercise(exercise); 
        
    }

   
    @Override
    public boolean removeExercise(AbstractExercise exercise) {
        boolean removed = getExercises().remove(exercise);
        if (removed) {
            updateDuration();  // Recalculate duration if exercise is removed
        }
        return removed;
    }

    //Getters Setters
    @Override
    public List<AbstractExercise> getExercises() {
        return super.getExercises();
    }

    @Override
    public int getDurationMinutes() {
        return super.getDurationMinutes();
    }

    @Override
    public String getWorkoutName() {
        return super.getWorkoutName();
    }

    @Override
    public void setWorkoutName(String workoutName) {
        super.setWorkoutName(workoutName);
    }
}
