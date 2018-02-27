package modul_4;

/* Det ble noe tilfeldig hvilke variabler i Pendulum-klassen som ble klassevariabler.
 * Dette grunnet mye eksperimentering og litt for lite tid. Leverer likevel som den er
 * da alt fungerer som det skal! :)
 */

import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Pendulum extends Application {

	private static Stage stage;
	Circle anchor = new Circle();
	Circle circle = new Circle();
	double startAngle = 120;
	double endAngle = 60;
	DoubleProperty curAngle = new SimpleDoubleProperty(startAngle);

	public void start(Stage primaryStage) {

		stage = primaryStage;

		PendulumPane pane = new PendulumPane();

		Scene scene = new Scene(pane, 450, 350);
		primaryStage.setTitle("Pendulum");
		primaryStage.setScene(scene);
		pane.requestFocus();
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public static Stage getStage() {
		return stage;
	}

}

class PendulumPane extends Pane {

	private char direction = 'R';
	private double curAngle = 120;
	private double lineLength = 5;
	private Circle circle = new Circle();
	private Circle anchor = new Circle();
	private Line line = new Line();
	Text tLVar = new Text();
	private Timeline animation;

	public PendulumPane() {

		/*
		 * Listeners tilknyttet stage width/height sørger for at innholdet i
		 * applikasjonen skaleres også når animasjonen er satt på pause.
		 */

		Pendulum.getStage().widthProperty().addListener((obs, oldVal, newVal) -> {
			if (animation.getStatus() == Status.PAUSED) {
				Point2D cPos = new Point2D(getWidth() / 2,
						Math.min(getWidth() - getWidth() / 6, getHeight() - getHeight() / 6));
				Point2D aPos = new Point2D(anchor.getCenterX(), anchor.getCenterY());
				lineLength = cPos.distance(aPos);
				circle.setCenterX(anchor.getCenterX() + lineLength * Math.cos(Math.toRadians(curAngle)));
				circle.setCenterY(anchor.getCenterY() + lineLength * Math.sin(Math.toRadians(curAngle)));
			}
		});

		Pendulum.getStage().heightProperty().addListener((obs, oldVal, newVal) -> {
			if (animation.getStatus() == Status.PAUSED) {
				Point2D cPos = new Point2D(getWidth() / 2,
						Math.min(getWidth() - getWidth() / 6, getHeight() - getHeight() / 6));
				Point2D aPos = new Point2D(anchor.getCenterX(), anchor.getCenterY());
				lineLength = cPos.distance(aPos);
				circle.setCenterX(anchor.getCenterX() + lineLength * Math.cos(Math.toRadians(curAngle)));
				circle.setCenterY(anchor.getCenterY() + lineLength * Math.sin(Math.toRadians(curAngle)));
			}
		});

		anchor.radiusProperty().bind(Bindings.min(heightProperty().divide(50), widthProperty().divide(50)));
		anchor.centerXProperty().bind(widthProperty().divide(2));
		anchor.centerYProperty().bind(heightProperty().divide(8));

		circle.radiusProperty().bind(Bindings.min(heightProperty().divide(20), widthProperty().divide(20)));

		line.setStrokeWidth(3);
		line.strokeWidthProperty().bind(Bindings.min(widthProperty().divide(180), heightProperty().divide(180)));

		line.startXProperty().bind(anchor.centerXProperty().add(circle.translateXProperty()));
		line.startYProperty().bind(anchor.centerYProperty().add(circle.translateYProperty()));

		line.endXProperty().bind(circle.centerXProperty());
		line.endYProperty().bind(circle.centerYProperty());

		StackPane pauseOverlay = new StackPane();
		pauseOverlay.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5);");
		pauseOverlay.prefWidthProperty().bind(widthProperty());
		pauseOverlay.prefHeightProperty().bind(heightProperty());

		Rectangle pauseRect1 = new Rectangle();
		pauseRect1.setFill(Color.BLACK);
		Rectangle pauseRect2 = new Rectangle();
		pauseRect2.setFill(Color.BLACK);
		HBox pauseIcon = new HBox(pauseRect1, pauseRect2);
		pauseIcon.spacingProperty().bind(widthProperty().divide(20));

		pauseOverlay.getChildren().addAll(pauseIcon);
		pauseOverlay.setVisible(false);
		pauseOverlay.toFront();

		pauseRect1.widthProperty().bind(pauseOverlay.widthProperty().divide(10));
		pauseRect2.widthProperty().bind(pauseOverlay.widthProperty().divide(10));
		pauseRect1.heightProperty().bind(pauseOverlay.heightProperty().divide(3));
		pauseRect2.heightProperty().bind(pauseOverlay.heightProperty().divide(3));
		pauseIcon.setAlignment(Pos.CENTER);

		getChildren().addAll(line, circle, anchor, pauseOverlay);

		animation = new Timeline(new KeyFrame(Duration.millis(100), e -> {
			next();
		}));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play(); // Start animation

		setOnMouseClicked(e -> {
			if (animation.getStatus() == Animation.Status.RUNNING) {
				animation.pause();
				pauseOverlay.setVisible(true);
			} else {
				animation.play();
				pauseOverlay.setVisible(false);
			}
		});

		setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.UP) {
				animation.setRate(animation.getRate() * 1.5);
				Pendulum.getStage().setTitle(String.format("Pendulum - Animation rate: %.2f", animation.getRate()));
			} else if (e.getCode() == KeyCode.DOWN) {
				animation.setRate(animation.getRate() / 1.5);
				Pendulum.getStage().setTitle(String.format("Pendulum - Animation rate: %.2f", animation.getRate()));

			}
		});

	}

	public void next() {

		if (direction == 'R' && curAngle > 60) {
			curAngle -= 1;
			direction = 'R';
			if (curAngle == 60)
				direction = 'L';
		} else {
			curAngle += 1;
			direction = 'L';
			if (curAngle == 120)
				direction = 'R';
		}

		Point2D cPos = new Point2D(getWidth() / 2,
				Math.min(getWidth() - getWidth() / 6, getHeight() - getHeight() / 6));
		Point2D aPos = new Point2D(anchor.getCenterX(), anchor.getCenterY());
		lineLength = cPos.distance(aPos);

		/*
		 * Hadde lyst å bruke binding her heller enn setCenterX() og setCenterY(), men
		 * får det ikke til å fungere skikkelig. Lar koden stå kommentert ut, så kan du
		 * frohåpentligvis si noe om hvorfor den ikke fungerer! :)
		 * 
		 */

		// circle.centerXProperty().bind(anchor.centerXProperty().add(lineLength).multiply(Math.cos(Math.toRadians(curAngle))));
		// circle.centerYProperty().bind(anchor.centerYProperty().add(lineLength).multiply(Math.sin(Math.toRadians(curAngle))));

		circle.setCenterX(anchor.getCenterX() + lineLength * Math.cos(Math.toRadians(curAngle)));
		circle.setCenterY(anchor.getCenterY() + lineLength * Math.sin(Math.toRadians(curAngle)));

	}

}
