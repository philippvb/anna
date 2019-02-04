package de.bfom.anna.gui;

import de.bfom.anna.business.file.boundary.FileBoundary;
import de.bfom.anna.business.file.entity.Transform.DefaultFileTransformer;
import de.bfom.anna.business.file.entity.Transform.FileTransformer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
            reducedTable = new ReducedFileEntityTable(boundary.retrieveAllReduced());
            table = new JTable(reducedTable);
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
            System.out.println("Accessed");
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




}
