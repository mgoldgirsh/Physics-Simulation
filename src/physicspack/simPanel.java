package physicspack;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color; 
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.Timer; 
import java.awt.event.*;
import java.util.ArrayList;

public class simPanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener{
	
	static int x = 100; 
	static int y = 100;
	static int width = 50; 
	static int height = 50; 
	
	int screenX; int screenY;
	
	int index =  0; 
	
	boolean dragged = false; 
	
	static int gravity = 10; 
	
	JButton settings = new JButton("Settings"); 
	static block b1 = new block(x, y, width, height); 
	simEditor se = new simEditor(); 
	
	public simPanel() {
		setFocusable(false);
		addMouseListener(this);
		addMouseMotionListener(this);
		settings.setVisible(true);
		settings.addActionListener(this);
		add(settings);
		
		se.totalBlocks.add(b1);
		
		Timer t = new Timer(1000/144, this);
		t.start();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Settings") {
			se.setVisible(true);
		}
		
		// gravity movement
		for (int i = 0; i<se.totalBlocks.size(); i++) {
			if (se.totalBlocks.get(i).getY() < getHeight()-height && dragged != true) { 
				se.totalBlocks.get(i).changeY(se.totalBlocks.get(i).getY() + se.totalBlocks.get(i).getVelocity());  
				se.totalBlocks.get(i).changeVelocity(se.totalBlocks.get(i).getVelocity() + (int) (gravity * .144));
			}
			if (se.totalBlocks.get(i).getY() > getHeight() - height && se.getElasticity() == 0) {
				se.totalBlocks.get(i).changeY(getHeight() - height);
				se.totalBlocks.get(i).changeVelocity(0);
			}
			if (se.totalBlocks.get(i).getY() > getHeight() - height && se.getElasticity() > 0) {
				se.totalBlocks.get(i).changeY(getHeight() - height);
				se.totalBlocks.get(i).changeVelocity(se.totalBlocks.get(i).getVelocity() * -1 + se.getElasticity());
				se.totalBlocks.get(i).changeY(se.totalBlocks.get(i).getY() + se.totalBlocks.get(i).getVelocity());
			}
			
			// Collision detection
			if (se.totalBlocks.size() > 1 && dragged != true && se.collisionsOn) {
				for (int j= i+1; j<se.totalBlocks.size(); j++) {
					if (se.totalBlocks.get(i).collides(se.totalBlocks.get(j))) {
						se.totalBlocks.get(i).changeIfCollision(se.totalBlocks.get(j));
					}
				}
			}
		}
		repaint(); 
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		//b1.createBlock(g);
		for (int i =0; i<se.totalBlocks.size(); i++) {
			se.totalBlocks.get(i).createBlock(g);
		}
	}

	public void mouseDragged(MouseEvent e) {
		if (dragged) {
			se.totalBlocks.get(index).changeVelocity(0);
			se.totalBlocks.get(index).changeX(x + e.getXOnScreen() - screenX);
			se.totalBlocks.get(index).changeY(y + e.getYOnScreen() - screenY);
		}
		
	}

	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		for (int i =0; i<se.totalBlocks.size(); i++) {
			if (se.totalBlocks.get(i).getRect().contains(e.getPoint())) {
				
				screenX = e.getXOnScreen();
				screenY = e.getYOnScreen(); 
				
				x = se.totalBlocks.get(i).getX();
				y = se.totalBlocks.get(i).getY();
				
				se.totalBlocks.get(i).changeVelocity(0);
				index = i;
				dragged = true; 
			}
		}
		
	}

	public void mouseReleased(MouseEvent e) {
		se.totalBlocks.get(index).changeVelocity(0);
		dragged = false; 
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
