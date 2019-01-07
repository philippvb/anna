package de.bfom.anna;

import de.bfom.anna.business.file.boundary.FileBoundary;
import de.bfom.anna.business.file.controller.*;
import de.bfom.anna.business.file.daos.*;

import javax.persistence.*;
import java.io.File;

public class Startup {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MeineJpaPU");

        FileSaver mysaver = new SaveFile(emf);
        FileTransformer mytransformer = new DefaultFileTransformer();
        FileController mycontroller = new FileController(emf, mysaver, mytransformer);

        FileBoundary myboundary = new FileBoundary(mycontroller);
        File testfile = new File("src/testfiles/test.txt");

        myboundary.save(testfile);
    }
}


/* to do:
    - Winterzeit anderes Format?
    - primary key generated oder auto-increment?

 */
