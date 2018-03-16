package lek;

import javafx.application.*;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Pair;

public class GreatestCommonDenominator extends Application {
	
	
	
	
	public static int gcd(int p, int q) {
		if (q == 0)
			return p;
		else
//			System.out.println(p % q);
			return Math.abs(gcd(q, p % q));
	}

	public static void main(String[] args) {
		
		launch(args);
		
//		int p = -394200;
//		int q = 1220430;
//		int d = gcd(p, q);
//		System.out.println("gcd(" + p + ", " + q + ") = " + d);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Største fellesnevner");
		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);

		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);

		TextField num1TF = new TextField();

		TextField num2TF = new TextField();
		num2TF.setStyle("-fx-opacity: 1.0;");

		gridPane.add(new Label("Tall 1: "), 0, 0);
		gridPane.add(num1TF, 1, 0);
		gridPane.add(new Label("Tall 2: "), 0, 1);
		gridPane.add(num2TF, 1, 1);
		
		
		
		
		Text numerator = new Text("5");
		numerator.xProperty().bind(dialog.xProperty().divide(2));
		numerator.setFont(Font.font ("Verdana", 20));
		
		Text denominator = new Text("2");
		denominator.xProperty().bind(dialog.xProperty().divide(2));
		denominator.setFont(Font.font ("Verdana", 20));
		
		Line line = new Line();
		line.startXProperty().bind(numerator.xProperty());
		
		VBox fraction = new VBox(numerator, line, denominator);
		fraction.setSpacing(5);
		Insets fractionLPadding = new Insets(0);
		
		VBox root = new VBox(gridPane, fraction);
		

		dialog.getDialogPane().setContent(root);
		dialog.setResizable(true);

		Platform.runLater(() -> num1TF.requestFocus());
		
		dialog.showAndWait();
		
	}
}
