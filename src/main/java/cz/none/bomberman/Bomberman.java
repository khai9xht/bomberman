package cz.none.bomberman;

import cz.none.bomberman.factory.BombermanObjectFactory;
import cz.none.bomberman.map.MapEntity;
import cz.none.bomberman.map.MapGenerator;
import cz.none.bomberman.map.MapParser;

public class Bomberman extends GameEngine<BombermanObjectFactory, MapEntity> {

	public Bomberman() {
		super((BombermanObjectFactory) Factory.getFactory(), (MapParser<MapEntity>) Factory.getMapParser(), (MapGenerator<MapEntity>) Factory.getGenerator(),
				Factory.getKeyLogger());
	}


}
