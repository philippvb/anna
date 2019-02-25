package de.bfom.anna;

import de.bfom.anna.business.file.boundary.FileBoundary;
import de.bfom.anna.business.file.controller.*;
import de.bfom.anna.gui.MainFrame;

import javax.persistence.*;
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
    }
}


/* to do:
    - handle if file is null

 */
