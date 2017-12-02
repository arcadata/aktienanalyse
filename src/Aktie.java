import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Aktie {
	public String name;
	ArrayList<Double> kurs;
	
	public Aktie (String name)
	{
		this.name = name;
		this.kurs = new ArrayList<Double>();
		
		Double line;
		
		try 
		{
			Scanner f = new Scanner(new FileReader("/Users/lukadjordjevic/git/aktienanalyse/src/Kurse/" + name + ".txt"));
			while (f.hasNextLine())
			{
				
				line = Double.parseDouble(f.nextLine());
				kurs.add(line);

			}
			f.close();
		} 
		catch (IOException e)
		{
			System.out.println(e);
		}
		
		
	}
	
}
