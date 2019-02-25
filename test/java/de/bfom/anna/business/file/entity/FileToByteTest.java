package de.bfom.anna.business.file.entity;

import de.bfom.anna.business.file.entity.Transform.FileToByte;
import org.junit.jupiter.api.Test;

import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

class FileToByteTest {

    @Test
    void tobyte(){
        File testfile = new File("src/testfiles/test.txt");
        assertEquals(byte[].class, FileToByte.tobyte(testfile).getClass());

    }

}