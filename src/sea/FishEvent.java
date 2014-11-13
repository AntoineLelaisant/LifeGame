/**
 * 
 */
package sea;

/**
 * FishEvent Class.
 * 
 * This class is used by the {@link Fish} class to transmit
 * information to the {@link Sea} in the context of the 
 * Observer pattern.
 * 
 * @author antoine
 */
public class FishEvent
{
	
	public static final int EVENT_MOVED = 0;
	public static final int EVENT_DIED = 1;
	
	/**
	 * The Fish who raised this event
	 */
	protected Fish source;
	
	/**
	 * The initial coordinate of the fish
	 */
	protected Coordinate initial;
	
	/**
	 * The type of the event.
	 * Should corespond with the defined constants EVENT_* 
	 */
	protected int eventType;

	/**
	 * The class constructor
	 * 
	 * @param source
	 * @param initial
	 * @param event
	 */
	public FishEvent(Fish source, Coordinate initial, int event)
	{
		this.source = source;
		this.initial = initial;
		this.eventType = event;
	}

	/**
	 * Gets this event source
	 * 
	 * @return The {@link Fish} who raised this event
	 */
	public Fish getSource()
	{
		return source;
	}
	
	/**
	 * Gets the initial {@link Coordinate} of the source {@link Fish}
	 * 
	 * @return The initial {@link Coordinate} of the source
	 */
	public Coordinate getInitial()
	{
		return this.initial;
	}

	/**
	 * Gets the event's type
	 * 
	 * @return the event's type
	 */
	public int getEventType()
	{
		return eventType;
	}
}
