package cocson.lawrence;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GUIDriver extends Application {

	Die d1 = new Die(6);
	Die d2 = new Die(6);


	@Override
	public void start(Stage stage) throws Exception {
		VBox vbox = new VBox(10);

		// Create and display the title
		Label message = new Label("ROLL DICE TO START");
		message.setStyle("-fx-font-weight: bold;");
		vbox.getChildren().add(message);

		HBox tileBox = new HBox(10);

		Button[] tileBtns = new Button[9];
		Tile[] tiles = new Tile[9];

		for (int i = 0; i < tileBtns.length; i++) {
			tileBtns[i] = new Button(String.valueOf(i + 1));
			tileBtns[i].setStyle("-fx-background-color: lightgray");
			tiles[i] = new Tile(i + 1, true);
			tileBox.getChildren().add(tileBtns[i]);
		}

		tileBox.setAlignment(Pos.CENTER);
		vbox.getChildren().add(tileBox);

		Button btnRoll = new Button("ROLL DICE");
		Button btnLock = new Button("LOCK IN");
		Button btnEnd = new Button("END ROUND");
		btnLock.setStyle("-fx-background-color: lightgray");
		btnEnd.setStyle("-fx-background-color: lightgray");
		HBox btnButtons = new HBox(3);

		btnButtons.setAlignment(Pos.CENTER);
		btnButtons.getChildren().addAll(btnLock, btnRoll, btnEnd);

		Label score = new Label("Score: 45"); // 45 is highest score
		score.setStyle("-fx-font-weight: bold;");
		Label result = new Label("Result");

		HBox resultBox = new HBox(2);

		Label[] results = new Label[3];
		Label lblValue = new Label("Unrolled"); // output of results
		Label space = new Label("       ");
		Label lblValue2 = new Label("Unrolled");
		results[0] = lblValue;
		results[1] = space;
		results[2] = lblValue2;
		resultBox.getChildren().addAll(results);
		resultBox.setAlignment(Pos.CENTER);

		vbox.getChildren().addAll(btnButtons, result, resultBox, score);
		vbox.setAlignment(Pos.CENTER);

		// buttons

		btnRoll.setOnAction(e -> {
			if (btnRoll.getStyle().equals("")) {
				btnEnd.setStyle("");
				btnRoll.setStyle("-fx-background-color: lightgray");

				int tilesDown = 0;
				for (Button btn : tileBtns) {
					if (btn.getStyle().equals("-fx-background-color: lightgray")) {
						tilesDown += 1;
					}
				}

				for (Button btn : tileBtns) {
					if (tilesDown == 9) {
						btn.setStyle("");
						btnLock.setStyle("");
					}

					else {
						btnLock.setStyle("");
					}
				}
				message.setText("PICK TILES");

				if (tileBtns[6].getStyle().equals("") || tileBtns[7].getStyle().equals("")
						|| tileBtns[8].getStyle().equals("")) {
					lblValue.setText(String.valueOf(d1.roll()));
					lblValue2.setText(String.valueOf(d2.roll()));
				}

				else {
					lblValue.setText(String.valueOf(d1.roll()));
					lblValue2.setText("0");
					d2.setValue(0);
				}

				int targetSum = d1.getValue() + d2.getValue();

				ArrayList<Tile> upTiles = new ArrayList<>();
				for (int i = 0; i < tileBtns.length; i++) {
					if (tileBtns[i].getStyle().equals("")) {
						upTiles.add(tiles[i]);
					}
				}

				System.out.println(targetSum);
				System.out.println(upTiles);
			}
		});

		// Selecting Numbers
		for (Button tileBtn : tileBtns) {
			tileBtn.setOnAction(e -> {
				if (tileBtn.getStyle().equals("-fx-background-color: green")) {
					tileBtn.setStyle("");
				}

				else if (tileBtn.getStyle().equals("")) {
					tileBtn.setStyle("-fx-background-color: green");

				}
			});

		}

		btnLock.setOnAction(e -> {
			int sum = 0;
			int targetSum = d1.getValue() + d2.getValue();
			if (btnLock.getStyle().equals("")) {
				for (int i = 0; i < tileBtns.length; i++) {
					if (tileBtns[i].getStyle().equals("-fx-background-color: green")) {
						sum += Integer.parseInt(tileBtns[i].getText());
					}
				}
				String[] scores = score.getText().split(" ");

				int playerScore = Integer.parseInt(scores[1]);
				if (sum == targetSum) {
					for (Button tileBtn : tileBtns) {
						if (tileBtn.getStyle().equals("-fx-background-color: green")) {
							tileBtn.setStyle("-fx-background-color: lightgray");
							btnRoll.setStyle("");
							btnLock.setStyle("-fx-background-color: lightgray");
							lblValue.setText(" ");
							lblValue2.setText(" ");
							message.setText("ROLL DICE");
							playerScore -= Integer.parseInt(tileBtn.getText());
						}

						score.setText("Score: " + playerScore);

					}
				} else {
					message.setText("INVALID");
				}

			}

		});
		
		
		btnEnd.setOnAction(e -> {
			if (btnEnd.getStyle().equals("")) {
				btnRoll.setStyle("-fx-background-color: lightgray");
				btnLock.setStyle("-fx-background-color: lightgray");
				btnEnd.setStyle("-fx-background-color: lightgray");
				message.setText("Game Over!");
				for (Button tileBtn : tileBtns) {
					if (tileBtn.getStyle().equals("") || tileBtn.getStyle().equals("-fx-background-color: green")) {
						tileBtn.setStyle("-fx-background-color: brown");
					}

					else if (tileBtn.getStyle().equals("-fx-background-color: lightgray")) {
						tileBtn.setStyle("-fx-background-color: lightgreen");
					}
				}
			}
		});

		Scene scene = new Scene(vbox, 500, 200);
		stage.setScene(scene);

		stage.show();
	}

	public static void main(String[] args) {
		launch(args);

	}
}
