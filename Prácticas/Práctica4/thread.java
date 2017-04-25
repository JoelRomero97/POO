import java.util.Scanner;

public class Thread implements Runnable
{
	static Thread hilo;
	static String string;

	public Thread(){

		hilo=new Thread(this);

	}
	public void run()
	{
		while(true)
		{
			char c;
			c=string.charAt(string.length()-1);
			string=c+string.substring(0,string.length()-1);
			System.out.println(""+string);
		
		try
		{
			Thread.sleep(1000);

		}
		catch(Exception e)
		{
			return;
		}
	}
}

	public static void main(String args[])
	{
		System.out.println("Introduce una palabra o frase:");
		Scanner a=new Scanner(System.in);
		string=a.nextLine();
		new Thread();
		hilo.start();
	}
}
