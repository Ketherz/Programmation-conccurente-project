import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.*;

public class Panneau extends JPanel implements Runnable{
	ArrayList<Cercle> cercle = new ArrayList<Cercle>();
	private boolean affiche = true;
	public boolean pause;
	public int nb_collision = 0;
	public JLabel lab = new JLabel("Score : 0");

	public Panneau() {

		pause = false;
		for(int i=0; i<cercle.size();i++) {
			int x = (int)(Math.random()*(300-0)+1);
			int y = (int)(Math.random()*(300-0)+1);

			cercle.get(i).setX(x);
			cercle.get(i).setY(y);
		}
	}

	public Panneau(boolean statut) {
		pause = statut;
		if(!pause) notifyAll();
	}

	public void setPause(boolean p) {
		pause = p;
	}

	public boolean getPause() {
		return pause;
	}

	public boolean isPause() {
		return pause;
	}
	public void paintComponent(Graphics g) {

		/*Fond blanc*/
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		/*Couleur + dessin de la balle*/
		Color c = g.getColor();
		for(int i=0; i<cercle.size();i++) {
			g.setColor(cercle.get(i).getColor());
			cercle.get(i).draw(g);
			g.setColor(c);
		}
	}

	public void Collision() {
		int x = 0;
		int y = 0;
		int distance = 0;

		int savedI=0;
		int savedH=0;
		boolean collision = false;

		for(int i=0;i<cercle.size();i++) {
			for(int h=0;h<cercle.size();h++) {
				if(i != h) {
					x = cercle.get(i).getX() - cercle.get(h).getX();
					y = cercle.get(i).getY() - cercle.get(h).getY();
					distance = x*x + y*y;

					if(distance < (cercle.get(i).getRayon()/2 + cercle.get(h).getRayon())/2 * (cercle.get(i).getRayon()/2 + cercle.get(h).getRayon()/2)) {
						collision = true;
						savedI = i;
						savedH = h;
					}
				}
			}
		}
		if(collision == true) {
			cercle.remove(savedI);
			cercle.remove(savedH);
			collision = false;
			nb_collision++;
			ThreadCollisionAffiche p = new ThreadCollisionAffiche(lab,nb_collision);
			p.start();
			System.out.println(nb_collision);
		}
	}

	public synchronized void relaunch(){
		notifyAll();
	}


	public synchronized void run() {
		while(true) {
			try {
				while (pause){
					wait();
				}
				for(int i=0;i<cercle.size();i++) {
					cercle.get(i).move(this);

				}
				Collision();
				repaint();

				Thread.sleep(3);

			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
