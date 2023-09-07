package com.alura.hotel;

import javax.swing.JFrame;

import com.alura.hotel.views.MenuPrincipal;

public class Main {

	public static void main(String[] args) {
		MenuPrincipal menuPrincipalFrame = new MenuPrincipal();
		menuPrincipalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuPrincipalFrame.setVisible(true);

	}

}
