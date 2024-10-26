package com.projectoop.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.projectoop.game.GameWorld;
import com.badlogic.gdx.math.Rectangle;
import com.projectoop.game.scences.Hud;
import com.projectoop.game.screens.PlayScreen;
import com.projectoop.game.tools.AudioManager;


import java.awt.*;

public class Spike extends InteractiveTileObject{
    public Spike (PlayScreen screen, Rectangle bounds){
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(GameWorld.SPIKE_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Spike", "Collision");
        setCategoryFilter(GameWorld.DESTROYED_BIT);
        //getCell().setTile(null);
        Hud.addScore(200);
        AudioManager.manager.get(AudioManager.knightHurtAudio, Sound.class).play();
    }

    @Override
    public void onFootHit() {
        Gdx.app.log("Spike", "Collision");
        System.out.println("Spike");
        AudioManager.manager.get(AudioManager.knightHurtAudio, Sound.class).play();
    }
}
