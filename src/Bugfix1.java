import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Bugfix1 {

	public static void main (String args[]) 
	{
		String line;
		//ArrayList <Double> currentKurse = new ArrayList<Double>();
		String aktienname = "ALLIANZ";
		
		try 
		{
			Scanner f = new Scanner(new FileReader("/Users/lukadjordjevic/git/aktienanalyse/src/Kurse/" + aktienname + ".txt"));
			while (f.hasNextLine())
			{
				line = f.nextLine();
				System.out.println(Double.parseDouble(line));
			}
			f.close();
		} 
		catch (IOException e)
		{
			System.out.println(e);
		}
		
		
	}
	
}
