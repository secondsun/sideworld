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
package net.saga.games.sidewol.util.controller;

import java.util.concurrent.ConcurrentLinkedQueue;

import net.saga.games.util.GamePad;
import net.saga.games.util.Player;
import net.saga.games.util.PointedSprite;
import net.saga.games.util.Supports;

import org.andengine.extension.tmx.TMXLayer;
import org.andengine.extension.tmx.TMXProperties;
import org.andengine.extension.tmx.TMXTileProperty;
import org.andengine.extension.tmx.TMXTiledMap;

import android.graphics.PointF;
import android.view.KeyEvent;

@Supports(GamePad.class)
public class PlayerMovementController implements Controller<GamePad> {

	public static final int frameDelta = 15;

	private static final int SOLID_INDEX = 1;
	private static final int LADDER_INDEX = 0;
	
	private TMXLayer mTMXLayer;
	private TMXTiledMap mTMXTiledMap;
	private final Player player;

	
	private float delta = .5f;
	private GamePad virtualPad = new GamePad(false);
	private InputAnimationController playerStateController;

	ConcurrentLinkedQueue<KeyEvent> entries;

	public PlayerMovementController(TMXLayer tmxLayer,
			TMXTiledMap mTMXTiledMap, Player player) {
		this.playerStateController = new InputAnimationController(player);
		this.mTMXLayer = tmxLayer;
		this.mTMXTiledMap = mTMXTiledMap;
		this.player = player;
	}

	@Override
	public void execute(PointedSprite sprite, long currentTime, GamePad input) {
			entries = input.getEvents();
			
			delta = 0.5f;

			PointF spritePosition;
			PointF otherFoot;
			PointF shoulder;
			PointF otherShoulder;
			if (player.isLeft()) {
				shoulder = player.getAdjustedLeftShoulderPosition();
				spritePosition = player.getAdjustedLeftFootPosition();
				otherShoulder = player.getAdjustedRightShoulderPosition();
				otherFoot = player.getAdjustedRightFootPosition();
			} else {//Player is Right
				shoulder = player.getAdjustedRightShoulderPosition();
				otherShoulder = player.getAdjustedLeftShoulderPosition();
				spritePosition = player.getAdjustedRightFootPosition();
				otherFoot = player.getAdjustedLeftFootPosition();
			}
			PointF headPosition = shoulder;
			int tileY = Float.valueOf(spritePosition.y / mTMXTiledMap.getTileHeight()).intValue();
						
			
			if (!entries.isEmpty()) {
				KeyEvent entry = entries.poll();
				while (entry.getEventTime() <= currentTime) {
					KeyEvent event = entry;
					if (event.getAction() == KeyEvent.ACTION_DOWN) {
						virtualPad.down(event);
					}
					if (event.getAction() == KeyEvent.ACTION_UP) {
						virtualPad.up(event);
					}
					entries.remove(entry);
					if (!entries.isEmpty()) {
						entry = entries.poll();
					} else {
						break;
					}
				}
			}
			
			playerStateController.updatePlayerState(sprite,currentTime, virtualPad);

			applyGravity();
			
			if (!onGround(spritePosition) && !onGround(otherFoot) && !player.isJumping()) {
				if (player.yvelocity < 0 && (onGround(headPosition) || onGround(otherShoulder))) {//headbutt
					player.yvelocity = -0.2f;					
				}
				sprite.setPosition(sprite.getX(), sprite.getY()
						+ (player.yvelocity * delta));
				player.falling();
			} else if (player.isJumping()) {
				if (player.canRise() ) {
					if (!(onGround(headPosition) || onGround(otherShoulder))/*Check for head butt*/) {
						sprite.setPosition(sprite.getX(), sprite.getY()
								+ (player.yvelocity * delta));
					} else {
						player.yvelocity = -.2f;
						player.falling();	
					}
				} else if (!onGround(spritePosition) && !onGround(otherFoot)) {
					if (!(onGround(headPosition) || onGround(otherShoulder)))
						sprite.setPosition(sprite.getX(), sprite.getY()
								+ (player.yvelocity));
					player.falling();
				}
			} else {
				sprite.setPosition(sprite.getX(),
						(tileY - 1) * mTMXTiledMap.getTileHeight()
								+ sprite._h.y);
				player.land();
			}

			if (!onGround(spritePosition) && !onGround(otherFoot) && !player.isJumping()) {
			} else if (player.isJumping()) {
			} else {
				sprite.setPosition(sprite.getX(),
						(tileY - 1) * mTMXTiledMap.getTileHeight()
								+ sprite._h.y);
				player.land();
			}

			if (player.isMoving()) {
				
				shoulder.x = shoulder.x +  (player.xvelocity * delta);
				spritePosition.x = spritePosition.x +  (player.xvelocity * delta);
				
				if (!onGround(shoulder) ) {//Don't move INTO a wall and get stuck
					if (!onGround(spritePosition) && !onGround(otherFoot) && (player.isJumping() || (!player.isJumping() && !player.canRise()))) {//Don't fall through a wall while jumping
						sprite.setPosition(sprite.getX() + player.xvelocity * delta, sprite.getY());
					} else{
						spritePosition.x = spritePosition.x - (player.xvelocity * delta);
						if ((onGround(spritePosition) || onGround(otherFoot))  && (!player.isJumping() && player.canRise())) {//As long as one foot is on the ground...
							if (!onGround(otherFoot) && (player.isJumping() || (!player.isJumping() && !player.canRise()))) {
								
							} else {
							sprite.setPosition(sprite.getX() + player.xvelocity * delta, sprite.getY());
							}
						}
					}
				}
			} 

		virtualPad.copyState(input);
	}

	private void applyGravity() {
		if (Math.abs(player.yvelocity) < 1.0) {
			player.yvelocity += -.8f;
		} else {
			player.yvelocity += -.2f;
		}
	}

	private boolean onGround(PointF position) {
		int tileX = Float.valueOf(
				position.x
						/ mTMXTiledMap.getTileWidth()).intValue();
		int tileY = Float.valueOf(
				(mTMXTiledMap.getHeight() - position.y)
						/ mTMXTiledMap.getTileHeight()).intValue();
		
		TMXProperties<TMXTileProperty> val = mTMXLayer.getTMXTiles()[tileY][tileX].getTMXTileProperties(mTMXTiledMap);
		if (val != null && val.size() > 0) {
			TMXTileProperty prop = val.get(SOLID_INDEX);
			return prop!= null && prop.getValue().equals("true");
		} else {
			return false;
		}
	}

}
