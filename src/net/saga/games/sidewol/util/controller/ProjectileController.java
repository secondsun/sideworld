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
import net.saga.games.util.PointedSprite;
import net.saga.games.util.ProjectileSource;
import net.saga.games.util.ShotPellet;
import net.saga.games.util.Supports;

import org.andengine.entity.scene.Scene;

@Supports(GamePad.class)
/**
 * Builds and controls flight of Tappa's shot pellet
 */
public class ProjectileController implements Controller<ProjectileSource> {

	private final Scene _mScene;

	private ConcurrentLinkedQueue<ShotPellet> availableShotQueue = new ConcurrentLinkedQueue<ShotPellet>();
	private ConcurrentLinkedQueue<ShotPellet> flyingShotQueue = new ConcurrentLinkedQueue<ShotPellet>();

	public ProjectileController(Scene scene, PointedSprite projectileSprite) {
		super();
		this._mScene = scene;
		ShotPellet shot = new ShotPellet(new PointedSprite(projectileSprite));
		scene.attachChild(shot);
		availableShotQueue.add(shot);

		shot = new ShotPellet(new PointedSprite(projectileSprite));
		scene.attachChild(shot);
		availableShotQueue.add(shot);

		shot = new ShotPellet(new PointedSprite(projectileSprite));
		scene.attachChild(shot);
		availableShotQueue.add(shot);
	}

	@Override
	public void execute(PointedSprite sourceSprite, long currentTime,
			ProjectileSource input) {

		if (flyingShotQueue.peek() != null) {
			int shotIndex = 0;
			while (shotIndex++ < flyingShotQueue.size()) {
				ShotPellet shot = flyingShotQueue.poll();
				PointedSprite shotSprite = shot._sprite;
				shotSprite.setPosition(shotSprite.getX() + shot.velocity,
						shotSprite.getY());
				shot.distance += shot.speed;
				if (shot.distance > shot.range) {
					shotSprite.stopAnimation();
					shotSprite.setVisible(false);
					shotSprite.setPosition(-20, -20);
					shot.velocity = 0;
					shot.distance = 0;
					availableShotQueue.add(shot);
				}  else {
					flyingShotQueue.add(shot);
				}
				
			}
		}
		

		if (input.spawnProjectile()) {

			if (availableShotQueue.peek() != null) {
				ShotPellet shot = availableShotQueue.poll();
				PointedSprite shotSprite = shot._sprite;
				if (sourceSprite.isFlippedHorizontal()) {
					shot.velocity = -shot.speed;
					shotSprite.setPosition(sourceSprite.getX(),
							sourceSprite.getY() + 12);

				} else {
					shot.velocity = shot.speed;
					shotSprite.setPosition(
							sourceSprite.getX() + sourceSprite.getWidth(),
							sourceSprite.getY() + 12);
				}

				shotSprite.setVisible(true);
				shotSprite.animate(10);
				flyingShotQueue.add(shot);
			}

		}

	}

}
