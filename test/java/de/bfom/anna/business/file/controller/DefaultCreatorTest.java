package de.bfom.anna.business.file.controller;

import de.bfom.anna.business.file.controller.Create.Create;
import de.bfom.anna.business.file.controller.Create.DefaultCreator;
import de.bfom.anna.business.file.controller.FileController;
import de.bfom.anna.business.file.entity.FileEntity;
import de.bfom.anna.business.file.entity.Transform.DefaultFileTransformer;
import de.bfom.anna.business.file.entity.Transform.FileTransformer;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DefaultCreatorTest {
    private EntityManagerFactory myfactory =Persistence.createEntityManagerFactory("MeineJpaPU");
    private FileController mycontroller = FileController.defaultinit(myfactory, null);
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
        mycontroller.delete(1);
        // file equality does not work
    }
}