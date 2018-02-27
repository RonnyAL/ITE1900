package modul_4;

import javafx.animation.*;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
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
	Text tLVar = new Text();
	Timeline animation;
	

	
	public PendulumPane() {
		direction = 'R';
		
		anchor.radiusProperty().bind(Bindings.min(heightProperty().divide(50), widthProperty().divide(50)));
		anchor.centerXProperty().bind(widthProperty().divide(2));
		anchor.centerYProperty().bind(heightProperty().divide(8));

		circle.radiusProperty().bind(Bindings.min(heightProperty().divide(20), widthProperty().divide(20)));
		
		
		line.setStrokeWidth(3);
		line.strokeWidthProperty()
				.bind(Bindings.min(widthProperty().divide(180), heightProperty().divide(180)));

		line.startXProperty().bind(anchor.centerXProperty().add(circle.translateXProperty()));
		line.startYProperty().bind(anchor.centerYProperty().add(circle.translateYProperty()));

		line.endXProperty().bind(circle.centerXProperty());
		line.endYProperty().bind(circle.centerYProperty());
		
		Text tAText = new Text("Angle: ");
		Text tAVar = new Text();
		TextFlow tfA = new TextFlow();
		
		tAVar.textProperty().bind(curAngle.asString());
		tfA.getChildren().addAll(tAText, tAVar);
		
		Text tLText = new Text("Linelength: ");
		tLVar = new Text();
		tLVar.setText(String.valueOf(lineLength));
		
		TextFlow tfL = new TextFlow(tLText, tLVar);
		
		VBox textStuff = new VBox(tfA, tfL);
		
		getChildren().addAll(line, circle, anchor, textStuff);
		
		animation = new Timeline(new KeyFrame(Duration.millis(40), e -> {
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
			curAngle.set(curAngle.doubleValue()-0.4);
			direction = 'R';
			if (curAngle.doubleValue() == 60)
				direction = 'L';
		} else {
			curAngle.set(curAngle.doubleValue()+0.4);
			direction = 'L';
			if (curAngle.doubleValue() == 120)
				direction = 'R';
		}
		
		Point2D cPos = new Point2D(getWidth()/2, Math.min(getWidth()-getWidth()/6, getHeight()-getHeight()/6));
		Point2D aPos = new Point2D(anchor.getCenterX(), anchor.getCenterY());
		lineLength = cPos.distance(aPos);

		tLVar.setText(String.valueOf(animation.getRate()));
		
		animation.setRate(animation.getRate() +0.1);


		
//		circle.centerXProperty().bind(anchor.centerXProperty().add(lineLength).multiply(Math.cos(Math.toRadians(curAngle.doubleValue()))));
//		cklircle.centerYProperty().bind(anchor.centerYProperty().add(lineLength).multiply(Math.sin(Math.toRadians(curAngle.doubleValue()))));

		
		circle.setCenterX(anchor.getCenterX()+lineLength*Math.cos(Math.toRadians(curAngle.doubleValue())));
		circle.setCenterY(anchor.getCenterY()+lineLength*Math.sin(Math.toRadians(curAngle.doubleValue())));
		
		
		
		
		

	}

}
