package de.bfom.anna.business.file.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;


class ReducedFileEntityTest {

    LocalDateTime now = LocalDateTime.now();
    FileEntity test = FileEntity.newFileEntity().id(1).name("test").mime("dummy").created(now).buildWithID();
    ReducedFileEntity rettest = new ReducedFileEntity(test);

    @Test
    void getID() {
        assertEquals(1, rettest.getID());
    }

    @Test
    void getName() {
        assertEquals("test", rettest.getName());
    }

    @Test
    void getMime() {
        assertEquals("dummy", rettest.getMime());
    }

    @Test
    void getCreated() {
        assertEquals(now, rettest.getCreated());
    }
}