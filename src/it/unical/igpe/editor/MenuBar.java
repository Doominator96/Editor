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
	private FileNameExtensionFilter filter = new FileNameExtensionFilter("Map","map");

	public MenuBar(EditorPanel ep) {
		super();
		this.ep = ep;

		JMenu file = new JMenu("File");
		JMenuItem save = new JMenuItem("Save");
		JMenuItem load = new JMenuItem("Load");
		JMenuItem exit = new JMenuItem("Exit");
		JMenuItem clear = new JMenuItem("New");
		exit.addActionListener((ActionEvent event) -> {
			System.exit(0);
		});
		save.addActionListener((ActionEvent event) -> {
			save3();
		});
		load.addActionListener((ActionEvent event) -> {
			load3();
		});
		clear.addActionListener((ActionEvent event) -> {
			clear3();

		});

		file.add(clear);
		file.add(save);
		file.add(load);
		file.add(exit);
		this.add(file);
	}

	public void save3() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("."));
		fileChooser.setFileFilter(filter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.showSaveDialog(MenuBar.this);
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

	public void load3() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("."));
		fileChooser.setFileFilter(filter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.showOpenDialog(MenuBar.this);
		File file = fileChooser.getSelectedFile();
		
		try {
			clear3();
			BufferedReader br = new BufferedReader(new FileReader(file));
			String delimiters = " ";
			for (int i = 0; i < ep.mDim; i++) {
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

		for (int i = 0; i < ep.mDim; i++)
			for (int j = 0; j < ep.mDim; j++)
				for (int id = 0; id < ButtonsPanel.BNumber; id++) {
					if (ep.M[i][j] == id)
						ep.points.add(new ImagePoint(new Point(j, i), ButtonsPanel.bImage[id], id));
			
					if (ep.M[i][j] ==ep.stairPos || (ep.M[i][j] >= ep.keyPos && ep.M[i][j] <= ep.playerPos)) {
						ButtonsPanel.buttons[ep.M[i][j]].setEnabled(false);
					} 	
				}
		ep.repaint();
	}
	public void clear3(){
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
		for (int i = 0; i < ButtonsPanel.BNumber; i++)
			ButtonsPanel.buttons[i].setEnabled(true);
	}
}
