package de.bfom.anna.business.file.controller;

import de.bfom.anna.business.file.controller.Create.Create;
import de.bfom.anna.business.file.controller.Delete.Delete;
import de.bfom.anna.business.file.controller.Retrieve.DefaultRetriever;
import de.bfom.anna.business.file.entity.Transform.FileTransformer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.sql.Blob;

class FileControllerTest {
    private EntityManagerFactory myfactory;
    private FileController mycontroller;
    private Create mysaver;
    private FileTransformer mytransformer;
    private DefaultRetriever myretriever;
    private Delete mydeleter;

    @BeforeAll
    void init(){
        myfactory = Persistence.createEntityManagerFactory("MeineJpaPU");

        mycontroller = FileController.defaultinit(myfactory, null);
    }


    @Test
    void persist(Blob b) {
        //mycontroller.persist(b);
        // get data back and look if blob and timestamp is equal
    }
}