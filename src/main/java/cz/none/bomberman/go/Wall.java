package cz.none.bomberman.go;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import cz.none.bomberman.GameEngine;
import cz.none.bomberman.factory.GameObjectFactory;

public class Wall extends GameObject {

	public Wall(double x, double y, double width, double height, Color color) {
		Rectangle rectangle = RectangleBuilder.create().translateX(x).translateY(y).width(width).height(height)
												.fill(color).build();
		node = rectangle;
	}

	@Override
	public void update(Scene scene, long time) {
	}

	@Override
	public <T extends GameObjectFactory> void collide(GameEngine<T, ?> atomSmasher, GameObject go1) {

	}

}
