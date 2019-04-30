package de.bfom.anna.business.file.entity.Transform;

import de.bfom.anna.Startup;
import de.bfom.anna.business.file.entity.FileEntity;
import org.apache.tika.mime.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteToFile {

    public static File transform(FileEntity entity){
        FileOutputStream fos = null;
        File retfile = null;
        String extension = "";

        try{
            MimeTypes alltypes = MimeTypes.getDefaultMimeTypes();
            MimeType type = alltypes.forName(entity.getMime());
            extension = type.getExtension();

            retfile = new File(Startup.properties.getProperty("tempFolder"),entity.getName() + extension);
            fos = new FileOutputStream(retfile);
            fos.write(entity.getFile());
            fos.flush();
        }
        catch(IOException|MimeTypeException e){
            e.printStackTrace();
        }
        finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            }
            catch (IOException e) {
                System.out.println("Error in closing the Stream");
            }
        }
        return retfile;
    }
}
