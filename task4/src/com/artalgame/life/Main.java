package com.artalgame.life;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.security.NoSuchAlgorithmException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Main extends JFrame 
                  implements Runnable {
	/**
	 * Program begins here
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main window = new Main();
		window.setVisible(true);
	}
	
	private Field field;
	
	private JSpinner heightSpinner;
	
	private JSpinner widthSpinner;
	
	private JLabel heightLabel;

	private JLabel widthLabel;

	private JButton playButton;

	private JButton pauseButton;

	private JButton nextButton;

	private JButton resizeButton;

	private JButton clearButton;

	private JLabel startCycleLabel;

	private JButton cycleButton;

	private JLabel countCycleLabel;
	
	private Thread cycleCalculatingThread;
	
	private Thread drawThread;

	private JSlider speedSlider;
	
	public Main(){
		super();
		initComponents();
	}
	
	private void addLabels()
	{
		add(getWidthLabel());
		add(getHeightLabel());
		add(getStartCycleLabel());
	    add(getCountCycleLabel());
	}
	
	private void addButtons(){
		add(getNextButton());
		add(getPauseButton());
		add(getPlayButton());
		add(getCycleButton());
		add(getResizeButton());
		add(getClearButton());
	}
	
	private void addSpiners(){
		add(getHeightSpinner());
		add(getWidthSpinner());
	}
	
	private void addSliders(){
		add(getSpeedSlider());
	}
	
	private void  setWindowListener(){
		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent event) {
				try {
					windowWindowOpened(event);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void initComponents() {
		setLayout(null);
		addButtons();
		addSliders();
		addSpiners();
		addLabels();
		setWindowListener();
		setSize(940, 650);
	}

	private JSlider getSpeedSlider() {
		if (speedSlider == null) {
			speedSlider = new JSlider();
			speedSlider.setBounds(787, 134, 150, 16);
			speedSlider.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent event) {
					speedSliderChangeStateChanged(event);
				}
			});
		}
		return speedSlider;
	}

	private void setMouseListenerForClearButton(){
		clearButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				try {
					clearButtonMouseMouseClicked(event);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private JButton getClearButton() {
		if (clearButton == null) {
			clearButton = new JButton();
			clearButton.setText("Clear");
			clearButton.setBounds(816, 94, 81, 32);
			setMouseListenerForClearButton();
		}
		return clearButton;
	}
	
	private JLabel getCountCycleLabel() {
		if (countCycleLabel == null) {
			countCycleLabel = new JLabel();
			countCycleLabel.setBounds(793, 455, 116, 16);
		}
		return countCycleLabel;
	}

	private void setMouseListenerForCycleButton(){
		cycleButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				cycleButtonMouseMouseClicked(event);
			}
		});
	}
	
	private JButton getCycleButton() {
		if (cycleButton == null) {
			cycleButton = new JButton();
			cycleButton.setText("calculate cycle");
			cycleButton.setBounds(787, 364, 128, 40);
			setMouseListenerForCycleButton();
		}
		return cycleButton;
	}

	private JLabel getStartCycleLabel() {
		if (startCycleLabel == null) {
			startCycleLabel = new JLabel();
			startCycleLabel.setBounds(789, 427, 126, 16);
		}
		return startCycleLabel;
	}
    
	private void setMouseListenerForResizeButton(){
		resizeButton.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent event) {
			try {
				resizeButtonMouseMouseClicked(event);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	});
	}
	
	private JButton getResizeButton() {
		if (resizeButton == null) {
			resizeButton = new JButton();
			resizeButton.setText("resize");
			resizeButton.setBounds(794, 60, 132, 22);
			setMouseListenerForResizeButton();
		}
		return resizeButton;
	}
    
	private void setMouseListenerForNextButton(){
		nextButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				try {
					nextButtonMouseMouseClicked(event);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
			}
		});

	}
	
	private JButton getNextButton() {
		if (nextButton == null) {
			nextButton = new JButton();
			nextButton.setText("Next");
			nextButton.setBounds(810, 266, 81, 26);
			setMouseListenerForNextButton();
		}
		return nextButton;
	}
    
	private void setMouseListenerForPauseButton(){
		pauseButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				pauseButtonMouseMouseClicked(event);
			}
		});
	}
	
	private JButton getPauseButton() {
		if (pauseButton == null) {
			pauseButton = new JButton();
			pauseButton.setText("Pause");
			pauseButton.setBounds(810, 219, 81, 26);
			setMouseListenerForPauseButton();
		}
		return pauseButton;
	}

	private void setMouseListenerForPlayButton(){
		playButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				playButtonMouseMouseClicked(event);
			}
		});
	}
	
	private JButton getPlayButton() {
		if (playButton == null) {
			playButton = new JButton();
			playButton.setText("Play");
			playButton.setBounds(810, 172, 81, 26);
			setMouseListenerForPlayButton();
		}
		return playButton;
	}

	private JLabel getWidthLabel() {
		if (widthLabel == null) {
			widthLabel = new JLabel();
			widthLabel.setText("Width");
			widthLabel.setBounds(801, 15, 41, 16);
		}
		return widthLabel;
	}

	private JSpinner getHeightSpinner() {
		if (heightSpinner == null) {
			heightSpinner = new JSpinner();
			heightSpinner.setBounds(865, 37, 60, 20);
		}
		return heightSpinner;
	}

	private JLabel getHeightLabel() {
		if (heightLabel == null) {
			heightLabel = new JLabel();
			heightLabel.setText("Heigth");
			heightLabel.setBounds(797, 36, 41, 20);
		}
		return heightLabel;
	}

	private JSpinner getWidthSpinner() {
		if (widthSpinner == null) {
			widthSpinner = new JSpinner();
			widthSpinner.setBounds(865, 13, 61, 20);
		}
		return widthSpinner;
	}

	private void windowWindowOpened(WindowEvent event) throws NoSuchAlgorithmException {
		field = new Field();
		field.setBounds(0, 0, 601, 601);
		try{
			field.updateSize(50, 50);
		}
		catch(Exception ex){
			showSizeErrorMessage(ex.getMessage());
		}
		this.add(field);
	}
	
	
	private void resizeButtonMouseMouseClicked(MouseEvent event) throws NoSuchAlgorithmException {
		try{
		    int row = (int)widthSpinner.getValue();
		    int column = (int)heightSpinner.getValue();
			field.updateSize(row, column);
		}
		catch(Exception ex){
			showSizeErrorMessage(ex.getMessage());
		}
		
	}
	
	private void drawingThreadInicialize(){
		if(drawThread ==null){
		    drawThread = new Thread(field,"draw throw");
		    drawThread.start();
		}
	}
	
	private void showSizeErrorMessage(String message){
		JOptionPane.showConfirmDialog(this,  message, "Warning",
	              JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
	}
	
	private void nextButtonMouseMouseClicked(MouseEvent event) throws NoSuchAlgorithmException {
		field.nextState();
	}
	
	private void pauseButtonMouseMouseClicked(MouseEvent event) {
	    field.switchOffDrawing();
	    setEnabledForControls(true);
	}

	private synchronized void playButtonMouseMouseClicked(MouseEvent event) {
		drawingThreadInicialize();
		setEnabledForControls(false);	
		field.switchOnDrawing();
	}
	
	private void setEnabledForControls(boolean isEnabled){
		resizeButton.setEnabled(isEnabled);
		clearButton.setEnabled(isEnabled);
		nextButton.setEnabled(isEnabled);
		playButton.setEnabled(isEnabled);
		getCycleButton().setEnabled(isEnabled);
	}

	private void clearButtonMouseMouseClicked(MouseEvent event) throws NoSuchAlgorithmException {
		field.clearAll();
	}

	@Override
	public void run() {
		try {
			calculateCycle();
		} 
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	private void calculateCycle() throws NoSuchAlgorithmException{
		startCycleLabel.setText("cycle start: Loading...");
		countCycleLabel.setText("cycle: Loading...");
		field.calculateCycle();
		startCycleLabel.setText("cycle start:"+field.getCycleStart());
		countCycleLabel.setText("cycle:"+field.getCycle());
	}

	private void cycleButtonMouseMouseClicked(MouseEvent event) {
		setNewCycleThread();
		cycleCalculatingThread.start();
	}
	
	private void setNewCycleThread(){
		if(cycleCalculatingThread != null){
			cycleCalculatingThread.interrupt();
		}	
		cycleCalculatingThread = new Thread(this,"calculate cycle");
		
	}

	private void speedSliderChangeStateChanged(ChangeEvent event) {
		field.setSpeed(speedSlider.getValue()*10);
	}
	
}
