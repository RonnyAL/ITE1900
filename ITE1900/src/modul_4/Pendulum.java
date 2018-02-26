package modul_4;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
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

		// circle.centerXProperty().bind(anchor.centerXProperty().add(lineLength).multiply(Math.cos(Math.toRadians(curAngle.getValue()))));
		// circle.centerYProperty().bind(anchor.centerYProperty().add(lineLength).multiply(Math.sin(Math.toRadians(curAngle.getValue()))));


		primaryStage.setTitle("Pendulum"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage

//		Timeline animation = new Timeline(new KeyFrame(Duration.millis(100), e -> {
//			pane.next();
//		}));
//		animation.setCycleCount(Timeline.INDEFINITE);
//		animation.play(); // Start animation
//
//		pane.setOnMouseClicked(e -> {
//			if (animation.getStatus() == Animation.Status.RUNNING) {
//				animation.pause();
//			} else {
//				animation.play();
//			}
//		});
//
//		pane.requestFocus();
//		pane.setOnKeyPressed(e -> {
//			if (e.getCode() == KeyCode.UP) {
//				animation.setRate(animation.getRate() * 1.1);
//			} else if (e.getCode() == KeyCode.DOWN) {
//				animation.setRate(animation.getRate() * 0.9);
//			}
//		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}

class PendulumPane extends Pane {
	
	public char direction;
	public DoubleProperty curAngle = new SimpleDoubleProperty(120);
	public double lineLength = 5;
	Circle circle = new Circle();
	Circle anchor = new Circle();
	Line line = new Line();
	

	
	public PendulumPane() {
		direction = 'R';
		
		anchor.radiusProperty().bind(Bindings.min(heightProperty().divide(50), widthProperty().divide(50)));
		anchor.centerXProperty().bind(widthProperty().divide(2));
		anchor.centerYProperty().bind(heightProperty().divide(8));

		circle.radiusProperty().bind(Bindings.min(heightProperty().divide(20), widthProperty().divide(20)));
		circle.centerXProperty().bind(widthProperty().divide(2));
		circle.centerYProperty().bind(heightProperty().subtract(heightProperty().divide(6)));
		
		
		
		line.setStrokeWidth(3);
		line.strokeWidthProperty()
				.bind(Bindings.min(widthProperty().divide(180), heightProperty().divide(180)));

		line.startXProperty().bind(anchor.centerXProperty().add(circle.translateXProperty()));
		line.startYProperty().bind(anchor.centerYProperty().add(circle.translateYProperty()));

		line.endXProperty().bind(circle.centerXProperty());
		line.endYProperty().bind(circle.centerYProperty());
		
		Text t1 = new Text("Direction|angle: ");
		Text t2 = new Text();
		Text t3 = new Text();
		TextFlow tf = new TextFlow();
		
		t3.textProperty().bind(curAngle.asString());
		
		tf.getChildren().addAll(t1, t2, t3);
		
		getChildren().addAll(line, circle, anchor, tf);
		
		Timeline animation = new Timeline(new KeyFrame(Duration.millis(25), e -> {
			next();
		}));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play(); // Start animation

		setOnMouseClicked(e -> {
			if (animation.getStatus() == Animation.Status.RUNNING) {
				animation.pause();
			} else {
				animation.play();
			}
		});

		requestFocus();
		setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.UP) {
				animation.setRate(animation.getRate() * 3);
			} else if (e.getCode() == KeyCode.DOWN) {
				animation.setRate(animation.getRate() * 0.9);
			}
		});
		
		
	}

	public void next() {
		
		
		
		if (direction == 'R' && curAngle.doubleValue() > 60) {
			curAngle.set(curAngle.doubleValue()-1);
			direction = 'R';
			if (curAngle.doubleValue() == 60)
				direction = 'L';
		} else {
			curAngle.set(curAngle.doubleValue()+1);
			direction = 'L';
			if (curAngle.doubleValue() == 120)
				direction = 'R';
		}
		
		Point2D cPos = new Point2D(circle.getCenterX(), circle.getCenterY());
		Point2D aPos = new Point2D(anchor.getCenterX(), anchor.getCenterY());
//		lineLength = cPos.distance(aPos);
		lineLength = 247.91666666666669;
		
		
		circle.centerXProperty().bind(widthProperty().divide(2).add(anchor.centerXProperty().add(lineLength).multiply(Math.cos(Math.toRadians(curAngle.doubleValue())))));
		circle.centerYProperty().bind(heightProperty().divide(6).add(anchor.centerYProperty().add(lineLength).multiply(Math.sin(Math.toRadians(curAngle.doubleValue())))));

		
		
		

	}

}
