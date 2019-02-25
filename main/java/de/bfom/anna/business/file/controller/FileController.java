package de.bfom.anna.business.file.controller;

import de.bfom.anna.business.file.boundary.FileBoundary;
import de.bfom.anna.business.file.controller.Create.Create;
import de.bfom.anna.business.file.controller.Create.DefaultCreator;
import de.bfom.anna.business.file.controller.Delete.DefaultDeletor;
import de.bfom.anna.business.file.controller.Delete.Delete;
import de.bfom.anna.business.file.controller.Retrieve.DefaultRetriever;
import de.bfom.anna.business.file.controller.Retrieve.Retrieve;
import de.bfom.anna.business.file.controller.Update.DefaultUpdate;
import de.bfom.anna.business.file.controller.Update.Update;
import de.bfom.anna.business.file.entity.FileEntity;
import de.bfom.anna.business.file.entity.ReducedFileEntity;
import de.bfom.anna.business.file.entity.Transform.DefaultFileTransformer;
import de.bfom.anna.business.file.entity.Transform.FileTransformer;
import org.apache.commons.io.FilenameUtils;

import javax.persistence.*;
import java.io.File;
import java.util.List;


public class FileController{
    private EntityManagerFactory myfactory;
    private FileBoundary myboundary;
    private Create creator;
    private FileTransformer transformer;
    private Retrieve retriever;
    private Delete deleter;
    private Update updater;




    public FileController(EntityManagerFactory myfactory, Create creator, FileTransformer transformer,
                          DefaultRetriever retriever, Delete deleter, Update updater, FileBoundary myboundary){
        this.myfactory = myfactory;
        this.creator = creator;
        this.transformer = transformer;
        this.retriever = retriever;
        this.deleter = deleter;
        this.updater = updater;
        this.myboundary = myboundary;
    }

    public static FileController defaultinit(EntityManagerFactory myfactory, FileBoundary myboundary){
        Create mysaver = new DefaultCreator(myfactory);
        FileTransformer mytransformer = new DefaultFileTransformer();
        DefaultRetriever myretriever = new DefaultRetriever(myfactory);
        Delete mydeleter = new DefaultDeletor(myfactory);
        Update myupdater = new DefaultUpdate(myfactory);
        return new FileController(myfactory, mysaver, mytransformer, myretriever, mydeleter, myupdater, myboundary);
    }



    public void persist(File file) throws RuntimeException{
        FileEntity tosave = transformer.transform(file);
        creator.save(tosave);
    }

    public void persistOrUpdate(File file){
        FileEntity tosave = transformer.transform(file);
        List<FileEntity> results = retriever.retrieve(FilenameUtils.removeExtension(file.getName()));
        if(results.isEmpty()){
            creator.save(tosave);
        }
        else{
            int decision = myboundary.persistOrUpdate();
            if(decision == 0){
                tosave.setId(results.get(0).getId());
                updater.update(tosave);
            }
            else if(decision == 1){
                tosave.setName(tosave.getName() + 1);
                creator.save(tosave);
            }
        }
    }


    public void update(File file, int id){
        FileEntity toupdate = transformer.transform(file);
        toupdate.setId(id);
        updater.update(toupdate);

    }


    public FileEntity retrieve(int id){
        return retriever.retrieve(id);
    }

    public List<FileEntity> retrieveAll(){
        return retriever.retrieveAll();
    }

    public List<ReducedFileEntity> retrieveAllReduced(){
        return retriever.retrieveAllReduced();
    }


    public boolean delete(int id){
        return deleter.delete(id);
    }


}
