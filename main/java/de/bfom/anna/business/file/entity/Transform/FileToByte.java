package de.bfom.anna.business.file.entity.Transform;

import javax.ejb.Stateless;
import java.io.File;
import java.io.FileInputStream;


@Stateless
public class FileToByte {

    public byte[] tobyte(File file){
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
