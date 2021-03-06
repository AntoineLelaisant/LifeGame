package sea;

import java.util.LinkedList;

/**
 * SharkStateSemiAdult Class.
 * 
 * Represent the semi-adult state of the {@link Shark}
 * 
 * @author antoine
 */
public class SharkStateSemiAdult extends SharkState
{

	/**
	 * @see SharkState#SharkState(Shark)
	 */
	public SharkStateSemiAdult(Shark shark)
	{
		super(shark);
	}

	@Override
	public void checkAge()
	{
		if (this.shark.getAge() >= Shark.AGE_ADULT) {
			this.shark.setState(this.shark.stateAdult);
		}
	}
	
	/**
	 * Gets the list of {@link Coordinate} that contains a {@link Sardine}
	 * 
	 * If there's no {@link Sardine} around the {@link Shark}, it only return
	 * the normal available places
	 * 
	 * @param availablesPlaces the availables places around the shark
	 * @return the list of prefered places
	 */
	protected LinkedList<Coordinate> getPreferedPlaces(LinkedList<Coordinate> availablesPlaces)
	{
		/*
		 * Clone to prevent ConcurrentModificationException
		 */
		@SuppressWarnings("unchecked")
		LinkedList<Coordinate> coords = (LinkedList<Coordinate>) availablesPlaces.clone();
		LinkedList<Coordinate> preferedPlaces = new LinkedList<Coordinate>();
		
		for (Coordinate coordinate : coords) {
			
			try {
				/*
				 * If the square contains a Sardine
				 */
				if (this.shark.getSea().getSquare(coordinate) instanceof Sardine) {
					preferedPlaces.add(coordinate);
				}
			} catch (IndexOutOfBoundsException e) {
				/*
				 * This exception means that the coordinate
				 * is out of the sea's bounds. So we must not
				 * add it to the preferedPlaces
				 */
			}
		}
		
		/*
		 * If preferdePlaces does not contain any Coordinate
		 * it means that there no Sardine next to the Shark.
		 * There is no particular prefered places to go so 
		 * the Shark can move to any available place
		 */
		return (preferedPlaces.size() > 0) ? preferedPlaces : availablesPlaces;
	}

}
