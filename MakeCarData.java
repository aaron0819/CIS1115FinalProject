// MakeData.java by Christopher Dillman on 11/14/2012
// This will create as many raw vehicle records as you desire.

import java.io.*;
import java.util.*;
import java.text.*;

public class MakeCarData
{
	public static void main(String[] args) throws Exception
	{
		final int MAX_CARS = 1000;
		final String NEW_LINE = "\n";

		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File("cardata.txt"))));

		String[] brandArray = {"Lincoln", "Dodge", "Jeep", "Ford", "Ram", "Chevrolet", "Pontiac",
			"Buick", "Cadillac" };

		String[] yearArray = {"1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005",
			"2006", "2007", "2008",	"2009", "2010", "2011", "2012" };

		String[] colorArray = {"Blue", "Red", "Green", "White", "Black", "Yellow", "Gray" };

		String[] sizeArray = {"Compact", "Mid-Size", "Full-Size" };

		String[] dueArray = {"Less Than One Day", "One Day", "Two Days", "Three Days",
			"More Than Three Days" };


		Random r = new Random();
		String available;
		boolean damage;
		String due;

		for(int i=1; i<=MAX_CARS; i++)
		{
			int dmg = r.nextInt(100);
			if (dmg == 0)
				damage = true;
			else
				damage = false;

			int av = r.nextInt(2);
			if (damage == true && av >-1)
				available = "Damaged undergoing repairs";
			else if (av == 0)
				available = "Available";
			else
				available = "Rented due back in";

			int du = r.nextInt(11);
			if (du > -1 && du < 4 )
				due = dueArray[0];
			else if (du >= 4 && du <= 5)
				due = dueArray[1];
			else if (du >= 6 && du <= 7)
				due = dueArray[2];
			else if (du == 8)
				due = dueArray[3];
			else if (du > 8 && du <= 10)
				due = dueArray[4];

			if (av != 1)
			{
				String s = brandArray[r.nextInt(brandArray.length)]
				+ ",\t" + yearArray[r.nextInt(yearArray.length)]
				+ ",\t" + colorArray[r.nextInt(colorArray.length)]
				+ ",\t" + sizeArray[r.nextInt(sizeArray.length)]
				+ ",\t" + available;

				out.println(s);
			}
			else
			{
				String s = brandArray[r.nextInt(brandArray.length)]
				+ ",\t" + yearArray[r.nextInt(yearArray.length)]
				+ ",\t" + colorArray[r.nextInt(colorArray.length)]
				+ ",\t" + sizeArray[r.nextInt(sizeArray.length)]
				+ ",\t" + available + " " + dueArray[r.nextInt(dueArray.length)];

				out.println(s);
			}

		}
		out.close();
	}
}
