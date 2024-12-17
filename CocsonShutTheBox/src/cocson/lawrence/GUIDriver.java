package cocson.lawrence;

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

		btnRoll.setOnAction(e -> {
			lblValue.setText(String.valueOf(d1.roll()));
			lblValue2.setText(String.valueOf(d2.roll()));
		});
		
		int sum = d1.getValue() + d2.getValue();

		Scene scene = new Scene(vbox, 500, 200);
		stage.setScene(scene);

		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
		Scanner in = new Scanner(System.in);

		Die d1 = new Die(6);
		Die d2 = new Die(6);

		Tile[] tiles = new Tile[10];

		tiles[0] = new Tile();

		for (int i = 1; i < tiles.length; i++) {
			tiles[i] = new Tile(i, true);
		}

		boolean tilesUp = true;

		String[] numbers = new String[2];

		int num1 = 0;
		int num2 = 0;

		while (tilesUp == true) {
			tiles[0].putUp();
			printTiles(tiles);

			int sum = 0;

			if (tiles[9].isUp()) {
				System.out.println("Rolled two dice.");
				sum = d1.roll() + d2.roll();
			}

			else {
				System.out.println("Rolled one die.");
				sum = d1.roll();
			}

			System.out.println("Sum: " + sum);

			boolean got = false;
			if (movePossible(tiles, sum)) {
				while (got == false) {
					System.out.println("Can move: " + movePossible(tiles, sum));
					numbers = ask(in).split(" ");

					// Numbers
					if (numbers.length > 1) {
						num1 = Integer.parseInt(numbers[0]);
						num2 = Integer.parseInt(numbers[1]);
					}

					else {
						num1 = Integer.parseInt(numbers[0]);
						num2 = 0;
					}

					// Checking if valid
					if (isValid(num1, num2, tiles, sum)) {
						tiles[num1].putDown();
						tiles[num2].putDown();
						got = true;
					}

					else {
						System.out.println("invalid");

						printTiles(tiles);
						System.out.println(sum);
					}

				}
			}

			else {
				System.out.println("Can move: " + movePossible(tiles, sum));
				tilesUp = false;
			}

		}

		int score = 0;

		for (Tile tile : tiles) {
			if (tile.isUp()) {
				score += tile.getValue();
			}
		}

		System.out.println("Score: " + score);

	}

	public static String ask(Scanner in) {
		String answer = in.nextLine();

		return answer;
	}

	public static void printTiles(Tile[] tiles) {
		for (Tile tile : tiles) {
			System.out.println(tile);
		}
	}

	public static boolean isValid(int num1, int num2, Tile[] tiles, int sum) {

		if (num1 + num2 == sum) {
			if (tiles[num1].isUp() && tiles[num2].isUp()) {
				if (num1 != num2) {
					return true;
				}
			}

		}
		return false;

	}

	public static boolean movePossible(Tile[] tiles, int sum) {
		boolean move = false;

		for (int i = 0; i < tiles.length; i++) {
			for (int j = i + 1; j < tiles.length; j++) {
				if (tiles[i].getValue() + tiles[j].getValue() == sum) {
					if (tiles[i].isUp() && tiles[j].isUp()) {
						move = true;
					}
				}
			}
		}

		return move;
	}

}
