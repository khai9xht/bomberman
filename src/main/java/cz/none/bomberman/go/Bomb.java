package cz.none.bomberman.go;

import javafx.animation.ScaleTransition;
import javafx.animation.ScaleTransitionBuilder;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CircleBuilder;
import javafx.util.Duration;
import cz.none.bomberman.GameEngine;
import cz.none.bomberman.factory.GameObjectFactory;

public class Bomb extends GameObject {

	private static final long	BOMB_TIME	= 5000000000L;
	private long	startTime	= 0;
	private boolean	firstUpdate	= false;
	private ScaleTransition		ticking;

	public Bomb(int x, int y, int size) {
		super();
		Circle build = CircleBuilder.create().centerX(x).centerY(y).radius(size).cache(true).fill(Color.BLACK).build();
		node = build;
		ticking = ScaleTransitionBuilder.create().duration(Duration.millis(500)).fromX(1).fromY(1).toX(0.8).toY(0.8).autoReverse(true).cycleCount(1000)
				.node(node).build();
	}

	@Override
	public void update(Scene scene, long time) {
		if (!firstUpdate) {
			firstUpdate = true;
			startTime = time;
			ticking.play();
		}

		if (time - BOMB_TIME > startTime) {
			ticking.stop();
		}

	}

	@Override
	public <T extends GameObjectFactory> void collide(GameEngine<T, ?> atomSmasher, GameObject go1) {

	}

}
