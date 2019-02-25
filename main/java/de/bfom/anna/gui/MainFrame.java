package de.bfom.anna.gui;

import de.bfom.anna.business.file.boundary.FileBoundary;
import de.bfom.anna.business.file.entity.Transform.DefaultFileTransformer;
import de.bfom.anna.business.file.entity.Transform.FileTransformer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class MainFrame implements ActionListener{
    private JFrame mainframe = new JFrame();
    private JTable table;
    private ReducedFileEntityTable reducedTable;
    private JScrollPane tablecontainer;
    private FileBoundary boundary;
    private JButton update;
    private JButton save;
    private FileTransformer transformer = new DefaultFileTransformer();
    final JFileChooser fc = new JFileChooser("src/testfiles");
    private int chooseFile;



    public void init(FileBoundary myboundary){
        this.boundary = myboundary;
        reducedTable = new ReducedFileEntityTable(myboundary.retrieveAllReduced());
        table = new JTable(reducedTable);
        table.setFillsViewportHeight(true);
        tablecontainer = new JScrollPane(table);
        update = new JButton("Update");
        update.addActionListener(this);
        save = new JButton("persist");
        save.addActionListener(this);

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(evt.getButton() == MouseEvent.BUTTON1){
                    int row = table.rowAtPoint(evt.getPoint());
                    openfile(getFileId(row));
                }
                else if(evt.getButton() == MouseEvent.BUTTON3) {
                    int choice = delete();
                    if (choice == JFileChooser.APPROVE_OPTION) {
                        boundary.delete(getFileId(table.rowAtPoint(evt.getPoint())));
                        update.doClick();
                    }
                }
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
            reducedTable = new ReducedFileEntityTable(boundary.retrieveAllReduced());
            table = new JTable(reducedTable);
            table.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if(evt.getButton() == MouseEvent.BUTTON1){
                        int row = table.rowAtPoint(evt.getPoint());
                        openfile(getFileId(row));
                    }
                    else if(evt.getButton() == MouseEvent.BUTTON3){
                        int choice = delete();
                        if(choice == JFileChooser.APPROVE_OPTION){
                            boundary.delete(getFileId(table.rowAtPoint(evt.getPoint())));
                            update.doClick();
                        }

                    }

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
                boundary.persist(selectedFile);
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
            d.open(transformer.transformToFile(boundary.retrieve(id)));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public int persistOrUpdate(){
        return JOptionPane.showOptionDialog(null,
                "The filename already exists. Would you like to override the existing file or create a new one? ",
                "Warning",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null,
                    new String[]{"Override", "Create new One"}, null);
    }

    public int delete(){
        return JOptionPane.showOptionDialog(null,
                "Would you like to delete this file?",
                "Warning",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null,
                new String[]{"YES", "No"}, null);
    }

    public int getFileId(int row){
        return reducedTable.getData().get(row).getID();
    }




}
