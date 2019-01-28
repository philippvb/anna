package de.bfom.anna.business.file.daos;

import de.bfom.anna.business.file.boundary.FileBoundary;
import de.bfom.anna.business.file.controller.FileController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class DefaultCreatorTest {
    EntityManagerFactory myfactory;
    FileController mycontroller;

    @BeforeEach
    void init(){
        myfactory = Persistence.createEntityManagerFactory("MeineJpaPU");
        mycontroller = FileController.defaultinit(myfactory, null);
    }

    @Test
    void save() {
        assertEquals(null, mycontroller.retrieve(1));
        mycontroller.persist(new File("src/testfiles/test.txt"));
        assertTrue(mycontroller.retrieve(1) != null);
        mycontroller.saveDeletion(1);
        assertEquals(null, mycontroller.retrieve(1));
    }
}