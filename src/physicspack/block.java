package physicspack;

import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;

public class block extends JComponent {
	public int x; public int y; public int width; public int height; int velocity = 0;
	
	public Rectangle r = new Rectangle(x,y,width,height); 
	
	public block(int x, int y, int width, int height) {
		this.x = x;
		this.y = y; 
		this.width = width;
		this.height = height; 
		this.velocity = velocity;
		r.setBounds(x, y, width, height);
		
	}
	
	public int getX() {
		return this.x; 
	}
	
	public int getY() {
		return this.y; 
	}

	public int getWidth() {
		return this.width; 
	}
	
	public int getHeight() {
		return this.height; 
	}
	
	public int getVelocity() {
		return velocity; 
	}
	
	public Rectangle getRect() {
		return this.r;
	}
	
	public int[] getMid() {
		int[] r = new int[2]; 
		r[0] = (this.x + this.x + this.width)/2; 
		r[1] = (this.y + this.y + this.height) / 2; 
		return r;
	}
	
	public double midH(int x, int y) {
		int dX = x - this.getMid()[0];
		int dY = y - this.getMid()[1];
		return Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2));
	}
	
	public double getAngle(int x, int y) {
		int dX = x - this.getMid()[0];
		int dY = y - this.getMid()[1];
		return Math.atan(dY/dX);
	}
	
	public void changeRect(Rectangle r) {
		this.r.setBounds(r);
	}

	public void changeX(int x) {
		this.x = x;
	}
	
	public void changeY(int y) {
		this.y = y;
	}
	
	public void changeWidth(int width) {
		this.width = width;
	}
	
	public void changeHeight(int height) {
		this.height = height;
	}
	
	public void changeVelocity(int velocity) {
		this.velocity = velocity; 
	}
	
	public boolean collides(block b) {
		if (this.x >= b.x && this.x <= b.x + b.width) { 
			if (this.y + this.height >= b.y && this.y + this.height <= b.y + b.height) {
				return true; 
			}
		}
		return false; 
	}
	
	public void changeIfCollision(block b) {
		if (this.collides(b)) {
			this.y = b.y - b.height;
			this.velocity = 0; 
		}
	}
	
	public void createBlock(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(x, y, width, height);
		r.setBounds(x, y, width, height);;
//		g.fillPolygon(p);
//		r.setBounds(p.getBounds());
	}
	
}
