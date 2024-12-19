package cocson.lawrence;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUIDriver extends Application {

	Die d1 = new Die(6);
	Die d2 = new Die(6);

//adadfesbvgf
	@Override
	public void start(Stage stage) throws Exception {
		VBox vbox = new VBox(10);

		// Create and display the title
		Label title = new Label("Shut The Box");
		vbox.getChildren().add(title);

		HBox tileBox = new HBox(10);

		Button[] tileBtns = new Button[9];
		Tile[] tiles = new Tile[9];

		for (int i = 0; i < tileBtns.length; i++) {
			tileBtns[i] = new Button(String.valueOf(i + 1));
			tiles[i] = new Tile(i + 1, true);
			tileBox.getChildren().add(tileBtns[i]);

		}

		tileBox.setAlignment(Pos.CENTER);
		vbox.getChildren().add(tileBox);

		Button btnRoll = new Button("ROLL DICE");
		Button btnLock = new Button("LOCK IN");
		HBox btnButtons = new HBox(2);

		btnButtons.setAlignment(Pos.CENTER);
		btnButtons.getChildren().addAll(btnRoll, btnLock);

		Button btnEnd = new Button("END ROUND");
		Label result = new Label("Result");

		HBox resultBox = new HBox(2);

		Label[] results = new Label[3];
		Label lblValue = new Label(); // output of results
		Label space = new Label("       ");
		Label lblValue2 = new Label();
		results[0] = lblValue;
		results[1] = space;
		results[2] = lblValue2;
		resultBox.getChildren().addAll(results);
		resultBox.setAlignment(Pos.CENTER);

		vbox.getChildren().addAll(btnButtons, result, resultBox, btnEnd);
		vbox.setAlignment(Pos.CENTER);

		// code starts

		boolean got = true;
		btnRoll.setOnAction(e -> {
			// change to if we are still playing
			if (btnRoll.getStyle().equals("")) {
				lblValue.setText(String.valueOf(d1.roll()));
				lblValue2.setText(String.valueOf(d2.roll()));
				btnRoll.setStyle("-fx-background-color: lightgray");
			}

		});

		// Selecting Numbers
		for (Button tileBtn : tileBtns) {
			tileBtn.setOnAction(e -> {
					if (tileBtn.getStyle().equals("-fx-background-color: green")) {
						tileBtn.setStyle(" ");
					}

					else if (tileBtn.getStyle().equals("")) {
						tileBtn.setStyle("-fx-background-color: green");

					}
				
				

			});

		}

		btnLock.setOnAction(e -> {
			if (btnLock.getStyle().equals("")) {
				for (int i=0; i< tileBtns.length; i++) {
					if (tileBtns[i].getStyle().equals("-fx-background-color: green")) {
						System.out.println(tileBtns[i]);
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
