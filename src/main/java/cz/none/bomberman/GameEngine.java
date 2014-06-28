package cz.none.bomberman;

import java.io.File;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import cz.none.bomberman.factory.GameObjectFactory;
import cz.none.bomberman.go.GameObject;
import cz.none.bomberman.go.GameObjectManager;
import cz.none.bomberman.keyboard.KeyListener;
import cz.none.bomberman.keyboard.KeyLogger;
import cz.none.bomberman.map.MapGenerator;
import cz.none.bomberman.map.MapParser;

public abstract class GameEngine<GOFactory extends GameObjectFactory, Entity> {

	private TimerWithStatus			animationTimer;
	// private Timeline gameLoop;
	private Scene					gameSurface;
	private Group					sceneNodes;
	private GameObjectManager		manager	= new GameObjectManager();
	private GOFactory				factory;
	private MapParser<Entity>		mapParser;
	private MapGenerator<Entity>	generator;
	private KeyLogger				keyLogger;

	public GameEngine(GOFactory factory, MapParser<Entity> mapParser, MapGenerator<Entity> generator, KeyLogger keyLogger) {
		super();
		this.factory = factory;
		this.mapParser = mapParser;
		this.generator = generator;
		this.keyLogger = keyLogger;
		buildAndSetGameLoop();
	}

	protected final void buildAndSetGameLoop() {
		animationTimer = new TimerWithStatus() {

			@Override
			public void handle(long time) {
				updateSprites(time);

				checkCollisions();

				cleanupSprites();

			}
		};

	}

	public void initialize(final Stage primaryStage, String title, int width, int height, File map) {
		primaryStage.setTitle(title);
		setSceneNodes(new Group());
		setGameSurface(new Scene(getSceneNodes(), width, height));
		primaryStage.setScene(getGameSurface());
		keyLogger.init(getGameSurface());
		List<List<Entity>> parse = getMapParser().parse(map);
		List<GameObject> generate = getGenerator().generate(parse);
		addObjects(generate);
	}

	public void removeObjects(GameObject... gameObjects) {
		getManager().addGosToBeRemoved(gameObjects);
		for (GameObject gameObject : gameObjects) {
			getSceneNodes().getChildren().remove(gameObject.node);
			if (gameObject instanceof KeyListener) {
				keyLogger.removeListener((KeyListener) gameObject);
			}
		}
	}

	private void addObjects(List<GameObject> generate) {
		getManager().addGo(generate.toArray(new GameObject[0]));
		for (GameObject gameObject : generate) {
			getSceneNodes().getChildren().add(gameObject.node);
		}

	}

	public void beginGameLoop() {
		getAnimationTimer().start();
	}

	protected void cleanupSprites() {
		manager.cleanupGo();
	}

	protected void checkCollisions() {
		for (GameObject go1 : manager.getActors()) {
			for (GameObject go2 : manager.getActors()) {
				if (handleCollision(go1, go2)) {
					break;
				}
			}
		}
	}

	protected MapParser<Entity> getMapParser() {
		return mapParser;
	}

	protected MapGenerator<Entity> getGenerator() {
		return generator;
	}

	protected GOFactory getFactory() {
		return factory;
	}

	protected Scene getGameSurface() {
		return gameSurface;
	}

	protected boolean handleCollision(GameObject go1, GameObject go2) {
		if (go1.isColliding(go2)) {
			go1.collide(this, go2);
			go2.collide(this, go1);
			return true;
		}
		return false;
	}

	protected void updateSprites(long time) {
		for (GameObject gameObject : manager.getActors()) {
			gameObject.update(getGameSurface(), time);
		}
	}

	protected void setSceneNodes(Group sceneNodes) {
		this.sceneNodes = sceneNodes;
	}

	public Group getSceneNodes() {
		return sceneNodes;
	}

	protected void setGameSurface(Scene gameSurface) {
		this.gameSurface = gameSurface;
	}

	public GameObjectManager getManager() {
		return manager;
	}

	public TimerWithStatus getAnimationTimer() {
		return animationTimer;
	}
}
