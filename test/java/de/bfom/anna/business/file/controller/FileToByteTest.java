package de.bfom.anna.business.file.controller;

import de.bfom.anna.business.file.entity.Transform.FileToByte;
import org.junit.jupiter.api.Test;

import java.io.File;

class FileToByteTest {

    @Test
    void tobyte(){
        FileToByte transformer = new FileToByte();
        File testfile = new File("src/testfiles/test.txt");
        transformer.tobyte(testfile);
    }

}