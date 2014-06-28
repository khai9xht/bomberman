package cz.none.bomberman;

import cz.none.bomberman.factory.BombermanObjectFactory;
import cz.none.bomberman.factory.BombermanObjectFactoryImpl;
import cz.none.bomberman.factory.GameObjectFactory;
import cz.none.bomberman.keyboard.KeyLogger;
import cz.none.bomberman.keyboard.KeyLoggerImpl;
import cz.none.bomberman.map.BombermanMapGeneratorImpl;
import cz.none.bomberman.map.BombermanMapParserImpl;
import cz.none.bomberman.map.MapGenerator;
import cz.none.bomberman.map.MapParser;

public final class Factory {
	private static final Factory	FACTORY	= new Factory();

	private final KeyLogger			keyLogger;
	private final GameObjectFactory	factory;
	private final MapParser<?>		mapParser;
	private final MapGenerator<?>	generator;

	private Factory() {
		keyLogger = new KeyLoggerImpl();
		factory = new BombermanObjectFactoryImpl(keyLogger);
		mapParser = new BombermanMapParserImpl();
		generator = new BombermanMapGeneratorImpl((BombermanObjectFactory) factory);
	}

	public static KeyLogger getKeyLogger() {
		return FACTORY.keyLogger;
	}

	public static GameObjectFactory getFactory() {
		return FACTORY.factory;
	}

	public static MapGenerator<?> getGenerator() {
		return FACTORY.generator;
	}

	public static MapParser<?> getMapParser() {
		return FACTORY.mapParser;
	}
}
