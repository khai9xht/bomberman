package cz.none.bomberman.map;

public class BombermanMapParserImpl extends MapParserImpl<MapEntity> {

	@Override
	protected MapEntity parseEntity(char charAt) {
		return MapEntity.valueOf(String.valueOf(charAt));
	}

}
