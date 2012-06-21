package com.artalgame.life;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import sun.swing.MenuItemLayoutHelper.ColumnAlignment;

public class DrawCells extends JPanel {
 public int rowCount;
 public int columnCount;
 Dimension cellSize;
 
 public DrawCells(int rowCount, int columnCount){
	 super();
	 this.rowCount = rowCount;
	 this.columnCount = columnCount;
 }
 
 public void paintComponent(Graphics g){
	 super.paintComponent(g);
	 g.setColor(Color.RED);
	 this.setBackground(Color.BLACK);
	 cellSize = new Dimension(this.getSize().width/columnCount,this.getSize().height/rowCount);
	 for(int i=0;i<=rowCount;i++){
		 g.drawLine(0, i*cellSize.height, this.getSize().width, i*cellSize.height);
	 }
	 for(int i=0;i<=columnCount;i++){
		 g.drawLine(i*cellSize.width, 0, i*cellSize.width,this.getSize().height);
	 }
 }
}
