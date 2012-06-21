package com.artalgame.life;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Main extends JFrame 
                  implements ActionListener {

	/**
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

	private JButton hashButton;

	private JTextField hashTextField;
	
	private Timer timer;

	private JSlider jSlider0;
	
	private int speed;

	private JButton clearButton;

	private JLabel speedLabel;

	private JButton cycleButton;

	private JLabel countCycleLabel;
	
	public Main(){
		super();
		initComponents();
	}
	
	private void initComponents() {
		setLayout(null);
		add(getHeightSpinner());
		add(getWidthLabel());
		add(getHeightLabel());
		add(getResizeButton());
		add(getWidthSpinner());
		add(getHashTextField());
		add(getHashButton());
		add(getNextButton());
		add(getPauseButton());
		add(getPlayButton());
		add(getClearButton());
		add(getJSlider0());
		add(getJLabel0());
		add(getCycleButton());
		add(getJLabel0());
		add(getJLabel1());
		addWindowListener(new WindowAdapter() {
	
			public void windowOpened(WindowEvent event) {
				try {
					windowWindowOpened(event);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
			}
		});
		setSize(940, 650);
	}

	private JButton getClearButton() {
		if (clearButton == null) {
			clearButton = new JButton();
			clearButton.setText("Clear");
			clearButton.setBounds(817, 94, 81, 32);
			clearButton.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					try {
						clearButtonMouseMouseClicked(event);
					} catch (NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
		return clearButton;
	}

	private JLabel getJLabel1() {
		if (countCycleLabel == null) {
			countCycleLabel = new JLabel();
			countCycleLabel.setText("f");
			countCycleLabel.setBounds(827, 453, 41, 16);
		}
		return countCycleLabel;
	}

	private JButton getCycleButton() {
		if (cycleButton == null) {
			cycleButton = new JButton();
			cycleButton.setText("calculate cycle");
			cycleButton.setBounds(787, 364, 128, 40);
		}
		return cycleButton;
	}

	private JLabel getJLabel0() {
		if (speedLabel == null) {
			speedLabel = new JLabel();
			speedLabel.setText("d");
			speedLabel.setBounds(827, 423, 41, 16);
		}
		return speedLabel;
	}

	private JSlider getJSlider0() {
		if (jSlider0 == null) {
			jSlider0 = new JSlider();
			jSlider0.setBounds(863, 136, 68, 23);
			jSlider0.addChangeListener(new ChangeListener() {
	
				public void stateChanged(ChangeEvent event) {
					jSlider0ChangeStateChanged(event);
				}
			});
		}
		return jSlider0;
	}

	private JButton getHashButton() {
		if (hashButton == null) {
			hashButton = new JButton();
			hashButton.setText("hash");
			hashButton.setBounds(807, 316, 81, 26);
			hashButton.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					hashButtonMouseMouseClicked(event);
				}
			});
		}
		return hashButton;
	}

	private JTextField getHashTextField() {
		if (hashTextField == null) {
			hashTextField = new JTextField();
			hashTextField.setText("jTextField0");
			hashTextField.setBounds(0, 625, 940, 22);
		}
		return hashTextField;
	}

	private JButton getResizeButton() {
		if (resizeButton == null) {
			resizeButton = new JButton();
			resizeButton.setText("resize");
			resizeButton.setBounds(794, 60, 132, 22);
			try{
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
			catch(Exception ex){
				String s = ex.getMessage();
			}
		}
		return resizeButton;
	}

	private JButton getNextButton() {
		if (nextButton == null) {
			nextButton = new JButton();
			nextButton.setText("Next");
			nextButton.setBounds(810, 266, 81, 26);
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
		return nextButton;
	}

	private JButton getPauseButton() {
		if (pauseButton == null) {
			pauseButton = new JButton();
			pauseButton.setText("Pause");
			pauseButton.setBounds(810, 219, 81, 26);
			pauseButton.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					pauseButtonMouseMouseClicked(event);
				}
			});
		}
		return pauseButton;
	}

	private JButton getPlayButton() {
		if (playButton == null) {
			playButton = new JButton();
			playButton.setText("Play");
			playButton.setBounds(810, 172, 81, 26);
			playButton.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					playButtonMouseMouseClicked(event);
				}
			});
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
			field.updateSize(5, 5);
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
	
	private void showSizeErrorMessage(String message){
		JOptionPane.showConfirmDialog(this,  message, "Warning",
	              JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
	}
	
	private void nextButtonMouseMouseClicked(MouseEvent event) throws NoSuchAlgorithmException {
		field.NextState();
		field.repaint();
	}

	private void hashButtonMouseMouseClicked(MouseEvent event) {
		hashTextField.setText(field.getHash());
	}

	@Override
	/**This method is needed for timer that controls animation 
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		try {
			nextButtonMouseMouseClicked(null);
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void pauseButtonMouseMouseClicked(MouseEvent event) {
		if((timer!=null)){
			timer.stop();
		}
	}

	private void playButtonMouseMouseClicked(MouseEvent event) {
		updateTimer(speed*10);
		timer.start();
	}

	private void jSlider0ChangeStateChanged(ChangeEvent event) {
		boolean isRunning = false;
		if(timer!=null){
			isRunning = timer.isRunning();
		}
		speed = jSlider0.getValue();
		updateTimer(speed*10);
		if(isRunning){
			timer.start();
		}
	}
	private void updateTimer(int newSpeed){
		if(timer!=null){
			timer.stop();
		}
		timer = new Timer(newSpeed,this);
	}

	private void clearButtonMouseMouseClicked(MouseEvent event) throws NoSuchAlgorithmException {
		field.clearAll();
	}
}
