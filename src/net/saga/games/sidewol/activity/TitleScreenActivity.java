/**
 * Copyright Hoyt Summers Pittman III
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.saga.games.sidewol.activity;

import javax.microedition.khronos.opengles.GL10;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.extension.tmx.TMXLayer;
import org.andengine.extension.tmx.TMXLoader;
import org.andengine.extension.tmx.TMXProperties;
import org.andengine.extension.tmx.TMXTile;
import org.andengine.extension.tmx.TMXTileProperty;
import org.andengine.extension.tmx.TMXTiledMap;
import org.andengine.extension.tmx.TMXLoader.ITMXTilePropertiesListener;
import org.andengine.extension.tmx.util.exception.TMXLoadException;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.TextMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ColorMenuItemDecorator;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;


import net.saga.games.sidewol.activity.titleScreen.MenuOptions;
import net.saga.games.util.SidewolBaseGameActivity;

public class TitleScreenActivity extends SidewolBaseGameActivity implements IOnMenuItemClickListener {


	private static final int CAMERA_WIDTH = 1024;
	private static final int CAMERA_HEIGHT = 576;
	private BoundCamera mBoundChaseCamera;
	private Scene mScene;
	private TMXTiledMap mTMXTiledMap;
	private BitmapTextureAtlas mFontTexture;
	private Font mFont;
	private Scene mMenuScene;

	protected static final int MENU_RESET = 0;
	protected static final int MENU_QUIT = MENU_RESET + 1;
    
	@Override
	public Engine onCreateEngine(EngineOptions pEngineOptions) {
		this.mBoundChaseCamera = new BoundCamera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);		
		this.mEngine= new Engine(new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.mBoundChaseCamera));
		return this.mEngine;
	}

        
        
        @Override
	public void onCreateResources() {
		this.mFontTexture = new BitmapTextureAtlas(this.getTextureManager(), 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);

		FontFactory.setAssetBasePath("font/");
		this.mFont = FontFactory.createFromAsset(this.getFontManager(), this.mFontTexture, this.getAssets(), "Plok.ttf", 48, true, Color.WHITE);
		this.mEngine.getTextureManager().loadTexture(this.mFontTexture);
		this.mEngine.getFontManager().loadFont(this.mFont);
	}        
        
	@Override
	public Scene onCreateScene() {
		mScene = new Scene();
		
		try {
			final TMXLoader tmxLoader = new TMXLoader(this.getAssets(), this.mEngine.getTextureManager(), TextureOptions.BILINEAR_PREMULTIPLYALPHA, this.getVertexBufferObjectManager(), new ITMXTilePropertiesListener() {
				@Override
				public void onTMXTileWithPropertiesCreated(final TMXTiledMap pTMXTiledMap, final TMXLayer pTMXLayer, final TMXTile pTMXTile, final TMXProperties<TMXTileProperty> pTMXTileProperties) {
				
					
				}
			});
			this.mTMXTiledMap = tmxLoader.loadFromAsset("title.tmx");

			
		} catch (final TMXLoadException tmxle) {
                    Log.e("TITLE_SCREEN", tmxle.getMessage(), tmxle);
		}
		final TMXLayer tmxLayer = this.mTMXTiledMap.getTMXLayers().get(0);
		mScene.attachChild(this.mTMXTiledMap);

		/* Make the camera not exceed the bounds of the TMXEntity. */
		this.mBoundChaseCamera.setBounds(0, tmxLayer.getWidth(), 0, tmxLayer.getHeight());
		this.mBoundChaseCamera.setBoundsEnabled(true);
		
		this.mMenuScene = this.createMenuScene();
		this.mScene.setChildScene(this.mMenuScene, false, true, true);		
		return mScene;
		
	}

	

	protected MenuScene createMenuScene() {
		final MenuScene menuScene = new MenuScene(this.mBoundChaseCamera);

		
		for (MenuOptions option : MenuOptions.values()) {
			final IMenuItem resetMenuItem = new ColorMenuItemDecorator(
                                new TextMenuItem(option.ordinal(), this.mFont, option.getLabel(), this.getVertexBufferObjectManager()), 
                                org.andengine.util.adt.color.Color.RED,
                                org.andengine.util.adt.color.Color.WHITE);
			resetMenuItem.setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
			menuScene.addMenuItem(resetMenuItem);
		}

		menuScene.buildAnimations();

		menuScene.setBackgroundEnabled(false);

		menuScene.setOnMenuItemClickListener(this);
		return menuScene;
	}


	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		
		try {

			switch (MenuOptions.values()[pMenuItem.getID()]){
			case START:
				Intent intent = new Intent(); 
					intent.setClass(this, Sidewol.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
					startActivity(intent);
					return true;
			case QUIT:
				this.finish();
				return true;
			default:
				return false;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IllegalArgumentException("The Menu Item ID is not set as the orginal of the MenuOptions enum");
		}
		
	}

    @Override
    public EngineOptions onCreateEngineOptions() {
        return null;
    }

	
}
