package com.artalgame.life;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Dimension2D;
import java.security.NoSuchAlgorithmException;

import javax.swing.JComponent;
import javax.swing.JPanel;

import sun.misc.Cleaner;

public class Field extends JPanel {
	public static final int DEFAULT_COUNT_ROW = 30;
	public static final int DEFAULT_COUNT_COLUMN = 30;
	public static final int MAX_ROW_COUNT = 150;
	public static final int MAX_COLUMN_COUNT = 150;
	
	private int rowCount,columnCount;
	private CellContainer cells;
	private Dimension cellSize;
	
	public Field() throws NoSuchAlgorithmException{
		super();
		setBackground(Color.BLACK);
		setParameters();
		super.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				try {
					clicked(event);
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			public void mousePressed(MouseEvent event){
				try {
					pressed(event);
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	
	public void setParameters() throws NoSuchAlgorithmException{
		setRowCount(DEFAULT_COUNT_ROW);
		setColumnCount(DEFAULT_COUNT_COLUMN);
		updateCellSize();
		cells = new CellContainer(getRowCount(), getColumnCount());
	}
	
	public void NextState() throws NoSuchAlgorithmException{
        CellContainer newCells = getNewContainer();
	    for(int i = 0; i < rowCount; i++)
		    for(int j = 0; j < columnCount; j++){
			    setState(i,j,newCells);
			}
	    cells = newCells;
	   }
	   
   
	
	public Dimension getFieldSize(){
		return getSize();
	}
	
	public int getRowCount(){
		return rowCount;
	}
	
	public int getColumnCount(){
		return columnCount;
	}
	
	public void updateSize(int rowCount,int columnCount) throws Exception{
		if(checkSize(rowCount,columnCount)){
			setColumnCount(columnCount);
			setRowCount(rowCount);
			updateCellSize();
			cells.updateSize(rowCount, columnCount);
			drawField();
		}
		else
		{
			throw new Exception("bad size of field. Count of row or count of column can be more than 0 and less or equal than 150");
		}
	}
	
	public void drawField() throws NoSuchAlgorithmException{
		this.clearField();
		Graphics g = getGraphics();
		if(g != null){
		    this.paintComponent(getGraphics());
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(g!=null){
		    drawCells(g);
		    drawVertical(g);
		    drawHorizontal(g);
		}
	}
	
	public void drawCells(Graphics g){
		for(int i = 0; i < getRowCount(); i++)
			for(int j = 0; j< getColumnCount(); j++){
				byte state = cells.getElement(i, j);
				if(state == 1){
					drawRect(i,j,g);
				}
				else{
					clearRect(i,j,g);
				}
			}
	}
	
	private boolean checkSize(int row, int column){
		if((row>MAX_ROW_COUNT)||(row<=0)||(column>MAX_COLUMN_COUNT)||(column<=0)){
			return false;
		}
		else{
			return true;
		}
	}
	
	private void clearField() throws NoSuchAlgorithmException{
		Graphics g = this.getGraphics();
		if(g!=null){
			g.clearRect(0, 0, getSize().width, getSize().height);
		}
	}
	
	public void clearAll() throws NoSuchAlgorithmException{
		clearField();
		cells = new CellContainer(rowCount,columnCount);
		this.paintComponent(getGraphics());
	}
	
	private CellContainer getNewContainer() throws NoSuchAlgorithmException{
	        return new CellContainer(getRowCount(), getColumnCount());
		}
		   
    private void setState(int row, int column,CellContainer newCells) throws NoSuchAlgorithmException{
		int state =cells.getElement(row, column);
	    int live = getCountOfLiveNeighbour(row, column);
	    if(state == 0){
	    	newCells.setElement(row, column, IsNoLiveCell(live));
	    }
	    else{
	    	newCells.setElement(row, column, IsLiveCell(live));
	    }
	}
    
    private byte IsLiveCell(int liveNeighbour){
    	if((liveNeighbour<2)||(liveNeighbour>3)){
    		return 0;
    	}
    	else
    		return 1;
    }
    
    private byte IsNoLiveCell(int liveNeighbour){
    	if(liveNeighbour == 3){
			return 1;
    }
    else
    	return 0;
    }
	    
    private int getCountOfLiveNeighbour(int row, int column){
	    int count = cells.getElement(row-1, column-1)+
	    		    cells.getElement(row-1, column)+
				    cells.getElement(row-1,column+1)+
				    cells.getElement(row,column-1)+
				    cells.getElement(row,column+1)+
				    cells.getElement(row+1,column-1)+
				    cells.getElement(row+1, column)+
				    cells.getElement(row+1, column+1);
		return count;
	   }	
	
	private void drawRect(int row,int column,Graphics g){
		g.setColor(Color.BLACK);
		g.drawRect(row*cellSize.width, column*cellSize.height, cellSize.width, cellSize.height);
	}
	
	private void clearRect(int row, int column, Graphics g){
		g.clearRect(row*cellSize.width, column*cellSize.height, cellSize.width, cellSize.height);
	}
	
	private void drawVertical(Graphics g){
		for(int i=0;i<=getRowCount();i++){
			g.drawLine(0, i*getCellSize().height, getFieldSize().width, i*getCellSize().height);
		}
	}
	
	private void drawHorizontal(Graphics g){
		for(int i=0;i<=getColumnCount();i++){
			g.drawLine(i*getCellSize().width, 0, i*getCellSize().width, getFieldSize().height);
		}
	}
	
	private Dimension getCellSize()
	{
		return cellSize;
	}

	private void setRowCount(int rowCount){
		this.rowCount = rowCount;
	}
	
	private void setColumnCount(int columnCount){
		this.columnCount = columnCount;
	}
	
	private void updateCellSize(){
		int width = 0;
		if(getColumnCount()>0){
			width = getFieldSize().width/getColumnCount();	
		}
		int height = 0;
		if(getRowCount()>0){
		    height = getFieldSize().height/getRowCount();
		}
		cellSize = new Dimension(width,height);
	}
	
	private Dimension getCellFromPixel(int x, int y){
		Dimension cell = new Dimension(x/cellSize.width, y/cellSize.height);
		return cell;
	}
	
	private void clicked(MouseEvent event) throws NoSuchAlgorithmException{
		int x = event.getX();
		int y = event.getY();
		Dimension cell = getCellFromPixel(x, y);
		//cells.swapElement(cell.width, cell.height);
		repaint();
	}
	
	private void pressed(MouseEvent event) throws NoSuchAlgorithmException{
		int x = event.getX();
		int y = event.getY();
		Dimension cell = getCellFromPixel(x, y);
		cells.setElement(cell.width, cell.height, (byte)1);
		repaint();
	}
	
	public String getHash(){
		return cells.getHash();
	}
}
