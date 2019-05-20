package de.bfom.anna.business.file.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.io.File;


class DefaultDeletorIntegrationTest {
    EntityManagerFactory myfactory = Persistence.createEntityManagerFactory("MeineJpaPU");
    FileController mycontroller = null; //FileController.defaultinit(myfactory, null);


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