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
public class EditorPanel extends JPanel implements MouseListener, MouseMotionListener {

	public int tilePx = 32;
	public int dim = 2048;
	public int mDim = 64;

	int stairPos = 2;
	int keyPos = 6;
	int redKeyPos = 7;
	int blueKeyPos = 8;
	int greenKeyPos = 9;
	int playerPos = 10;
	int enemy1Pos = 11;

	public static BufferedImageLoader loader = new BufferedImageLoader();
	BufferedImage background = loader.loadImage("/background.png");
	Vector<ImagePoint> points = new Vector<ImagePoint>();

	Point player;
	Vector<Point> enemy;

	BufferedImage paintImage = null;

	public int id = 0;

	int M[][] = new int[mDim][mDim];

	public EditorPanel() {
		super();
		addMouseListener(this);
		addMouseMotionListener(this);

		for (int i = 0; i < mDim; i++) {
			for (int j = 0; j < mDim; j++) {
				if (i == 0 || i == mDim - 1 || j == 0 || j == mDim - 1)
					M[i][j] = 1;
				else
					M[i][j] = 0;
			}

		}

	}

	public void paintComponent(Graphics g) {

		g.drawImage(background, 0, 0, null);

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
		if (e.getX() < dim && e.getX() >= 0 && e.getY() < dim && e.getY() >= 0 && paintImage != null) {
			int x = e.getX() / tilePx;
			int y = e.getY() / tilePx;
			if(x!=0&&x!=mDim-1&&y!=0&&y!=mDim-1){
			Point p = new Point(x, y);
			ImagePoint ip = new ImagePoint(p, paintImage, id);
			removeDuplicate(p);
			points.add(ip);

			M[y][x] = id;
			repaint();
			if (id == stairPos || (id >= keyPos && id <= playerPos)) {
				ButtonsPanel.buttons[id].setEnabled(false);
				id = 0;
				paintImage = null;
			}}
		}
	}

	private void removeDuplicate(Point p) {
		for (int i = 0; i < points.size(); i++) {
			ImagePoint tmp = points.get(i);
			if (tmp.getPoint().equals(p)) {
				if (tmp.type == type.PLAYER)
					ButtonsPanel.buttons[playerPos].setEnabled(true);
				else if (tmp.type == type.STAIR)
					ButtonsPanel.buttons[stairPos].setEnabled(true);
				else if (tmp.type == type.KEY)
					ButtonsPanel.buttons[keyPos].setEnabled(true);
				else if (tmp.type == type.REDKEY)
					ButtonsPanel.buttons[redKeyPos].setEnabled(true);
				else if (tmp.type == type.BLUEKEY)
					ButtonsPanel.buttons[blueKeyPos].setEnabled(true);
				else if (tmp.type == type.GREENKEY)
					ButtonsPanel.buttons[greenKeyPos].setEnabled(true);
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
