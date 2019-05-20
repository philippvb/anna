package de.bfom.anna.business.file.entity.Transform;

import de.bfom.anna.business.file.entity.FileEntity;
import org.apache.tika.Tika;
import org.apache.commons.io.FilenameUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@Stateless
public class DefaultFileTransformer implements FileTransformer {

    @Inject
    private FileToByte transformer;
    @Inject
    private ByteToFile transformerToByte;


     public FileEntity transform(File file){
         String namewithex = file.getName();
         String name = FilenameUtils.removeExtension(namewithex);
         String mime = "";
         Tika t = new Tika();
         try{
             mime = t.detect(file);
         }
         catch(IOException e) {
             e.printStackTrace();
         }

         byte[] bFile = transformer.tobyte(file);


         FileEntity transfile = FileEntity.newFileEntity()
                .name(name)
                .mime(mime)
                .created(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
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
