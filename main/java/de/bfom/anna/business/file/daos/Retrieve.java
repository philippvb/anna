package de.bfom.anna.business.file.daos;

import de.bfom.anna.business.file.entity.FileEntity;

import java.util.List;

public interface Retrieve {

    public FileEntity retrieve(int id);

    public List<FileEntity> retrieveAll();
}
