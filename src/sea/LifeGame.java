/**
 * 
 */
package sea;

import java.util.LinkedList;

/**
 * LifeGame Class.
 * 
 * Main class of the game that allows to start the game.
 * 
 * @author antoine
 */
public class LifeGame
{
	/**
	 * The maximal cycles number
	 */
	private static final int MAX_CYCLE = 1000;
	
	/**
	 * The sea in which the game take place
	 */
	private Sea sea;
	
	/**
	 * The current cycle number
	 */
	private int currentCycle;
	
	/**
	 * Whether the cycle should be displayed or not
	 */
	private boolean dumpCycles = false;
	
	/**
	 * Main class constructor
	 * 
	 * @param sea The sea in which the system take place
	 */
	public LifeGame(Sea sea)
	{
		this.sea = sea;
		this.currentCycle = 0;
	}
	
	/**
	 * Secondary class constructor.
	 * 
	 * Creates a new {@link Sea} before call the 
	 * main constructor of this class.
	 * 
	 * @param seaX The x size of the sea area
	 * @param seaY The y size of the sea area
	 * @param sardines The number of sardines
	 * @param sharks The number of sharks
	 */
	public LifeGame(int seaX, int seaY, int sardines, int sharks)
	{
		this(new Sea(seaX, seaY, sardines, sharks));
	}
	
	/**
	 * Starts the time and performs the actions
	 * until the cycle's counter has reached the given
	 * cycles number. 
	 * 
	 * @param cycles The number of cycle to perform
	 */
	public void startTime(int cycles)
	{	
		while (this.currentCycle < cycles) {
			
			this.sea.diedFish.clear();
			
			if (this.dumpCycles) {
				this.dump();
			}
			
			/*
			 * Clone to prevent ConcurrentModificationException
			 */
			@SuppressWarnings("unchecked")
			LinkedList<Fish> fishes = (LinkedList<Fish>) this.sea.getFishes().clone();
			
			for (Fish fish : fishes) {
				/*
				 * Check wether the fish didn't die during
				 * the cycle
				 */
				if (!this.sea.diedFish.contains(fish)) {
					fish.nextCycle();
				}
			}
			
			this.currentCycle++;
			System.out.println("Fin du cycle");
		}
	}
	
	/**
	 * Starts the time and performs the actions
	 * until the cycle's counter has reached the 
	 * defined maximal cycles number
	 */
	public void startTime()
	{
		this.startTime(MAX_CYCLE);
	}
	
	/**
	 * Set the parameter dumpCycle
	 * 
	 * @param value the value of the parameter
	 */
	public void dumpCycles(boolean value) 
	{
		this.dumpCycles = value;
	}
	
	/**
	 * Dump the Life Game
	 */
	public void dump()
	{
		System.out.println("Cycle "+this.currentCycle+"\n");
		System.out.println("Sardines: "+this.sea.getSardineNumber());
		System.out.println("Sharks: "+this.sea.getSharkNumber());
		System.out.println(this.toString());
	}
	
	@Override
	public String toString()
	{
		return this.sea.toString();
	}
	
	/**
	 * the main game method
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		int x = Integer.parseInt(args[0]);
		int y = Integer.parseInt(args[1]);
		
		int sardines = Integer.parseInt(args[2]);
		int sharks = Integer.parseInt(args[3]);
		
		int cycles = 40;
		if (args.length > 4) {
			cycles = Integer.parseInt(args[4]);
		}
		
		LifeGame game = new LifeGame(x, y, sardines, sharks);
		game.dumpCycles(true);
		
		game.startTime(cycles);
	}
}
