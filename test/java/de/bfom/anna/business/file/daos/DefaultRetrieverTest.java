package de.bfom.anna.business.file.daos;

import de.bfom.anna.business.file.controller.FileController;
import de.bfom.anna.business.file.controller.Retrieve.DefaultRetriever;
import de.bfom.anna.business.file.entity.FileEntity;
import de.bfom.anna.business.file.entity.ReducedFileEntity;
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
    DefaultRetriever ret;


    @BeforeEach
    void init(){
        myfactory = Persistence.createEntityManagerFactory("MeineJpaPU");
        mycontroller = FileController.defaultinit(myfactory, null);
        ret = new DefaultRetriever(myfactory);
    }

    @Test
    void retrieve() {
        assertEquals(null, mycontroller.retrieve(1));
        mycontroller.persist(new File("src/testfiles/test.txt"));
        assertTrue(mycontroller.retrieve(1) != null);
        mycontroller.delete(1);
        assertEquals(null, mycontroller.retrieve(1));
    }

    @Test
    void retrieveByName(){
        mycontroller.persist(new File("src/testfiles/test.txt"));
        mycontroller.persist(new File("src/testfiles/test.txt"));
        assertFalse(ret.retrieve("test").isEmpty());
    }

    @Test
    void retrieveAllReduced(){
        assertEquals(0, mycontroller.retrieveAll().size());
        mycontroller.persist(new File("src/testfiles/test.txt"));
        assertEquals(ReducedFileEntity.class, mycontroller.retrieveAllReduced().get(0).getClass());
        mycontroller.delete(1);

    }
}