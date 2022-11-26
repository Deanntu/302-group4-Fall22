package com.gurup.timerbasedanimation;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class AnimatorMain {
	
	
    public static void main(String args[]) {
    	
       
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(); 
            }
        });
        
        
            
        //running timer task as daemon thread
        Timer timer = new Timer(true);
        System.out.println(Thread.currentThread().getName()+" TimerTask started");
        //cancel after sometime
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.cancel();
    }
    
    
    private static void createAndShowGUI() {
    	
    	System.out.println(Thread.currentThread().getName() +" Created GUI "+
    	        SwingUtilities.isEventDispatchThread());
    	
    	JFrame f = new JFrame("Swing Animation Demo");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
       
        BallControlledBySwing ball2 = new BallControlledBySwing(Color.blue, 20, 20, 450, 450);
    	f.add(new AnimationPanel(ball2));
    	
    	f.pack();
    	f.setVisible(true);
    	
    }

}
