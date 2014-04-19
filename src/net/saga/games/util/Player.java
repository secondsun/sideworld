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
package net.saga.games.util;

import android.graphics.PointF;

public class Player extends ControllablePointedSprite {

	private boolean jumping = false;
	private final int MAX_JUMP = 5;
	private int rise = 0;
	private boolean canJump = true;

	private boolean right = false;
	private boolean left = false;
	private boolean moving = false;

	public float xvelocity = 0.0f;
	public float yvelocity = 0.0f;

	public boolean shooting = false;
	private long timeOfLastShot;
	
	
	public Player(PointedSprite _sprite) {
		super(_sprite);
	}

	/**
	 * When player presses jump
	 * 
	 */
	public void jump() {
		if (canJump) {
			jumping = true;
			if (rise == 0) {
				yvelocity = -6f;
			}
			if (canRise()) {
				++rise;
			} else {
				canJump = false;
			}
		}
	}

	public boolean canRise() {
		return (rise < MAX_JUMP &&canJump); 
	}

	/**
	 * When player releases jump early or is not pressing jump
	 * 
	 */
	public void stopRise() {
		jumping = false;
		canJump = (rise == 0);
		if (!canJump && yvelocity < -.15) {
			yvelocity = 0f;
		}
	}
	

	/**
	 * When player falls
	 * 
	 */
	public void falling() {
		jumping = false;
		canJump = false;
		rise = MAX_JUMP;
	}

	
	/**
	 * When player lands
	 */
	public void land() {
		rise = 0;
		yvelocity = 0;
		jumping = false;
		canJump = true;
	}

	public boolean isJumping() {
		return jumping;
	}

	public boolean isMoving() {
		return moving;
	}

	public void right() {
		left = false;
		right = true;
		xvelocity = 4;
		moving = true;
	}

	public void left() {
		right = false;
		xvelocity = -4;
		left = true;
		moving = true;
	}

	public void stop() {
		moving  = false;
	}

	public boolean isRight() {
		return right;
	}
	
	public boolean isLeft() {
		return left;
	}

	public PointF getAdjustedRightShoulderPosition() {
		PointF position = new PointF(_sprite.getX() +  _sprite._c.x, _sprite.getY()  + _sprite.getHeight() - _sprite._c.y);
		if (this.isLeft()) {
				position = new PointF(_sprite.getX()   + _sprite.getWidth() - _sprite._a.x, _sprite.getY()  + _sprite.getHeight() - _sprite._a.y);	
		}
		return position;
	}
	
	public PointF getAdjustedLeftShoulderPosition() {
		
		PointF position = new PointF(_sprite.getX() +  _sprite._a.x, _sprite.getY()  + _sprite.getHeight() - _sprite._a.y);
		if (this.isLeft()) {
			position = new PointF(_sprite.getX() +  _sprite.getWidth() -_sprite._c.x, _sprite.getY()  + _sprite.getHeight() - _sprite._c.y);
		}
		return position;
	}
	
	public PointF getAdjustedRightFootPosition() {
		
		PointF position = new PointF(_sprite.getX() +  _sprite._2.x, _sprite.getY()  + _sprite.getHeight() - _sprite._2.y);
		if (this.isLeft()) {
			position = new PointF(_sprite.getX() +  _sprite.getWidth() -_sprite._1.x, _sprite.getY()  + _sprite.getHeight() - _sprite._1.y);
		}
		return position;
	}
	
	public PointF getAdjustedLeftFootPosition() {
		
		PointF position = new PointF(_sprite.getX() +  _sprite._1.x, _sprite.getY()  + _sprite.getHeight() - _sprite._1.y);
		if (this.isLeft()) {
			position = new PointF(_sprite.getX() +  _sprite.getWidth() -_sprite._2.x, _sprite.getY()  + _sprite.getHeight() - _sprite._2.y);
		}
		return position;
	}
	
	public void shooting() {
		if (!shooting) {
			timeOfLastShot = System.currentTimeMillis();
		}
		shooting = true;
	}
	
	public void holster() {
		shooting = false;
	}
	
	public boolean isShooting() {
		return timeOfLastShot + 250 > System.currentTimeMillis();
	}

	public PointF getAdjustedHeadPosition() {
		return new PointF(_sprite.getX() +  _sprite._t.x, _sprite.getY()  + _sprite.getHeight() - _sprite._t.y);
	}
	
}
