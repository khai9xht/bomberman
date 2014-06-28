package cz.none.bomberman;

import java.io.File;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Bomberman atom = new Bomberman();
		atom.initialize(primaryStage, "Bomberman", 800, 640, new File("map/map1.m"));
		atom.beginGameLoop();
		primaryStage.show();
	}

}
