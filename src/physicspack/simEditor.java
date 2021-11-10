package physicspack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.Timer;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints; 
import java.awt.Color; 

import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class simEditor implements ActionListener{
	JFrame f = new JFrame("Settings");
	GridBagConstraints c = new GridBagConstraints();
	
	JLabel massLabel = new JLabel("Block Mass (kg): ");
	JSlider massSlider = new JSlider(JSlider.HORIZONTAL, 1, 20, 1); 
	
	JLabel elasticSliderLabel = new JLabel("Percent Inelastic: ");
	JSlider elasticSlider = new JSlider(JSlider.HORIZONTAL, 10, 110, 110); 
	
	JButton addBlock = new JButton("Add Block");
	
	JButton collisionDetection = new JButton("Collisions On");
	
	public ArrayList<block> totalBlocks = new ArrayList<block>();
	
	public boolean collisionsOn = true; 
	
	simEditor() {
		f.setLayout(new GridBagLayout());
		f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		f.setSize(400, 200);
		f.setVisible(false);
		
		addBlock.addActionListener(this);
		collisionDetection.addActionListener(this);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx  = .5; 
		
		c.gridx = 0; 
		c.gridy = 1; 
		c.insets = new Insets(0, 20, 0, 0);
		//f.add(massLabel, c);
		c.gridx = 1; 
		c.insets = new Insets(0, 0, 0, 20);
		//f.add(massSlider, c);	
		c.gridy = 2; 
		c.gridx = 0;
		c.insets = new Insets(0, 20, 0, 0);
		f.add(elasticSliderLabel, c);
		c.gridx = 1; 
		c.insets = new Insets(0, 0, 0, 20);
		f.add(elasticSlider, c);
		c.gridy = 3;
		c.gridx = 0;
		c.gridwidth = 2; 
		c.weightx = 0; 
		c.insets = new Insets(0,100,0,100);
		f.add(addBlock,c); 
		c.gridy = 4; 
		c.gridx = 0;
		c.gridwidth = 2;
		c.weightx = 0; 
		c.insets = new Insets(0,100,0,100);
		collisionDetection.setOpaque(true);
		collisionDetection.setBackground(Color.green);
		f.add(collisionDetection, c); 
		
		Timer t = new Timer(1000/60, this);
		t.start();
	}
	
	public void setVisible(boolean a) {
		f.setVisible(a);
	}
	
	public int getElasticity() {
		if (elasticSlider.getValue() == 110) {
			return 0;
		}
		return (int) elasticSlider.getValue() / 5;
	}
	
	public int getMass() {
		return massSlider.getValue(); 
	}
	
	public void actionPerformed(ActionEvent e) {
		massLabel.setText("Block Mass (kg): " + massSlider.getValue());
		elasticSliderLabel.setText("Percent Inelastic: " + (elasticSlider.getValue() - 10));
		
		if (e.getActionCommand() == "Add Block") {
			totalBlocks.add(new block(150, 100, 50, 50));
		}
		
		if (e.getActionCommand() == "Collisions On") {
			collisionDetection.setBackground(Color.RED);
			collisionDetection.setText("Collisions Off");
			collisionsOn = false; 
		}
		
		if (e.getActionCommand() == "Collisions Off") {
			collisionDetection.setBackground(Color.GREEN);
			collisionDetection.setText("Collisions On");
			collisionsOn = true; 
		}
	}
}
