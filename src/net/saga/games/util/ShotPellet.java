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
