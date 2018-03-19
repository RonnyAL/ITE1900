package modul_5;

import java.math.BigInteger;
import java.util.function.UnaryOperator;
import javafx.application.Application;
import javafx.beans.value.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Converter extends Application {

	public static String outputStr;
	private static final String hexChars = "0123456789ABCDEF";
	private static final String DEC_REGEX = "[0-9]*", HEX_REGEX = "[0-9A-F]+", BIN_REGEX = "[01]*";
	public static String CUR_REGEX = "";
	private static TextField inputField;
	private static TextArea outputField;
	public static String inputType, outputType;
	public Class<?> myClass = this.getClass();

	public static void main(String[] args) {
		launch(args);
	}

	private static String dec2Bin(int value) {

		if (value < 2) {
			outputStr = String.valueOf(value);
			return outputStr;
		} else if (value != 0) {
			dec2Bin(value / 2);
			outputStr += value % 2;
		}
		return outputStr;

	}

	private static String dec2BinBig(BigInteger value) {

		if (value.compareTo(getBigInt("2")) == -1) {
			outputStr = value.toString();
			return outputStr;

		} else if (!value.equals(getBigInt("0"))) {
			dec2BinBig(value.divide(getBigInt("2")));
			outputStr += value.mod(getBigInt("2"));
		}
		return outputStr;

	}

	private static String dec2Hex(int value) {
		StringBuilder sb = new StringBuilder();
		if (value > 0) {
			String hexNumber = dec2Hex(value / 16);
			int hexDigit = value % 16;
			sb.append(hexNumber + hexChars.charAt(hexDigit));
		}
		return sb.toString();
	}

	private static String dec2HexBig(BigInteger value) {
		StringBuilder sb = new StringBuilder();
		if (value.compareTo(new BigInteger("0")) == 1) {
			String hexNumber = dec2HexBig(value.divide(new BigInteger("16")));
			BigInteger hexDigit = value.mod(new BigInteger("16"));
			sb.append(hexNumber + hexChars.charAt(hexDigit.intValue()));
		}
		return sb.toString();
	}

	private static int bin2Dec(String string) {
		int len = string.length();
		if (string.length() == 0)
			return 0;
		String now = string.substring(0, 1);
		String next = string.substring(1);
		return Integer.parseInt(now) * (int) Math.pow(2, len - 1) + bin2Dec(next);
	}

	private static String bin2DecBig(String string) {
		int len = string.length();
		if (string.length() == 0)
			return "0";
		String now = string.substring(0, 1);
		String next = string.substring(1);
		BigInteger result = getBigInt(now).multiply(getBigInt("2").pow(len - 1))
				.add(getBigInt(bin2DecBig(next).toString()));
		outputStr = result.toString();
		return outputStr;
	}

	private static int hex2Dec(String string) {
		int dec = 0;
		string = string.toUpperCase();
		int len = string.length();
		if (len > 0) {
			char ch = string.charAt(0);
			int digit = hexChars.indexOf(ch);
			String substring = string.substring(1);
			dec = digit * (int) Math.pow(16, len - 1) + hex2Dec(substring);
		}
		return dec;
	}

	private static String hex2DecBig(String string) {
		BigInteger dec = getBigInt("0");
		string = string.toUpperCase();
		int len = string.length();
		if (len > 0) {
			char ch = string.charAt(0);
			BigInteger digit = getBigInt(String.valueOf(hexChars.indexOf(ch)));
			String substring = string.substring(1);
			dec = digit.multiply(getBigInt("16").pow(len - 1).add(getBigInt(String.valueOf(hex2DecBig(substring)))));
		}
		return dec.toString();
	}

	private static BigInteger getBigInt(String num) {
		return new BigInteger(num);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Pane pane = new Pane();
		Scene scene = new Scene(pane, 500, 165);

		Tab tabD2B = new Tab("Decimal to binary");
		Tab tabD2H = new Tab("Decimal to hex");
		Tab tabB2D = new Tab("Binary to decimal");
		Tab tabH2D = new Tab("Hex to decimal");

		TabPane tabPane = new TabPane();
		tabPane.getTabs().addAll(tabD2B, tabD2H, tabB2D, tabH2D);

		for (Tab t : tabPane.getTabs()) {
			t.setClosable(false);
			t.setStyle("-fx-pref-width: " + (scene.getWidth() / tabPane.getTabs().size() - 3));
		}

		UnaryOperator<Change> filter = change -> {

			if (change.getText() == "") {
				outputField.setText("");
				return change;
			} else {

				change.setText(change.getText().toUpperCase());
				String text = change.getText();

				if (text.matches(CUR_REGEX)) {
					return change;
				}

			}
			return null;

		};
		TextFormatter<String> textFormatter = new TextFormatter<>(filter);

		inputField = new TextField();
		inputField.setTextFormatter(textFormatter);
		outputField = new TextArea();
		outputField.setWrapText(true);

		tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {

			@Override
			public void changed(ObservableValue<? extends Tab> obs, Tab oldTab, Tab newTab) {

				CUR_REGEX = "";
				inputField.setText("");
				outputField.clear();
				inputType = newTab.getText().split(" ")[0];
				outputType = newTab.getText().split(" ")[2];

				inputField.setPromptText(String.format("Enter a %s number", inputType.toLowerCase()));
				outputField
						.setPromptText(String.format("%s value appears here", outputType.substring(0, 1).toUpperCase()
								+ outputType.toLowerCase().substring(1, outputType.length())));

				switch (inputType) {
				case "Decimal":
					CUR_REGEX = DEC_REGEX;
					break;
				case "Binary":
					CUR_REGEX = BIN_REGEX;
					break;
				case "Hex":
					CUR_REGEX = HEX_REGEX;
					break;
				}

			}

		});

		outputField.setEditable(false);

		VBox textFieldsV = new VBox(inputField, outputField);
		textFieldsV.setSpacing(15);
		textFieldsV.setAlignment(Pos.TOP_CENTER);

		pane.getChildren().addAll(tabPane, textFieldsV);

		inputField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				if (CUR_REGEX != "" && !newValue.isEmpty()) {
					outputStr = "";
					String methodToCall = inputType.substring(0, 3).toLowerCase() + "2"
							+ outputType.substring(0, 1).toUpperCase() + outputType.substring(1, 3);

					switch (methodToCall) {
					case "dec2Bin":
					case "dec2Hex":
						if (new BigInteger(newValue)
								.compareTo(new BigInteger(String.valueOf(Integer.MAX_VALUE))) == 1) {
							methodToCall = methodToCall + "Big";
						}
						break;
					case "bin2Dec":
						if (new BigInteger(newValue)
								.compareTo(new BigInteger("1111111111111111111111111111111")) == 1) {
							methodToCall = methodToCall + "Big";
						}
						break;
					case "hex2Dec":
						if (new BigInteger(newValue, 16) // Jukser litt her!
								.compareTo(new BigInteger(String.valueOf(Integer.MAX_VALUE))) == 1) {
							methodToCall = methodToCall + "Big";
						}
						break;
					}

					switch (methodToCall) {
					case "dec2Bin":
						outputField.setText(dec2Bin(Integer.parseInt(inputField.getText())));
						break;
					case "dec2Hex":
						outputField.setText(dec2Hex(Integer.parseInt(inputField.getText())));
						break;
					case "bin2Dec":
						outputField.setText(String.valueOf(bin2Dec(inputField.getText())));
						break;
					case "hex2Dec":
						outputField.setText(String.valueOf(hex2Dec(inputField.getText())));
						break;
					case "dec2BinBig":
						outputField.setText(dec2BinBig(new BigInteger(inputField.getText())));
						break;
					case "dec2HexBig":
						outputField.setText(dec2HexBig(new BigInteger(inputField.getText())));
						break;
					case "bin2DecBig":
						outputField.setText(bin2DecBig(inputField.getText()));
						break;
					case "hex2DecBig":
						outputField.setText(hex2DecBig(inputField.getText()));
						break;
					}

				}

			}
		});

		primaryStage.setScene(scene);

		primaryStage.setResizable(false);
		primaryStage.setTitle("Converter");
		tabPane.getSelectionModel().select(1);
		tabPane.getSelectionModel().select(0);

		textFieldsV.setLayoutY(45);
		inputField.setPrefWidth(scene.getWidth() * 0.95);
		outputField.setPrefWidth(scene.getWidth() * 0.95);
		textFieldsV.setLayoutX(scene.getWidth() * 0.025);
		outputField.setPrefHeight(65);

		primaryStage.show();
		primaryStage.sizeToScene();

	}

}