package sea;

import java.util.HashSet;
import java.util.LinkedList;

public class SharkStateAdult extends SharkState
{

	public SharkStateAdult(Shark shark)
	{
		super(shark);
	}

	@Override
	protected LinkedList<Coordinate> getPreferedPlaces(LinkedList<Coordinate> availablesPlaces)
	{
		LinkedList<Coordinate> nearestSardines = this.getNearestSardines();
		
		/*
		 * We use HashSet to not add duplicate
		 * Coordinate
		 */
		HashSet<Coordinate> preferedPlaces = new HashSet<Coordinate>();
		
		for (Coordinate coordinate : nearestSardines) {
			preferedPlaces.addAll(Coordinate.getNearestCoords(availablesPlaces, coordinate));
		}
		
		/*
		 * The HashSet is converted to LinkedList
		 * and then returned
		 */
		return new LinkedList<Coordinate>(preferedPlaces);
	}
	
	/**
	 * 
	 * @return
	 */
	protected LinkedList<Coordinate> getNearestSardines()
	{
		LinkedList<Coordinate> sardines = new LinkedList<Coordinate>();
		
		for (Sardine sardine : this.shark.getSea().getSardines()) {
			sardines.add(sardine.getCoordinate());
		}
		
		return Coordinate.getNearestCoords(sardines, this.shark.getCoordinate());
	}
	
	@Override
	public void checkAge()
	{
		/*
		 * This is the maximal age for the shark
		 * so there is nothing to do
		 */
	}

}
