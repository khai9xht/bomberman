package cz.none.bomberman.go;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameObjectManager {
	private final List<GameObject>	actors				= new ArrayList<>();
	private final Set<GameObject>	cleanUpSprites		= new HashSet<>();

	public List<GameObject> getActors() {
		return actors;
	}

	public void addGo(GameObject... gameObjects) {
		actors.addAll(Arrays.asList(gameObjects));
	}

	public void removeGo(GameObject... gameObjects) {
		actors.removeAll(Arrays.asList(gameObjects));
	}

	public Set<GameObject> getCleanUpSprites() {
		return cleanUpSprites;
	}

	public void addGosToBeRemoved(GameObject... gameObjects) {
		cleanUpSprites.addAll(Arrays.asList(gameObjects));
	}

	public void cleanupGo() {
		actors.removeAll(cleanUpSprites);
		cleanUpSprites.clear();
	}

}
