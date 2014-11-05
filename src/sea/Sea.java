/**
 * 
 */
package sea;

import java.util.Random;

/**
 * Sea Class.
 * Represent the sea in which the {@link Fish} live 
 * 
 * @author antoine
 */
public class Sea
{
	/**
	 * @see Fish
	 * The area in which the fishes live
	 */
	private Fish[][] area;
	
	/** The area x size */
	private final int xSize;
	
	/** The area y size */
	private final int ySize;
	
	/**
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
		this.area = new Fish[x][y];
		
		while(sardine > 0) {
			try {
				this.putFish(new Sardine(this));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sardine--;
		}
		
		while(shark > 0) {
			try {
				this.putFish(new Shark(this));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			shark--;
		}
	}
	
	/**
	 * Randomly put the fish into the sea
	 * 
	 * @param fish the {@link Fish} to put into the sea
	 * @throws Exception 
	 */
	private void putFish(Fish fish) throws Exception
	{
		Random rand = new Random();
		
		int x;
		int y;
		boolean isTaken;
		int counter = 0;
		int maxIteration = this.xSize * this.ySize;
		do {
			counter++;
			x = rand.nextInt(this.xSize);
			y = rand.nextInt(this.ySize);
			isTaken = (this.getSquare(x, y) == null) ? false : true;
		} while (isTaken && counter < maxIteration);

		if (counter >= maxIteration) {
			throw new Exception("No more places available.");
		}
		
		this.area[x][y] = fish;
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Fish getSquare(int x, int y)
	{
		return this.area[x][y];
	}
	
	@Override
	public String toString()
	{
		String ret = "Sea:\n";
		
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
					if (fish.getClass().getSimpleName().equals("Sardine")) {
						ret += " O ";
					} else if (fish.getClass().getSimpleName().equals("Shark")) {
						ret += " X ";
					} 
				} else {
					ret += "   ";
				}
				
			}
			ret += "|\n";
		}
		
		
		return ret;
	}
}
