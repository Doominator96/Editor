package it.unical.igpe.editor;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.JPanel;

import it.unical.igpe.editor.ImagePoint.type;

@SuppressWarnings("serial")
public class PreviewPanel extends JPanel implements MouseListener, MouseMotionListener {

	public int tilePx = 32;
	public int dim = 2048;
	public int mDim=64;

	Vector<ImagePoint> points = new Vector<ImagePoint>();
	
	Point player;
	Vector<Point> enemy;
	
	BufferedImage paintImage=null;
	 
	public int id=0;

	int M[][]=new int [mDim][mDim];

	public PreviewPanel() {
		super();
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void paintComponent(Graphics g) {

		for (int i = 0; i < mDim; i++) {
			for (int j = 0; j < mDim; j++) {
				g.drawImage(ToolsPanel.bImage[0],i*tilePx,j*tilePx,null);
			}
					
		}

		for (int i = 0; i < points.size(); i++) {
			ImagePoint tmp = points.get(i);
			g.drawImage(tmp.getImage(), tmp.getPoint().x * tilePx, tmp.getPoint().y * tilePx, tilePx, tilePx, null);
		}
		for (int i = 0; i < dim; i += tilePx) {
			g.setColor(Color.black);
			g.fillRect(i, 0, 1, dim);
			g.fillRect(0, i, dim, 1);
			
		}
	}

	public void mouseDragged(MouseEvent e) {
		mousePressed(e);

	}

	public void mousePressed(MouseEvent e) {
		if (e.getX() < dim && e.getX() >= 0 && e.getY() < dim && e.getY() >= 0&& paintImage!=null) {
			int x = e.getX();
			int y = e.getY();
			Point p = clickToGrid(x, y);
			ImagePoint ip = new ImagePoint(p,paintImage,id);
			removeDuplicate(p);
			points.add(ip);

				M[y/tilePx][x/tilePx] = id;
			repaint();
			if(id==9||(id>=13&&id<=17)){
				ToolsPanel.buttons[id].setEnabled(false);
				id=0;
				paintImage=null;
			}
			for (int i = 0; i < mDim; i++) {
				for (int j = 0; j < mDim; j++) {
					System.out.print(M[i][j]);
				}
				System.out.println( );
				
			}
		}
	}

	private Point clickToGrid(int x, int y) {
		int px = x;
		int py = y;
		px = px / tilePx;
		py = py / tilePx;
		return new Point(px, py);
	}

	private void removeDuplicate(Point p) {
		for (int i = 0; i < points.size(); i++) {
			ImagePoint tmp = points.get(i);
			if (tmp.getPoint().equals(p)) {
				if(tmp.type==type.PLAYER)
					ToolsPanel.buttons[17].setEnabled(true);
				else if(tmp.type==type.STAIR)
					ToolsPanel.buttons[9].setEnabled(true);
				else if(tmp.type==type.KEY)
					ToolsPanel.buttons[13].setEnabled(true);
				else if(tmp.type==type.REDKEY)
					ToolsPanel.buttons[14].setEnabled(true);
				else if(tmp.type==type.BLUEKEY)
					ToolsPanel.buttons[15].setEnabled(true);			
				else if(tmp.type==type.GREENKEY)
					ToolsPanel.buttons[16].setEnabled(true);	
				points.remove(i);
					return;
			}
		}
	}
	public void mouseMoved(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

}
