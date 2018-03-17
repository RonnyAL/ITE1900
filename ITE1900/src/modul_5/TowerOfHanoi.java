package modul_5;

import java.math.BigInteger;
import java.util.*;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.value.*;
import javafx.concurrent.Task;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TowerOfHanoi extends Application {

	private final int MAXDISKS = 20;
	private final int MAXMOVELEN = 9;
	private static int moveCounter;

	private static final StringBuilder SB = new StringBuilder();
	private final String VALID_REGEX = "\\d{0,6}"; // Allowing only 6 digits in the text field to prevent lag.

	private static Text footer;
	private boolean validInput = false;

	// Saving superscript symbols as Unicode.
	private static final String[] superString = { "\\u2070", "\\u00B9", "\\u00B2", "\\u00B3", "\\u2074", "\\u2075",
			"\\u2076", "\\u2077", "\\u2078", "\\u2079" };
	private static final char[] superScript = new char[10];
	private static BigInteger movesRequired;
	private static String[] complaints = { "No thanks.", "Yuck!", "F*ck that.", "You sadist.",
			"I'm calling the police.", "Ain't nobody got time for that!", "Do it yourself, man!", "I'd rather die!",
			"Please don't, computers have feelings too." };
	private static ReadOnlyDoubleWrapper progress = new ReadOnlyDoubleWrapper();

	public static void main(String[] args) {
		launch(args);
	}

	public static void moveDisks(int n, char fromTower, char toTower, char auxTower) {

		if (moveCounter == movesRequired.intValue() - 1) {
			footer.setText("Writing result to screen..."); // Doesn't always work. :(
		}

		if (n == 1) {
			moveCounter += 1;
			SB.append(String.format("\r\nMove number %d: \t Move disk %s from %s to %s", moveCounter, n, fromTower,
					toTower));
			progress.set(moveCounter * 1.0 / movesRequired.doubleValue());
		}

		else {
			moveDisks(n - 1, fromTower, auxTower, toTower);
			moveCounter += 1;
			SB.append(String.format("\r\nMove number %d: \t Move disk %s from %s to %s", moveCounter, n,
					fromTower, toTower));
			progress.set(moveCounter * 1.0 / movesRequired.doubleValue());
			moveDisks(n - 1, auxTower, toTower, fromTower);
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		// Convert superString to character array.
		for (int i = 0; i < superString.length; i++) {
			superScript[i] = (char) Integer.parseInt(superString[i].substring(2), 16);
		}

		TextField userInput = new TextField();
		userInput.setPromptText("Number of disks (1-" + MAXDISKS + ")");
		Button findMoves = new Button("Find moves");
		findMoves.setDefaultButton(true);
		findMoves.setDisable(true);
		HBox input = new HBox(userInput, findMoves);

		TextArea output = new TextArea();
		output.setEditable(false);

		footer = new Text();

		VBox content = new VBox(input, output, footer);
		content.setPadding(new Insets(10, 10, 10, 10));
		content.setSpacing(10);
		Pane pane = new Pane(content);

		input.setPrefWidth(400);

		input.spacingProperty().bind(pane.widthProperty().multiply(0.025));

		output.prefWidthProperty().bind(pane.widthProperty().subtract(content.getPadding().getLeft())
				.subtract(content.getPadding().getRight()));
		output.prefHeightProperty().bind(pane.heightProperty().multiply(0.8));
		findMoves.prefWidthProperty()
				.bind(output.widthProperty().multiply(0.2).subtract(content.spacingProperty().divide(2)));
		userInput.prefWidthProperty()
				.bind(output.widthProperty().multiply(0.8).subtract(content.spacingProperty().divide(2)));
		footer.wrappingWidthProperty().bind(output.widthProperty());

		findMoves.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (validInput) {
					SB.setLength(0);
					SB.append("Moves:");
					moveCounter = 0;

					userInput.setDisable(true);
					findMoves.setDisable(true);
					output.setDisable(true);
					output.setText("");
					userInput.getScene().setCursor(Cursor.WAIT);
					footer.setText("Finding moves...");
					long startTime = System.currentTimeMillis();
					ProgressBar diskProg = new ProgressBar();

					Task<Void> diskTask = new Task<Void>() {
						@Override
						protected Void call() {
							progress.addListener(
									(obs, oldProgress, newProgress) -> updateProgress(newProgress.doubleValue(), 1));
							moveDisks(Integer.parseInt(userInput.getText()), 'A', 'B', 'C');
							return null;
						}

					};

					diskProg.progressProperty().bind(diskTask.progressProperty());
					pane.getChildren().add(diskProg);
					diskProg.layoutXProperty()
							.bind(pane.widthProperty().divide(2).subtract(diskProg.widthProperty().divide(2)));
					diskProg.layoutYProperty().bind(pane.heightProperty().divide(2));

					diskTask.setOnScheduled(s -> {
						diskProg.setOpacity(1);
					});

					diskTask.setOnSucceeded(ev -> {
						long endTime = System.currentTimeMillis();
						diskProg.setOpacity(0);
						userInput.setDisable(false);
						findMoves.setDisable(false);
						output.setDisable(false);
						output.setText(SB.toString());
						userInput.getParent().getParent().getScene().setCursor(Cursor.DEFAULT);
						double duration = (endTime - startTime);

						if (duration < 100) {
							footer.setText(String.format(Locale.ROOT, "%s move%s performed in %.0f ms.", moveCounter,
									userInput.getText().equals("1") ? "" : "s", duration));
						} else {
							footer.setText(String.format(Locale.ROOT, "%s move%s performed in %.2f seconds.",
									moveCounter, userInput.getText().equals("1") ? "" : "s", duration / 1000.0));
						}

					});

					Thread th = new Thread(diskTask);
					th.setDaemon(true);
					th.start();

				} else {
					userInput.setPromptText("Please choose a positive integer!");
					userInput.setStyle("-fx-border-color: red");
				}

			}
		});

		userInput.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				int rIndex = -1;
				userInput.setPromptText("Number of disks (1-" + MAXDISKS + ")");

				if (newValue.isEmpty() || newValue.equals("0")) {
					footer.setText("");
					validInput = false;
					findMoves.setDisable(true);
					userInput.setText("");
				} else if (!newValue.matches(VALID_REGEX)) {
					userInput.setText(oldValue);
				} else {

					BigInteger bigOne = new BigInteger("1");
					BigInteger bigTwo = new BigInteger("2");
					movesRequired = bigTwo.pow(Integer.parseInt(userInput.getText()));
					movesRequired = movesRequired.subtract(bigOne);

					if (Integer.parseInt(newValue) <= MAXDISKS) {
						userInput.setStyle("");
						validInput = true;
						findMoves.setDisable(false);
					} else {
						userInput.setStyle("-fx-text-fill: #999;");
						validInput = false;
						findMoves.setDisable(true);
						Random r = new Random();
						rIndex = r.nextInt(complaints.length);
					}

					String moveStr = String.format("%,d", movesRequired.intValue()).replaceAll(",", " ");

					if (movesRequired.toString().length() > MAXMOVELEN) {
						int power = movesRequired.toString().length() - 1;
						ArrayList<Integer> powerDigits = new ArrayList<Integer>();

						while (power > 0) {
							powerDigits.add(power % 10);
							power = power / 10;
						}

						Collections.reverse(powerDigits);

						moveStr = movesRequired.toString().substring(0, 1) + "."
								+ movesRequired.toString().substring(2, 6) + "×10";
						for (int i : powerDigits) {
							moveStr += Character.toString(superScript[i]);
						}

					}

					footer.setText(String.format("%s disk%s %s require %s move%s.%s", newValue,
							newValue.equals("1") ? "" : "s", Integer.parseInt(newValue) > MAXDISKS ? "would" : "will",
							moveStr, newValue.equals("1") ? "" : "s", rIndex == -1 ? "" : " " + complaints[rIndex]));
				}

			}

		});

		userInput.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue,
					Boolean newPropertyValue) {
				if (newPropertyValue) {
					userInput.setPromptText("Number of disks (1-" + MAXDISKS + ")");
					userInput.setStyle("");
				}

			}
		});

		Scene scene = new Scene(pane, 600, 400);
		pane.prefWidthProperty().bind(pane.getScene().widthProperty());
		primaryStage.setTitle("Tower of Hanoi");
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.show();
		primaryStage.setResizable(false);
		primaryStage.setMinWidth(primaryStage.getWidth());
	}
}