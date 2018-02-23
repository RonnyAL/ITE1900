package modul_4;

import java.util.Random;

import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class CircleAndRect extends Application {

	private String currentShape;

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) {

		Pane centerPane = new Pane();
		centerPane.setBorder((new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT))));
		centerPane.setStyle("-fx-background-color: #fff;");

		BorderPane bp = new BorderPane();
		bp.setStyle("-fx-background-color: #729cc7;");

		Button btnRed = new Button("Rød");

		btnRed.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (currentShape == "Circle") {
					Circle c = null;
					int cIndex = getIndex(centerPane, "Circle");
					if (cIndex != -1) {
						c = (Circle) centerPane.getChildren().get(cIndex);
						c.setFill(Color.RED);
					}
				} else if (currentShape == "Rectangle") {
					Rectangle r = null;
					int rIndex = getIndex(centerPane, "Rectangle");
					if (rIndex != -1) {
						r = (Rectangle) centerPane.getChildren().get(rIndex);
						r.setFill(Color.RED);
					}
				}
			}
		});

		Button btnBlue = new Button("Blå");

		btnBlue.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (currentShape == "Circle") {
					Circle c = null;
					int cIndex = getIndex(centerPane, "Circle");
					if (cIndex != -1) {
						c = (Circle) centerPane.getChildren().get(cIndex);
						c.setFill(Color.BLUE);
					}
				} else if (currentShape == "Rectangle") {
					Rectangle r = null;
					int rIndex = getIndex(centerPane, "Rectangle");
					if (rIndex != -1) {
						r = (Rectangle) centerPane.getChildren().get(rIndex);
						r.setFill(Color.BLUE);
					}
				}

			}
		});

		VBox leftGroup = new VBox(5, btnRed, btnBlue);
		leftGroup.setAlignment(Pos.CENTER);
		// -----------

		bp.setLeft(leftGroup);
		bp.setCenter(centerPane);

		Button btnRotate = new Button("Rotér");
		VBox rightGroup = new VBox(5, btnRotate);
		rightGroup.setAlignment(Pos.CENTER);
		bp.setRight(rightGroup);

		Button btnSqr = new Button("Firkant");

		btnSqr.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Random rnd = new Random();
				Rectangle r = new Rectangle((rnd.nextInt(80 - 50) + 50), rnd.nextInt(80 - 50) + 50);

				// Fjern rektangel hvis den eksisterer
				int rIndex = getIndex(centerPane, "Rectangle");
				if (rIndex != -1) {
					centerPane.getChildren().remove(rIndex);
				}

				// Pytagoras sørger deler av rotert rektangel ikke havner utenfor centerPane
				int rXMax = (int) (centerPane.getWidth()
						- Math.sqrt(Math.pow(r.getWidth(), 2) + Math.pow(r.getHeight(), 2)));
				int rXMin = (int) Math.sqrt(Math.pow(r.getWidth(), 2) + Math.pow(r.getHeight(), 2));
				int rYMax = (int) (centerPane.getHeight()
						- Math.sqrt(Math.pow(r.getWidth(), 2) + Math.pow(r.getHeight(), 2)));
				int rYMin = (int) Math.sqrt(Math.pow(r.getWidth(), 2) + Math.pow(r.getHeight(), 2));

				r.setX(rnd.nextInt(rXMax - rXMin) + rXMin);
				r.setY(rnd.nextInt(rYMax - rYMin) + rYMin);

				double rXRatio = r.getX() / centerPane.getWidth();
				double rYRatio = r.getY() / centerPane.getHeight();

				r.xProperty().bind(centerPane.widthProperty().multiply(rXRatio));
				r.yProperty().bind(centerPane.heightProperty().multiply(rYRatio));

				r.setStrokeWidth(3);

				r.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent t) {
						r.toFront();
						r.setStroke(Color.MEDIUMORCHID);
						currentShape = "Rectangle";
						int cIndex = getIndex(centerPane, "Circle");
						if (cIndex != -1) {
							Circle c = (Circle) centerPane.getChildren().get(cIndex);
							c.setStroke(null);
						}
					}
				});

				currentShape = "Rectangle";

				centerPane.getChildren().add(r);
				Event.fireEvent(r, new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY, 1, true,
						true, true, true, true, true, true, true, true, true, null));

			}
		});

		btnRotate.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				int rIndex = getIndex(centerPane, "Rectangle");
				if (rIndex != -1) {
					Rectangle r = (Rectangle) centerPane.getChildren().get(rIndex);
					r.setRotate(r.getRotate() + 45);
				}
			}
		});

		Button btnCircle = new Button("Sirkel");

		btnCircle.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				int cIndex = getIndex(centerPane, "Circle");

				if (cIndex != -1) {
					centerPane.getChildren().remove(cIndex);
				}

				Random rnd = new Random();
				int cRadius = rnd.nextInt(40 - 25) + 25;
				Circle c = new Circle(cRadius);

				c.setCenterX(c.getRadius() + rnd.nextDouble() * (centerPane.getWidth() - c.getRadius() * 2));
				c.setCenterY(c.getRadius() + rnd.nextDouble() * (centerPane.getHeight() - c.getRadius() * 2));

				double cXRatio = c.getCenterX() / centerPane.getWidth();
				double cYRatio = c.getCenterY() / centerPane.getHeight();

				c.centerXProperty().bind(centerPane.widthProperty().multiply(cXRatio));
				c.centerYProperty().bind(centerPane.heightProperty().multiply(cYRatio));

				c.setStrokeWidth(3);

				c.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent t) {
						c.toFront();
						c.setStroke(Color.MEDIUMORCHID);
						currentShape = "Circle";
						int rIndex = getIndex(centerPane, "Rectangle");
						if (rIndex != -1) {
							Rectangle r = (Rectangle) centerPane.getChildren().get(rIndex);
							r.setStroke(null);
						}
					}
				});

				centerPane.getChildren().add(c);
				Event.fireEvent(c, new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY, 1, true,
						true, true, true, true, true, true, true, true, true, null));

			}
		});

		HBox bottomGroup = new HBox(5, btnSqr, btnCircle);
		bottomGroup.setAlignment(Pos.CENTER);
		bp.setBottom(bottomGroup);

		Scene scene = new Scene(bp, 350, 250);
		primaryStage.setTitle("Sirkel og rektangel");
		primaryStage.setMinHeight(250);
		primaryStage.setMinWidth(350);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	final void setCurrentShape(String currentShape) {
		this.currentShape = currentShape;
	}

	final int getIndex(Pane pane, String type) {

		if (type == "Circle") {
			try {

				if (pane.getChildren().get(0) instanceof Circle) {
					return 0;
				} else if (pane.getChildren().get(1) instanceof Circle) {
					return 1;
				}
			} catch (IndexOutOfBoundsException exc) {
				return -1;
			}
		} else if (type == "Rectangle") {
			try {
				if (pane.getChildren().get(0) instanceof Rectangle) {
					return 0;
				} else if (pane.getChildren().get(1) instanceof Rectangle) {
					return 1;
				}
			} catch (IndexOutOfBoundsException exc) {
				return -1;
			}
		}
		return -1;
	}

}
