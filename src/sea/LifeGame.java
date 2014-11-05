/**
 * 
 */
package sea;

/**
 * @author antoine
 *
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
	 * 
	 * @param sea The sea in which the system take place
	 */
	public LifeGame(Sea sea)
	{
		this.sea = sea;
		this.currentCycle = 0;
	}
	
	/**
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
		
	}
	
	/**
	 * Starts the time and performs the actions
	 * until the cycle's counter has reached the 
	 * defined maximal cycles number
	 */
	public void startTime()
	{
		
	}
	
	@Override
	public String toString()
	{
		String ret = "Cycle "+this.currentCycle+"\n";
		ret += this.sea.toString();
		
		return ret;
	}
}
