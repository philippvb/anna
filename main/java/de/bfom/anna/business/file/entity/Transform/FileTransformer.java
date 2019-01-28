package de.bfom.anna.business.file.entity.Transform;

import de.bfom.anna.business.file.entity.FileEntity;

import java.io.File;

public interface FileTransformer {

    public FileEntity transform(File file);

    public File transformToFile(FileEntity entity);
}
