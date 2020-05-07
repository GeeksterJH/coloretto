package main;

import db.Database;
import javafx.application.Application;
import ui.GUIApplication;

class Startup {
	public static void main(String[] args) {
		Database.init();
		Application.launch(GUIApplication.class, args);
		Database.close();
	}
}
