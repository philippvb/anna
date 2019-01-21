package de.bfom.anna;

import de.bfom.anna.business.file.boundary.FileBoundary;
import de.bfom.anna.business.file.controller.*;
import de.bfom.anna.gui.MainFrame;

import javax.persistence.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Startup {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MeineJpaPU");
        FileController mycontroller = FileController.defaultinit(emf);
        FileBoundary myboundary = new FileBoundary(mycontroller);
        File testfile = new File("src/testfiles/testpicture.jpg");
        File testfile2 = new File("src/testfiles/test.txt");
        MainFrame myframe = new MainFrame();
        myframe.init(myboundary);

        myboundary.save(testfile);
        myboundary.save(testfile2);


        /* myboundary.save(testfile);
        Desktop d = Desktop.getDesktop();
        try{
            d.open(myboundary.retrieve(1));
        }
        catch(IOException e){
            e.printStackTrace();
        } */

        //mycontroller.update(updatefile, 3);

        /*
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
    - is it possible that file does not exist and you use that update key?
    - handle merge exceptions if there are some?
    + Data field size too small for pictures, also mime field to small for some values
    + updating a file
    - maybe getting id back when persisting a fileentity

 */
