/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.music;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.sound.sampled.Clip;
import javax.swing.JLabel;
import javax.swing.JSlider;

 /**
* <h1>Id and Passwords</h1>
* This class counts playing time in the form of HH:mm:ss, while also updating the time slider 
* <p>
* @author  Austin Liu
* @version 19
* @since   2023-01-23
  **/
public class PlayingTimer extends Thread {
	private DateFormat dateFormater = new SimpleDateFormat("HH:mm:ss");	
	private boolean isRunning = false;
	private boolean isPause = false;
	private boolean isReset = false;
	private long startTime;
	private long pauseTime;
	
	private JLabel labelRecordTime;
	private JSlider slider;
	private Clip audioClip;
	
	public void setAudioClip(Clip audioClip) {
		this.audioClip = audioClip;
	}

	PlayingTimer(JLabel labelRecordTime, JSlider slider) {
		this.labelRecordTime = labelRecordTime;
		this.slider = slider;
	}
	
	public void run() {
		isRunning = true;
		
		startTime = System.currentTimeMillis();
		
		while (isRunning) {
			try {
				Thread.sleep(100);
				if (!isPause) {
					if (audioClip != null && audioClip.isRunning()) {
						labelRecordTime.setText(toTimeString());
						int currentSecond = (int) audioClip.getMicrosecondPosition() / 1_000_000; 
						slider.setValue(currentSecond);
					}
				} else {
					pauseTime += 100;
				}
			} catch (InterruptedException ex) {
				ex.printStackTrace();
				if (isReset) {
					slider.setValue(0);
					labelRecordTime.setText("00:00:00");
					isRunning = false;		
					break;
				}
			}
		}
	}
	
	
	
	 // Reset counting to "00:00:00"
	
	void reset() {
		isReset = true;
		isRunning = false;
	}
	
	void pauseTimer() {
		isPause = true;
	}
	
	void resumeTimer() {
		isPause = false;
	}
	
	/**
	 * Generate a String for time counter in the format of "HH:mm:ss"
	 * @return the time counter
	 */
	private String toTimeString() {
		long now = System.currentTimeMillis();
		Date current = new Date(now - startTime - pauseTime);
		dateFormater.setTimeZone(TimeZone.getTimeZone("GMT"));
		String timeCounter = dateFormater.format(current);
		return timeCounter;
	}
}