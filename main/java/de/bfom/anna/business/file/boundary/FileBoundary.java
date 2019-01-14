package de.bfom.anna.business.file.boundary;

import de.bfom.anna.business.file.controller.FileController;

import java.io.File;

public class FileBoundary {
    private FileController mycontroller;

    public FileBoundary(FileController mycontroller) {
        this.mycontroller = mycontroller;
    }

    public void save(File file) {
        mycontroller.persist(file);
    }

    public File retrieve(int id){
        return mycontroller.retrieveToFile(id);
    }

    public boolean delete(int id){
        return mycontroller.saveDeletion(id);
    }
}
