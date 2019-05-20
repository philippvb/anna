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
import de.bfom.anna.business.file.entity.Transform.ByteToFile;
import de.bfom.anna.business.file.entity.Transform.DefaultFileTransformer;
import de.bfom.anna.business.file.entity.Transform.FileTransformer;
import org.apache.commons.io.FilenameUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.*;
import java.io.File;
import java.util.List;



@Stateless
public class FileController{

    @Inject
    private FileBoundary myboundary;
    @Inject
    private Create creator;
    @Inject
    private FileTransformer transformer;
    @Inject
    private Retrieve retriever;
    @Inject
    public Delete deleter;
    @Inject
    private Update updater;




    public void persist(File file){
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
                tosave.setName(tosave.getName() + 1); // 1 is dummy value
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

    public File retrieveFile(int id){
        FileEntity retrieved = retriever.retrieve(id);
        return ByteToFile.transform(retrieved);
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
