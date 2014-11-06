/**
 * 
 */
package sea;

/**
 * @author antoine
 *
 */
public class FishEvent
{
	public static final int EVENT_MOVED = 0;
	public static final int EVENT_DIED = 1;
	
	protected Fish source;
	protected Coordinate initial;
	protected int eventType;

	/**
	 * @param source
	 */
	public FishEvent(Fish source, Coordinate initial, int event)
	{
		this.source = source;
		this.initial = initial;
		this.eventType = event;
	}

	public Fish getSource()
	{
		return source;
	}
	
	public Coordinate getInitial()
	{
		return this.initial;
	}

	public int getEventType()
	{
		return eventType;
	}
}
