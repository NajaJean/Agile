package controllers;

import UI.StartLoginPage;

public class App {
	 public static void main(String[] args) {
		  // Assemble all the pieces of the MVC
		  StartLoginPage v = new StartLoginPage();
		  StartLoginPageController c = new StartLoginPageController(v);
		  c.initController();
	 }
}
