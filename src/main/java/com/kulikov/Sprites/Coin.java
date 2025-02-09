package com.kulikov.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Vector2;
import com.kulikov.MarioBros;
import com.kulikov.Scenes.Hud;
import com.kulikov.Screens.PlayScreen;
import com.kulikov.Sprites.Items.ItemDefinition;
import com.kulikov.Sprites.Items.Mushroom;

public class Coin extends InteractiveTileObject {
  private static TiledMapTileSet tileSet;
  private final int BLANK_COIN = 28;

  /**
   * Конструктор класу Coin.
   *
   * @param screen Екран гри.
   * @param object Об'єкт карти.
   */
  public Coin(PlayScreen screen, MapObject object) {
    super(screen, object);
    tileSet = map.getTileSets().getTileSet("tileset_gutter");
    fixture.setUserData(this);
    setCategoryFilter(MarioBros.COIN_BIT);
  }

  /**
   * Обробка відображення удару головою Маріо.
   *
   * @param mario Маріо, який вдарився об'єктом.
   */
  @Override
  public void onHeadHit(Mario mario) {
    Gdx.app.log("Coin", "Collision");
    if (getCell().getTile().getId() == BLANK_COIN) {
      MarioBros.getAssetManager().get("audio/sounds/bump.wav", Sound.class).play();
    } else {
      if (object.getProperties().containsKey("mushroom")) {
        screen.spawnItem(new ItemDefinition(new Vector2(body.getPosition().x, body.getPosition().y + 16 / MarioBros.PPM),
            Mushroom.class));
        MarioBros.getAssetManager().get("audio/sounds/powerup_spawn.wav", Sound.class).play();
      } else {
        MarioBros.getAssetManager().get("audio/sounds/coin.wav", Sound.class).play();
      }
    }
    getCell().setTile(tileSet.getTile(BLANK_COIN));
    Hud.addScore(300);
  }

}
