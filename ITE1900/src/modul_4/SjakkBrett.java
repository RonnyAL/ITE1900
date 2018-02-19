package modul_4;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class SjakkBrett extends Application {

	@Override
	public void start(Stage primaryStage) {
		Group root = new Group();
        Scene scene = new Scene(root, 500, 500, Color.BLACK);

        //Filled rectangle
        Rectangle rect1 = new Rectangle(10, 10, 200, 200);
        rect1.setFill(Color.BLUE);

        //Transparent rectangle with Stroke
        Rectangle rect2 = new Rectangle(60, 60, 200, 200);
        rect2.setFill(Color.TRANSPARENT);
        rect2.setStroke(Color.RED);
        rect2.setStrokeWidth(10);

        //Rectangle with Stroke, no Fill color specified
        Rectangle rect3 = new Rectangle(110, 110, 200, 200);
        rect3.setStroke(Color.GREEN);
        rect3.setStrokeWidth(10);

        root.getChildren().addAll(rect1, rect2, rect3);

        primaryStage.setTitle("java-buddy.blogspot.com");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
	
	public static void main(String[] args) {
        launch(args);
    }
		
}


	

