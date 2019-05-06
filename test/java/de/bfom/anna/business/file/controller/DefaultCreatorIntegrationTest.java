package de.bfom.anna.business.file.controller;

import de.bfom.anna.business.file.controller.Create.Create;
import de.bfom.anna.business.file.controller.Create.DefaultCreator;
import de.bfom.anna.business.file.entity.FileEntity;
import de.bfom.anna.business.file.entity.Transform.DefaultFileTransformer;
import de.bfom.anna.business.file.entity.Transform.FileTransformer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.io.File;


class DefaultCreatorIntegrationTest {
    EntityManagerFactory myfactory =Persistence.createEntityManagerFactory("MeineJpaPU");
    FileController mycontroller = FileController.defaultinit(myfactory, null);
    FileTransformer trans = new DefaultFileTransformer();
    Create creator = new DefaultCreator(myfactory);
    FileEntity testent = trans.transform(new File("src/testfiles/test.txt"));

    @BeforeEach
    @AfterEach
    void isEmpty(){
        assertTrue(mycontroller.retrieveAll().isEmpty());
    }

    @Test
    void save() {
        creator.save(testent);
        FileEntity retval = mycontroller.retrieveAll().get(0);
        assertTrue(testent.equalsWithOutId(retval));
        mycontroller.delete(retval.getId());
    }
}