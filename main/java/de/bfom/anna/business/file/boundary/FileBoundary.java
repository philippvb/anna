package de.bfom.anna.business.file.boundary;

import de.bfom.anna.business.file.controller.FileController;
import de.bfom.anna.business.file.entity.FileEntity;
import de.bfom.anna.gui.MainFrame;

import javax.swing.*;
import java.io.File;
import java.util.List;

public class FileBoundary {
    private FileController mycontroller;
    private MainFrame mainframe;

    public FileBoundary(FileController mycontroller, MainFrame mainframe) {
        this.mycontroller = mycontroller;
        this.mainframe = mainframe;
    }

    public void save(File file) {
        mycontroller.saveOrUpdate(file);
    }

    public void forceSave(File file){
        mycontroller.persist(file);
    }

    public File retrieve(int id){
        return mycontroller.retrieveToFile(id);
    }

    public boolean delete(int id){
        return mycontroller.saveDeletion(id);
    }

    public void update(File file, int id){
        mycontroller.update(file, id);
    }

    public List<FileEntity> getAll(){
        return mycontroller.retrieveAll();
    }

    public int saveOrUpdate(){
        return mainframe.saveOrUpdate();
    }

    public void setController(FileController controller){
        this.mycontroller = controller;
    }

}
