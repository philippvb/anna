package de.bfom.anna.business.file.controller.Update;

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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DefaultUpdateIntegrationTest {
    EntityManagerFactory myfactory = Persistence.createEntityManagerFactory("MeineJpaPU");
    FileController mycontroller = FileController.defaultinit(myfactory, null);
    FileTransformer trans = new DefaultFileTransformer();
    Create creator = new DefaultCreator(myfactory);
    Update updater = new DefaultUpdate(myfactory);
    FileEntity textFile = trans.transform(new File("src/testfiles/test.txt"));
    FileEntity jpgFile = trans.transform(new File("src/testfiles/testpictrue.jpg"));


    @BeforeEach
    @AfterEach
    void isEmpty(){
        assertTrue(mycontroller.retrieveAll().isEmpty());
    }

    @Test
    public void updateTest(){
        creator.save(textFile);
        assertTrue(textFile.equalsWithOutId(mycontroller.retrieve(1)));
        jpgFile.setId(1);
        updater.update(jpgFile);
        assertFalse(textFile.equalsWithOutId(mycontroller.retrieve(1)));
        mycontroller.delete(1);
    }

}