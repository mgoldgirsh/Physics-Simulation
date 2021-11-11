package physicspack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class graphPanel extends JPanel implements ActionListener{
	JFrame f = new JFrame("Graph");
	GridBagConstraints c = new GridBagConstraints();
	
	ArrayList<Integer> xPos = new ArrayList<Integer>(); 
	ArrayList<Integer> yPos = new ArrayList<Integer>();
	
	boolean recording = false; 
	JButton record = new JButton("Record");
	
	double scaleFactor = 0.6; 
	
	// graphs the initial block's position vs time
	// WIP 
	graphPanel() {
		f.add(this); 
		f.setLayout(new GridBagLayout());
		f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		f.setSize(400, 400);
		f.setVisible(false);
		
		record.addActionListener(this);
		record.setOpaque(true);
		record.setBackground(Color.GREEN);
		
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx  = .5; 
		
		c.gridx = 0; 
		c.gridy = 0;
		c.gridwidth = 2;
		c.weightx = 0; 
		c.insets = new Insets(0,100,300,100);
		f.add(record, c); 
		
		Timer t = new Timer(1000/60, this);
		t.start();
	}

	public void drawPoint(Graphics g, int x, int y) {
		g.setColor(Color.BLACK);
		g.fillRect(x, y, 5, 5);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawRect(0, 0, 10, 10);
		for (int i =0; i < xPos.size(); i++) {
			drawPoint(g, (int) (xPos.get(i) * scaleFactor), (int) (yPos.get(i) * scaleFactor));
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Record") {
			recording = true; 
			record.setBackground(Color.RED);
			JOptionPane.showMessageDialog(f, "Recording Started", "Recording Message", JOptionPane.PLAIN_MESSAGE);
			record.setText("Recording In Progress");
		}
		if (e.getActionCommand() == "Recording In Progress") {
			recording = false; 
			record.setBackground(Color.GREEN);
			JOptionPane.showMessageDialog(f, "Recording Stopped", "Recording Message", JOptionPane.PLAIN_MESSAGE);
			record.setText("Record");
		}
		repaint(); 
	}

	public void setVisible(boolean b) {
		f.setVisible(b);
	}
	
	public boolean getRecording() {
		return recording; 
	}
	

}
