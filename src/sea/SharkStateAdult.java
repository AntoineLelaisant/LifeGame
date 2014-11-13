package sea;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * SharkStateAdult Class
 * 
 * Represent the adult state of the {@link Shark}
 * 
 * @author antoine
 */
public class SharkStateAdult extends SharkState
{
	
	/**
	 * @see SharkState#SharkState(Shark)
	 */
	public SharkStateAdult(Shark shark)
	{
		super(shark);
	}

	/**
	 * Looks after the nearest {@link Sardine} from the {@link Fish#coordinate}
	 * 
	 * When this {@link Sardine} has been found, it looks after the nearest availble places
	 * from this {@link Sardine}.
	 * 
	 * @param availablesPlaces The list of the available places around the {@link Shark}
	 * @see SharkState#getPreferedPlaces(LinkedList)
	 */
	protected LinkedList<Coordinate> getPreferedPlaces(LinkedList<Coordinate> availablesPlaces)
	{
		LinkedList<Coordinate> nearestSardines = this.getNearestSardines();
		
		/*
		 * If there is no more sardines, there is
		 * no more reason to look after a prefered place 
		 */
		if (nearestSardines.size() == 0) {
			return availablesPlaces;
		}
		
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
	 * Looks after the nearest {@link Sardine} from the {@link Fish#coordinate}
	 * 
	 * @return The list of nearest {@ink Sardine} coordinate
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
		// no op
	}

}
