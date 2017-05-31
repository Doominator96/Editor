package it.unical.igpe.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ToolsPanel extends JPanel implements ActionListener {
	
	public static int BNumber = 23;
	private int iconDim = 32;
	
	
	
	public static JButton[] buttons;
	static BufferedImage[] bImage;
	PreviewPanel pp;
	public BufferedImageLoader loader;

	public ToolsPanel(PreviewPanel pp) {
		super();
		this.pp = pp;
		this.buttons = new JButton[BNumber];
		ToolsPanel.bImage = new BufferedImage[BNumber];
		this.loader = new BufferedImageLoader();
		for (int i = 0; i < BNumber; i++) {
			buttons[i] = new JButton();
			bImage[i] = null;
			bImage[i] = loader.loadImage("/map (" + i + ").png");
			buttons[i].addActionListener(this);
			buttons[i].setIcon(new ImageIcon(bImage[i].getScaledInstance(iconDim, iconDim, 0)));
			add(buttons[i]);
		}
																		
	}

	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < BNumber; i++) {
			if (e.getSource() == buttons[i] && buttons[i].isEnabled() )
			{
				pp.paintImage = bImage[i];
				pp.id = i;
			
							}
		}
	}


}