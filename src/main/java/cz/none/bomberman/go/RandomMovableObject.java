package cz.none.bomberman.go;

import java.util.Random;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import cz.none.bomberman.GameEngine;
import cz.none.bomberman.factory.GameObjectFactory;

public class RandomMovableObject extends MovableObject {
	protected Random	random	= new Random();
	private int			base	= 31;
	private int			modulo	= 60;

	public RandomMovableObject(double x, double y, double width, double height, Color color) {
		super(x, y, width, height, color);
		changeDirection();
	}

	@Override
	public void update(Scene scene, long time) {
		if (extracted(position.getY()) && extracted(position.getX())) {
			changeDirection();
		}
		super.update(scene, time);
	}

	private void changeDirection() {
		Direction tmp;
		direction = (tmp = randomDirection()) == direction ? randomDirection() : tmp;
	}

	private Direction randomDirection() {
		return Direction.values()[random.nextInt(Direction.values().length)];
	}

	private boolean extracted(int position) {
		return (position - base) % modulo == 0;
	}

	@Override
	protected <T extends GameObjectFactory> void collide(GameEngine<T, ?> atomSmasher, Wall go1) {
		super.collide(atomSmasher, go1);
		changeDirection();

	}

	@Override
	protected <T extends GameObjectFactory> void collide(GameEngine<T, ?> atomSmasher, Bomb go1) {
		super.collide(atomSmasher, go1);
		changeDirection();
	}

}
