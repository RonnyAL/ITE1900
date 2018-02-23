package modul_4;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class CircleText extends Application {

	private DoubleProperty fontSize = new SimpleDoubleProperty(10);

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Pane pane = new Pane();
		Group group = new Group();
		Scene scene = new Scene(pane, 400, 400);

		String wrapText = "DETTE ER EN TEKST...";

		Circle circle = new Circle(100);
		circle.setFill(Color.BLANCHEDALMOND);
		circle.setStroke(Color.DARKGREY);

		pane.getChildren().add(circle);

		Font font = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, circle.getRadius() / 6);

		double rotation = 0;

		Text tempString = new Text(wrapText.replaceAll(" ", ""));
		Group tempGroup = new Group();
		tempString.setFont(font);

		tempGroup.getChildren().add(tempString);
		double stringPxLength = tempGroup.getLayoutBounds().getWidth();
		int stringLength = wrapText.replaceAll(" ", "").length();

		double totalPadding = Math.PI * 2 * circle.getRadius() - stringPxLength;
		stringPxLength += totalPadding;

		double paddingPerLetter = totalPadding / (stringLength);
		System.out.println(stringLength);
		System.out.println(stringPxLength);
		System.out.println(paddingPerLetter);

		double rotationSum = 0;

		for (char c : wrapText.toCharArray()) {
			if (!Character.isWhitespace(c)) {
				Text t = new Text(Character.toString(c));

				t.setFont(font);

				// Binder font-størrelse til sirekelns radius / 6
				fontSize.bind(circle.radiusProperty().divide(6));
				t.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString()));

				t.setX(pane.getWidth() / 2);
				t.setY(pane.getHeight() / 2);

				Rotate rot = new Rotate(rotation, pane.getWidth() / 2, pane.getHeight() / 2 - circle.getRadius());

				t.yProperty().bind(circle.centerYProperty().subtract(circle.radiusProperty()));

				t.xProperty().bind(pane.widthProperty().divide(2));

				rot.pivotXProperty().bind(circle.centerXProperty());
				rot.pivotYProperty().bind(circle.centerYProperty());

				t.getTransforms().add(rot);

				group.getChildren().add(t);

				double arcLength = paddingPerLetter + t.getLayoutBounds().getWidth();

				double rotationAdd = arcLength / circle.getRadius();

				rotation += Math.toDegrees(rotationAdd);

				rotationSum += Math.toDegrees(rotationAdd);

			}

		}

		System.out.println(rotationSum);

		circle.centerXProperty().bind(pane.widthProperty().divide(2));
		circle.centerYProperty().bind(pane.heightProperty().divide(2));
		circle.radiusProperty().bind(Bindings.min(pane.widthProperty().divide(3), pane.heightProperty().divide(3)));
		circle.strokeWidthProperty()
				.bind(Bindings.min(pane.widthProperty().divide(80), pane.heightProperty().divide(80)));
		group.getChildren().add(circle);
		circle.toBack();

		pane.getChildren().add(group);

		primaryStage.setTitle("Sirkel med tekst");
		primaryStage.setResizable(true);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

}
