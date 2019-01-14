package de.bfom.anna.business.file.daos;


import de.bfom.anna.business.file.entity.FileEntity;

public interface FileDeleter {
    public boolean delete(int id);
}
