package it.unical.igpe.editor;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ButtonsPanel extends JPanel implements ActionListener {
	
	

	public static int BNumber = 18;
	private int iconDim = 32;
	
	public static BufferedImageLoader loader;
	
	public static JButton[] buttons;
	static BufferedImage[] bImage;
	EditorPanel ep;
	
	public static JButton SP=new JButton();
	public static JButton MP=new JButton();
	
	@SuppressWarnings("static-access")
	public ButtonsPanel(EditorPanel ep) {
		super();
		this.ep = ep;
		this.setLayout(new GridLayout(0,3));
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
	 SP.setText("SinglePlayer Map");
	 MP.setText("MultiPlayer Map");
	 add(SP);
	 add(MP);
	 SP.addActionListener(this);
	 MP.addActionListener(this);	
	 
	 buttons[17].setEnabled(false);
	}
	
	public void clear(EditorPanel ep){
		ep.points.clear();
		ep.repaint();
		for (int i = 0; i < ep.mDim; i++) {
			for (int j = 0; j < ep.mDim; j++) {
				if (i == 0 || i == ep.mDim - 1 || j == 0 || j == ep.mDim - 1)
					ep.M[i][j] = 1;
				else
					ep.M[i][j] = 0;
			}

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
		
		if (e.getSource() == SP ){
			clear(ep);
			ep.mDim = 64;
			ep.background= loader.loadImage("/background.png");
			ep.tilePx=32;
			for (int i = 0; i < BNumber; i++)
				buttons[i].setEnabled(true);
			buttons[17].setEnabled(false);
			
		
		}
		if(e.getSource()==MP){
			clear(ep);
			ep.mDim = 32;
			ep.background= loader.loadImage("/backgroundMP.png");
			ep.tilePx=64;
			for (int i = 0; i < ep.mDim; i++) {
				for (int j = 0; j < ep.mDim; j++) {
					if (i == 0 || i == ep.mDim - 1 || j == 0 || j == ep.mDim - 1)
						ep.M[i][j] = 1;
					else
						ep.M[i][j] = 0;
				}}
			buttons[17].setEnabled(true);
			for (int i = 2; i <12 ; i++)
				buttons[i].setEnabled(false);
		}
	}

}