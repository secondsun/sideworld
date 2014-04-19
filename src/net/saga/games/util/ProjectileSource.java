package net.saga.games.util;

public interface ProjectileSource {

	/**
	 * 
	 * Should I spawn a projectile?
	 * 
	 * @return
	 */
	public boolean pollSpawnProjectile();
	
	/**
	 * 
	 * Should I spawn a projectile and clears the flag
	 * 
	 * @return
	 */
	public boolean spawnProjectile();
	
}
