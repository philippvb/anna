package de.bfom.anna.business.file.entity;

import de.bfom.anna.business.file.entity.Transform.FileToByte;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.time.LocalDateTime;


class FileEntityTest {

    @Test
    public void equalsTest(){
        LocalDateTime now = LocalDateTime.now();
        byte[] file = FileToByte.tobyte(new File("src/testfiles/test.txt"));
        FileEntity test1 = FileEntity.newFileEntity().id(1).name("test").mime("test").created(now).file(file).buildWithID();
        FileEntity test2 = FileEntity.newFileEntity().id(1).name("test").mime("test").created(now).file(file).buildWithID();
        assertTrue(test1.equals(test2));
    }

    @Test
    public void equalsWithOutIdTest(){
        LocalDateTime now = LocalDateTime.now();
        byte[] file = FileToByte.tobyte(new File("src/testfiles/test.txt"));
        FileEntity test1 = FileEntity.newFileEntity().name("test").mime("test").created(now).file(file).build();
        FileEntity test2 = FileEntity.newFileEntity().name("test").mime("test").created(now).file(file).build();
        assertTrue(test1.equalsWithOutId(test2));
    }

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