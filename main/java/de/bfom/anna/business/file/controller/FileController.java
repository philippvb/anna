package de.bfom.anna.business.file.controller;

import de.bfom.anna.business.file.daos.*;
import de.bfom.anna.business.file.entity.FileEntity;
import javax.persistence.*;
import java.io.File;


public class FileController{
    EntityManagerFactory myfactory;
    Create saver;
    FileTransformer transformer;
    RetrieveByID retriever;
    Delete deleter;

    public FileController(EntityManagerFactory myfactory, Create saver, FileTransformer transformer, RetrieveByID retriever, Delete deleter){
        this.myfactory = myfactory;
        this.saver = saver;
        this.transformer = transformer;
        this.retriever = retriever;
        this.deleter = deleter;
    }

    public static FileController defaultinit(EntityManagerFactory myfactory){
        Create mysaver = new CreateFile(myfactory);
        FileTransformer mytransformer = new DefaultFileTransformer();
        RetrieveByID myretriever = new RetrieveByID(myfactory);
        Delete mydeleter = new DeleteFile(myfactory);
        return new FileController(myfactory, mysaver, mytransformer, myretriever, mydeleter);
    }

    public void persist(File file) throws RuntimeException{
        FileEntity tosave = transformer.transform(file);
        saver.save(tosave);
    }

    public File retrieveToFile(int id){
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

    public FileEntity retrieve(int id){
        return retriever.retrieve(id);
    }

    public boolean saveDeletion(int id){
        return deleter.delete(id);
    }

}
