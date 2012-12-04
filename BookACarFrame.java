/**
 * Rental Car Book A Car interface
 * @author Aaron Hampson
 * @version 1.0
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.text.*;
import java.util.Scanner;
import java.io.*;

public class BookACarFrame extends JFrame
{
    public BookACarFrame()
    {
		this.setVisible(true);
        setTitle("Book A Car");
        centerWindow(this);
        setResizable(true);
        JPanel panel = new BookACarPanel();
        this.add(panel);
        this.pack();
    }

    private void centerWindow(Window w)
    {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        setLocation((d.width-w.getWidth())/2, (d.height-w.getHeight())/2);
    }
}

class BookACarPanel extends JPanel implements ActionListener
{
	Scanner sc = new Scanner(System.in);

    private JPanel			framePanel;
    private JLabel			priceLbl, timeLbl;
    private JButton     	bookButton, exitButton;
	private JComboBox		makeCBX, yearCBX, colorCBX, sizeCBX, durationCBX;
	public static boolean foundCar = false;

	String[] make = { "Buick","Cadillac", "Chevrolet", "Dodge", "Lincoln", "Ford", "Jeep",
					"Pontiac", "Ram" };
	String[] year = {"1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005",
					"2006", "2007", "2008",	"2009", "2010", "2011", "2012" };
	String[] color = {"Blue", "Black", "Gray", "Green", "Red", "Yellow", "White" };

	String[] size = {"Compact", "Mid-Size", "Full-Size" };

	String[] duration = {"One Day", "Two Days", "Three Days", "Four Days", "Five Days", "Six Days", "Seven Days", "2 Weeks",
					"3 Weeks", "1 month"};


    public BookACarPanel()
    {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel CBXPanel = new JPanel();
        Border CBXBorder = BorderFactory.createEtchedBorder();
        CBXBorder = BorderFactory.createTitledBorder(CBXBorder, "Select your car");
        CBXPanel.setBorder(CBXBorder);
        CBXPanel.setLayout(new GridLayout(1,5));

        makeCBX = new JComboBox<String>(make);
        makeCBX.setSelectedIndex(0);
        makeCBX.addActionListener(this);
 		CBXPanel.add(makeCBX);

        yearCBX = new JComboBox<String>(year);
        yearCBX.addActionListener(this);
        yearCBX.setSelectedIndex(0);
        CBXPanel.add(yearCBX);


        colorCBX = new JComboBox<String>(color);
        colorCBX.addActionListener(this);
        colorCBX.setSelectedIndex(0);
        CBXPanel.add(colorCBX);

        sizeCBX = new JComboBox<String>(size);
        sizeCBX.addActionListener(this);
        sizeCBX.setSelectedIndex(0);
        CBXPanel.add(sizeCBX);

        durationCBX = new JComboBox<String>(duration);
		durationCBX.addActionListener(this);
        durationCBX.setSelectedIndex(0);
        CBXPanel.add(durationCBX);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        bookButton = new JButton("Book it!");
		bookButton.addActionListener(this);
        buttonPanel.add(bookButton);
        exitButton = new JButton("Close");
        exitButton.addActionListener(this);
        buttonPanel.add(exitButton);

        this.add(CBXPanel);
        this.add(buttonPanel);
	}

		public void actionPerformed(ActionEvent e)
		{
			Object source = e.getSource();
			if (source == exitButton)
			/*This line closes the interface window without closing
			 * rest of program */
				SwingUtilities.getWindowAncestor(this).dispose();
			else if (source == bookButton)
			{

			try{

				Scanner sc = new Scanner(new File("cardata.txt")); /*This scans in the text file listing all cars */

				String chosenCar = ((String) makeCBX.getSelectedItem() + ",          " + yearCBX.getSelectedItem()
					+ ",          " + colorCBX.getSelectedItem() + ",          " + sizeCBX.getSelectedItem()
					+ ",          " + "Available"); /* This adds "Available" to the search query to match the way it
													 * it is listed in cardata.txt. This addition is used to verify
													 * the searched car is indeed available. */

				String[] ca = new String[1000];

				int carsSearched = 0;

				for(int i = 0; i < 1000; i++)			/* This for loop scans in the list of cars for searching\
														 * into an array.*/
				{
					String c = sc.nextLine();
					ca[i] = c;

					if(chosenCar.equals(ca[i]))
					{
						carsSearched += 1000;
					}
					else
					{
						carsSearched -=1;
					}
				}

				if(carsSearched > 0)
				{
					JOptionPane.showMessageDialog(framePanel,			/* This is displayed when the car
																		 * searched is found and available
																		 * in the array of cars. */
						"Your car has successfully been booked.",
						"Success", JOptionPane.INFORMATION_MESSAGE);

						 foundCar = true;
				}
				else
				{
					JOptionPane.showMessageDialog(framePanel,
						"The car you tried to book is currently unavailable, sorry.", /* This is displayed when
																					   * the searched car is either
																					   * not in the car array or
																					   * is listed unavailable. */
						"Error", JOptionPane.INFORMATION_MESSAGE);

						 foundCar = false;
				}

			} catch (Exception f) {System.out.println("Exception with file: cardata.txt"); }
		}
	}
}