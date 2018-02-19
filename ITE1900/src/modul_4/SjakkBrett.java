package modul_4;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class SjakkBrett extends Application {

	@Override
	public void start(Stage primaryStage) {
		
        int boardSize = 8; // Definerer antall kolonner og rader.
        int squareSize = 40; // Definerer hver rutes h�yde/bredde (i piksler).
		
		Pane pane = new Pane();
		Group group = new Group();
        Scene scene = new Scene(pane, boardSize*squareSize, boardSize*squareSize);

        Rectangle rect = null;

        for (int c = 0; c < boardSize; c++) {
        	for (int r = 0; r < boardSize; r++) {
        		rect = new Rectangle(squareSize*c, squareSize*r, squareSize, squareSize);

        		if (c % 2 == r % 2)
        			rect.setFill(Color.WHITE);
        		else
        			rect.setFill(Color.BLACK);
        		
        		group.getChildren().add(rect);
        	}
        }

        pane.getChildren().add(group);
        
        primaryStage.setTitle("Sjakkbrett");
        primaryStage.setResizable(false);
        primaryStage.sizeToScene(); // Uten denne linjen skapes un�dvendige marginer. Bug?
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
	
	public static void main(String[] args) {
        launch(args);
    }
		
}