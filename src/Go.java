import java.io.FileReader;
import java.io.IOException;

public class Go 
{

	public static void main(String[] args) {
		lesenDatei("/Users/lukadjordjevic/git/aktienanalyse/src/Kurse/Aktien.txt");
	
	
	}

	public static void lesenDatei(String dateiname)
	{
		FileReader datei;
		int c;
		try 
		{
			datei = new FileReader (dateiname);
			while ((c = datei.read()) != -1)
			{
				System.out.print((char)c);
			}
			datei.close();
		}
		catch (IOException e)
		{
			System.out.println(e);
		}
	}
}
