package de.bfom.anna.gui;

import javax.swing.*;

public class MainFrame{
    JFrame mainframe = new JFrame();

    public void init(){
        mainframe.setTitle("test");
        mainframe.setTitle("Word Cloud");
        mainframe.setSize(1000, 620);
        mainframe.setResizable(false);
        mainframe.setLocation(50, 50);
        mainframe.setVisible(true);
    }

}
