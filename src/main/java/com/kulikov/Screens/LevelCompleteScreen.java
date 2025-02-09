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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kulikov.MarioBros;

/**
 * Екран LevelCompleteScreen відображається, коли гравець успішно завершує рівень.
 * Показує повідомлення про завершення рівня та кнопку для повернення до головного меню.
 */
public class LevelCompleteScreen implements Screen {
  private Viewport viewport;
  private Stage stage;
  private Game game;
  private SpriteBatch batch;
  private TextButton returnButton;

  private Texture backgroundTexture;
  private TextureRegion backgroundRegion;

  /**
   * Конструктор класу LevelCompleteScreen.
   *
   * @param game  Екземпляр гри.
   * @param batch Спрайт бетч для відображення графіки.
   */
  public LevelCompleteScreen(Game game, SpriteBatch batch) {
    this.batch = batch;
    this.game = game;
    viewport = new StretchViewport(MarioBros.V_WIDTH, MarioBros.V_HEIGHT, new OrthographicCamera());
    stage = new Stage(viewport, ((MarioBros) game).getBatch());

    backgroundTexture = new Texture("gameover.jpg");
    backgroundRegion = new TextureRegion(backgroundTexture);

    BitmapFont font = new BitmapFont();

    TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
    textButtonStyle.font = font;
    textButtonStyle.fontColor = Color.WHITE;

    returnButton = new TextButton("Click to return to main menu", textButtonStyle);
    returnButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        game.setScreen(new MainMenuScreen(game, batch));
        dispose();
      }
    });

    Label.LabelStyle labelStyle = new Label.LabelStyle();
    labelStyle.font = font;
    labelStyle.fontColor = Color.WHITE;
    Label levelCompletedLabel = new Label("Level completed!", labelStyle);

    Table table = new Table();
    table.center();
    table.setFillParent(true);
    table.add(levelCompletedLabel).center().padBottom(20f);
    table.row();
    table.add(returnButton).center();

    stage.addActor(table);
  }

  @Override
  public void show() {
    Gdx.input.setInputProcessor(stage);
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    batch.begin();
    batch.draw(backgroundRegion, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
    batch.end();

    stage.act(delta);
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

  /**
   * Видаляє всі ресурси, пов'язані з екраном LevelCompleteScreen.
   */
  @Override
  public void dispose() {
    stage.dispose();
  }
}
