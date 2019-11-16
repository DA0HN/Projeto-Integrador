package br.edu.ifmt.cba.agenda.gui.utils;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ButtonEvent {

	public static boolean hasUserConfirmed(KeyEvent event) {
		return (ButtonEvent.hasEnterPressed(event) || ButtonEvent.hasSpacePressed(event) );
	}
	
	public static boolean hasEnterPressed(KeyEvent event) {
		return (event.getCode() == KeyCode.ENTER);
	}
	public static boolean hasSpacePressed(KeyEvent event) {
		return (event.getCode() == KeyCode.SPACE);
	}
}
