package cz.none.bomberman.map;

public enum MapEntity {
	W("Wall"), N("None"), E("Enemy"), P("Player"), B("Bomb");

	private String	name;

	private MapEntity(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
