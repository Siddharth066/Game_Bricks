package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;

public class MapGenerator {
	
	public int map[][];
	public int brickw,brickh;
	public MapGenerator(int row,int col) {
		map = new int[row][col];
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[0].length;j++) {
				map[i][j]=1;
			}
		}
		brickw=540/col;
		brickh=150/row;
	}
	public void draw(Graphics g) {
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[0].length;j++) {
				if(map[i][j]>0) {
					g.setColor(Color.WHITE);
					g.fillRect(j*brickw+80,i*brickh+50,brickw,brickh);
					
					g.setColor(Color.BLACK);
					g.drawRect(j*brickw+80,i*brickh+50,brickw,brickh);
				}
			}
		}
	}
	public void setBrick(int value,int row,int col) {
		map[row][col]=value;
	}
}
