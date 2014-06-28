package cz.none.bomberman.factory;

import cz.none.bomberman.go.Bomb;
import cz.none.bomberman.go.MovableObject;
import cz.none.bomberman.go.Player;
import cz.none.bomberman.go.Wall;

public interface BombermanObjectFactory extends GameObjectFactory {

	MovableObject getMovableObject(int x, int y);

	Player getPlayer(int x, int y);

	Wall getWall(int x, int y);

	Bomb getBomb(int x, int y);

}
