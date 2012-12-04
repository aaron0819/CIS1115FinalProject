/**
 * Rental Car application made with code provided by John Phillips at Mansfield University
 * @author Aaron Hampson and Christopher Dillman
 * @version 1.0
 */

import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class CarAppGUI
{
	public CarAppGUI()
	{
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUIPanel();
            }
        });
	}

	public static void main(String[] args)
	{
		new CarAppGUI();
	}
}

class GUIPanel
{
	private JFrame 		frame;
	private JPanel 		framePanel, dataPanel, statusPanel;
	private JTextArea 	dataTA, statusTA, resultTA;
	private JMenuItem	printMI, exitMI, aboutMI, rateListMI, rateCalculatorMI,
						ListAllMI, sortByMakeMI, sortByModelYearMI, sortByColorMI, bookACarMI;
	private CarArray 	carData;

	public GUIPanel()
	{
		framePanel = new JPanel();
		dataPanel = new JPanel();
		statusPanel = new JPanel();

		dataTA = new JTextArea(10, 50);
		dataTA.setBackground(new Color(200,200,200));
		dataTA.setLineWrap(true);
		dataTA.setWrapStyleWord(true);
		dataTA.setEditable(true);
		JScrollPane dataSP = new JScrollPane(dataTA,
			ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
			ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		dataPanel.add(dataSP);

		statusTA = new JTextArea("Status Panel", 5, 50);
		statusTA.setBackground(new Color(200,200,200));
		statusTA.setLineWrap(true);
		statusTA.setWrapStyleWord(true);
		statusTA.setEditable(false);
		JScrollPane statusSP = new JScrollPane(statusTA,
			ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
			ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		statusPanel.add(statusSP);

        framePanel.setOpaque(true);
        framePanel.setLayout(new BorderLayout());
        framePanel.add(dataPanel, BorderLayout.CENTER);
        framePanel.add(statusPanel, BorderLayout.SOUTH);

		frame = new JFrame("Car App GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(createMenuBar());
		frame.setContentPane(framePanel);
		frame.pack();
		frame.setLocation(100, 150);
		frame.setResizable(true);
		frame.setVisible(true);

		carData = new CarArray();

	} // end GUIPanel constructor

    public JMenuBar createMenuBar()
    {
		JMenuBar menuBar;
		JMenu menu;

		menuBar = new JMenuBar();

		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(menu);

		printMI = new JMenuItem("Print...");
		printMI.addActionListener(new PrintListener());
		menu.add(printMI);

		exitMI = new JMenuItem("Exit");
		exitMI.addActionListener(new ExitListener());
		menu.add(exitMI);

		menu = new JMenu("Rates & Booking");
		menu.setMnemonic(KeyEvent.VK_R);
		menuBar.add(menu);

		rateListMI = new JMenuItem("Rate List");
		rateListMI.addActionListener(new RateListListener());
		menu.add(rateListMI);

		rateCalculatorMI = new JMenuItem("Rate Calculator");
		rateCalculatorMI.addActionListener(new RateCalculatorListener());
		menu.add(rateCalculatorMI);

		bookACarMI = new JMenuItem("Book A Car");
		bookACarMI.addActionListener(new BookACarListener());
		menu.add(bookACarMI);

		menu = new JMenu("List");
		menu.setMnemonic(KeyEvent.VK_S);
		menuBar.add(menu);

		ListAllMI = new JMenuItem("List all");
		ListAllMI.addActionListener(new ListAllListener());
		menu.add(ListAllMI);

		sortByColorMI = new JMenuItem("List by Color");
		sortByColorMI.addActionListener(new SortByColorListener());
		menu.add(sortByColorMI);

		sortByMakeMI = new JMenuItem("List by Make");
		sortByMakeMI.addActionListener(new SortByMakeListener());
		menu.add(sortByMakeMI);

		sortByModelYearMI = new JMenuItem("List by Model Year");
		sortByModelYearMI.addActionListener(new SortByMdYrListener());
		menu.add(sortByModelYearMI);

		menu = new JMenu("Help");
		menu.setMnemonic(KeyEvent.VK_H);
		menuBar.add(menu);

		aboutMI = new JMenuItem("About");  //info about the company
		aboutMI.addActionListener(new AboutListener());
		menu.add(aboutMI);

		return menuBar;
    } // end createMenuBar

	class PrintListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				dataTA.print();
				statusTA.setText("Print dialog finished");
			} catch (Exception pe) { statusTA.setText("Printer error");	}
		}
	}

	class ExitListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}

/**
 * RateListListener components designed by Chris Dillman.
 * Action listener opens the rate list interface
 * @return Action listener opens the rate list interface
 */

		class RateListListener implements ActionListener
		{

			public void actionPerformed(ActionEvent e)
			{
							JOptionPane.showMessageDialog(framePanel,
								("                                      Rates\n\n     Rates are determined by the vehicle's " +
								"size. A \nsmall, compact vehicle can seat about 4 people, \nand has meager storage space." +
								" On the flip side, a \nfull-size vehicle is large enough to seat 8-10 people, \nand has vast" +
								" storage space.\n\n" +
								"The rates are as followed:\n"	+
								"     Compact:\t\t$35/day\n"	+
								"     Mid-Size:\t$60/day\n"		+
								"     Full-Size:\t$85/day\n\n"),
					"Rates List", JOptionPane.INFORMATION_MESSAGE);
			}
	}

/**
 * RateListListener and associated components designed by Chris Dillman.
 * This action listener is used to activate the Rate Calculator button,
 * which opens the Rate Calculator in a seperate window. The code is located in
 * RatesCalcFrame.java.
 * @return Opens Rate Calculator interface in file RatesCalcFrame.java
 */

	class RateCalculatorListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			new RatesCalcFrame();
		}
	}

/**
 * BookACarListener and associated components designed by Aaron Hampson.
 * This action listener is used to activate the Book A Car button,
 * which opens the Book A Car menu in a seperate window. The code is located in
 * BookACarFrame.java.
 * @return Opens Book A Car interface in file BookACarFrame.java
 */

	class BookACarListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			new BookACarFrame();

		}
	}

/**
 * Sort by color coded by Aaron Hampson.
 * This action listener when activated will sort the list of rental cars by car color.
 * @return sorts cars by color
 */

 	class SortByColorListener implements ActionListener
 	{
 		public void actionPerformed(ActionEvent e)
 		{
 			carData.sortByColor();

 			dataTA.setText(carData.toString());
 		}
	}

/**
 * Sort by make coded by Aaron Hampson.
 * This action listener when activated will sort the list of rental cars by car make.
 * @return sorts car by make
 */


	class SortByMakeListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			carData.sortByMake();

			dataTA.setText(carData.toString());
		}
	}

/**
 * Sort by year coded by Aaron Hampson.
 * This action listener when activated will sort the list of rental cars by model year.
 * @return sorts car by model year
 */

	class SortByMdYrListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			carData.sortByModelYear();

			dataTA.setText(carData.toString());
		}
	}

/**
 * This action listener prints all cars in the car array.
 * @return lists all cars in CarArray.java
 */

	class ListAllListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			dataTA.setText(carData.toString());
		}
	}

/**
 * AboutListener opens window .
 * This action when activated will display an information panel with information about the program.
 * @return displays about interface
 */

	class AboutListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JOptionPane.showMessageDialog(framePanel,
				"Car Rental App by Christopher Dillman and Aaron Hampson \n for CIS-1115 at Mansfield University.",
				"About", JOptionPane.INFORMATION_MESSAGE);
		}
	}

}