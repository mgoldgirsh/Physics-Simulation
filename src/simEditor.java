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

public class simEditor implements ActionListener {
  JFrame f = new JFrame("Settings");
  GridBagConstraints c = new GridBagConstraints();

  JLabel massLabel = new JLabel("Block Mass (kg): ");
  JSlider massSlider = new JSlider(JSlider.HORIZONTAL, 1, 20, 1);

  JLabel elasticSliderLabel = new JLabel("Percent Inelastic: ");
  JSlider elasticSlider = new JSlider(JSlider.HORIZONTAL, 10, 110, 110);

  JButton addBlock = new JButton("Add Block");

  JButton removeBlock = new JButton("Remove Block");

  JButton collisionDetection = new JButton("Collisions On");

  JButton graph = new JButton("Graph");

  public ArrayList<block> totalBlocks = new ArrayList<block>();

  public graphPanel g = new graphPanel();
  public boolean collisionsOn = true;

  simEditor() {
    this.f.setLayout(new GridBagLayout());
    this.f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    this.f.setSize(400, 200);
    this.f.setVisible(false);

    this.addBlock.addActionListener(this);
    this.removeBlock.addActionListener(this);
    this.collisionDetection.addActionListener(this);
    this.graph.addActionListener(this);

    this.c.fill = GridBagConstraints.HORIZONTAL;
    this.c.weightx = .5;

    this.c.gridx = 0;
    this.c.gridy = 1;
    this.c.insets = new Insets(0, 20, 0, 0);
    // f.add(massLabel, c);
    this.c.gridx = 1;
    this.c.insets = new Insets(0, 0, 0, 20);
    // f.add(massSlider, c);
    this.c.gridy = 2;
    this.c.gridx = 0;
    this.c.insets = new Insets(0, 20, 0, 0);
    this.f.add(this.elasticSliderLabel, this.c);
    this.c.gridx = 1;
    this.c.insets = new Insets(0, 0, 0, 20);
    this.f.add(this.elasticSlider, this.c);
    this.c.gridy = 3;
    this.c.gridx = 0;
    this.c.gridwidth = 2;
    this.c.weightx = 0;
    this.c.insets = new Insets(0, 100, 0, 100);
    this.f.add(this.addBlock, this.c);
    this.c.gridy = 4;
    this.c.gridx = 0;
    this.c.gridwidth = 2;
    this.c.weightx = 0;
    this.c.insets = new Insets(0, 100, 0, 100);
    this.f.add(this.removeBlock, this.c);
    this.c.gridy = 5;
    this.c.gridx = 0;
    this.c.gridwidth = 2;
    this.c.weightx = 0;
    this.c.insets = new Insets(0, 100, 0, 100);
    this.collisionDetection.setOpaque(true);
    this.collisionDetection.setBackground(Color.green);
    this.f.add(this.collisionDetection, this.c);
    this.c.gridy = 6;
    this.c.gridx = 0;
    this.c.gridwidth = 2;
    this.c.weightx = 0;
    this.c.insets = new Insets(0, 100, 0, 100);
    this.f.add(this.graph, this.c);

    Timer t = new Timer(1000 / 60, this);
    t.start();
  }

  public void setVisible(boolean a) {
    this.f.setVisible(a);
  }

  public int getElasticity() {
    if (this.elasticSlider.getValue() == 110) {
      return 0;
    }
    return this.elasticSlider.getValue() / 5;
  }

  public int getMass() {
    return this.massSlider.getValue();
  }

  public void addXPos(int num) {
    this.g.xPos.add(num);
  }

  public void addYPos(int num) {
    this.g.yPos.add(num);
  }

  public boolean graphGetRecording() {
    return this.g.getRecording();
  }

  public void actionPerformed(ActionEvent e) {
    this.massLabel.setText("Block Mass (kg): " + this.massSlider.getValue());
    this.elasticSliderLabel.setText("Percent Inelastic: " + (this.elasticSlider.getValue() - 10));

    if (e.getActionCommand() == "Add Block") {
      this.totalBlocks.add(new block(150, 100, 50, 50));
    }

    if (e.getActionCommand() == "Remove Block") {
      this.totalBlocks.remove(this.totalBlocks.size() - 1);
    }

    if (e.getActionCommand() == "Collisions On") {
      this.collisionDetection.setBackground(Color.RED);
      this.collisionDetection.setText("Collisions Off");
      this.collisionsOn = false;
    }

    if (e.getActionCommand() == "Collisions Off") {
      this.collisionDetection.setBackground(Color.GREEN);
      this.collisionDetection.setText("Collisions On");
      this.collisionsOn = true;
    }

    if (e.getActionCommand() == "Graph") {
      this.g.setVisible(true);
    }
  }
}
