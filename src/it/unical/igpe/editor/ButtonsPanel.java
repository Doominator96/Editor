package it.unical.igpe.editor;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ButtonsPanel extends JPanel implements ActionListener {
	
	public static int BNumber = 12;
	private int iconDim = 32;
	
	public static BufferedImageLoader loader;
	
	public static JButton[] buttons;
	static BufferedImage[] bImage;
	EditorPanel ep;
	
	
	@SuppressWarnings("static-access")
	public ButtonsPanel(EditorPanel ep) {
		super();
		this.ep = ep;
		this.buttons = new JButton[BNumber];
		ButtonsPanel.bImage = new BufferedImage[BNumber];
		this.loader = new BufferedImageLoader();

		for (int i = 0; i < BNumber; i++) {
			buttons[i] = new JButton();
			bImage[i] = loader.loadImage("/map_"+i+".png");
			buttons[i].addActionListener(this);
			buttons[i].setIcon(new ImageIcon(bImage[i].getScaledInstance(iconDim, iconDim, 0)));
			add(buttons[i]);
		}
		
																		
	}

	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < BNumber; i++) {
			if (e.getSource() == buttons[i] && buttons[i].isEnabled() )
			{
				ep.paintImage = bImage[i];
				ep.id = i;
			
							}
		}
	}


}