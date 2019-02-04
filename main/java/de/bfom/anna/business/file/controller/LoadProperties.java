package de.bfom.anna.business.file.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadProperties {

    public static Properties getProperties(String filename){
        Properties prop = new Properties();
        InputStream stream = null;

        try{
            stream = new FileInputStream(filename);
            prop.load(stream);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally{
            if(stream == null){
                try{
                    stream.close();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        return prop;
    }
}
