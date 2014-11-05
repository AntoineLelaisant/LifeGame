/**
 * 
 */
package sea;

/**
 * @author antoine
 *
 */
public class Shark extends Fish
{

	/**
	 * 
	 */
	public Shark(Sea sea)
	{
		super(sea);
	}

	/* (non-Javadoc)
	 * @see sea.Fish#move()
	 */
	@Override
	public void move()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public int getDeathAge()
	{
		return 70;
	}

	@Override
	public int getReproductionFrequency()
	{
		return 5;
	}

}
