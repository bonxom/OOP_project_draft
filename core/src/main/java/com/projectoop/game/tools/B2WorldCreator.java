package com.projectoop.game.tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.projectoop.game.GameWorld;
import com.projectoop.game.screens.PlayScreen;
import com.projectoop.game.sprites.Lava;
import com.projectoop.game.sprites.Spike;

public class B2WorldCreator {
    public B2WorldCreator(PlayScreen screen){
        World world = screen.getWorld();
        TiledMap map = screen.getMap();

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //ground
        for (MapObject object : map.getLayers().get(9).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth()/2)/ GameWorld.PPM, (rect.getY() + rect.getHeight()/2)/GameWorld.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2/GameWorld.PPM, rect.getHeight()/2/GameWorld.PPM);
            fdef.shape = shape;
            fdef.filter.categoryBits = GameWorld.OBJECT_BIT;//if (automatic) enemy collide with object, then change direction
            body.createFixture(fdef);
        }
        //trap
        for (MapObject object : map.getLayers().get(10).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();

            new Spike(screen, rect);
        }
        //lava
        for (MapObject object : map.getLayers().get(11).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();

            new Lava(screen, rect);
        }
    }
}
