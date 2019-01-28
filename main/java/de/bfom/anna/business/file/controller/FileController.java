package de.bfom.anna.business.file.controller;

import de.bfom.anna.business.file.daos.*;
import de.bfom.anna.business.file.entity.FileEntity;
import javax.persistence.*;
import java.io.File;
import java.util.List;


public class FileController{
    EntityManagerFactory myfactory;
    Create saver;
    FileTransformer transformer;
    Retrieve retriever;
    Delete deleter;
    Update updater;



    public FileController(EntityManagerFactory myfactory, Create saver, FileTransformer transformer,
                          DefaultRetriever retriever, Delete deleter, Update updater){
        this.myfactory = myfactory;
        this.saver = saver;
        this.transformer = transformer;
        this.retriever = retriever;
        this.deleter = deleter;
        this.updater = updater;
    }

    public static FileController defaultinit(EntityManagerFactory myfactory){
        Create mysaver = new DefaultCreator(myfactory);
        FileTransformer mytransformer = new DefaultFileTransformer();
        DefaultRetriever myretriever = new DefaultRetriever(myfactory);
        Delete mydeleter = new DefaultDeletor(myfactory);
        Update myupdater = new DefaultUpdate(myfactory);
        return new FileController(myfactory, mysaver, mytransformer, myretriever, mydeleter, myupdater);
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

    public void update(File file, int id){
        FileEntity toupdate = transformer.transform(file);
        toupdate.setId(id);
        updater.update(toupdate);

    }

    public List<FileEntity> retrieveAll(){
        return retriever.retrieveAll();
    }
}
