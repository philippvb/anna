package de.bfom.anna.business.file.controller;

import de.bfom.anna.business.file.daos.FileSaver;
import de.bfom.anna.business.file.daos.RetrieveByID;
import de.bfom.anna.business.file.entity.FileEntity;
import javax.persistence.*;
import java.io.File;


public class FileController{
    EntityManagerFactory myfactory;
    FileSaver saver;
    FileTransformer transformer;
    RetrieveByID retriever;

    public FileController(EntityManagerFactory myfactory, FileSaver saver, FileTransformer transformer, RetrieveByID retriever){
        this.myfactory = myfactory;
        this.saver = saver;
        this.transformer = transformer;
        this.retriever = retriever;
    }

    public void persist(File file) throws RuntimeException{
        FileEntity tosave = transformer.transform(file);
        saver.save(tosave);
    }

    public File retrieveById(int id){
        FileEntity retentity = retriever.retrieve(id);
        File retfile = null;

        if(retentity == null){
            return retfile;
        }
        else{
            retfile = transformer.transformToFile(retentity);
            return retfile;
        }

    }

}
