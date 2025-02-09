package com.kulikov.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kulikov.MarioBros;

/**
 * Екран вибору рівнів гри.
 */
public class LevelScreen implements Screen {

  private Viewport viewport;
  private Stage stage;
  private Game game;
  private SpriteBatch batch;
  private Texture backgroundTexture;
  private Texture buttonBackgroundTexture;

  /**
   * Конструктор класу LevelScreen.
   *
   * @param game  Екземпляр гри.
   * @param batch SpriteBatch для відображення графіки.
   */
  public LevelScreen(Game game, SpriteBatch batch) {
    this.game = game;
    this.batch = batch;
    viewport = new StretchViewport(MarioBros.V_WIDTH, MarioBros.V_HEIGHT, new OrthographicCamera());
    stage = new Stage(viewport, batch);

    Gdx.input.setInputProcessor(stage);

    backgroundTexture = new Texture("assets/background-image.jpg");

    Image backgroundImage = new Image(backgroundTexture);
    backgroundImage.setSize(MarioBros.V_WIDTH, MarioBros.V_HEIGHT);
    backgroundImage.setFillParent(true);

    stage.addActor(backgroundImage);

    buttonBackgroundTexture = new Texture("assets/button.png");

    int buttonWidth = 200;
    int buttonHeight = 80;

    buttonBackgroundTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    TextureRegionDrawable buttonDrawable = new TextureRegionDrawable(new TextureRegion(buttonBackgroundTexture));
    buttonDrawable.setMinWidth(buttonWidth);
    buttonDrawable.setMinHeight(buttonHeight);

    // Font for buttons
    BitmapFont font = new BitmapFont();

    // Create button style
    TextButtonStyle textButtonStyle = new TextButtonStyle();
    textButtonStyle.font = font;
    textButtonStyle.fontColor = Color.WHITE;
    textButtonStyle.up = buttonDrawable;

    // Create buttons
    TextButton level1Button = new TextButton("Level 1", textButtonStyle);
    TextButton level2Button = new TextButton("Level 2", textButtonStyle);
    TextButton backButton = new TextButton("Back", textButtonStyle);

    level1Button.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        game.setScreen(new PlayScreen(game, batch, "level1.tmx"));
        dispose();
      }
    });

    level2Button.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        game.setScreen(new PlayScreen(game, batch, "level2.tmx"));
        dispose();
      }
    });

    backButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        game.setScreen(new MainMenuScreen(game, batch));
        dispose();
      }
    });

    Table table = new Table();
    table.center();
    table.setFillParent(true);

    table.add(level1Button).pad(-15).width(buttonWidth).height(buttonHeight).row();
    table.add(level2Button).pad(-15).width(buttonWidth).height(buttonHeight).row();
    table.add(backButton).pad(-15).width(buttonWidth).height(buttonHeight).row();

    stage.addActor(table);
  }

  @Override
  public void show() {
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
    stage.draw();
  }

  @Override
  public void resize(int width, int height) {
    viewport.update(width, height);
  }

  @Override
  public void pause() {
  }

  @Override
  public void resume() {
  }

  @Override
  public void hide() {
  }

  @Override
  public void dispose() {
    stage.dispose();
    backgroundTexture.dispose();
    buttonBackgroundTexture.dispose();
  }
}
