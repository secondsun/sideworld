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
package net.saga.games.sidewol.util.updateHandler;

import java.util.ArrayList;
import java.util.Collection;

import net.saga.games.sidewol.util.controller.Controller;
import net.saga.games.util.ControllablePointedSprite;
import net.saga.games.util.GamePad;
import net.saga.games.util.Supports;

import org.andengine.engine.handler.IUpdateHandler;

import android.os.SystemClock;
import android.util.Pair;

public class ControllableGamepadUpdateHandler implements IUpdateHandler {

	public final GamePad _gamepPad;
	private final ArrayList<Pair<ControllablePointedSprite, Controller<GamePad>>> controllers;
	private long previousTime = -1l;
	public static final int frameDelta = 15;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ControllableGamepadUpdateHandler(GamePad _gamepPad,
			Collection<ControllablePointedSprite> controllables) {
		super();
		this._gamepPad = _gamepPad;
		this.controllers = new ArrayList<Pair<ControllablePointedSprite, Controller<GamePad>>>(5);

		for (ControllablePointedSprite controllable : controllables) {
			for (Controller controller : controllable.getControllers()) {
				Supports annotation = controller.getClass().getAnnotation(
						Supports.class);

				if (annotation.value() != null
						&& annotation.value().equals(GamePad.class)) {
					this.controllers.add(new Pair<ControllablePointedSprite, Controller<GamePad>>(controllable, controller));
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onUpdate(float pSecondsElapsed) {
		long currentTime = SystemClock.uptimeMillis();

		if (previousTime == -1) {
			previousTime = currentTime - frameDelta;
		}

		while (previousTime + frameDelta <= currentTime) {

			for (Pair<ControllablePointedSprite, Controller<GamePad>> controller : controllers) {
				controller.second.execute(controller.first._sprite, previousTime
						+ frameDelta, _gamepPad);

			}
			previousTime += frameDelta;
		}

		previousTime = currentTime;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

}
