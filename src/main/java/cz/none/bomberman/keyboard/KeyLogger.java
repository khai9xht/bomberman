package cz.none.bomberman.keyboard;

import javafx.scene.Scene;

public interface KeyLogger {
	void init(Scene scene);

	void registerListener(KeyListener listener);

	void removeListener(KeyListener listener);
}
