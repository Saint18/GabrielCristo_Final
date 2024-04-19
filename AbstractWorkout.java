import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWorkout implements InterfaceWorkout {
	private List<AbstractExercise> exercises;
	private String workoutName;
	private int durationMinutes; // Total duration of the workout in minutes

	/**
	 * Constructor for initializing the workout with a name.
	 *
	 * @param workoutName Name of the workout.
	 */
	public AbstractWorkout(String workoutName) {
		this.workoutName = workoutName;
		this.exercises = new ArrayList<>();
		this.durationMinutes = 0; // Initialize to zero until exercises are added
	}

	// Getters
	public String getWorkoutName() {
		return workoutName;
	}

	public List<AbstractExercise> getExercises() {
		return new ArrayList<>(exercises);
	} 

	public int getDurationMinutes() {
		return durationMinutes;
	}

	// Setter for workout name with validation
	public void setWorkoutName(String workoutName) {
		if (workoutName == null || workoutName.trim().isEmpty()) {
			throw new IllegalArgumentException("Workout name cannot be empty");
		}
		this.workoutName = workoutName;
	}

	/**
	 * Adds an exercise to the workout and updates the duration.
	 *
	 * @param exercise The exercise to add.
	 */
	@Override
	public void addExercise(AbstractExercise exercise) {
		if (exercise == null) {
			throw new IllegalArgumentException("Exercise cannot be null");
		}
		exercises.add(exercise);
		updateDuration();
	}

	/**
	 * Recursive helper method to recalculate the duration of the workout. 
	 */
	

	
	private int calculateDurationRecursively(int index) {
		if (index < 0) {
			return 0;
		} else {
			return exercises.get(index).getSeconds() / 60 + calculateDurationRecursively(index - 1);
		}
	}
	
	protected void updateDuration() {
	    durationMinutes = calculateDurationRecursively(exercises.size() - 1);
	}


	@Override
	public String toString() {
		return String.format("Workout Name: %s, Duration: %d minutes, Exercises: %d", workoutName, durationMinutes,
				exercises.size());
	}
}
