package de.bfom.anna.business.file.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;


class FileEntityTest {

    @Test
    void build(){
        LocalDateTime testdate = LocalDateTime.now();
        FileEntity test = FileEntity.newFileEntity().id(1)
                .name("test")
                .mime(".txt")
                .created(testdate)
                .build();
        assertEquals(0, test.getId());
        assertEquals("test", test.getName());
        assertEquals(".txt", test.getMime());
        assertEquals(testdate, test.getCreated());
    }

    @Test
    void buildWithID(){
        LocalDateTime testdate = LocalDateTime.now();
        FileEntity test = FileEntity.newFileEntity().id(1)
                .name("test")
                .mime(".txt")
                .created(testdate)
                .buildWithID();

        assertEquals(1, test.getId());
        assertEquals("test", test.getName());
        assertEquals(".txt", test.getMime());
        assertEquals(testdate, test.getCreated());
    }

}