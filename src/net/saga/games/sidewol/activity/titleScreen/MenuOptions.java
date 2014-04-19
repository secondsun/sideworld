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
