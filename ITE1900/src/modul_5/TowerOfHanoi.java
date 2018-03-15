package modul_5;

import javafx.application.Application;
import javafx.beans.value.*;
import javafx.concurrent.Task;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class TowerOfHanoi extends Application {
	/** Main method */

	static String strOutput;
	static int moveCounter;
	public static final String VALID_REGEX = "\\d{0,2}";
	static boolean validInput = false;
	public static final int maxDisks = 15;

	public static void main(String[] args) {
		launch(args);
	}

	public static void moveDisks(int n, char fromTower, char toTower, char auxTower) {

		if (n == 1) { // Stopping condition
			moveCounter += 1;
			strOutput += String.format("\r\nMove number %d: \t Move disk %s from %s to %s", moveCounter, n, fromTower,
					toTower);
		}

		else {
			moveDisks(n - 1, fromTower, auxTower, toTower);
			moveCounter += 1;
			strOutput += String.format("\r\nMove number %d: \t Move disk %s from %s to %s", moveCounter, n, fromTower,
					toTower);
			moveDisks(n - 1, auxTower, toTower, fromTower);
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		TextField userInput = new TextField();
		userInput.setPromptText("Number of disks (1-" + maxDisks + ")");
		Button findMoves = new Button("Find moves");
		HBox input = new HBox(userInput, findMoves);

		TextArea output = new TextArea();
		output.setEditable(false);
		output.setStyle(
				"-fx-background-color: -fx-outer-border, -fx-inner-border, -fx-body-color; -fx-faint-focus-color: transparent; -fx-background-insets: 0, 1, 2; -fx-background-radius: 5, 4, 3;");
		VBox content = new VBox(input, output);
		content.setPadding(new Insets(10, 0, 0, 10));
		content.setSpacing(10);
		Pane pane = new Pane(content);

		input.spacingProperty().bind(pane.widthProperty().multiply(0.025));

		pane.widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldVal, Number newVal) {
				userInput.setPrefWidth(newVal.doubleValue() * 0.7);
				findMoves.setPrefWidth(newVal.doubleValue() * 0.2);

			}
		});

		pane.heightProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldVal, Number newVal) {
				output.setPrefHeight(newVal.doubleValue() - 60);

			}
		});

		findMoves.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (validInput) {
					strOutput = "Moves:";
					moveCounter = 0;

					userInput.setDisable(true);
					findMoves.setDisable(true);
					output.setDisable(true);
					primaryStage.setTitle("Tower of Hanoi - Working...");
					userInput.getScene().setCursor(Cursor.WAIT);

					Task<Void> diskTask = new Task<Void>() {
						@Override
						protected Void call() {
							moveDisks(Integer.parseInt(userInput.getText()), 'A', 'B', 'C');
							strOutput += "\r\n\r\nNumber of calls to method: " + moveCounter;
							output.setText(strOutput);
							return null;
						}

					};

					diskTask.setOnSucceeded(ev -> {
						userInput.getParent().getParent().getScene().setCursor(Cursor.DEFAULT);
						userInput.setDisable(false);
						findMoves.setDisable(false);
						output.setDisable(false);
						primaryStage.setTitle("Tower of Hanoi");
					});

					new Thread(diskTask).start();

				} else {
					userInput.setPromptText("Please choose a positive integer!");
					userInput.setStyle("-fx-border-color: red");
				}

			}
		});

		userInput.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				userInput.setPromptText("Number of disks (1-" + maxDisks + ")");

				if (newValue.isEmpty() || newValue.equals("0")) {
					validInput = false;
					userInput.setText("");
				} else if (!newValue.matches(VALID_REGEX)) {
					userInput.setText(oldValue);
				} else {
					try {
						if (Integer.parseInt(newValue) <= maxDisks) {
							validInput = true;
						} else {
							userInput.setText(oldValue);
						}
					} catch (NumberFormatException e) {
						// Skal ikke forekomme, men for sikkerhets skyld:
						System.out.println(e.getStackTrace());
					}

				}

			}

		});

		userInput.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue,
					Boolean newPropertyValue) {
				if (newPropertyValue) {
					userInput.setPromptText("Number of disks (1-" + maxDisks + ")");
					userInput.setStyle("");
				}

			}
		});

		Scene scene = new Scene(pane, 600, 400);
		primaryStage.setTitle("Tower of Hanoi");
		primaryStage.setScene(scene);
		primaryStage.show();

	}
}