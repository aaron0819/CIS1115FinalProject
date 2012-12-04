/**
 * Rental Car Car class
 * @author Aaron Hampson and Christopher Dillman
 * @version 1.0
 */
public class Car
{
	String make, color, size, status;
	int year;

	public Car(String m, int y, String c, String s, String st)
	{
		make = m;
		year = y;
		color = c;
		size = s;
		status = st;
	}

	public String getMake() { return make; }
	public int getYear() { return year; }
	public String getColor() { return color; }
	public String getSize() { return size; }
	public String getStatus() { return status; }

	public String toString()
	{
		return make + ",          " + year + ",          " + color + ",          " + size + ",          " + status;
	}
}