package de.bfom.anna.business.file.controller;

import de.bfom.anna.business.file.controller.Retrieve.DefaultRetriever;
import de.bfom.anna.business.file.controller.Retrieve.Retrieve;
import de.bfom.anna.business.file.entity.FileEntity;
import de.bfom.anna.business.file.entity.ReducedFileEntity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.io.File;


class DefaultRetrieverIntegrationTest {
    EntityManagerFactory myfactory = Persistence.createEntityManagerFactory("MeineJpaPU");
    FileController mycontroller = null; //FileController.defaultinit(myfactory, null);
    FileEntity del;
    Retrieve ret = new DefaultRetriever(myfactory);

    @BeforeEach
    @AfterEach
    void init(){
        assertEquals(0, mycontroller.retrieveAll().size());
    }

    @Test
    void retrieve() {
        mycontroller.persist(new File("src/testfiles/test.txt"));
        assertTrue(mycontroller.retrieve(1) != null);
        mycontroller.delete(1);
    }

    @Test
    void retrieveByName(){
        mycontroller.persist(new File("src/testfiles/test.txt"));
        assertFalse(ret.retrieve("test").isEmpty());
    }

    @Test
    void retrieveAllReduced(){
        mycontroller.persist(new File("src/testfiles/test.txt"));
        mycontroller.persist(new File("src/testfiles/testpicture.jpg"));
        assertEquals(ReducedFileEntity.class, mycontroller.retrieveAllReduced().get(0).getClass());
        assertFalse(mycontroller.retrieveAllReduced().isEmpty());
        mycontroller.delete(mycontroller.retrieveAllReduced().get(0).getID());
        mycontroller.delete(mycontroller.retrieveAllReduced().get(0).getID());

    }
}