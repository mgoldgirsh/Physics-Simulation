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

public class graphPanel extends JPanel implements ActionListener {
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
    this.f.add(this);
    this.f.setLayout(new GridBagLayout());
    this.f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    this.f.setSize(400, 400);
    this.f.setVisible(false);

    this.record.addActionListener(this);
    this.record.setOpaque(true);
    this.record.setBackground(Color.GREEN);

    this.c.fill = GridBagConstraints.HORIZONTAL;
    this.c.weightx = .5;

    this.c.gridx = 0;
    this.c.gridy = 0;
    this.c.gridwidth = 2;
    this.c.weightx = 0;
    this.c.insets = new Insets(0, 100, 300, 100);
    this.f.add(this.record, this.c);

    Timer t = new Timer(1000 / 60, this);
    t.start();
  }

  public void drawPoint(Graphics g, int x, int y) {
    g.setColor(Color.BLACK);
    g.fillRect(x, y, 5, 5);
  }

  public void paint(Graphics g) {
    super.paint(g);
    g.drawRect(0, 0, 10, 10);
    for (int i = 0; i < this.xPos.size(); i++) {
      this.drawPoint(g, (int) (this.xPos.get(i) * this.scaleFactor),
          (int) (this.yPos.get(i) * this.scaleFactor));
    }
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand() == "Record") {
      this.recording = true;
      this.record.setBackground(Color.RED);
      JOptionPane.showMessageDialog(this.f, "Recording Started", "Recording Message",
          JOptionPane.PLAIN_MESSAGE);
      this.record.setText("Recording In Progress");
    }
    if (e.getActionCommand() == "Recording In Progress") {
      this.recording = false;
      this.record.setBackground(Color.GREEN);
      JOptionPane.showMessageDialog(this.f, "Recording Stopped", "Recording Message",
          JOptionPane.PLAIN_MESSAGE);
      this.record.setText("Record");
    }
    this.repaint();
  }

  public void setVisible(boolean b) {
    this.f.setVisible(b);
  }

  public boolean getRecording() {
    return this.recording;
  }

}
