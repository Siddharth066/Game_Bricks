package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import javax.swing.JPanel;

public class Game_Play extends JPanel implements KeyListener, ActionListener {
	
	private boolean play=false;
	private int scores=0,c=0;
	
	private int totalbricks=21;
	
	private Timer timer;
	private int delay=8;
	
	private int playerX=310;
	private boolean pow=false;
	private int ballposX=120;
	private int ballposY=350;
	private int ballxdir=-2;
	private int ballydir=-3;
	
	private int rn=(int)(Math.random()*10);
	
	private MapGenerator map;
	
	public Game_Play() {
		
		map = new MapGenerator(3,7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		
		if(totalbricks%rn==0 && !pow) {
			pow=true;
			c=1;
		}
		else
		if(c<0){
			pow = false;
		}
		//background
		g.setColor(Color.BLACK);
		g.fillRect(1, 1, 692, 592);
		
		//drawing map
		map.draw((Graphics2D)g);
		
		//borders
		g.setColor(Color.YELLOW);
		g.fillRect(0,0,3,592);
		g.fillRect(0,0,692,3);
		g.fillRect(691,0,3,592);
		
		//Scores
		g.setColor(Color.WHITE);
		g.setFont(new Font("serif",Font.BOLD,25));
		g.drawString(""+scores, 590, 30);
		
		//the paddle
		if(pow) {
			g.setColor(Color.WHITE);
		}
		else {
			g.setColor(Color.GREEN);
		}
		g.fillRect(playerX, 550, 100, 8);
		
		//the ball
		if(pow) {
			g.setColor(Color.RED);
		}
		else {
			g.setColor(Color.YELLOW);
		}
		g.fillOval(ballposX, ballposY, 20, 20);
		
		if(totalbricks<=0) {
			play=false;
			ballxdir=0;
			ballydir=0;
			g.setColor(Color.GREEN);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("You Won, Scores:", 190, 300);
			
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("Press Enter to Restart", 230, 350);
		}
		
		if(ballposY>570) {
			play=false;
			ballxdir=0;
			ballydir=0;
			g.setColor(Color.RED);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("Game Over, Scores:", 190, 300);
			
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("Press Enter to Restart", 230, 350);
		}
		
		g.dispose();
		
	}
		
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		timer.start();
		if(play) {
			if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
				ballydir=-ballydir;
				if(pow) {
				c--;
				}
			}
			
			A:for(int i=0;i<map.map.length;i++) {
				for(int j=0;j<map.map[0].length;j++) {
					if(map.map[i][j]>0) {
						int x=j*map.brickw+80;
						int y=i*map.brickh+50;
						int w=map.brickw;
						int h=map.brickh;
						
						Rectangle rect=new Rectangle(x,y,w,h);
						Rectangle brect=new Rectangle(ballposX,ballposY,20,20);
						Rectangle brrect=rect;
						
						if(brect.intersects(brrect)) {
							map.setBrick(0, i, j);
							totalbricks--;
							scores+=5;
							if(!pow) {
							if(ballposX+19 <= brrect.x || ballposX+1 >=brrect.x + brrect.width) {
								ballxdir=-ballxdir;
							}
							else {
								ballydir=-ballydir;
							}
							}
							break A;
						}
					}
				}
			}
			
			ballposX+=ballxdir;
			ballposY+=ballydir;
			if(ballposX<0) {
				ballxdir=-ballxdir;
			}
			if(ballposX>670) {
				ballxdir=-ballxdir;
			}
			if(ballposY<0) {
				ballydir=-ballydir;
			}
		}
		
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(playerX>=600) {
				playerX=600;
			}
			else {
				moveright();
			}
			
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				if(playerX<=10) {
					playerX=10;
				}
				else {
					moveleft();
				}
			}
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			if(!play) {
				play=true;
				ballposX=120;
				ballposY=350;
				ballxdir=-2;
				ballydir=-3;
				playerX=310;
				scores=0;
				totalbricks=21;
				map=new MapGenerator(3,7);
				pow=false;
			}
			
		}
		}
	public void moveright() {
		play=true;
		playerX+=20;
	}
	public void moveleft() {
		play=true;
		playerX-=20;
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

}
