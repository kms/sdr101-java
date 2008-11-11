package org.picofarad.sdr101;

import java.awt.Component;
import javax.swing.*;
import static javax.swing.GroupLayout.Alignment.*;

public class Sdr101cli extends JFrame {
    public Sdr101cli() {
	JLabel label = new JLabel("Find What:");;
	JTextField textField = new JTextField();
	JCheckBox caseCheckBox = new JCheckBox("Match Case");
	JCheckBox wrapCheckBox = new JCheckBox("Wrap Around");
	JCheckBox wholeCheckBox = new JCheckBox("Whole Words");
	JCheckBox backCheckBox = new JCheckBox("Search Backwards");
	JButton findButton = new JButton("Find");
	JButton cancelButton = new JButton("Cancel");

	// remove redundant default border of check boxes - they would hinder
	// correct spacing and aligning (maybe not needed on some look and feels)
	caseCheckBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
	wrapCheckBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
	wholeCheckBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
	backCheckBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

	GroupLayout layout = new GroupLayout(getContentPane());
	getContentPane().setLayout(layout);
	layout.setAutoCreateGaps(true);
	layout.setAutoCreateContainerGaps(true);

	layout.setHorizontalGroup(layout.createSequentialGroup()
		.addComponent(label)
		.addGroup(layout.createParallelGroup(LEADING)
		    .addComponent(textField)
		    .addGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(LEADING)
			    .addComponent(caseCheckBox)
			    .addComponent(wholeCheckBox))
			.addGroup(layout.createParallelGroup(LEADING)
			    .addComponent(wrapCheckBox)
			    .addComponent(backCheckBox))))
		.addGroup(layout.createParallelGroup(LEADING)
		    .addComponent(findButton)
		    .addComponent(cancelButton))
		);

	layout.linkSize(SwingConstants.HORIZONTAL, findButton, cancelButton);

	layout.setVerticalGroup(layout.createSequentialGroup()
		.addGroup(layout.createParallelGroup(BASELINE)
		    .addComponent(label)
		    .addComponent(textField)
		    .addComponent(findButton))
		.addGroup(layout.createParallelGroup(LEADING)
		    .addGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(BASELINE)
			    .addComponent(caseCheckBox)
			    .addComponent(wrapCheckBox))
			.addGroup(layout.createParallelGroup(BASELINE)
			    .addComponent(wholeCheckBox)
			    .addComponent(backCheckBox)))
		    .addComponent(cancelButton))
		);

	setTitle("Find");
	pack();
	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String args[]) {
	java.awt.EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    UIManager.setLookAndFeel(
			"javax.swing.plaf.metal.MetalLookAndFeel");
		    //  "com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		    //UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
		new Sdr101cli().setVisible(true);
	    }
	});
    }
}
