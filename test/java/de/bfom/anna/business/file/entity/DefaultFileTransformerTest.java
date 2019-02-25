package de.bfom.anna.business.file.entity;


import de.bfom.anna.business.file.entity.FileEntity;
import de.bfom.anna.business.file.entity.Transform.DefaultFileTransformer;
import de.bfom.anna.business.file.entity.Transform.FileTransformer;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

class DefaultFileTransformerTest {

    @Test
    void transform() {
        File testfile = new File("src/testfiles/test.txt");
        FileTransformer testtransformer = new DefaultFileTransformer();
        FileEntity myentity = testtransformer.transform(testfile);
        assertEquals("test.txt", myentity.getName());
        assertEquals("text/plain", myentity.getMime());
    }

}