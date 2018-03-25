package modul_5;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class EncryptFile extends Application {

	File inputFile;
	File outputFile;
	String errorString;

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
		GridPane gridPane = new GridPane();

		Text headerHeader = new Text("Kryptering og dekryptering av fil");
		headerHeader.setFont(Font.font(null, FontWeight.NORMAL, 16));
		Text headerText = new Text(
				"Velg filen som skal krypteres, samt plassering og filnavn for den krypterte filen.");
		headerText.setWrappingWidth(300);
		Separator separator = new Separator();
		VBox header = new VBox(headerHeader, headerText);
		header.setPadding(new Insets(5,10,10,10));
		header.setSpacing(5);
		header.setStyle("-fx-border-width: 0 0 2 0; -fx-border-color: #eee;");

		Text labelIn = new Text("Kildefil");
		TextField tfIn = new TextField();
		Button btnIn = new Button("Velg");

		Text labelOut = new Text("Lagre som");
		TextField tfOut = new TextField();
		Button btnOut = new Button("Velg");

		Button krypterBtn = new Button("Kryptér");
		Button dekrypterBtn = new Button("Dekryptér");
		FlowPane footer = new FlowPane(krypterBtn, dekrypterBtn);
		footer.setPadding(new Insets(10,10,10,20));
		footer.setHgap(10.0);
		footer.setAlignment(Pos.CENTER_RIGHT);
		krypterBtn.setAlignment(Pos.CENTER);

		gridPane.add(labelIn, 0, 0);
		gridPane.add(tfIn, 1, 0);
		tfIn.setMinWidth(250);
		gridPane.add(btnIn, 2, 0);

		gridPane.add(labelOut, 0, 1);
		gridPane.add(tfOut, 1, 1);
		gridPane.add(btnOut, 2, 1);

		gridPane.setPadding(new Insets(20, 10, 20, 10));
		gridPane.setHgap(15);
		gridPane.setVgap(10);
		
		gridPane.setStyle("-fx-background-color: #fff;");

		
		separator.setOrientation(Orientation.HORIZONTAL);

		VBox content = new VBox(header, gridPane, footer);


		FileChooser fc = initFileChooser("Velg kildefil");

		btnIn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				inputFile = fc.showOpenDialog(stage);
				if (inputFile == null) {
					tfIn.setText("");
					krypterBtn.setDisable(true);
				} else {
					tfIn.setText(inputFile.getAbsolutePath());
					if (outputFile != null)
						krypterBtn.setDisable(false);
				}

			}
		});
		
		btnOut.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				outputFile = fc.showSaveDialog(stage);
				if (outputFile == null) {
					tfOut.setText("");
					krypterBtn.setDisable(true);
				} else {
					tfOut.setText(outputFile.getAbsolutePath());
					if (inputFile != null)
						krypterBtn.setDisable(false);
				}
			}
		});

		krypterBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				if (inputFile == null) {
					try {
						inputFile = new File(tfIn.getText());
					} catch (Exception e) {
						showError(e);
						return;
					}
				}
				
				if (outputFile == null) {
					try {
						outputFile = new File(tfOut.getText());
					} catch (Exception e) {
						showError(e);
						return;
					}
				}
				
				if(encryptDecrypt(inputFile, outputFile, 5)) {
					Alert alert = new Alert(AlertType.INFORMATION, String.format("Filen \"%s\" ble kryptert og lagret som \"%s\"", inputFile.getAbsolutePath(), outputFile.getAbsolutePath()), ButtonType.OK);
					alert.setTitle("Suksess!");
					alert.showAndWait();
				}
			}

		});
		
		dekrypterBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if(encryptDecrypt(inputFile, outputFile, -5)) {
					Alert alert = new Alert(AlertType.INFORMATION, String.format("Filen \"%s\" ble dekryptert og lagret som \"%s\"", inputFile.getAbsolutePath(), outputFile.getAbsolutePath()), ButtonType.OK);
					alert.setTitle("Suksess!");
					alert.showAndWait();
				}
			}

		});

		Scene scene = new Scene(content);
		stage.setScene(scene);
		stage.sizeToScene();
		stage.setResizable(false);
		header.setPrefWidth(scene.getWidth());
		stage.setTitle("Filkryptering");
		stage.show();

	}

	private FileChooser initFileChooser(String title) {
		FileChooser fc = new FileChooser();
		fc.setTitle(title);
		fc.setInitialDirectory(new File(System.getProperty("user.dir")));
		return fc;
	}

	private boolean encryptDecrypt(File inputFile, File outputFile, int shift) {
		try (
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inputFile));
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outputFile));
			)
		{
			int value;
			while ((value = bis.read()) != -1)
				bos.write(value + shift);

		} catch (Exception e) {
			System.err.print(String.format("En feil oppstod: %s.", e));
			showError(e);
			return false;
		}

		return true;
	}
	
	private void showError(Exception e) {
		// Tok her utgangspunkt i kode hentet fra: http://code.makery.ch/blog/javafx-dialogs-official
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Feilmelding!");
		alert.setHeaderText("En feil oppstod!");
		alert.setContentText(String.format("Kryptering feilet med feilmelding: %s", e));

		// Create expandable Exception.
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		String stringStackTrace = sw.toString();

		Label label = new Label("Stacktrace:");
		
		TextArea textArea = new TextArea(stringStackTrace);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);

		alert.getDialogPane().setExpandableContent(expContent);

		alert.showAndWait();
	}

}
