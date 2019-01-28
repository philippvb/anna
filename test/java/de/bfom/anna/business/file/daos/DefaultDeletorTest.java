package de.bfom.anna.business.file.daos;

import de.bfom.anna.business.file.controller.FileController;
import de.bfom.anna.business.file.entity.FileEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class DefaultDeletorTest {
    EntityManagerFactory myfactory;
    FileController mycontroller;
    FileEntity del;


    @BeforeEach
    void init(){
        myfactory = Persistence.createEntityManagerFactory("MeineJpaPU");
        mycontroller = FileController.defaultinit(myfactory, null);
    }

    @Test
    void delete() {
        // only works with empty table!!!
        mycontroller.persist(new File("src/testfiles/test.txt"));
        del = mycontroller.retrieve(1);
        assertTrue(del != null);
        assertTrue(mycontroller.delete(1));
        del = mycontroller.retrieve(1);
        assertTrue(del == null);
        assertFalse(mycontroller.delete(1));
    }
}