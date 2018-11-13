import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ThreadNotPause extends Thread{
	Panneau pan;
	public ThreadNotPause(Panneau pan){
		this.pan = pan;
	}


	public void run(){
		while(true){
			System.out.println(pan.pause); //Attention! Si cette ligne est retirée, la pause ne marche plus! (pourquoi?)
			if(pan.pause == false){
					pan.relaunch();
			}
		}
	}
}
