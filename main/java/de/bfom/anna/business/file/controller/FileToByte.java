package de.bfom.anna.business.file.controller;

import java.io.File;
import java.io.FileInputStream;

public class FileToByte {

    public static byte[] tobyte(File file){
        FileInputStream fileInputStream = null;
        byte[] bFile = new byte[(int) file.length()];
        try
        {
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return bFile;
    }

}
