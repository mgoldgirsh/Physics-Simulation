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

public class simPanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener {

  static int x = 100;
  static int y = 100;
  static int width = 50;
  static int height = 50;

  int screenX;
  int screenY;

  int index = 0;

  boolean dragged = false;

  static int gravity = 10;

  JButton settings = new JButton("Settings");
  static block b1 = new block(x, y, width, height);
  simEditor se = new simEditor();

  public simPanel() {
    this.setFocusable(false);
    this.addMouseListener(this);
    this.addMouseMotionListener(this);
    this.settings.setVisible(true);
    this.settings.addActionListener(this);
    this.add(this.settings);

    this.se.totalBlocks.add(b1);

    Timer t = new Timer(1000 / 144, this);
    t.start();
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand() == "Settings") {
      this.se.setVisible(true);
    }

    // gravity movement
    for (int i = 0; i < this.se.totalBlocks.size(); i++) {
      if (this.se.totalBlocks.get(i).getY() < this.getHeight() - height && this.dragged != true) {
        this.se.totalBlocks.get(i)
            .changeY(this.se.totalBlocks.get(i).getY() + this.se.totalBlocks.get(i).getVelocity());
        this.se.totalBlocks.get(i)
            .changeVelocity(this.se.totalBlocks.get(i).getVelocity() + (int) (gravity * .144));
      }
      if (this.se.totalBlocks.get(i).getY() > this.getHeight() - height
          && this.se.getElasticity() == 0) {
        this.se.totalBlocks.get(i).changeY(this.getHeight() - height);
        this.se.totalBlocks.get(i).changeVelocity(0);
      }
      if (this.se.totalBlocks.get(i).getY() > this.getHeight() - height
          && this.se.getElasticity() > 0) {
        this.se.totalBlocks.get(i).changeY(this.getHeight() - height);
        this.se.totalBlocks.get(i).changeVelocity(
            this.se.totalBlocks.get(i).getVelocity() * -1 + this.se.getElasticity());
        this.se.totalBlocks.get(i)
            .changeY(this.se.totalBlocks.get(i).getY() + this.se.totalBlocks.get(i).getVelocity());
      }

      // Collision detection
      if (this.se.totalBlocks.size() > 1 && this.dragged != true && this.se.collisionsOn) {
        for (int j = i + 1; j < this.se.totalBlocks.size(); j++) {
          if (this.se.totalBlocks.get(i).collides(this.se.totalBlocks.get(j))) {
            this.se.totalBlocks.get(i).changeIfCollision(this.se.totalBlocks.get(j));
          }
        }
      }
    }
    if (this.se.graphGetRecording()) {
      this.se.addXPos(this.se.totalBlocks.get(0).x);
      this.se.addYPos(this.se.totalBlocks.get(0).y);
    }
    this.repaint();
  }

  public void paint(Graphics g) {
    super.paint(g);
    for (int i = 0; i < this.se.totalBlocks.size(); i++) {
      this.se.totalBlocks.get(i).createBlock(g);
    }
  }

  public void mouseDragged(MouseEvent e) {
    if (this.dragged) {
      this.se.totalBlocks.get(this.index).changeVelocity(0);
      this.se.totalBlocks.get(this.index).changeX(x + e.getXOnScreen() - this.screenX);
      this.se.totalBlocks.get(this.index).changeY(y + e.getYOnScreen() - this.screenY);
    }

  }

  public void mouseMoved(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  public void mouseClicked(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  public void mousePressed(MouseEvent e) {
    for (int i = 0; i < this.se.totalBlocks.size(); i++) {
      if (this.se.totalBlocks.get(i).getRect().contains(e.getPoint())) {

        this.screenX = e.getXOnScreen();
        this.screenY = e.getYOnScreen();

        x = this.se.totalBlocks.get(i).getX();
        y = this.se.totalBlocks.get(i).getY();

        this.se.totalBlocks.get(i).changeVelocity(0);
        this.index = i;
        this.dragged = true;
      }
    }

  }

  public void mouseReleased(MouseEvent e) {
    this.se.totalBlocks.get(this.index).changeVelocity(0);
    this.dragged = false;

  }

  public void mouseEntered(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  public void mouseExited(MouseEvent e) {
    // TODO Auto-generated method stub

  }
}
