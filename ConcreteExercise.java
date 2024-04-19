public class ConcreteExercise extends AbstractExercise {

    /**
     * Constructs a ConcreteExercise with specified parameters.
     * @param name The name of the exercise.
     * @param reps The number of repetitions.
     * @param sets The number of sets.
     * @param weight The weight used in kilograms.
     * @param seconds The duration of the exercise in seconds.
     */
    public ConcreteExercise(String name, int reps, int sets, double weight, int seconds) {
        super(name, reps, sets, weight, seconds);
    }

    /**
     * Calculate the exercise volume as the product of weight, reps, and sets.
     * @return The calculated volume of the exercise.
     */
    @Override
    public double calculateVolume() {
        return getWeight() * getReps() * getSets();
    }

    /**
     * Provides a detailed description of the exercise, formatting it for readability.
     * @return A formatted string describing the exercise.
     */
    @Override
    public String getDescription() {
        return String.format("Exercise %s: Perform %d sets of %d reps at %.2f kg for %d seconds each.",
                             getName(), getSets(), getReps(), getWeight(), getSeconds());
    }
}
