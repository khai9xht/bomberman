package cz.none.bomberman.go;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import cz.none.bomberman.GameEngine;
import cz.none.bomberman.factory.GameObjectFactory;

public class Flame extends MovableObject {
	private final Direction	direction;
	private final Position	position;
	private final int		length;
	private final int		size;
	private Rectangle		rectangle;
	private boolean			start	= false;

	public Flame(int x, int y, Direction direction, int length, int size) {
		super(x, y, size, size, Color.RED);
		this.position = new Position(x, y);
		this.direction = direction;
		this.length = length;
		this.size = size;
		rectangle = RectangleBuilder.create().translateX(x).translateY(y).width(0).height(0).fill(Color.RED).build();
		node = rectangle;

	}

	public void play() {
		rectangle.setWidth(size);
		rectangle.setHeight(size);
		start = true;
	}


	@Override
	public void update(Scene scene, long time) {

	}

	@Override
	public <T extends GameObjectFactory> void collide(GameEngine<T, ?> atomSmasher, GameObject go1) {
	}
}
