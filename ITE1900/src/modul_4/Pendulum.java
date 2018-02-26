package modul_4;

import javafx.animation.*;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Pendulum extends Application {

	Circle anchor = new Circle();
	Circle circle = new Circle();
	double startAngle = 120;
	double endAngle = 60;
	DoubleProperty curAngle = new SimpleDoubleProperty(startAngle);
	

	public void start(Stage primaryStage) {
		PendulumPane pane = new PendulumPane(); // du må lage klassen PendulumPane

		Scene scene = new Scene(pane, 450, 350);

		anchor.radiusProperty().bind(Bindings.min(pane.heightProperty().divide(50), pane.widthProperty().divide(50)));
		anchor.centerXProperty().bind(pane.widthProperty().divide(2));
		anchor.centerYProperty().bind(pane.heightProperty().divide(8));

		circle.radiusProperty().bind(Bindings.min(pane.heightProperty().divide(20), pane.heightProperty().divide(20)));
		circle.centerXProperty().bind(pane.widthProperty().divide(2));
		circle.centerYProperty().bind(pane.heightProperty().subtract(pane.heightProperty().divide(6)));
		
		
		Point2D cPos = new Point2D(circle.getCenterX(), circle.getCenterY());
		Point2D aPos = new Point2D(anchor.getCenterX(), anchor.getCenterX());
		double lineLength = aPos.distance(cPos);
		
		System.out.println(lineLength);
		System.out.println(anchor.getCenterX()+lineLength*Math.cos(Math.toRadians(curAngle.doubleValue())));
		System.out.println(anchor.getCenterY()+lineLength*Math.sin(Math.toRadians(curAngle.doubleValue())));
		
		circle.centerXProperty().bind(anchor.centerXProperty().add(lineLength).multiply(Math.cos(Math.toRadians(curAngle.getValue()))));
		circle.centerYProperty().bind(anchor.centerYProperty().add(lineLength).multiply(Math.sin(Math.toRadians(curAngle.getValue()))));
		
		

		Line line = new Line();
		line.setStrokeWidth(3);
		line.strokeWidthProperty()
				.bind(Bindings.min(pane.widthProperty().divide(180), pane.heightProperty().divide(180)));

		line.startXProperty().bind(anchor.centerXProperty().add(circle.translateXProperty()));
		line.startYProperty().bind(anchor.centerYProperty().add(circle.translateYProperty()));
		
		line.endXProperty().bind(circle.centerXProperty());
		line.endYProperty().bind(circle.centerYProperty());

		


		pane.getChildren().addAll(anchor, circle, line);

		primaryStage.setTitle("Pendulum"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage

		Timeline animation = new Timeline(new KeyFrame(Duration.millis(100), e -> {
			pane.next(circle, anchor, lineLength, curAngle.doubleValue());
		}));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play(); // Start animation

		pane.setOnMouseClicked(e -> {
			if (animation.getStatus() == Animation.Status.RUNNING) {
				animation.pause();
			} else {
				animation.play();
			}
		});

		pane.requestFocus();
		pane.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.UP) {
				animation.setRate(animation.getRate() * 1.1);
			} else if (e.getCode() == KeyCode.DOWN) {
				animation.setRate(animation.getRate() * 0.9);
			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}

class PendulumPane extends Pane {

	public void next(Circle c, Circle a, double lineLength, double curAngle) {

			curAngle -= 1;
			c.centerXProperty().bind(a.centerXProperty().add(lineLength).multiply(Math.cos(Math.toRadians(curAngle))));
			c.centerYProperty().bind(a.centerYProperty().add(lineLength).multiply(Math.sin(Math.toRadians(curAngle))));
		

	}

}
