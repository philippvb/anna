package de.bfom.anna.business.file.entity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;

@Stateless
public class ReducedFileEntity {

    @Inject
    FileEntity fileEntity;


    public ReducedFileEntity(){
    }

    public ReducedFileEntity(FileEntity fileEntity){
        this.fileEntity = fileEntity;
    }
    
    public int getID(){
        return fileEntity.getId();
    }

    public String getName(){
        return fileEntity.getName();
    }

    public String getMime(){
        return fileEntity.getMime();
    }

    public LocalDateTime getCreated(){
        return fileEntity.getCreated();
    }
}
