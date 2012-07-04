package com.artalgame.life;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import javax.swing.JPanel;

public class Field extends JPanel implements Runnable {
	public static final int DEFAULT_COUNT_ROW = 30;
	public static final int DEFAULT_COUNT_COLUMN = 30;
	public static final int MAX_ROW_COUNT = 300;
	public static final int MAX_COLUMN_COUNT = 300;
	public static final int DEFAULT_FIELD_WIDTH = 600;
	public static final int DEFAULT_FIELD_HEIGHT = 600;
	public static final int DEFAULT_SPEED = 100;

	private int rowCount,columnCount;
	private CellContainer cells;
	private Dimension cellSize;

	private boolean isDrawing;
    private int speed;
	private int cycle;
	private int cycleStart;
    
	private void setMouseListenerForField(){
		super.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent event) {
				try {
					clicked(event);
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	
	}
	
	public Field()  throws NoSuchAlgorithmException{
		super();
		setBackground(Color.BLACK);
		setMouseListenerForField();
		setParameters();
	}

	public void setParameters() throws NoSuchAlgorithmException{
		setRowCount(DEFAULT_COUNT_ROW);
		setColumnCount(DEFAULT_COUNT_COLUMN);
		speed = DEFAULT_SPEED;
		updateCellSize();
		cells = new CellContainer(getRowCount(), getColumnCount());
	}

	public synchronized void nextState() throws NoSuchAlgorithmException{
		cells = nextState(cells);
		repaint();
	}
	
	public  CellContainer nextState(CellContainer oldCells) throws NoSuchAlgorithmException{
		CellContainer newCells = getNewContainer();
		for(int i = 0; i < rowCount; i++)
			for(int j = 0; j < columnCount; j++){
				setState(i,j,newCells,oldCells);
			}
		newCells.updateHash();
		return newCells;
	}

	public void calculateCycle() throws NoSuchAlgorithmException{
		CellContainer tempCells = cells;
		int step = 1;
		HashMap<String, Integer> hashs = new HashMap<>();
		do{
			hashs.put(tempCells.getHash(), step);
			tempCells = nextState(tempCells);
			step++;
		}
		while(!hashs.containsKey(tempCells.getHash()));

		int start = hashs.get(tempCells.getHash());
		cycle = step-start;
		cycleStart = start;
	}

	public int getCycle(){
		return cycle;
	}

	public int getCycleStart(){
		return cycleStart;
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

	public CellContainer getCellContainer(){
		return cells;
	}

	public void updateSize(int rowCount,int columnCount) throws Exception{
		if(checkSize(rowCount,columnCount)){
			setColumnCount(columnCount);
			setRowCount(rowCount);
			updateCellSize();
			cells.updateSize(rowCount, columnCount);
			setSize(columnCount*cellSize.width+1,rowCount*cellSize.height+1);
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

	private void drawCells(Graphics g){
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

	private void setState(int row, int column,CellContainer newCells, CellContainer oldCells) throws NoSuchAlgorithmException{
		int state =oldCells.getElement(row, column);
		int live = getCountOfLiveNeighbour(row, column,oldCells);
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

	private int getCountOfLiveNeighbour(int row, int column,CellContainer cells){
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
		g.drawRect(column*cellSize.width,row*cellSize.height, cellSize.width, cellSize.height);
	}

	private void clearRect(int row, int column, Graphics g){
		g.clearRect(column*cellSize.width,row*cellSize.height, cellSize.width, cellSize.height);
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
			width = DEFAULT_FIELD_WIDTH/getColumnCount();	
		}
		
		int height = 0;
		if(getRowCount()>0){
			height = DEFAULT_FIELD_HEIGHT/getRowCount();
		}
		int min = Math.min(width, height);
		cellSize = new Dimension(min,min);
	}

	private Dimension getCellFromPixel(int x, int y){
			Dimension cell = new Dimension(y/cellSize.height,x/cellSize.width );
			return cell;
	}

	private boolean checkCoordinate(int x, int y){
		if((x>cellSize.width*columnCount)||(y>cellSize.height*rowCount)){
			return false;
		}
		else{
			return true;
		}
	}
	
	private void clicked(MouseEvent event) throws NoSuchAlgorithmException{
		int x = event.getX();
		int y = event.getY();
		if(checkCoordinate(x,y)){
		    swapElementState(x, y);
		}
	}

	private void swapElementState(int mouseX, int mouseY) throws NoSuchAlgorithmException{
		Dimension cell = getCellFromPixel(mouseX, mouseY);
		cells.swapElement(cell.width, cell.height);
		repaint();
	}

	public String getHash(){
		return cells.getHash();
	}

	public void setSpeed(int speed){
		if(speed>0){
			this.speed = speed;
		}
	}

	public synchronized void switchOnDrawing(){
		isDrawing = true;
		notify();
	}

	public void switchOffDrawing(){
		isDrawing = false;
	}

	@Override
	public synchronized void run() {
		// TODO Auto-generated method stub
		try {
			while(true){
				nextState();
				Thread.sleep(speed);
				if(!isDrawing){
					wait();
				}
			}
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
