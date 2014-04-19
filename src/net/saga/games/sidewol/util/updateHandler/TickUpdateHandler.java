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

import java.util.Collection;

import net.saga.games.sidewol.util.controller.Controller;
import net.saga.games.util.ControllablePointedSprite;
import net.saga.games.util.Supports;

import org.andengine.engine.handler.IUpdateHandler;

public class TickUpdateHandler implements IUpdateHandler {

	private final Collection<ControllablePointedSprite> controllables; 
	
	private long previousTime = -1l;
	public static final int frameDelta = 15;
	
	public TickUpdateHandler(Collection<ControllablePointedSprite> controllables) {
		super();
		this.controllables = controllables;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onUpdate(float pSecondsElapsed) {
		long currentTime = System.currentTimeMillis();

		
		if (previousTime == -1) {
			previousTime = currentTime - frameDelta;
		}

		while (previousTime + frameDelta <= currentTime) {

		for (ControllablePointedSprite controllable : controllables) {
			for (@SuppressWarnings("rawtypes") Controller controller : controllable.getControllers()) {
				Supports annotation = controller.getClass().getAnnotation(Supports.class);
				
				
				if (annotation.value() != null && annotation.value().equals(Void.class)) {
					controller.execute(controllable._sprite,System.currentTimeMillis(), null);
				}
			}
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
