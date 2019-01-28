package de.bfom.anna.business.file.controller;



import de.bfom.anna.business.file.entity.FileEntity;
import de.bfom.anna.business.file.entity.Transform.DefaultFileTransformer;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ByteToFileTest {


    @Test
    void transformTest(){
        DefaultFileTransformer transformer = new DefaultFileTransformer();
        File testfile = new File("src/testfiles/test.txt");
        FileEntity entity = transformer.transform(testfile);
        File rettestfile = transformer.transformToFile(entity);

        assertEquals(File.class, rettestfile.getClass());
        assertTrue(rettestfile.length() != 0);

    }

}