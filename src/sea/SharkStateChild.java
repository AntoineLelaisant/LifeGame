package sea;

import java.util.LinkedList;

public class SharkStateChild extends SharkState
{
	
	public SharkStateChild(Shark shark)
	{
		super(shark);
	}

	@Override
	public void checkAge()
	{
		if (this.shark.getAge() >= Shark.AGE_SEMI_ADULT) {
			this.shark.setState(this.shark.stateSemiAdult);
			System.out.println("Shark grew up to Semi-Adult");
		}
		
	}

	@Override
	protected LinkedList<Coordinate> getPreferedPlaces(LinkedList<Coordinate> availablesPlaces)
	{
		return availablesPlaces;
	}

}
