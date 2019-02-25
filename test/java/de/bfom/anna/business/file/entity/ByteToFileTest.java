package de.bfom.anna.business.file.entity;



import de.bfom.anna.business.file.entity.FileEntity;
import de.bfom.anna.business.file.entity.Transform.DefaultFileTransformer;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class ByteToFileTest {

    @Test
    void transformTest(){
        DefaultFileTransformer transformer = new DefaultFileTransformer();
        File testfile = new File("src/testfiles/test.txt");
        FileEntity entity = transformer.transform(testfile);
        File rettestfile = transformer.transformToFile(entity);

        boolean fileEqual = false;
        try{
            fileEqual = FileUtils.contentEquals(testfile, rettestfile);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        assertEquals(File.class, rettestfile.getClass());
        assertTrue(fileEqual);
    }

}