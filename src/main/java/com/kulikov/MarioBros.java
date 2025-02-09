package com.kulikov;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kulikov.Controller.AuthenticationController;
import com.kulikov.Service.AuthenticationService;
import lombok.Getter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;


/**
 * Клас гри MarioBros, що розширює фреймворк LibGDX.
 */
@Component
public class MarioBros extends Game {
	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 200;
	public static final float PPM = 100;

	public static final short NOTHING_BIT = 0;
	public static final short GROUND_BIT = 1;
	public static final short MARIO_BIT = 2;
	public static final short BRICK_BIT = 4;
	public static final short COIN_BIT = 8;
	public static final short DESTROYED_BIT = 16;
	public static final short OBJECT_BIT = 32;
	public static final short ENEMY_BIT = 64;
	public static final short ENEMY_HEAD_BIT = 128;
	public static final short ITEM_BIT = 256;
	public static final short MARIO_HEAD_BIT = 512;

	@Getter
	private SpriteBatch batch;
	@Getter
	private static AssetManager assetManager;

	private AuthenticationController authenticationController;

	public static final AnnotationConfigApplicationContext springContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);

	/**
	 * Метод create, що ініціалізує гру.
	 */
	@Override
	public void create() {
		batch = new SpriteBatch();
		assetManager = new AssetManager();
		assetManager.load("audio/music/mario_music.ogg", Music.class);
		assetManager.load("audio/sounds/coin.wav", Sound.class);
		assetManager.load("audio/sounds/bump.wav", Sound.class);
		assetManager.load("audio/sounds/breakblock.wav", Sound.class);
		assetManager.load("audio/sounds/powerup_spawn.wav", Sound.class);
		assetManager.load("audio/sounds/powerup.wav", Sound.class);
		assetManager.load("audio/sounds/powerdown.wav", Sound.class);
		assetManager.load("audio/sounds/stomp.wav", Sound.class);
		assetManager.load("audio/sounds/mariodie.wav", Sound.class);
		assetManager.finishLoading();

		// Fetch AuthenticationService bean from Spring context
		AuthenticationService authenticationService = springContext.getBean(AuthenticationService.class);

		// Initialize the AuthenticationController
		authenticationController = new AuthenticationController(this, authenticationService, batch);

		setScreen(authenticationController);
	}

	/**
	 * Метод dispose, що відповідає за звільнення ресурсів після завершення гри.
	 */
	@Override
	public void dispose() {
		super.dispose();
		assetManager.dispose();
		batch.dispose();
		springContext.close();
	}

	/**
	 * Метод render, що відповідає за відображення гри.
	 */
	@Override
	public void render() {
		super.render();
	}
}
