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
package net.saga.games.sidewol.activity.titleScreen;

public enum MenuOptions {
	START("Start"),
	QUIT("Quit");
	
	private String label;

	private MenuOptions(String label) {
		this.label = label;
	}
	
	/**
	 * 
	 * This will search for a {@link MenuOptions} with the same label value.
	 * This method ignores case.
	 * 
	 * @throws IllegalArgumentException if label is not in the set of labels
	 * @param label
	 * @return
	 */
	public static final MenuOptions getFromLabel(String label) {
		for (MenuOptions option : values()) {
			if (option.getLabel().equalsIgnoreCase(label)) {
				return option;
			}
		}
		throw new IllegalArgumentException(label + " is not a valid label for this enum typpe.");
	} 
	
	public String getLabel() {
		return this.label;
	}
	
	
}
