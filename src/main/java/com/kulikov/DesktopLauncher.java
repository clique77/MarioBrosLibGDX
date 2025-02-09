package com.kulikov;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.kulikov.Mapper.UserMapper;
import com.kulikov.Repository.UserRepository;
import com.kulikov.connection.ConnectionManager;

public class DesktopLauncher {
	public static void main(String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setWindowedMode(1920, 1080);
		config.setTitle("Super Mario Bros");

		// Ensure these objects are created correctly, potentially with Spring if needed
		UserMapper userMapper = new UserMapper();
		ConnectionManager connectionManager = new ConnectionManager();
		UserRepository userRepository = new UserRepository(userMapper, connectionManager);

		// Initialize the game with the configuration
		new Lwjgl3Application(new MarioBros(), config);
	}
}
