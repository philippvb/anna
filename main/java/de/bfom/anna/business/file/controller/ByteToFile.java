package de.bfom.anna.business.file.controller;

import de.bfom.anna.business.file.entity.FileEntity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteToFile {

    public static File transform(FileEntity entity){
        FileOutputStream fos = null;
        File retfile = null;


        try{
            // add suffix with mimetype
            retfile = File.createTempFile(entity.getName(), ".txt");
            fos = new FileOutputStream(retfile);
            fos.write(entity.getFile());
            fos.flush();
        }
        catch(IOException e){
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
