/**
 * Rental Car CarArray
 * @author Aaron Hampson and Christopher Dillman
 * @version 1.0
 */

import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


public class CarArray
{
	Car[] ca;
	int n;
	final int maxRecords = 1000;

	public CarArray()				/*This method creates a new array with a size of "maxRecords"
									 * and calls the readFile method */
	{
		ca = new Car[maxRecords];
		readFile();
	}

public void readFile()				/*This method reads in the data in cardata.txt and assigns
									 * each line to a position in the array "ca" in the class CarArray */
	{
		try {
			Scanner sc = new Scanner(new File("cardata.txt"));
			int i = 0;

			while (sc.hasNext())
			{
				String c = sc.nextLine();
				String[] cd = c.split(",          "); /* This line splits the data file into each parameter
													   * that needs to be passed into the Car class */
				ca[i] = new Car(cd[0], Integer.parseInt(cd[1]),
					cd[2], cd[3], cd[4]);
				i++;
			}
			n = i;
		} catch (Exception e) {
			System.out.println("Exception with file: cardata.txt");
		}
	}

	public void sortByMake()
	{
		Arrays.sort(ca, 0, n, new SortByMake());
	}

	public void sortByColor()
	{
		Arrays.sort(ca, 0, n, new SortByColor());
	}

	public void sortByModelYear()
	{
		Arrays.sort(ca, 0, n, new SortByModelYear());
	}

	public String toString()
	{
		String s = "";

		for (int i=0; i<n; i++)
			s += ca[i] + "\n";

		return s;
	}

	public static void main(String[] args)
	{
		CarArray car = new CarArray();
		System.out.println(car);
	}
}

class SortByMake implements Comparator<Car>
{
	public int compare(Car c1, Car c2)
	{
		return c1.getMake().compareTo(c2.getMake());
	}
}

class SortByModelYear implements Comparator<Car>
{
	public int compare(Car c1, Car c2)
	{
		if (c1.getYear() > c2.getYear() ) return -1;
		else if (c1.getYear() < c2.getYear() ) return 1;
		else return 0;
	}
}

class SortByColor implements Comparator<Car>
{
	public int compare(Car c1, Car c2)
	{
		return c1.getColor().compareTo(c2.getColor());
	}
}


