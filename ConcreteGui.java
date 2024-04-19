import javax.swing.text.AttributeSet;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

import java.awt.*;
import java.awt.event.ActionEvent;

public class ConcreteGui extends JFrame implements InterfaceGui {
    private static final long serialVersionUID = 1L;

    private JTextArea displayArea;
    private JTextField nameField, repsField, setsField, weightField, secondsField;
    private JButton addButton, clearButton, displayWorkoutsButton, exportDataButton, viewStatsButton, addWorkoutButton;
    private Controller controller;
    private JComboBox<String> workoutList;

    /**
     * Constructor for ConcreteGui. Initializes the controller and sets the frame title.
     * @param initialController the controller to handle logic operations
     */
    public ConcreteGui(Controller initialController) {
        super("Fitness Tracker App");
        this.controller = initialController;
    }

    /**
     * Sets the controller. This method allows the controller to be set after the GUI has been constructed.
     * @param controller the controller to set
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }

    /**
     * Initializes the GUI components and layout.
     */
    @Override
    public void initialize() {
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        JPanel formPanel = createFormPanel();
        JPanel buttonPanel = createButtonPanel();

        add(scrollPane, BorderLayout.CENTER);
        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * Handles the action performed when the add button is clicked.
     * It retrieves input data, creates an exercise, and adds it to the controller.
     * @param e the event that triggered this method
     */
    private void addButtonActionPerformed(ActionEvent e) {
        try {
            String name = nameField.getText().trim();
            int reps = parseIntField(repsField.getText(), "Reps");
            int sets = parseIntField(setsField.getText(), "Sets");
            double weight = parseDoubleField(weightField.getText(), "Weight");
            int seconds = parseIntField(secondsField.getText(), "Seconds");

            AbstractExercise exercise = new ConcreteExercise(name, reps, sets, weight, seconds);
            controller.addExercise(exercise);
            JOptionPane.showMessageDialog(this, "Exercise added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException iae) {
            JOptionPane.showMessageDialog(this, iae.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Failed to add exercise: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Parses a string to an integer. Throws IllegalArgumentException if input is invalid.
     * @param input the string to parse
     * @param fieldName the field name for the input, used in error messages
     * @return the parsed integer
     * @throws IllegalArgumentException if the input is invalid
     */
    private int parseIntField(String input, String fieldName) throws IllegalArgumentException {
        input = input.trim();
        if (input.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be blank.");
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input for " + fieldName + ": '" + input + "'. Please ensure it's a valid integer.");
        }
    }

    /**
     * Parses a string to a double. Throws IllegalArgumentException if input is invalid.
     * @param input the string to parse
     * @param fieldName the field name for the input, used in error messages
     * @return the parsed double
     * @throws IllegalArgumentException if the input is invalid
     */
    private double parseDoubleField(String input, String fieldName) throws IllegalArgumentException {
        input = input.trim();
        if (input.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be blank.");
        }
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input for " + fieldName + ": '" + input + "'. Please ensure it's a valid number.");
        }
    }
    /**
     * Creates the form panel with input fields for user interaction.
     * This method sets up a grid layout for inputs related to workout sessions including workout selection, name, and reps.
     * It ensures that the 'reps' field only accepts numerical input to prevent formatting errors.
     * @return JPanel that contains all the labeled input fields for capturing workout details.
     */
	private JPanel createFormPanel() {
	    JPanel formPanel = new JPanel(new GridLayout(6, 2)); 

	    formPanel.add(new JLabel("Select Workout:"));  
	    workoutList = new JComboBox<>();  

	    formPanel.add(workoutList); 

	    formPanel.add(new JLabel("Name:"));  
	    nameField = new JTextField();  

	    nameField.setText("");  

	    formPanel.add(nameField);  

	    formPanel.add(new JLabel("Reps:")); 
	    repsField = new JTextField();  

	    repsField.setText("");  

	    ((PlainDocument) repsField.getDocument()).setDocumentFilter(new DocumentFilter() {
	        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
	                throws BadLocationException {
	            if (string.matches("\\d*")) {  
	                super.insertString(fb, offset, string, attr);
	            }
	        }
	        /**
	         * Overrides the replace method to filter text input in text fields.
	         * This implementation checks if the provided text consists only of digits before allowing the replacement.
	         * It ensures that only numeric values can be entered, which is crucial for fields like 'reps' and 'sets' where only numeric input is valid.
	         * @param fb the FilterBypass that allows mutation of the document
	         * @param offset the offset from the beginning of the document at which the replacement will occur
	         * @param length the length of the text to replace
	         * @param text the text that will replace the existing content
	         * @param attrs the set of attributes for the text (if any)
	         * @throws BadLocationException if the insertion position is less than zero or greater than the length of the document
	         */
	        @Override
	        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
	                throws BadLocationException {
	            if (text.matches("\\d*")) {  
	                super.replace(fb, offset, length, text, attrs);
	            }
	        }
	    });
	    formPanel.add(repsField);  

	    formPanel.add(new JLabel("Sets:"));  
	    setsField = new JTextField();  

	    setsField.setText("");  

	    ((PlainDocument) setsField.getDocument()).setDocumentFilter(new DocumentFilter() {
	    	/**
	    	 * Overrides the insertString method to enforce numeric-only input in text fields.
	    	 * This method is used to intercept the process of inserting text into a document, ensuring that only digits are entered.
	    	 * @param fb the FilterBypass that provides the means to mutate the document
	    	 * @param offset the position in the document where the text should be inserted
	    	 * @param string the string to be inserted
	    	 * @param attr the set of attributes that might accompany the string
	    	 * @throws BadLocationException if the offset is invalid or out of bounds of the document's length
	    	 */
	    	@Override
	        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
	                throws BadLocationException {
	            if (string.matches("\\d*")) {  // Regex to allow only digits
	                super.insertString(fb, offset, string, attr);
	            }
	        }
	    	/**
	    	 * Overrides the replace method to restrict text field input to numeric characters only.
	    	 * This method intercepts text replacement in the document, applying a filter that permits only numeric values.
	    	 * @param fb the FilterBypass that allows mutation of the document
	    	 * @param offset the offset from the beginning of the document at which the replacement will occur
	    	 * @param length the length of the text to replace
	    	 * @param text the text that will replace the existing content
	    	 * @param attrs the set of attributes for the text (if any)
	    	 * @throws BadLocationException if attempting to insert beyond the document's bounds
	    	 */
	        @Override
	        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
	                throws BadLocationException {
	            if (text.matches("\\d*")) {  
	                super.replace(fb, offset, length, text, attrs);
	            }
	        }
	    });
	    formPanel.add(setsField);  

	    formPanel.add(new JLabel("Weight:"));  
	    weightField = new JTextField();  

	    weightField.setText("");  

	    formPanel.add(weightField);  

	    formPanel.add(new JLabel("Seconds:"));  
	    secondsField = new JTextField();  

	    secondsField.setText(""); 

	    formPanel.add(secondsField);  

	    return formPanel;  
	}

	/**
	 * Handles the action of adding a new workout.
	 * This method prompts the user to enter a workout name through a dialog box.
	 * It performs validation to ensure that the input is not null or empty, displaying an error message if it is.
	 * If valid, it adds the trimmed workout name to the controller.
	 */
	private void addWorkoutAction() {
	    String workoutName = JOptionPane.showInputDialog(null, "Workout name:"); 

	    if (workoutName == null) {  
	        return; 
	    }

	    if (workoutName.trim().isEmpty()) {  
	        JOptionPane.showMessageDialog(this, "Workout name cannot be blank.", "Input Error", JOptionPane.ERROR_MESSAGE);  
	        return; 
	    }

	    controller.addWorkout(workoutName.trim());  
	}
	/**
	 * Creates a panel containing buttons for user interaction with the application.
	 * This method initializes buttons for adding workouts, adding exercises, clearing inputs, displaying workouts,
	 * exporting data, and viewing statistics. Each button is assigned an action listener that defines its behavior.
	 * The method organizes these buttons into a panel that is then returned for inclusion in the main GUI.
	 * @return JPanel containing all operational buttons for the application interface
	 */
	private JPanel createButtonPanel() {
	    JPanel buttonPanel = new JPanel();  

	    // Initialize buttons with labels
	    addWorkoutButton = new JButton("Add Workout");
	    addButton = new JButton("Add Exercise");
	    clearButton = new JButton("Clear");
	    displayWorkoutsButton = new JButton("Display Workouts");
	    exportDataButton = new JButton("Export Data");
	    viewStatsButton = new JButton("View Stats");

	    // Add action listeners to buttons
	    addWorkoutButton.addActionListener(e -> addWorkoutAction());
	    addButton.addActionListener(this::addButtonActionPerformed);
	    clearButton.addActionListener(e -> clear());
	    displayWorkoutsButton.addActionListener(this::displayWorkoutsActionPerformed);
	    exportDataButton.addActionListener(e -> controller.exportWorkoutData());
	    viewStatsButton.addActionListener(e -> controller.displayStatistics());

	    // Add buttons to the panel
	    buttonPanel.add(addWorkoutButton);
	    buttonPanel.add(addButton);
	    buttonPanel.add(clearButton);
	    buttonPanel.add(displayWorkoutsButton);
	    buttonPanel.add(exportDataButton);
	    buttonPanel.add(viewStatsButton);

	    return buttonPanel;  
	}

	/**
	 * Responds to the display workouts button action by calling the controller's method to display all workouts.
	 * This method is typically called when the user clicks the 'Display Workouts' button.
	 * @param e the ActionEvent triggered by button click
	 */
	private void displayWorkoutsActionPerformed(ActionEvent e) {
	    controller.displayWorkouts();  
	}

	/**
	 * Retrieves the workout name currently selected in the workoutList dropdown.
	 * This method is useful for obtaining the user-selected workout from the GUI.
	 * @return String representing the selected workout name
	 */
	public String getSelectedWorkout() {
	    return (String) workoutList.getSelectedItem();  
	}

	/**
	 * Updates the GUI components with current data from the controller.
	 * This includes updating the text in the display area and refreshing the workout list dropdown.
	 * It is called whenever the underlying data changes that need to be reflected in the GUI.
	 */
	@Override
	public void update() {
	    displayArea.setText(controller.getWorkoutsDisplayText());  

	    workoutList.removeAllItems();  
	    for (String workoutName : controller.getWorkoutNames()) {  
	        workoutList.addItem(workoutName); 
	    }
	}
	/**
	 * Displays a message to the user using a dialog box.
	 * This method is often used for showing alerts, errors, or informational messages.
	 * @param message the message to display in the dialog box
	 */

	@Override
	public void showMessage(String message) {
	    JOptionPane.showMessageDialog(this, message);  
	}

	/**
	 * Clears all the user input fields and the display area in the GUI.
	 * This method resets the text fields for name, reps, sets, weight, and seconds to empty strings,
	 * and clears any text displayed in the main display area.
	 */
	@Override
	public void clear() {
		nameField.setText("");  
		repsField.setText("");
		setsField.setText("");
		weightField.setText("");
		secondsField.setText("");
		displayArea.setText("");
	}
}
