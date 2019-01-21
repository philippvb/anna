package de.bfom.anna.gui;

import de.bfom.anna.business.file.boundary.FileBoundary;

import javax.persistence.EntityManagerFactory;
import javax.swing.*;
import java.awt.event.ActionListener;

public class MainFrame {
    private JFrame mainframe = new JFrame();
    private JTable table;
    private EntityTable entityTable;
    private JScrollPane tablecontainer;
    private FileBoundary boundary;
    private JButton update;



    public void init(FileBoundary myboundary){
        this.boundary = myboundary;
        entityTable = new EntityTable(myboundary.getAll());
        table = new JTable(entityTable);
        table.setFillsViewportHeight(true);
        tablecontainer = new JScrollPane(table);
        update = new JButton("Update");

        mainframe.setTitle("test");
        mainframe.setTitle("Word Cloud");
        mainframe.setSize(1000, 620);
        mainframe.setResizable(false);
        mainframe.setLocation(50, 50);
        mainframe.getContentPane().setLayout(new java.awt.FlowLayout());
        mainframe.setVisible(true);

        mainframe.getContentPane().add(update);
        mainframe.getContentPane().add(tablecontainer);




    }




}
