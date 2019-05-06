package de.bfom.anna;

import de.bfom.anna.business.file.boundary.FileBoundary;
import de.bfom.anna.business.file.controller.*;
import de.bfom.anna.gui.MainFrame;

import javax.persistence.*;
import java.io.File;
import java.util.Properties;

public class Startup {
    public static EntityManagerFactory emf;
    public static MainFrame myframe;
    public static FileBoundary myboundary;
    public static FileController mycontroller;

    public static final Properties properties = LoadProperties.getProperties("src/main/resources/properties/config.properties");
    public static void main(String[] args) {

        emf = Persistence.createEntityManagerFactory("MeineJpaPU");
        myframe = new MainFrame();
        myboundary = new FileBoundary(null, myframe);
        mycontroller = FileController.defaultinit(emf, myboundary);
        myboundary.setController(mycontroller);
        mycontroller.retrieveAllReduced();
        myframe.init(myboundary);


    }


    public static void cleanUpTemp(){
        File folder = new File(properties.getProperty("tempFolder"));
        File[] listOfFiles = folder.listFiles();
        for(File f:listOfFiles){
            if(!f.delete()) {
                System.out.println("Failed to delete" + f.getName());
            }
        }
    }
}


/* to do:

 */
