package de.bfom.anna.business.file.boundary;

import de.bfom.anna.business.file.controller.FileController;
import de.bfom.anna.business.file.entity.FileEntity;
import de.bfom.anna.gui.MainFrame;

import java.io.File;
import java.util.List;

public class FileBoundary {
    private FileController mycontroller;
    private MainFrame mainframe;

    public FileBoundary(FileController mycontroller, MainFrame mainframe) {
        this.mycontroller = mycontroller;
        this.mainframe = mainframe;
    }



    public void persist(File file) {
        mycontroller.persistOrUpdate(file);
    }

    public void forcedPersist(File file){
        mycontroller.persist(file);
    }


    public FileEntity retrieve(int id){
        return mycontroller.retrieve(id);
    }

    public List<FileEntity> retrieveAll(){
        return mycontroller.retrieveAll();
    }


    public boolean delete(int id){
        return mycontroller.delete(id);
    }


    public void update(File file, int id){
        mycontroller.update(file, id);
    }

    public int persistOrUpdate(){
        return mainframe.persistOrUpdate();
    }



    public void setMainframe(MainFrame mainframe){
        this.mainframe = mainframe;
    }
    public void setController(FileController controller){
        this.mycontroller = controller;
    }

}
