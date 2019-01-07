package de.bfom.anna.business.file.controller;

import de.bfom.anna.business.file.daos.FileSaver;
import de.bfom.anna.business.file.entity.FileEntity;
import javax.persistence.*;
import java.io.File;


public class FileController{
    EntityManagerFactory myfactory;
    FileSaver saver;
    FileTransformer transformer;

    public FileController(EntityManagerFactory myfactory, FileSaver saver, FileTransformer transformer){
        this.myfactory = myfactory;
        this.saver = saver;
        this.transformer = transformer;
    }

    public void persist(File file) throws RuntimeException{
        FileEntity tosave = transformer.transform(file);
        saver.save(tosave);
    }

}
