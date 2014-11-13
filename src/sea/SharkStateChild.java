package sea;

import java.util.LinkedList;

/**
 * SharkStateChild Class.
 * 
 * Represent the child state of a {@link Shark}
 * 
 * @author antoine
 */
public class SharkStateChild extends SharkState
{
	/**
	 * @see SharkState#SharkState(Shark)
	 */
	public SharkStateChild(Shark shark)
	{
		super(shark);
	}

	@Override
	public void checkAge()
	{
		if (this.shark.getAge() >= Shark.AGE_SEMI_ADULT) {
			this.shark.setState(this.shark.stateSemiAdult);
		}
		
	}

	/**
	 * At this state, the {@link Shark} is naive. 
	 * So don't look after a particular prefered place
	 */
	protected LinkedList<Coordinate> getPreferedPlaces(LinkedList<Coordinate> availablesPlaces)
	{
		return availablesPlaces;
	}

}
