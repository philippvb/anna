package de.bfom.anna;

import de.bfom.anna.business.file.boundary.FileBoundary;
import de.bfom.anna.business.file.controller.*;
import de.bfom.anna.gui.MainFrame;

import javax.persistence.*;
import java.io.File;
import java.util.Properties;

public class Startup {

    public static final Properties properties = LoadProperties.getProperties("src/main/resources/properties/config.properties");
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MeineJpaPU");
        MainFrame myframe = new MainFrame();
        FileBoundary myboundary = new FileBoundary(null, myframe);
        FileController mycontroller = FileController.defaultinit(emf, myboundary);
        myboundary.setController(mycontroller);
        mycontroller.retrieveAllReduced();
        myframe.init(myboundary);


        //File test = new File("C:\\Users\\Philipp Admin\\IdeaProjects\\anna\\src\\testfiles\\test.txt");

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
    + handle if file is null (eg in retrieve or if input is null)
 */
