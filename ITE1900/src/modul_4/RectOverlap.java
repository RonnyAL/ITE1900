package modul_4;

import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class RectOverlap extends Application {

	static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {

		launch(args);
	}

	/** Returns true if r1 contains r2 */
	private static boolean contains(Rectangle r1, Rectangle r2) {
		// Four corner points in r2
		double x1 = r2.getX();
		double y1 = r2.getY();
		double x2 = x1 + r2.getWidth();
		double y2 = y1;
		double x3 = x1;
		double y3 = y1 + r2.getHeight();
		double x4 = x1 + r2.getWidth();
		double y4 = y1 + r2.getHeight();
		return r1.contains(x1, y1) && r1.contains(x2, y2) && r1.contains(x3, y3) && r1.contains(x4, y4);
	}

	/** Returns true if r1 overlaps r2 */
	private static boolean overlaps(Rectangle r1, Rectangle r2) {
		// Four corner points in r2
		double r1xCenter = r1.getX() + r1.getWidth() / 2;
		double r2xCenter = r2.getX() + r2.getWidth() / 2;
		double r1yCenter = r1.getY() + r1.getHeight() / 2;
		double r2yCenter = r2.getY() + r2.getHeight() / 2;
		return Math.abs(r1xCenter - r2xCenter) <= (r1.getWidth() + r2.getWidth()) / 2
				&& Math.abs(r1yCenter - r2yCenter) <= (r1.getHeight() + r2.getHeight()) / 2;
	}

	@Override
	public void start(Stage primaryStage) {
		Pane pane = new Pane();
		Group group = new Group();
		Scene scene = new Scene(pane, 400, 400);

		Rectangle r1 = new Rectangle();
		System.out.println("[REKTANGEL 1]");
		System.out.print("Oppgi høyde: ");
		r1.setHeight(Double.parseDouble(in.next()));
		System.out.print("Oppgi bredde: ");
		r1.setWidth(in.nextDouble());
		System.out.print("Oppgi x-koordinat: ");
		r1.setX(in.nextDouble());
		System.out.print("Oppgi y-koordinat: ");
		r1.setY(in.nextDouble());
		System.out.println();
		r1.setStroke(Color.RED);
		r1.setFill(Color.TRANSPARENT);
		r1.setStrokeWidth(1);

		Rectangle r2 = new Rectangle();
		System.out.println("[REKTANGEL 2]");
		System.out.print("Oppgi høyde: ");
		r2.setHeight(in.nextDouble());
		System.out.print("Oppgi bredde: ");
		r2.setWidth(in.nextDouble());
		System.out.print("Oppgi x-koordinat: ");
		r2.setX(in.nextDouble());
		System.out.print("Oppgi y-koordinat: ");
		r2.setY(in.nextDouble());
		r2.setStroke(Color.BLUE);
		r2.setFill(Color.TRANSPARENT);
		r2.setStrokeWidth(1);

		double heightScale = 350 / (Math.max(r1.getHeight() + 2 * r1.getY(), r2.getHeight() + 2 * r2.getY()));
		double widthScale = 350 / (Math.max(r1.getWidth() + 2 * r1.getX(), r2.getWidth() + 2 * r2.getX()));
		double absScale = Math.min(heightScale, widthScale);

		r1.setHeight(r1.getHeight() * absScale);
		r1.setWidth(r1.getWidth() * absScale);
		r1.setX(25 + r1.getX() * absScale);
		r1.setY(25 + r1.getY() * absScale);

		r2.setHeight(r2.getHeight() * absScale);
		r2.setWidth(r2.getWidth() * absScale);
		r2.setX(25 + r2.getX() * absScale);
		r2.setY(25 + r2.getY() * absScale);

		String legend;

		if (absScale < 0.01)
			legend = "Scale factor: < 0,01";
		else
			legend = String.format("Scale factor: %.2f", absScale);

		Text redRect = new Text("1");
		redRect.setFill(Color.RED);

		Text blueRect = new Text("2");
		blueRect.setFill(Color.BLUE);

		TextFlow flow = new TextFlow();
		flow.getChildren().add(new Text(legend));

		if (contains(r1, r2) || (contains(r2, r1))) {
			flow.getChildren().add(new Text(String.format("%nRectangle ")));
			if (contains(r1, r2)) {
				flow.getChildren().addAll(redRect, new Text(" contains rectangle "), blueRect);
			} else {
				flow.getChildren().addAll(blueRect, new Text(" contains rectangle "), redRect);
			}

		}

		flow.getChildren().add(new Text(String.format("%nRectangles%s overlap", overlaps(r1, r2) ? "" : " do not")));

		group.getChildren().add(r1);
		group.getChildren().add(r2);
		group.getChildren().add(flow);
		pane.getChildren().add(group);

		flow.applyCss();
		flow.setStyle("-fx-background-color: #EEE; -fx-padding: 3 8 3 8;  -fx-border-color: #999");
		flow.setLayoutX(pane.getWidth() * 0.5 - (0.5 * flow.prefWidth(-1)) - 8);
		flow.setLayoutY(pane.getHeight() - 20 - flow.prefHeight(-1));

		primaryStage.setTitle("Rectangle overlap check");
		primaryStage.setResizable(false);
		primaryStage.sizeToScene();
		primaryStage.setScene(scene);
		primaryStage.show();

	}

}