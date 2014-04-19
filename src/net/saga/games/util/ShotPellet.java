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

/**
 * 
 * PLayer fired shots
 * 
 * @author summers
 *
 */
public class ShotPellet extends ControllablePointedSprite {

	/**
	 * How fast it moves
	 */
	public final int speed = 10;
	
	/**
	 * How far it moves
	 */
	public final int range  = 320;
	
	/**
	 * How fast and what direction it is moving
	 */
	public int velocity = 0;
	
	/**
	 * How far it has moved
	 */
	public int distance = 0;
	
	public ShotPellet(PointedSprite _sprite) {
		super(_sprite);
	}

}
