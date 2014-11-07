package sea;

import java.util.LinkedList;

public abstract class SharkState
{
	protected Shark shark;
	
	/**
	 * 
	 * @param shark
	 */
	public SharkState(Shark shark)
	{
		this.shark = shark;
	}
	
	/**
	 * Move the shark
	 */
	public void move()
	{
		LinkedList<Coordinate> preferedPlaces = this.getPreferedPlaces(this.shark.getAvailablePlaces()); 
		Coordinate target = Coordinate.getRandomCoord(preferedPlaces);
		
		/*
		 * If target is null it means
		 * that the fish have no available place
		 * to go. So we do nothing
		 */
		if (target != null) {
			// this.sea.moveFish(this, target);
			this.shark.setCoordinate(target);	
		}
	}
	
	/**
	 * Retrieves the prefered places for the shark
	 * 
	 * @param availablesPlaces
	 * @return
	 */
	protected abstract LinkedList<Coordinate> getPreferedPlaces(LinkedList<Coordinate> availablesPlaces);
	
	/**
	 * Check the age of the shark 
	 * and change its state if necessary
	 */
	public abstract void checkAge();

}
