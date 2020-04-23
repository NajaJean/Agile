package controllers;

import UI.StartLoginPage;

public class App {
	
	public static void main(String[] args) {
		StartLoginPage view = new StartLoginPage();
		StartLoginPageController c = new StartLoginPageController(view);
		c.initController();
	}
	
}
