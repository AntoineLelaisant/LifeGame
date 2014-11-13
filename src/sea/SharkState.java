package sea;

import java.util.LinkedList;

/**
 * SharkState Class.
 * 
 * Represent a {@link Shark} state in the context of the State pattern
 * 
 * @author antoine
 */
public abstract class SharkState
{
	protected Shark shark;
	
	/**
	 * The class constructor
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
			this.shark.setCoordinate(target);	
		}
	}
	
	/**
	 * Retrieves the prefered places from the available places around the {@link Shark}.
	 * 
	 * This method contains all the artifical intelligence of the sharks.
	 * 
	 * @param availablesPlaces The list of the available places around the {@link Shark}
	 * @return The LinkedList of prefered coordinate 
	 */
	protected abstract LinkedList<Coordinate> getPreferedPlaces(LinkedList<Coordinate> availablesPlaces);
	
	/**
	 * Check the age of the shark 
	 * and change its state if necessary
	 */
	public abstract void checkAge();

}
