package cz.none.bomberman.go;

import static cz.none.bomberman.go.Direction.E;
import static cz.none.bomberman.go.Direction.N;
import static cz.none.bomberman.go.Direction.S;
import static cz.none.bomberman.go.Direction.W;
import static javafx.scene.input.KeyCode.DOWN;
import static javafx.scene.input.KeyCode.LEFT;
import static javafx.scene.input.KeyCode.RIGHT;
import static javafx.scene.input.KeyCode.UP;

import java.util.Arrays;
import java.util.List;

import javafx.animation.FadeTransitionBuilder;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import cz.none.bomberman.GameEngine;
import cz.none.bomberman.factory.GameObjectFactory;
import cz.none.bomberman.keyboard.KeyListener;

public class Player extends MovableObject implements KeyListener {

	private KeyCode	currentlyPressed;

	public Player(double x, double y, double width, double height, Color color) {
		super(x, y, width, height, color);
		this.speed = 0;
	}

	@Override
	public List<KeyCode> interestedIn() {
		return Arrays.asList(LEFT, RIGHT, UP, DOWN);
	}

	@Override
	public void notify(KeyEvent keyEvent) {
		EventType<? extends Event> eventType = keyEvent.getEventType();
		if (KeyEvent.KEY_RELEASED.equals(eventType)) {
			if (keyEvent.getCode().equals(currentlyPressed)) {
				this.speed = 0;
			}
		} else if (KeyEvent.KEY_PRESSED.equals(eventType)) {
			currentlyPressed = keyEvent.getCode();
			this.direction = getDirection(keyEvent);
			this.speed = 1;

		}

	}

	private Direction getDirection(KeyEvent keyEvent) {
		switch (keyEvent.getCode()) {
		case LEFT:
			return E;
		case UP:
			return N;
		case DOWN:
			return S;
		case RIGHT:
		default:
			return W;
		}
	}

	@Override
	protected <T extends GameObjectFactory> void collide(GameEngine<T, ?> atomSmasher, RandomMovableObject go1) {
		if (!isDead) {
			speed = 0;
			isDead = true;
			FadeTransitionBuilder.create().node(node).duration(Duration.millis(3000)).fromValue(node.getOpacity()).toValue(0)
					.onFinished(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent arg0) {
							atomSmasher.removeObjects(Player.this);
						}
					}).build().play();
		}
	}

}
