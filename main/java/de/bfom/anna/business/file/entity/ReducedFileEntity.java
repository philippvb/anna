package de.bfom.anna.business.file.entity;

import java.time.LocalDateTime;

public class ReducedFileEntity {
    FileEntity fileEntity;

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
