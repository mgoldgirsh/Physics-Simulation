package physicspack;

import javax.swing.JFrame; 
import java.awt.BorderLayout;

public class mainSimulation {
	public static void main(String[] args) {
		JFrame f = new JFrame("Physics Simulation");
		f.setLayout(new BorderLayout());
		simPanel p = new simPanel();
		p.setVisible(true);
		f.add(p, BorderLayout.CENTER);
		f.setSize(400, 500);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
