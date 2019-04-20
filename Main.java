package game;

import javax.swing.JFrame;

public class Main {
	
	public static void main(String args[]) {
		
		Game_Play game=new Game_Play();
		JFrame frame = new JFrame();
		frame.setBounds(10 ,10 ,700 ,600);
		frame.setTitle("Bricks");
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
		
	}

}
