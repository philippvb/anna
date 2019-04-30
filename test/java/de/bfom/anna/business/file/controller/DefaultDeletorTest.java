package de.bfom.anna.business.file.controller;

import de.bfom.anna.business.file.controller.FileController;
import de.bfom.anna.business.file.entity.FileEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class DefaultDeletorTest {
    private EntityManagerFactory myfactory =Persistence.createEntityManagerFactory("MeineJpaPU");
    private FileController mycontroller = FileController.defaultinit(myfactory, null);


    @BeforeEach
    @AfterEach
    void isEmpty(){
        assertTrue(mycontroller.retrieveAll().isEmpty());
    }

    @Test
    void delete() {
        mycontroller.persist(new File("src/testfiles/test.txt"));
        mycontroller.delete(mycontroller.retrieveAll().get(0).getId());
    }
}