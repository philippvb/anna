package de.bfom.anna.business.file.controller;

import static org.mockito.ArgumentMatchers.any;


class FileControllerTest {

    /*@Mock
    Delete mydeletor = new DefaultDeletor(null);

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
    int id = 1;


    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        testent.setId(id);
    }


    @Test
    public void persistTest(){
        mycontroller.persist(test);
        verify(mytransformer).transform(any(File.class));
        verify(mycreator).save(any(FileEntity.class));
    }

    @Test
    public void persistOrUpdateTest(){
        ArrayList<FileEntity> entlist = new ArrayList<FileEntity>();
        when(myretriever.retrieve(any(String.class))).thenReturn(entlist);

        mycontroller.persistOrUpdate(test);
        verify(mytransformer).transform(test);
        verify(myretriever).retrieve(any(String.class));
        verify(mycreator).save(any(FileEntity.class));

        entlist.add(testent);
        when(myboundary.persistOrUpdate()).thenReturn(0);
        doAnswer(invocationOnMock -> {
            assertEquals(id , ((FileEntity) invocationOnMock.getArgument(0)).getId());
            return null;
        }).when(myupdater).update(any(FileEntity.class));

        mycontroller.persistOrUpdate(test);
        verify(myboundary).persistOrUpdate();
        verify(myupdater).update(any(FileEntity.class));

        when(myboundary.persistOrUpdate()).thenReturn(1);
        doAnswer(invocationOnMock -> {
            assertEquals(FilenameUtils.removeExtension(test.getName()) + "1",
                    ((FileEntity) invocationOnMock.getArgument(0)).getName());
            return null;
        }).when(mycreator).save(any(FileEntity.class));

        mycontroller.persistOrUpdate(test);
        verify(mycreator, times(2)).save(any(FileEntity.class));
    }

    @Test
    public void updateTest(){
        int updateId = 2;
        doAnswer(invocationOnMock -> {
            assertEquals(updateId, ((FileEntity) invocationOnMock.getArgument(0)).getId());
            return null;
        }).when(myupdater).update(any(FileEntity.class));

        mycontroller.update(test, updateId);
        verify(mytransformer).transform(any(File.class));
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

     */

}