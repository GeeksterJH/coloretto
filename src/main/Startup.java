package main;

import javafx.application.Application;
import ui.ConsoleApplication;
import ui.GUIApplication;

class Startup {
	public static void main(String[] args) {
		//ConsoleApplication.start();
		Application.launch(GUIApplication.class, args);
	}
}
