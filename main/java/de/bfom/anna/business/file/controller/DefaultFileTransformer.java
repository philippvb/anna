package de.bfom.anna.business.file.controller;

import de.bfom.anna.business.file.entity.FileEntity;
import sun.misc.IOUtils;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.Calendar;

import de.bfom.anna.business.file.entity.*;

public class DefaultFileTransformer implements FileTransformer{

    private FileToByte transformer = new FileToByte();
    private ByteToFile transformerToByte = new ByteToFile();


     public FileEntity transform(File file){
         String name = file.getName();
         String mime = new MimetypesFileTypeMap().getContentType(file);
         byte[] bFile = transformer.tobyte(file);

         FileEntity transfile = FileEntity.newFileEntity()
                .name(name)
                .mime(mime)
                .created(LocalDateTime.now())
                .file(bFile)
                .build();
        return transfile;
    }

    public File transformToFile(FileEntity entity){
         File output;
         output = transformerToByte.transform(entity);
         return output;
     }



}
