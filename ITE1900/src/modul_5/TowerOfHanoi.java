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

	private static int moveCounter;
	private final String VALID_REGEX = "\\d{0,10}";
	private boolean validInput = false;
	private final int maxDisks = 20;
	private static final StringBuilder sb = new StringBuilder();
	private static final String[] superString = { "\\u2070", "\\u00B9", "\\u00B2", "\\u00B3", "\\u2074", "\\u2075",
			"\\u2076", "\\u2077", "\\u2078", "\\u2079" };
	private static final char[] superScript = new char[10];
	private static BigInteger movesRequired;
	private static String[] complaints = { "No thanks.", "Yuck!", "F*ck that.", "You sadist.",
			"I'm calling the police.", "Ain't nobody got time for that!", "Do it yourself, man!" };
	public static TextField userInput;
	public static char fromTower = 'A';
	public static char toTower = 'B';
	public static char auxTower = 'C';
	private static ReadOnlyDoubleWrapper progress = new ReadOnlyDoubleWrapper();

	public static void main(String[] args) {

		for (int i = 0; i < superString.length; i++) {
			superScript[i] = (char) Integer.parseInt(superString[i].substring(2), 16);
		}

		launch(args);
	}

	public static void moveDisks(int n, char fromTower, char toTower, char auxTower) {

		if (n == 1) { // Stopping condition
			moveCounter += 1;
			sb.append(String.format(Locale.ROOT, "\r\nMove number %d: \t Move disk %s from %s to %s", moveCounter, n,
					fromTower, toTower));
			progress.set(moveCounter * 1.0 /movesRequired.doubleValue());
		}

		else {
			moveDisks(n - 1, fromTower, auxTower, toTower);
			moveCounter += 1;
			sb.append(String.format(Locale.ROOT, "\r\nMove number %d: \t Move disk %s from %s to %s", moveCounter, n,
					fromTower, toTower));
			progress.set(moveCounter * 1.0 /movesRequired.doubleValue());
			moveDisks(n - 1, auxTower, toTower, fromTower);
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		userInput = new TextField();
		userInput.setPromptText("Number of disks (1-" + maxDisks + ")");
		Button findMoves = new Button("Find moves");
		HBox input = new HBox(userInput, findMoves);

		TextArea output = new TextArea();
		output.setEditable(false);

		Text footer = new Text();

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
					sb.setLength(0);
					sb.append("Moves:");
					moveCounter = 0;

					userInput.setDisable(true);
					findMoves.setDisable(true);
					output.setDisable(true);
					output.setText("");
					userInput.getScene().setCursor(Cursor.WAIT);
					footer.setText("Working...");
					long startTime = System.currentTimeMillis();
					
					Task<Void> diskTask = new Task<Void>() {
						@Override
						protected Void call() {
							progress.addListener((obs, oldProgress, newProgress) -> 
							updateProgress(newProgress.doubleValue(), 1));
							moveDisks(Integer.parseInt(userInput.getText()), 'A', 'B', 'C');
							return null;
						}

					};
					
					ProgressBar diskProg = new ProgressBar();
					diskProg.progressProperty().bind(diskTask.progressProperty());
					pane.getChildren().add(diskProg);
					diskProg.layoutXProperty().bind(pane.widthProperty().divide(2).subtract(diskProg.widthProperty().divide(2)));
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
						output.setText(sb.toString());
						userInput.getParent().getParent().getScene().setCursor(Cursor.DEFAULT);
						double duration = (endTime - startTime);
						System.out.println("her");
						
						
						
						
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

//					new Thread(diskTask).start();

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
					footer.setText("");
					validInput = false;
					findMoves.setDisable(true);
					userInput.setText("");
				} else if (!newValue.matches(VALID_REGEX)) {
					userInput.setText(oldValue);
				} else {
					try {
						BigInteger bigOne = new BigInteger("1");
						BigInteger bigTwo = new BigInteger("2");
						movesRequired = bigTwo.pow(Integer.parseInt(userInput.getText()));
						movesRequired = movesRequired.subtract(bigOne);
					} catch (NumberFormatException e) {
						// Skal ikke forekomme, men for sikkerhets skyld:
						System.out.println(e.getStackTrace());
					}

					if (Integer.parseInt(newValue) <= maxDisks) {
						userInput.setStyle("");
						validInput = true;
						findMoves.setDisable(false);
					} else {
						userInput.setStyle("-fx-text-fill: #999;");
						validInput = false;
						findMoves.setDisable(true);
					}

					String str = movesRequired.toString();

					if (movesRequired.toString().length() > 9) {
						int power = movesRequired.toString().length() - 1;
						ArrayList<Integer> powerDigits = new ArrayList<Integer>();

						while (power > 0) {
							powerDigits.add(power % 10);
							power = power / 10;
						}

						Collections.reverse(powerDigits);

						str = movesRequired.toString().substring(0, 1) + "." + movesRequired.toString().substring(2, 6)
								+ "×10";
						for (int i : powerDigits) {
							str += Character.toString(superScript[i]);
						}

						Random r = new Random();
						int rIndex = r.nextInt(complaints.length);

						footer.setText(String.format(Locale.ROOT, "%s disk%s would require %s move%s. %s",
								userInput.getText(), userInput.getText().equals("1") ? "" : "s", str,
								userInput.getText().equals("1") ? "" : "s", complaints[rIndex]));

					} else {
						footer.setText(String.format("%s disk%s will require %d move%s.", userInput.getText(),
								userInput.getText().equals("1") ? "" : "s", movesRequired,
								movesRequired.toString().equals("1") ? "" : "s"));
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
		pane.prefWidthProperty().bind(pane.getScene().widthProperty());
		primaryStage.setTitle("Tower of Hanoi");
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.show();
		primaryStage.setMinWidth(primaryStage.getWidth());
	}
}