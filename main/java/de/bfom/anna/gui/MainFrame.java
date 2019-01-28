package de.bfom.anna.gui;

import de.bfom.anna.business.file.boundary.FileBoundary;
import de.bfom.anna.business.file.controller.ByteToFile;
import de.bfom.anna.business.file.controller.DefaultFileTransformer;
import de.bfom.anna.business.file.controller.FileTransformer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MainFrame implements ActionListener{
    private JFrame mainframe = new JFrame();
    private JTable table;
    private EntityTable entityTable;
    private JScrollPane tablecontainer;
    private FileBoundary boundary;
    private JButton update;
    private JButton save;
    private FileTransformer transformer = new DefaultFileTransformer();
    final JFileChooser fc = new JFileChooser("P:/Dokumente");
    private int chooseFile;



    public void init(FileBoundary myboundary){
        this.boundary = myboundary;
        entityTable = new EntityTable(myboundary.getAll());
        table = new JTable(entityTable);
        table.setFillsViewportHeight(true);
        tablecontainer = new JScrollPane(table);
        update = new JButton("Update");
        update.addActionListener(this);
        save = new JButton("save");
        save.addActionListener(this);

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                openfile(row);
            }
        });

        mainframe.setTitle("test");
        mainframe.setTitle("Word Cloud");
        mainframe.setSize(1000, 620);
        mainframe.setResizable(false);
        mainframe.setLocation(50, 50);
        mainframe.getContentPane().setLayout(new java.awt.FlowLayout());
        mainframe.setVisible(true);

        mainframe.getContentPane().add(update);
        mainframe.getContentPane().add(save);
        mainframe.getContentPane().add(tablecontainer);
    }

    // bad Style
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == update){
            mainframe.getContentPane().remove(tablecontainer);
            entityTable = new EntityTable(boundary.getAll());
            table = new JTable(entityTable);
            table.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    int row = table.rowAtPoint(evt.getPoint());
                    openfile(row);
                }
            });
            tablecontainer = new JScrollPane(table);
            mainframe.getContentPane().add(tablecontainer);
            this.refresh();
        }
        if(e.getSource() == save){
            chooseFile = fc.showSaveDialog(null);
            if (chooseFile == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fc.getSelectedFile();
                System.out.println(selectedFile.getAbsolutePath());
                boundary.save(selectedFile);
                update.doClick();
            }
        }



    }


    public void refresh(){
        mainframe.invalidate();
        mainframe.validate();
        mainframe.repaint();
    }

    public void openfile(int id){
        Desktop d = Desktop.getDesktop();
        try{
            d.open(transformer.transformToFile(entityTable.getEntity(id)));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }




}
