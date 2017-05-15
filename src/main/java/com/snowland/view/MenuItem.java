package com.snowland.view;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MenuItem extends JPanel {
	
	JLabel label ;
	JTextField textField;
	
	
	public MenuItem() {
		label = new JLabel();
		textField = new JTextField();
		// TODO Auto-generated constructor stub
		super.setLayout(new FlowLayout());
		super.add(label);
		super.add(textField);
	}
}
