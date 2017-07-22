package it.unical.igpe.editor;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {

	EditorPanel ep;
	private FileNameExtensionFilter filter = new FileNameExtensionFilter("Map", "map");

	public MenuBar(EditorPanel ep) {
		super();
		this.ep = ep;

		JMenu file = new JMenu("File");
		JMenuItem save = new JMenuItem("Save");
		JMenuItem loadSP = new JMenuItem("Load SinglePlayer");
		JMenuItem loadMP = new JMenuItem("Load MultiPlayer");
		JMenuItem exit = new JMenuItem("Exit");
		JMenuItem clear = new JMenuItem("New");
		exit.addActionListener((ActionEvent event) -> {
			System.exit(0);
		});
		save.addActionListener((ActionEvent event) -> {
			save3();
		});
		loadMP.addActionListener((ActionEvent event) -> {
			load3MP();
		});
		loadSP.addActionListener((ActionEvent event) -> {
			load3SP();
		});
		clear.addActionListener((ActionEvent event) -> {
			clear3();

		});

		file.add(clear);
		file.add(save);
		file.add(loadSP);
		file.add(loadMP);
		file.add(exit);
		this.add(file);
	}

	public void save3() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("."));
		fileChooser.setFileFilter(filter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.showSaveDialog(MenuBar.this);
		if (fileChooser.getSelectedFile() != null) {
			File file = fileChooser.getSelectedFile();
			file = new File(file.toString() + ".map");
			fileChooser.setFileFilter(filter);

			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(file));

				for (int i = 0; i < ep.mDim; i++) {
					for (int j = 0; j < ep.mDim; j++) {
						bw.write(ep.M[i][j] + " ");
					}
					bw.newLine();
				}

				bw.flush();
				bw.close();
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
	}

	public void load3MP() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("."));
		fileChooser.setFileFilter(filter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.showOpenDialog(MenuBar.this);
		if (fileChooser.getSelectedFile() != null) {
			File file = fileChooser.getSelectedFile();
			try {
				clear3();
				ep.mDim = 32;
				ep.tilePx = 64;

				BufferedReader br = new BufferedReader(new FileReader(file));
				String delimiters = " ";

				for (int i = 0; i < 32; i++) {
					String line = br.readLine();
					String[] tokens = line.split(delimiters);
					for (int j = 0; j < tokens.length; j++) {
						ep.M[i][j] = Integer.parseInt(tokens[j]);
					}
				}

				br.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
			ep.points.clear();

			for (int i = 0; i < 32; i++)
				for (int j = 0; j < 32; j++)
					for (int id = 0; id < ButtonsPanel.BNumber; id++) {
						if (ep.M[i][j] == id)
							ep.points.add(new ImagePoint(new Point(j, i), ButtonsPanel.bImage[id], id));

						if (ep.M[i][j] == ep.stairPos || (ep.M[i][j] >= ep.keyPos && ep.M[i][j] <= ep.playerPos)) {
							ButtonsPanel.buttons[ep.M[i][j]].setEnabled(false);
						}
					}
			ep.repaint();
			ButtonsPanel.buttons[17].setEnabled(true);
			for (int i = 2; i < 12; i++)
				ButtonsPanel.buttons[i].setEnabled(false);
		}
	}

	public void load3SP() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("."));
		fileChooser.setFileFilter(filter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.showOpenDialog(MenuBar.this);
		if (fileChooser.getSelectedFile() != null) {
			File file = fileChooser.getSelectedFile();
			try {
				clear3();
				ep.mDim = 64;
				ep.tilePx = 32;

				BufferedReader br = new BufferedReader(new FileReader(file));
				String delimiters = " ";

				for (int i = 0; i < 64; i++) {
					String line = br.readLine();
					String[] tokens = line.split(delimiters);
					for (int j = 0; j < tokens.length; j++) {
						ep.M[i][j] = Integer.parseInt(tokens[j]);
					}
				}

				br.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
			ep.points.clear();

			for (int i = 0; i < 64; i++)
				for (int j = 0; j < 64; j++)
					for (int id = 0; id < ButtonsPanel.BNumber; id++) {
						if (ep.M[i][j] == id)
							ep.points.add(new ImagePoint(new Point(j, i), ButtonsPanel.bImage[id], id));

						if (ep.M[i][j] == ep.stairPos || (ep.M[i][j] >= ep.keyPos && ep.M[i][j] <= ep.playerPos)) {
							ButtonsPanel.buttons[ep.M[i][j]].setEnabled(false);
						}
					}
			ep.repaint();
		}
	}

	@SuppressWarnings("static-access")
	public void clear3() {
		ep.points.clear();
		ep.mDim = 64;
		ep.background = ep.loader.loadImage("/background.png");
		ep.tilePx = 32;
		ep.repaint();
		for (int i = 0; i < ep.mDim; i++) {
			for (int j = 0; j < ep.mDim; j++) {
				if (i == 0 || i == ep.mDim - 1 || j == 0 || j == ep.mDim - 1)
					ep.M[i][j] = 1;
				else
					ep.M[i][j] = 0;
			}

		}
		for (int i = 0; i < ButtonsPanel.BNumber; i++)
			ButtonsPanel.buttons[i].setEnabled(true);

		ButtonsPanel.buttons[17].setEnabled(false);
	}

}
