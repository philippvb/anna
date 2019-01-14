package de.bfom.anna;

import de.bfom.anna.business.file.boundary.FileBoundary;
import de.bfom.anna.business.file.controller.*;
import de.bfom.anna.business.file.daos.*;

import javax.activation.MimetypesFileTypeMap;
import javax.persistence.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Startup {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MeineJpaPU");

        FileController mycontroller = FileController.defaultinit(emf);

        FileBoundary myboundary = new FileBoundary(mycontroller);
        File testfile = new File("src/testfiles/test.txt");
        /*
        myboundary.save(testfile);
        Desktop d = Desktop.getDesktop();
        try{
            d.open(myboundary.retrieve(1));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        */
    }
}


/* to do:
    - Winterzeit anderes Format?
    - primary key generated oder auto-increment?
    +  transform von by[] to File suffiy with mimetype
    + name of FileEntity cut suffix
    - retrieve with other values than primary key? (maybe at front end??)
    - handle if file is null
    - Data field size too small for pictures, also mime field to small for some values

 */
