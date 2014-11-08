/**
 * 
 */
package sea;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;


/**
 * Sea Class.
 * Represent the sea in which the {@link Fish} lives
 * 
 * @author antoine
 */
public class Sea implements Observer
{
	/**
	 * @see Fish
	 * The area in which the fishes live
	 */
	private Fish[][] area;
	
	/**
	 * @see Fish
	 * The list of fishes in the sea
	 */
	private LinkedList<Fish> fishes;
	
	/** The area x size */
	private final int xSize;
	
	/** The area y size */
	private final int ySize;
	
	/**
	 * Create the sea with the given proportions. 
	 * Also filled it with as many fishes as required
	 * (both {@link Sardine} and {@link Shark}).
	 * The fishes are randomly placed.
	 * 
	 * @param x The area x size
	 * @param y The area y size
	 * @param sardine The number of Sardines
	 * @param shark The number of Sharks
	 */
	public Sea(final int x, final int y, int sardine, int shark)
	{
		this.xSize = x;
		this.ySize = y;
		this.fishes = new LinkedList<Fish>();
		this.area = new Fish[x][y];
		
		while(sardine > 0) {
			try {
				this.putFish(new Sardine(this));
			} catch (FullSeaException e) {
				/* 
				 * If the Exception have been raised it means that
				 * the sea is full of fishes. We can not add fishes 
				 * anymore so we can break here.
				 */
				break;
			}
			sardine--;
		}
		
		while(shark > 0) {
			try {
				this.putFish(new Shark(this));
			} catch (FullSeaException e) {
				// Idem
				break;
			}
			shark--;
		}
	}
	
	/**
	 * Randomly put the fish into the sea
	 * 
	 * @param fish the {@link Fish} to put into the sea
	 * @throws FullSeaException thrown when the sea is full of fishes
	 */
	private void putFish(Fish fish) throws FullSeaException 
	{
		
		Coordinate coord;
		boolean isTaken;
		int counter = 0;
		int maxIteration = this.xSize * this.ySize;
		
		/*
		 * We are randomly looking for a free coordinate for the fish.
		 * While the coordinate is taken, we ask for a new one.
		 * To prevent an infinite loop (if the sea is already full)
		 * we have defined a maxIteration number which correspond to the
		 * area size.
		 */
		do {
			counter++;
			coord = Coordinate.getRandomCoord(this.xSize, this.ySize);
			isTaken = (this.getSquare(coord) == null) ? false : true;
		} while (isTaken && counter < maxIteration);

		/*
		 * If the counter have reached the maxIteration value
		 * it means that the sea is empty (or almost) so we 
		 * raised the FullSeaException
		 */
		if (counter >= maxIteration) {
			throw new FullSeaException();
		}

		this.addFish(fish, coord);
	}
	
	/**
	 * Add a fish into the sea. 
	 * 
	 * @param fish the {@link Fish} to add
	 * @param coord the {@link Coordinate} at which the fish should be placed
	 */
	public void addFish(Fish fish, Coordinate coord)
	{
		fish.setCoordinate(coord);
		this.area[coord.getX()][coord.getY()] = fish;
		this.fishes.add(fish);
	}
	
	/**
	 * Get the a square of the Sea's area
	 * 
	 * @param x the x index of the area
	 * @param y the y index of the area
	 * @return the fish contained in the square (or null if empty)
	 */
	public Fish getSquare(int x, int y)
	{
		return this.area[x][y];
	}
	
	/**
	 * @see Sea#getSquare(int, int)
	 * @param coord
	 * @return the fish contained in the square (or null if empty)
	 */
	public Fish getSquare(Coordinate coord)
	{
		return this.getSquare(coord.getX(), coord.getY());
	}
	
	/**
	 * Sets the given square with the given {@link Fish}
	 * 
	 * @param x the x index of the square to set
	 * @param y the y index of the square to set
	 * @param fish the fish to append to the square
	 */
	private void setSquare(int x, int y, Fish fish)
	{
		this.area[x][y] = fish;
	}
	
	/**
	 * @see Sea#setSquare(int, int, Fish)
	 * @param coord the {@link Coordinate} of the square to set
	 * @param fish the fish to append to the square
	 */
	private void setSquare(Coordinate coord, Fish fish)
	{
		this.setSquare(coord.getX(), coord.getY(), fish);
	}
	
	/**
	 * Clear the given Square by setting it to null
	 * 
	 * @param coord the Coordinate of the square to clear
	 */
	public void clearSquare(Coordinate coord)
	{
		this.area[coord.getX()][coord.getY()] = null;
	}
	
	/**
	 * Get the list of the sea's fishes
	 * 
	 * @return the LinkedList of the sea's fishes
	 */
	public LinkedList<Fish> getFishes()
	{
		return this.fishes;
	}
	
	/**
	 * 
	 * @return
	 */
	public LinkedList<Sardine> getSardines()
	{
		LinkedList<Sardine> sardines= new LinkedList<Sardine>();
		
		for (Fish fish : this.fishes) {
			if (fish instanceof Sardine) {
				sardines.add((Sardine) fish);
			}
		}
		return sardines;
	}
	
	/**
	 * 
	 * @return
	 */
	public LinkedList<Shark> getSharks()
	{
		LinkedList<Shark> sharks= new LinkedList<Shark>();
		
		for (Fish fish : this.fishes) {
			if (fish instanceof Shark) {
				sharks.add((Shark) fish);
			}
		}
		return sharks;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getSardineNumber()
	{
		return this.getSardines().size();
	}
	
	/**
	 * 
	 * @return
	 */
	public int getSharkNumber()
	{
		return this.getSharks().size();
	}
	
	/**
	 * 
	 * @param fish
	 * @param target
	 */
	private void fishMoved(Fish fish, Coordinate initial)
	{
		this.clearSquare(initial);
		this.setSquare(fish.getCoordinate(), fish);
	}
	
	/**
	 * 
	 * @param fish
	 */
	private void fishDied(Fish fish)
	{
		this.fishes.remove(fish);
		this.clearSquare(fish.getCoordinate());
	}

	@Override
	public void update(Observable o, Object event)
	{
		if (event instanceof FishEvent) {
			FishEvent fishEvent = ((FishEvent) event);
			
			switch(fishEvent.getEventType()) {
				case FishEvent.EVENT_MOVED:
					this.fishMoved(fishEvent.getSource(), fishEvent.getInitial());
					break;
				
				case FishEvent.EVENT_DIED:
					this.fishDied(fishEvent.source);
					break;
			}
		}
	}
	
	@Override
	public String toString()
	{
		String ret = "";
		
		for (int j = 0; j < area[0].length; j++) {
			ret += (j < 10) ? "  "+j+" " : " "+j+" ";	
		}
		ret += "\n";
		
		for (int i = 0; i < this.area.length; i++) {
			Fish[] columns = area[i];
			
			for (int j = 0; j < columns.length; j++) {
				ret += "----";
			}
			ret += "-\n";
			
			for (int j = 0; j < columns.length; j++) {
				Fish fish = columns[j];
				ret += "|";
				if (fish != null) {
					ret += " "+fish.toString()+" ";
				} else {
					ret += "   ";
				}		
			}
			ret += "|\n";
		}
		return ret;
	}
}
