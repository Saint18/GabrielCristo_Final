public abstract class AbstractExercise {
	private String name;
	private int reps;
	private int sets;
	private double weight;
	private int seconds;

	/**
	 * Constructor to initialize an exercise with all attributes.
	 * 
	 * @param name Name of the exercise.
	 * @param reps Number of repetitions.
	 * @param sets Number of sets.
	 * @param weight Weight used in the exercise.
	 * @param seconds Duration of the exercise in seconds.
	 */
	public AbstractExercise(String name, int reps, int sets, double weight, int seconds) {
		setName(name);
		setReps(reps);
		setSets(sets);
		setWeight(weight);
		setSeconds(seconds);
	}

	// Getters for all properties
	public String getName() {
		return name;
	}

	public int getReps() {
		return reps;
	}

	public int getSets() {
		return sets;
	}

	public double getWeight() {
		return weight;
	}

	public int getSeconds() {
		return seconds;
	}

	// Setters with validation
	public void setName(String name) {
		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("Name cannot be null or empty");
		}
		this.name = name;
	}

	public void setReps(int reps) {
		if (reps < 0) {
			throw new IllegalArgumentException("Reps cannot be negative");
		}
		this.reps = reps;
	}

	public void setSets(int sets) {
		if (sets < 0) {
			throw new IllegalArgumentException("Sets cannot be negative");
		}
		this.sets = sets;
	}

	public void setWeight(double weight) {
		if (weight < 0) {
			throw new IllegalArgumentException("Weight cannot be negative");
		}
		this.weight = weight;
	}

	public void setSeconds(int seconds) {
		if (seconds < 0) {
			throw new IllegalArgumentException("Seconds cannot be negative");
		}
		this.seconds = seconds;
	}

	/**
	 * Calculate the exercise volume based on formula reps*weight*sets.
	 * 
	 * @return Exercise volume as a double.
	 */
	public abstract double calculateVolume();

	/**
	 * Get a detailed description of the exercise.
	 * 
	 * @return Description as a String.
	 */
	public abstract String getDescription();

	@Override
	public String toString() {
		return String.format("Exercise: %s, Reps: %d, Sets: %d, Weight: %.2f kg, Duration: %d seconds, Volume: %.2f",
				name, reps, sets, weight, seconds, calculateVolume());
	}
}
