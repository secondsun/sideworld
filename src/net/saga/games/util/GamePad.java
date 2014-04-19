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

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import android.view.KeyEvent;

public class GamePad implements ProjectileSource {

	private Map<Integer, Button> buttonMap;
	private Map<Button, Integer> keyCodeMap;
	private int buttonState = 0;
	// set to false when S is released, true when spawnPorjectile is called.
	// This is to keep projectiles form spamming, on has to tap the button.
	public boolean waitForSUp = false;

	/**
	 * These will be consumed by the PlayerMovementController Events older than
	 * 15 seconds will cause a Assertion Exception at some point.
	 */
	private ConcurrentLinkedQueue<KeyEvent> eventTimeline = new ConcurrentLinkedQueue<KeyEvent>();
	private boolean logInput = true;

	public enum Button {
		LEFT(1), RIGHT(2), ACTION_1(4), ACTION_2(8);
		
		public final int id;
		private Button(int id) {
			this.id = id;
		}
		

	}

	public GamePad(boolean logInput) {
		this();
		this.logInput = logInput;
	}

	public GamePad() {
		buttonMap = new HashMap<Integer, GamePad.Button>();
		buttonMap.put(KeyEvent.KEYCODE_DPAD_LEFT, Button.LEFT);
		buttonMap.put(KeyEvent.KEYCODE_DPAD_RIGHT, Button.RIGHT);
		buttonMap.put(KeyEvent.KEYCODE_A, Button.ACTION_1);
		buttonMap.put(KeyEvent.KEYCODE_BUTTON_A, Button.ACTION_1);
		buttonMap.put(KeyEvent.KEYCODE_BUTTON_B, Button.ACTION_2);
		buttonMap.put(KeyEvent.KEYCODE_S, Button.ACTION_2);

		keyCodeMap = new HashMap<GamePad.Button, Integer>();
		keyCodeMap.put(Button.LEFT, KeyEvent.KEYCODE_DPAD_LEFT);
		keyCodeMap.put(Button.RIGHT, KeyEvent.KEYCODE_DPAD_RIGHT);
		keyCodeMap.put(Button.ACTION_1, KeyEvent.KEYCODE_A);
		keyCodeMap.put(Button.ACTION_2, KeyEvent.KEYCODE_S);

	}

	public void down(KeyEvent event) {
		int keyCode = event.getKeyCode();
		if (buttonMap.get(keyCode) != null)
			buttonState |= buttonMap.get(keyCode).id;
		logInput(event);
	}

	private void logInput(KeyEvent event) {
		if (logInput) {
			eventTimeline.add(event);

		}
	}

	public void up(KeyEvent event) {
		
		int keyCode = event.getKeyCode();
		if (buttonMap.get(keyCode) != null)
			buttonState &= ~buttonMap.get(keyCode).id;
		if (keyCode == KeyEvent.KEYCODE_S ||
				keyCode == KeyEvent.KEYCODE_BUTTON_B) {
			waitForSUp = false;
		}
		logInput(event);
	}

	public int getState() {
		return buttonState;
	}

	public boolean isADown() {	
		return (buttonState & Button.ACTION_1.id) > 0;
	}

	public boolean isSDown() {
		return (buttonState & Button.ACTION_2.id) > 0;
	}

	public boolean isLeftDown() {
		return (buttonState & Button.LEFT.id) > 0;
	}

	public boolean isRightDown() {
		return (buttonState & Button.RIGHT.id) > 0;
	}

	/**
	 * Returns an instance of entries after clearing it. NOT THREAD SAFE! MAKE
	 * DEFENSIVE COPIES ON THE CALLER END!
	 * 
	 * @return
	 */
	public ConcurrentLinkedQueue<KeyEvent> getEvents() {
			return eventTimeline;
	}

	public void copyState(GamePad otherInput) {
		this.buttonState = otherInput.getState();
	}

	@Override
	public boolean pollSpawnProjectile() {
		
		return isSDown() && !waitForSUp;
	}

	@Override
	public boolean spawnProjectile() {
		boolean toReturn = pollSpawnProjectile();
		if (toReturn) {
			waitForSUp = true;
		}
		return toReturn;
	}

	/**
	 * From a On Screen controller thing.
	 * 
	 * @param pValueX
	 * @param pValueY
	 */
	public void down(float pValueX, float pValueY) {
		if (pValueX == 0) {
			if (buttonMap.get(KeyEvent.KEYCODE_DPAD_LEFT) != null)
				this.up(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DPAD_LEFT));
			if (buttonMap.get(KeyEvent.KEYCODE_DPAD_RIGHT) != null)
				this.up(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DPAD_RIGHT));
		} else if (pValueX < 0) {
			this.down(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_LEFT));
			if (buttonMap.get(KeyEvent.KEYCODE_DPAD_RIGHT) != null)
				this.up(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DPAD_RIGHT));
		}  else if (pValueX > 0) {
			if (buttonMap.get(KeyEvent.KEYCODE_DPAD_LEFT) != null)				
				this.up(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DPAD_LEFT));
			this.down(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_RIGHT));
		} 
		
		if (pValueY == 0) {
			this.up(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DPAD_UP));
			this.up(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DPAD_DOWN));
		} else if (pValueY < 0) {
			this.up(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DPAD_UP));
			this.down(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_DOWN));
		} else if (pValueY > 0) {
			this.down(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_UP));
			this.up(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DPAD_DOWN));
		}  
		
	}

}
