package cz.none.bomberman.map;

import java.io.File;
import java.util.List;

public interface MapParser<Entity> {
	List<List<Entity>> parse(File file);
}
