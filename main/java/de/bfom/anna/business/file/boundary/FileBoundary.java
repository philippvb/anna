package de.bfom.anna.business.file.boundary;

import de.bfom.anna.business.file.controller.FileController;
import de.bfom.anna.business.file.entity.FileEntity;
import de.bfom.anna.business.file.entity.ReducedFileEntity;
import de.bfom.anna.gui.MainFrame;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.File;
import java.util.List;

@Stateless
public class FileBoundary {

    @Inject
    private FileController mycontroller;

    @Inject
    private MainFrame mainframe;


    public void persist(File file) {
        mycontroller.persistOrUpdate(file);
    }

    public File retrieveFile(int id){
        return mycontroller.retrieveFile(id);
    }

    public List<ReducedFileEntity> retrieveAllReduced(){
        return mycontroller.retrieveAllReduced();
    }

    public boolean delete(int id){
        return mycontroller.delete(id);
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
