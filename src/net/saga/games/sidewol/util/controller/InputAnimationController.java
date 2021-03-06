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

import net.saga.games.util.GamePad;
import net.saga.games.util.Player;
import net.saga.games.util.PointedSprite;
import net.saga.games.util.Supports;

/**
 * Control animation
 * 
 * @author Summers
 * 
 */
@Supports(GamePad.class)
public class InputAnimationController implements Controller<GamePad> {

	private final Player player;

	public InputAnimationController(Player player) {
		super();
		this.player = player;
	}

	public void updatePlayerState(PointedSprite sprite, long currentTime, GamePad input) {
		if (input.isRightDown()) {
			player.right();
		} else if (input.isLeftDown()) {
			player.left();
		} else {
			player.stop();
		}

		if (input.isADown()) {
			player.jump();

		} else {
			player.stopRise();
		}

		if (input.isSDown()) {
			player.shooting();
		} else {
			player.holster();
		}
		
		if (player.isLeft()) {
			sprite.setFlippedHorizontal(true);
		} else {
			sprite.setFlippedHorizontal(false);
		}


	}
	
	@Override
	/**
	 * Unlike PlayerMovementController, this only deals with the view.
	 * Therefore, it won't look at the controller events.
	 * 
	 */
	public void execute(PointedSprite sprite, long currentTime, GamePad input) {
		
		updatePlayerState(sprite, currentTime, input);
		
		if (!player.isMoving() && (player.canRise() && !player.isJumping())) {
			if (sprite.isAnimationRunning()) {
				sprite.stopAnimation();
			}
			if (input.pollSpawnProjectile() || player.isShooting()) {
				sprite.setCurrentTileIndex(6);
			} else {
				sprite.setCurrentTileIndex(0);
			}
		} else if (player.isJumping() || !player.canRise()) {
			if (sprite.isAnimationRunning()) {
				sprite.stopAnimation();
			}
			if (input.pollSpawnProjectile() || player.isShooting()) {
				sprite.setCurrentTileIndex(11);
			} else {
				sprite.setCurrentTileIndex(5);
			}
		} else if (player.isMoving()) {
			if (!sprite.isAnimationRunning() && !player.isJumping()) {
				long[] pFrameDurations = { 150, 150, 150, 150 };
				sprite.animate(pFrameDurations, 1, 4, true);
			}
			
			if (input.pollSpawnProjectile() || player.isShooting()) {
				sprite.frameAdjust = 6;
			} else {
				sprite.frameAdjust = 0;
			}
			

		}

	}

}
