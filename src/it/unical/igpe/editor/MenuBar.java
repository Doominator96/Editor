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

	PreviewPanel pp;
	private FileNameExtensionFilter filter = new FileNameExtensionFilter("Map","map");

	public MenuBar(PreviewPanel pp) {
		super();
		this.pp = pp;

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
		fileChooser.setFileFilter(filter);
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));

			for (int i = 0; i < pp.mDim; i++) {
				for (int j = 0; j < pp.mDim; j++) {
					bw.write(pp.M[i][j] + " ");
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
			for (int i = 0; i < pp.mDim; i++) {
				String line = br.readLine();
				String[] tokens = line.split(delimiters);
				for (int j = 0; j < tokens.length; j++) {
					pp.M[i][j] = Integer.parseInt(tokens[j]);
				}
			}
		
			br.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		pp.points.clear();

		for (int i = 0; i < pp.mDim; i++)
			for (int j = 0; j < pp.mDim; j++)
				for (int id = 0; id < ToolsPanel.BNumber; id++) {
					if (pp.M[i][j] == id)
						pp.points.add(new ImagePoint(new Point(j, i), ToolsPanel.bImage[id], id));
			
					if (pp.M[i][j] ==pp.stairPos || (pp.M[i][j] >= pp.keyPos && pp.M[i][j] <= pp.playerPos)) {
						ToolsPanel.buttons[pp.M[i][j]].setEnabled(false);
					} 	
				}
		pp.repaint();
	}
	public void clear3(){
		pp.points.clear();
		pp.repaint();
		for (int i = 0; i < pp.mDim; i++) {
			for (int j = 0; j < pp.mDim; j++) {
				pp.M[i][j] = 0;
			}
		}
		for (int i = 0; i < ToolsPanel.BNumber; i++)
			ToolsPanel.buttons[i].setEnabled(true);
	}
}
