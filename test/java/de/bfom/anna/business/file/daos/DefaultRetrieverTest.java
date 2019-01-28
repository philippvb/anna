package de.bfom.anna.business.file.daos;

import de.bfom.anna.business.file.controller.FileController;
import de.bfom.anna.business.file.entity.FileEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class DefaultRetrieverTest {
    EntityManagerFactory myfactory;
    FileController mycontroller;
    FileEntity del;


    @BeforeEach
    void init(){
        myfactory = Persistence.createEntityManagerFactory("MeineJpaPU");
        mycontroller = FileController.defaultinit(myfactory);
    }

    @Test
    void retrieve() {
        assertEquals(null, mycontroller.retrieve(1));
        mycontroller.persist(new File("src/testfiles/test.txt"));
        assertTrue(mycontroller.retrieve(1) != null);
        mycontroller.saveDeletion(1);
        assertEquals(null, mycontroller.retrieve(1));
    }
}