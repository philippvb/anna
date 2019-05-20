package de.bfom.anna.business.file.boundary;

import de.bfom.anna.business.file.controller.FileController;
import de.bfom.anna.business.file.entity.FileEntity;
import de.bfom.anna.business.file.entity.ReducedFileEntity;
import de.bfom.anna.business.file.entity.Transform.DefaultFileTransformer;
import de.bfom.anna.business.file.entity.Transform.FileTransformer;
import de.bfom.anna.gui.MainFrame;

import org.junit.jupiter.api.Test;
import org.mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.util.ArrayList;

class FileBoundaryTest {
/*
    @Mock
    FileController mycontroller = new FileController(null, null, null, null,
            null, null, null);

    @Mock
    MainFrame myframe = new MainFrame();

    @InjectMocks
    FileBoundary myboundary = new FileBoundary(mycontroller, myframe);

    File test = new File("C:\\Users\\Philipp Admin\\IdeaProjects\\anna\\src\\testfiles\\test.txt");


    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void persist() {
        myboundary.persist(test);
        verify(mycontroller).persistOrUpdate(any(File.class));
    }

    @Test
    void retrieveFile() {
        when(mycontroller.retrieveFile(any(Integer.class))).thenReturn(test);

        assertEquals(File.class, myboundary.retrieveFile(1).getClass());
        verify(mycontroller).retrieveFile(1);
    }

    @Test
    void retrieveAllReduced() {
        ArrayList<ReducedFileEntity> redentlist = new ArrayList<>();
        FileTransformer transformer = new DefaultFileTransformer();
        FileEntity testent = transformer.transform(test);
        ReducedFileEntity redtestent = new ReducedFileEntity(testent);
        redentlist.add(redtestent);
        when(mycontroller.retrieveAllReduced()).thenReturn(redentlist);

        assertEquals(ReducedFileEntity.class, myboundary.retrieveAllReduced().get(0).getClass());
        verify(mycontroller).retrieveAllReduced();
    }

    @Test
    void delete() {
        when(mycontroller.delete(any(Integer.class))).thenReturn(true);

        assertTrue(myboundary.delete(1));
        verify(mycontroller).delete(any(Integer.class));
    }

    @Test
    void persistOrUpdate() {
        when(myframe.persistOrUpdate()).thenReturn(1);

        assertEquals(1, myboundary.persistOrUpdate());
        verify(myframe).persistOrUpdate();
    }

 */
}