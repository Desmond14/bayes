package pl.edu.agh.bayes;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				MainFrame frame = new MainFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
    }
}
