package net.saga.games.sidewol.activity;

import android.content.res.Configuration;
import android.graphics.PointF;
import android.util.Log;
import java.util.ArrayList;
import net.saga.games.sidewol.util.controller.InputAnimationController;
import net.saga.games.sidewol.util.controller.PlayerMovementController;
import net.saga.games.sidewol.util.controller.ProjectileController;
import net.saga.games.sidewol.util.updateHandler.ControllableGamepadUpdateHandler;
import net.saga.games.util.ControllablePointedSprite;
import net.saga.games.util.Player;
import net.saga.games.util.PointedSprite;
import net.saga.games.util.SidewolBaseGameActivity;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.engine.options.EngineOptions;

import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.extension.tmx.TMXLayer;
import org.andengine.extension.tmx.TMXLoader;
import org.andengine.extension.tmx.TMXLoader.ITMXTilePropertiesListener;
import org.andengine.extension.tmx.TMXProperties;
import org.andengine.extension.tmx.TMXTile;
import org.andengine.extension.tmx.TMXTileProperty;
import org.andengine.extension.tmx.TMXTiledMap;
import org.andengine.extension.tmx.util.exception.TMXLoadException;
import org.andengine.entity.scene.Scene;

import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;

import org.andengine.engine.Engine;
import org.andengine.engine.options.ScreenOrientation;

public class Sidewol extends SidewolBaseGameActivity {

    /**
     * Called when the activity is first created.
     */

    private static final int CAMERA_WIDTH = 480;
    private static final int CAMERA_HEIGHT = 320;

	// ===========================================================
    // Fields
    // ===========================================================
    private BoundCamera mBoundChaseCamera;

    private TMXTiledMap mTMXTiledMap;
    private Scene mScene;
    private BitmapTextureAtlas mBitmapTextureAtlas;
    private TiledTextureRegion mTappaTextureRegion;
    private TiledTextureRegion mShotPelletTextureRegion;
    private Player player;
    private BitmapTextureAtlas mOnScreenControlTexture;
    private TextureRegion mOnScreenControlBaseTextureRegion;
    private TextureRegion mOnScreenControlKnobTextureRegion;
    private BoundCamera controlCamera;

    private boolean ost = false;

    @Override
    public Engine onCreateEngine(final EngineOptions pEngineOptions) {

        this.mBoundChaseCamera = new BoundCamera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
        this.controlCamera = new BoundCamera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);

        if (getResources().getConfiguration().keyboard == Configuration.KEYBOARD_QWERTY) {
            this.mEngine = new Engine(new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.mBoundChaseCamera));

        } else {

            throw new RuntimeException("Requires keyboard");
            
        }

        return this.mEngine;

    }

    @Override
    public void onCreateResources() {
        this.mBitmapTextureAtlas = new BitmapTextureAtlas(this.getTextureManager(), 512, 256, TextureOptions.BILINEAR);
        this.mTappaTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, this, "tappa.png", 0, 0, 2, 6);
        this.mShotPelletTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, this, "tappaPellet.png", 64, 0, 2, 2);

        this.mOnScreenControlTexture = new BitmapTextureAtlas(this.getTextureManager(), 256, 128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.mOnScreenControlBaseTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mOnScreenControlTexture, this, "onscreen_control_base.png", 0, 0);
        this.mOnScreenControlKnobTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mOnScreenControlTexture, this, "onscreen_control_knob.png", 128, 0);
        
        this.mBitmapTextureAtlas.load();

    }

    @SuppressWarnings("serial")
    @Override
    public Scene onCreateScene() {
        mScene = new Scene();

        try {
            final TMXLoader tmxLoader = new TMXLoader(this, this.mEngine.getTextureManager(), TextureOptions.BILINEAR_PREMULTIPLYALPHA, this.getVertexBufferObjectManager(), new ITMXTilePropertiesListener() {
                @Override
                public void onTMXTileWithPropertiesCreated(final TMXTiledMap pTMXTiledMap, final TMXLayer pTMXLayer, final TMXTile pTMXTile, final TMXProperties<TMXTileProperty> pTMXTileProperties) {

                }
            });
            this.mTMXTiledMap = tmxLoader.loadFromAsset("sidewold1.tmx");

        } catch (final TMXLoadException tmxle) {
            Log.d("Sidewol", tmxle.getMessage(), tmxle);
        }

        final TMXLayer tmxLayer = this.mTMXTiledMap.getTMXLayers().get(0);
        mScene.attachChild(tmxLayer);

        /* Make the camera not exceed the bounds of the TMXEntity. */
        this.mBoundChaseCamera.setBounds(0, tmxLayer.getWidth(), 0, tmxLayer.getHeight());
        this.mBoundChaseCamera.setBoundsEnabled(true);

        loadTappa();
        this.mBoundChaseCamera.setChaseEntity(player);
        this.player.addController(new PlayerMovementController(tmxLayer, mTMXTiledMap, this.player));
        this.player.addController(new InputAnimationController(this.player));
        this.player.addController(new ProjectileController(this.mScene, loadShot()));

		//mEngine.registerUpdateHandler(new TickUpdateHandler(new ArrayList<ControllablePointedSprite>() {{add(Sidewol.this.player);}}));
        mEngine.registerUpdateHandler(new ControllableGamepadUpdateHandler(gamePad, new ArrayList<ControllablePointedSprite>() {
            {
                add(Sidewol.this.player);
            }
        }));

        return mScene;
    }

    private PointedSprite loadShot() {
        final PointedSprite face = new PointedSprite(0, 0, 8, 8, this.mShotPelletTextureRegion, this.getVertexBufferObjectManager());
        return face;
    }

    private void loadTappa() {

        final PointedSprite face = new PointedSprite(33, 0, 32, 32, this.mTappaTextureRegion,
                new PointF(0, 28),
                new PointF(0, 4),
                new PointF(24, 28),
                new PointF(24, 4),
                new PointF(18, 28),
                new PointF(18, 4),
                new PointF(0, 4),
                new PointF(24, 4),
                this.getVertexBufferObjectManager()
        );
        /**
         * _b = new PointF(0, 0); _c = new PointF(pTileWidth, pTileHeight); _d =
         * new PointF(pTileWidth, 0); _h = new PointF(pTileWidth / 2, 0); _t =
         * new PointF(pTileWidth / 2, pTileHeight); _1 = _b; _2 = _d;
         */
        this.player = new Player(face);
        //this.player.addController( new InputAnimationController(player));
        mScene.attachChild(player);

    }

    @Override
    public EngineOptions onCreateEngineOptions() {
        return null;
    }


}
