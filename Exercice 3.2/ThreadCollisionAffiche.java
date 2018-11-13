import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ThreadCollisionAffiche extends Thread{
	private JLabel lab = new JLabel();
	private int n;
	
	public ThreadCollisionAffiche(JLabel l, int n) {
		this.lab = l;
		this.n = n;
	}
	public void run() {
		try {
			this.lab.setText("Score : "+Integer.toString(n));
			sleep(2);
		}catch(InterruptedException e) {
			
		}
	}
}