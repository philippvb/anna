package de.bfom.anna.business.file.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class FileEntityTest {

    @Test
    void build(){
        LocalDateTime testdate = LocalDateTime.now();
        FileEntity test = FileEntity.newFileEntity().id(1)
                .name("test")
                .mime(".txt")
                .created(testdate)
                .build();

        assertEquals("test", test.getName());
        assertEquals(".txt", test.getMime());
        assertEquals(testdate, test.getCreated());
    }

}