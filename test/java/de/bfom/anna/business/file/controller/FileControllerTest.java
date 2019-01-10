package de.bfom.anna.business.file.controller;

import de.bfom.anna.business.file.daos.FileSaver;
import de.bfom.anna.business.file.daos.RetrieveByID;
import de.bfom.anna.business.file.daos.SaveFile;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.sql.Blob;

import static org.junit.jupiter.api.Assertions.*;

class FileControllerTest {
    private EntityManagerFactory myfactory;
    private FileController mycontroller;
    private FileSaver mysaver;
    private FileTransformer mytransformer;
    private RetrieveByID myretriever;

    @BeforeAll
    void init(){
        myfactory = Persistence.createEntityManagerFactory("MeineJpaPU");
        mysaver = new SaveFile(myfactory);
        mytransformer = new DefaultFileTransformer();
        myretriever = new RetrieveByID(myfactory);
        mycontroller = new FileController(myfactory, mysaver, mytransformer, myretriever);
    }


    @Test
    void persist(Blob b) {
        //mycontroller.persist(b);
        // get data back and look if blob and timestamp is equal
    }
}