package it.unical.igpe.editor;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class Main extends JFrame {
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Level Editor");
		PreviewPanel panel =new PreviewPanel();
		JScrollPane scroll = new JScrollPane(panel);

		MenuBar mb= new MenuBar(panel);
		ToolsPanel tools = new ToolsPanel(panel);
		
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setPreferredSize(new Dimension(800,580));
		
		Container contentPane = frame.getContentPane();
		panel.setPreferredSize(new Dimension(panel.dim,panel.dim));
		tools.setPreferredSize(new Dimension(300,600));
		
		contentPane.setLayout(new BorderLayout());
		contentPane.add(scroll, BorderLayout.CENTER);
		contentPane.add(tools, BorderLayout.WEST);
		contentPane.add(mb,BorderLayout.NORTH);


		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

}
