package cz.none.bomberman.map;

import java.util.List;

import cz.none.bomberman.go.GameObject;

public interface MapGenerator<Entity> {
	List<GameObject> generate(List<List<Entity>> mapEntities);
}
