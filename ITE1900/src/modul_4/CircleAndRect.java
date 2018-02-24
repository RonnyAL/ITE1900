package modul_4;

import java.util.Random;

import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class CircleAndRect extends Application {

	private String currentShape;
	private Ellipse c = new Ellipse();
	private Rectangle r = new Rectangle();

	private double rWRatio;
	private double rHRatio;
	private double rXRatio;
	private double rYRatio;

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

		HBox allTxtVBox = new HBox();
		allTxtVBox.setAlignment(Pos.CENTER);
		allTxtVBox.setPadding(new Insets(5));
		allTxtVBox.spacingProperty().bind(centerPane.widthProperty().divide(2));

		Text rectX = new Text();
		Text rectY = new Text();
		TextFlow rectXFlow = new TextFlow(new Text("X: "));
		TextFlow rectYFlow = new TextFlow(new Text("Y: "));
		rectXFlow.getChildren().add(rectX);
		rectYFlow.getChildren().add(rectY);
		VBox rectVTxt = new VBox(new Text("[REKTANGEL]"), rectXFlow, rectYFlow);
		rectX.textProperty().bindBidirectional(r.xProperty(), new NumberStringConverter());
		rectY.textProperty().bindBidirectional(r.yProperty(), new NumberStringConverter());

		Text circleX = new Text();
		Text circleY = new Text();
		TextFlow circleXFlow = new TextFlow(new Text("X: "));
		TextFlow circleYFlow = new TextFlow(new Text("Y: "));

		circleXFlow.getChildren().add(circleX);
		circleYFlow.getChildren().add(circleY);
		VBox circleVTxt = new VBox(new Text("[SIRKEL]"), circleXFlow, circleYFlow);
		circleX.textProperty().bindBidirectional(c.centerXProperty(), new NumberStringConverter());
		circleY.textProperty().bindBidirectional(c.centerYProperty(), new NumberStringConverter());

		rectVTxt.setVisible(false);
		circleVTxt.setVisible(false);

		allTxtVBox.getChildren().addAll(rectVTxt, circleVTxt);

		allTxtVBox.minWidthProperty().bind(centerPane.widthProperty());

		bp.setTop(allTxtVBox);

		Button btnRed = new Button("Rød");

		btnRed.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (currentShape == "Ellipse") {
					c = null;
					int cIndex = getIndex(centerPane, "Ellipse");
					if (cIndex != -1) {
						c = (Ellipse) centerPane.getChildren().get(cIndex);
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
				if (currentShape == "Ellipse") {
					c = null;
					int cIndex = getIndex(centerPane, "Ellipse");
					if (cIndex != -1) {
						c = (Ellipse) centerPane.getChildren().get(cIndex);
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
				int maxLength = (int) centerPane.getWidth() / 5;
				int minLength = (int) centerPane.getHeight() / 5;

				rectX.textProperty().unbindBidirectional(r.xProperty());
				rectY.textProperty().unbindBidirectional(r.yProperty());

				r = new Rectangle((rnd.nextInt(maxLength - minLength) + minLength),
						rnd.nextInt(maxLength - minLength) + minLength);

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

				try {
					r.setY(rnd.nextInt(rYMax - rYMin) + rYMin);
				} catch (IllegalArgumentException exc) {
					System.out.println(
							"Blir ikke kvitt denne feilen. YMin blir på et eller annet vis større enn YMax med enkelte rektangler.");
					exc.printStackTrace();
				}

				rXRatio = r.getX() / centerPane.getWidth();
				rYRatio = r.getY() / centerPane.getHeight();
				rWRatio = r.getWidth() / centerPane.getWidth();
				rHRatio = r.getHeight() / centerPane.getHeight();

				r.xProperty().bind(centerPane.widthProperty().multiply(rXRatio));
				r.yProperty().bind(centerPane.heightProperty().multiply(rYRatio));
				r.widthProperty().bind(centerPane.widthProperty().multiply(rWRatio));
				r.heightProperty().bind(centerPane.heightProperty().multiply(rHRatio));

				r.setStrokeWidth(3);

				r.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent t) {
						r.toFront();
						r.setStroke(Color.MEDIUMORCHID);
						currentShape = "Rectangle";
						int cIndex = getIndex(centerPane, "Ellipse");
						if (cIndex != -1) {
							Ellipse c = (Ellipse) centerPane.getChildren().get(cIndex);
							c.setStroke(null);
						}
					}
				});

				rectX.textProperty().bindBidirectional(r.xProperty(), new NumberStringConverter());
				rectY.textProperty().bindBidirectional(r.yProperty(), new NumberStringConverter());

				rectVTxt.setVisible(true);

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
					r = (Rectangle) centerPane.getChildren().get(rIndex);

					r.setRotate(r.getRotate() + 45);
					if (r.getRotate() == 360) {
						r.setRotate(0);
					}

				}
			}
		});

		Button btnCircle = new Button("Sirkel");

		btnCircle.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				int cIndex = getIndex(centerPane, "Ellipse");

				if (cIndex != -1) {
					centerPane.getChildren().remove(cIndex);
				}

				Random rnd = new Random();
				int maxRadius = Math.min((int) centerPane.getHeight() / 7, (int) centerPane.getWidth() / 7);
				int minRadius = Math.min((int) centerPane.getHeight() / 12, (int) centerPane.getWidth() / 12);

				int cRadius = rnd.nextInt(maxRadius - minRadius) + minRadius;

				circleX.textProperty().unbindBidirectional(c.centerXProperty());
				circleY.textProperty().unbindBidirectional(c.centerYProperty());
				c = new Ellipse(cRadius, cRadius);

				c.setCenterX(c.getRadiusX() + rnd.nextDouble() * (centerPane.getWidth() - c.getRadiusX() * 2));
				c.setCenterY(c.getRadiusY() + rnd.nextDouble() * (centerPane.getHeight() - c.getRadiusY() * 2));

				double cXRatio = c.getCenterX() / centerPane.getWidth();
				double cYRatio = c.getCenterY() / centerPane.getHeight();

				c.centerXProperty().bind(centerPane.widthProperty().multiply(cXRatio));
				c.centerYProperty().bind(centerPane.heightProperty().multiply(cYRatio));
				c.radiusXProperty().bind(centerPane.widthProperty().multiply(c.getRadiusX() / centerPane.getWidth()));
				c.radiusYProperty().bind(centerPane.heightProperty().multiply(c.getRadiusY() / centerPane.getHeight()));

				c.setStrokeWidth(3);

				c.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent t) {
						c.toFront();
						c.setStroke(Color.MEDIUMORCHID);
						currentShape = "Ellipse";
						int rIndex = getIndex(centerPane, "Rectangle");
						if (rIndex != -1) {
							Rectangle r = (Rectangle) centerPane.getChildren().get(rIndex);
							r.setStroke(null);
						}
					}
				});

				circleX.textProperty().bindBidirectional(c.centerXProperty(), new NumberStringConverter());
				circleY.textProperty().bindBidirectional(c.centerYProperty(), new NumberStringConverter());

				circleVTxt.setVisible(true);

				centerPane.getChildren().add(c);
				Event.fireEvent(c, new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY, 1, true,
						true, true, true, true, true, true, true, true, true, null));

			}
		});

		HBox bottomGroup = new HBox(5, btnSqr, btnCircle);
		bottomGroup.setAlignment(Pos.CENTER);
		bp.setBottom(bottomGroup);

		Scene scene = new Scene(bp, 450, 350);
		primaryStage.setTitle("Sirkel og rektangel");
		primaryStage.setMinWidth(450);
		primaryStage.setMinHeight(350);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	final void setCurrentShape(String currentShape) {
		this.currentShape = currentShape;
	}

	final int getIndex(Pane pane, String type) {

		if (type == "Ellipse") {
			try {

				if (pane.getChildren().get(0) instanceof Ellipse) {
					return 0;
				} else if (pane.getChildren().get(1) instanceof Ellipse) {
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
