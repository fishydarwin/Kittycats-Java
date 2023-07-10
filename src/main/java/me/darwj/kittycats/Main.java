package me.darwj.kittycats;

import me.darwj.kittycats.gui.MainMenu;

import javax.swing.*;

public class Main {
    public static void main(String[] args)
            throws UnsupportedLookAndFeelException,
            ClassNotFoundException,
            InstantiationException,
            IllegalAccessException
    {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        MainMenu mainMenu = new MainMenu();
        mainMenu.display();
    }
}