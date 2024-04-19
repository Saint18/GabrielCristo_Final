import java.util.ArrayList;
import java.util.List;

public class LinkedListWorkouts {
	private Node head; // Head of the linked list

	public LinkedListWorkouts() {
		this.head = null;
	}

	/**
	 * Logs a workout using a recursive method to add it to the linked list.
	 * 
	 * @param workout The workout to log.
	 */
	public void logWorkout(AbstractWorkout workout) {
		if (workout == null) {
			throw new IllegalArgumentException("Workout cannot be null");
		}
		head = addWorkoutRecursively(head, workout);
	}

	/**
	 * Helper method to recursively add a workout to the linked list.
	 * 
	 * @param current The current node in the linked list.
	 * @param workout The workout to add.
	 * @return The node after adding the workout.
	 */
	private Node addWorkoutRecursively(Node current, AbstractWorkout workout) {
		if (current == null) {
			return new Node(workout);
		} else {
			current.next = addWorkoutRecursively(current.next, workout);
			return current;
		}
	}

	/**
	 * Retrieves all workouts in the linked list.
	 * 
	 * @return A list of all workouts.
	 */
	public List<AbstractWorkout> getAllWorkouts() {
		List<AbstractWorkout> workouts = new ArrayList<>();
		Node current = head;
		while (current != null) {
			workouts.add(current.workout);
			current = current.next;
		}
		return workouts;
	}

}
