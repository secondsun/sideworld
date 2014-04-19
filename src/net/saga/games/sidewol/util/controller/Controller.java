package net.saga.games.sidewol.util.controller;

import net.saga.games.util.ControllablePointedSprite;
import net.saga.games.util.PointedSprite;


/**
 * 
 * Does SOMETHING on a pointedSprite.
 * 
 * See {@link ControllablePointedSprite}
 * 
 * @author Summers
 *
 */
public interface Controller<T> {

	void execute(PointedSprite sprite, long currentTime, T input);
	
}
