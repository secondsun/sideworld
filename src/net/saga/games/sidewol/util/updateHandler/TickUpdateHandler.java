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
