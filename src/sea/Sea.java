/**
 * 
 */
package sea;

/**
 * @author antoine
 *
 */
public class Sea
{
	private Fish[][] area;
	
	/**
	 * 
	 * @param x int The area x size
	 * @param y int The area y size
	 * @param sardine The number of Sardines
	 * @param shark The number of Sharks
	 */
	public Sea(int x, int y, int sardine, int shark)
	{
		this.area = new Fish[x][y];
	}

}
