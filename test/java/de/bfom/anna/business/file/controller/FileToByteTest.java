package de.bfom.anna.business.file.controller;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class FileToByteTest {

    @Test
    void tobyte(){
        FileToByte transformer = new FileToByte();
        File testfile = new File("src/testfiles/test.txt");
        transformer.tobyte(testfile);
    }

}