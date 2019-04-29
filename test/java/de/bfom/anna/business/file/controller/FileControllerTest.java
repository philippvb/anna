package de.bfom.anna.business.file.controller;

import de.bfom.anna.business.file.boundary.FileBoundary;
import de.bfom.anna.business.file.controller.Create.Create;
import de.bfom.anna.business.file.controller.Create.DefaultCreator;

import de.bfom.anna.business.file.controller.Delete.DefaultDeletor;
import de.bfom.anna.business.file.controller.Delete.Delete;
import de.bfom.anna.business.file.controller.Retrieve.DefaultRetriever;
import de.bfom.anna.business.file.controller.Retrieve.Retrieve;
import de.bfom.anna.business.file.controller.Update.DefaultUpdate;
import de.bfom.anna.business.file.controller.Update.Update;
import de.bfom.anna.business.file.entity.FileEntity;
import de.bfom.anna.business.file.entity.ReducedFileEntity;
import de.bfom.anna.business.file.entity.Transform.ByteToFile;
import de.bfom.anna.business.file.entity.Transform.DefaultFileTransformer;
import de.bfom.anna.business.file.entity.Transform.FileTransformer;
import org.apache.commons.io.FilenameUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.mockito.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class FileControllerTest {
    @Mock
    DefaultDeletor mydeletor = new DefaultDeletor(null);

    @Mock
    Create mycreator = new DefaultCreator(null);

    @Spy
    FileTransformer mytransformer = new DefaultFileTransformer();

    @Mock
    Update myupdater = new DefaultUpdate(null);

    @Mock
    FileBoundary myboundary = new FileBoundary(null, null);

    @Mock
    Retrieve myretriever = new DefaultRetriever(null);


    @InjectMocks
    FileController mycontroller = new FileController(null, mycreator, mytransformer, myretriever,
            mydeletor, myupdater, myboundary);



    File test = new File("C:\\Users\\Philipp Admin\\IdeaProjects\\anna\\src\\testfiles\\test.txt");
    FileEntity testent = mytransformer.transform(test);








    //FileEntity testentity = FileEntity.newFileEntity().id(1).name("test").mime(null).created(null).file(null).buildWithID();


    //@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();


    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);

        /*doAnswer(invocationOnMock -> {
            assertEquals(invocationOnMock.getArgument(0).getClass(), File.class);
            assertEquals(((File) invocationOnMock.getArgument(0)).getName(), test.getName());
            return null;
        }).when(mytransformer).transform(any(File.class));*/



        doAnswer(invocationOnMock -> {
                    assertEquals(invocationOnMock.getArgument(0).getClass(), FileEntity.class );
                    //assertEquals(((FileEntity) invocationOnMock.getArgument(0)).getName(), FilenameUtils.removeExtension(test.getName()));
                    return  null;
                }
        ).when(mycreator).save(any(FileEntity.class));

        doAnswer(invocationOnMock -> {
            assertEquals(invocationOnMock.getArgument(0).getClass(), FileEntity.class);
            assertEquals(((FileEntity) invocationOnMock.getArgument(0)).getName(), testent.getName());
            return null;
        }).when(myupdater).update(any(FileEntity.class));

        testent.setId(1);

    }


    @Test
    public void persistTest(){
        /*doAnswer(invocationOnMock -> {
            assertEquals(invocationOnMock.getArgument(0).getClass(), File.class);
            assertEquals(((File) invocationOnMock.getArgument(0)).getName(), test.getName());
            return null;
        }).when(mytransformer).transform(any(File.class));

        doAnswer(invocationOnMock -> {
            assertEquals(invocationOnMock.getArgument(0).getClass(), FileEntity.class );
            assertEquals(((FileEntity) invocationOnMock.getArgument(0)).getName(), FilenameUtils.removeExtension(test.getName()));
            return  null;
        }
        ).when(mycreator).save(any(FileEntity.class));*/

        mycontroller.persist(test);
    }

    @Test
    public void persistOrUpdateTest(){
        ArrayList<FileEntity> entlist = new ArrayList<FileEntity>();
        when(myretriever.retrieve(any(String.class))).thenReturn(entlist);
        mycontroller.persistOrUpdate(test);
        verify(mytransformer).transform(test);
        verify(mycreator).save(any(FileEntity.class));

        entlist.add(testent);

        when(myboundary.persistOrUpdate()).thenReturn(0);
        mycontroller.persistOrUpdate(test);
        verify(myboundary).persistOrUpdate();
        verify(myupdater).update(any(FileEntity.class));

        when(myboundary.persistOrUpdate()).thenReturn(1);
        mycontroller.persistOrUpdate(test);
        verify(mycreator, times(2)).save(any(FileEntity.class));
    }

    @Test
    public void retrieveTest(){
        when(myretriever.retrieve(any(Integer.class))).thenReturn(testent);
        assertEquals(FileEntity.class, mycontroller.retrieve(1).getClass());
        verify(myretriever).retrieve(1);
    }

    @Test
    public void retrieveFileTest(){
        when(myretriever.retrieve(any(Integer.class))).thenReturn(testent);
        assertEquals(File.class, mycontroller.retrieveFile(1).getClass());
        verify(myretriever).retrieve(1);
        // bytetofile static test
    }


    @Test
    public void retrieveAllTest(){
        ArrayList<FileEntity> entlist = new ArrayList<FileEntity>();
        entlist.add(testent);
        when(myretriever.retrieveAll()).thenReturn(entlist);

        assertEquals(FileEntity.class, mycontroller.retrieveAll().get(0).getClass());
        verify(myretriever).retrieveAll();
    }

    @Test
    public void retrieveAllReducedTest(){
        ReducedFileEntity redtestent = new ReducedFileEntity(testent);
        ArrayList<ReducedFileEntity> redentlist = new ArrayList<ReducedFileEntity>();
        redentlist.add(redtestent);
        when(myretriever.retrieveAllReduced()).thenReturn(redentlist);

        assertEquals(ReducedFileEntity.class, mycontroller.retrieveAllReduced().get(0).getClass());
        verify(myretriever).retrieveAllReduced();
    }

    @Test
    public void deleteTest(){
        when(mydeletor.delete(any(Integer.class))).thenReturn(true);

        assertTrue(mycontroller.delete(1));
        verify(mydeletor).delete(1);
    }

}