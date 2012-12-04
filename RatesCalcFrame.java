/**
 * Rental Car rate calculator
 * @author Christopher Dillman
 * @version 1.0
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.text.*;
import java.util.Scanner;



public class RatesCalcFrame extends JFrame
{
    public RatesCalcFrame()
    {
        this.setVisible(true);
        setTitle("Rates Calculator");
        centerWindow(this);
        setResizable(true);
        JPanel panel = new CalculatorPanel();
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

class CalculatorPanel extends JPanel implements ActionListener
{
    private JRadioButton	smallRB, mediumRB, largeRB;
    private JPanel			framePanel;
    private JLabel			priceLbl, timeLbl;
    private JTextField		priceTF, timeTF;
    private JButton     	calculateButton, exitButton;

    Scanner sc = new Scanner(System.in);

    public CalculatorPanel()
    {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel sizePanel = new JPanel();
        Border sizeBorder = BorderFactory.createEtchedBorder();
        sizeBorder = BorderFactory.createTitledBorder(sizeBorder, "Size");
        sizePanel.setBorder(sizeBorder);
        sizePanel.setLayout(new GridLayout(3,1));
        smallRB = new JRadioButton("Compact: $35/day", true);		/*Prices of cars*/
        sizePanel.add(smallRB);
        mediumRB = new JRadioButton("Mid-Size: $60/day");
        sizePanel.add(mediumRB);
        largeRB = new JRadioButton("Full-Size: $85/day");
        sizePanel.add(largeRB);
        ButtonGroup sizeGrp = new ButtonGroup();
        sizeGrp.add(smallRB); sizeGrp.add(mediumRB); sizeGrp.add(largeRB);

		JPanel daysPanel = new JPanel();
        Border daysBorder = BorderFactory.createEtchedBorder();
        daysBorder = BorderFactory.createTitledBorder(daysBorder, "Time");
        daysPanel.setBorder(daysBorder);
        timeLbl = new JLabel("Days Rented:");
        timeTF = new JTextField(10);
        timeTF.setEditable(true);
        timeTF.setFocusable(true);
        daysPanel.add(timeLbl); daysPanel.add(timeTF);


        JPanel pricePanel = new JPanel();
        pricePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        priceLbl = new JLabel("Price:");
        pricePanel.add(priceLbl);
        priceTF = new JTextField(10);
        priceTF.setEditable(false);
        priceTF.setFocusable(false);
        pricePanel.add(priceTF);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(this);
        buttonPanel.add(calculateButton);
        exitButton = new JButton("Close");
        exitButton.addActionListener(this);
        buttonPanel.add(exitButton);

        this.add(sizePanel);
        this.add(daysPanel);
        this.add(pricePanel);
        this.add(buttonPanel);
	}


	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		if (source == exitButton)
		{
			/*This line closes the interface window without closing
			rest of program */
			SwingUtilities.getWindowAncestor(this).dispose();

		}
		else if (source == calculateButton)
		{
			try {

			String dayString = timeTF.getText();

			if (dayString == "") /* This line validates there is at least some form of user input and not
								  * a blank message
								  */
			{ JOptionPane.showMessageDialog(framePanel,
			    "Please enter a rental period between 1 and 120 days.",
				"Invalid Entry", JOptionPane.INFORMATION_MESSAGE);
			}

			/*This line converts the user input into a numeric value */
			double days = Double.parseDouble(dayString);

			if (days < 1 || days > 120)	/* This if statement validates user entry */
			{ JOptionPane.showMessageDialog(framePanel,
			    "Please enter a rental period between 1 and 120 days.",
				"Invalid Entry", JOptionPane.INFORMATION_MESSAGE);
			}

			else
			{
				double price = 0;
				double finalPrice = 0;

				if(smallRB.isSelected()) price = 35;
				else if(mediumRB.isSelected()) price = 60;
				else price = 85;

				finalPrice = price * days;

				/*The next two lines format the price that is output for the user  */
				NumberFormat currency = NumberFormat.getCurrencyInstance();
           	 	priceTF.setText(currency.format(finalPrice));
			}
				/*This exception is caught when the user enters characters that are unable to be parsed into
				numerical values for the calculations then prompts the user for correct and valid input*/
           	 	} catch(NumberFormatException nFE)
           	 	{JOptionPane.showMessageDialog(framePanel, "Please enter a rental period between 1 and 120 days.",
					"Invalid Entry", JOptionPane.INFORMATION_MESSAGE);}

		}
	}
}

