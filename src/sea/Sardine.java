/**
 * 
 */
package sea;

/**
 * @author antoine
 *
 */
public class Sardine extends Fish
{

	/**
	 * 
	 */
	public Sardine(Sea sea)
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
		return 15;
	}

	@Override
	public int getReproductionFrequency()
	{
		return 2;
	}

}
