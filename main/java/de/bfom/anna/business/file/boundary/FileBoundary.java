package de.bfom.anna.business.file.boundary;

import de.bfom.anna.business.file.controller.FileController;
import de.bfom.anna.business.file.entity.FileEntity;
import de.bfom.anna.business.file.entity.ReducedFileEntity;
import de.bfom.anna.gui.MainFrame;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.io.File;
import java.util.List;

@Stateless
@Path("/test")
public class FileBoundary {

    @Inject
    private FileController mycontroller;

    @Inject
    private MainFrame mainframe;


    public void persist(File file) {
        mycontroller.persistOrUpdate(file);
    }

    public File retrieveFile(int id){
        return mycontroller.retrieveFile(id);
    }

    public List<ReducedFileEntity> retrieveAllReduced(){
        return mycontroller.retrieveAllReduced();
    }

    public boolean delete(int id){
        return mycontroller.delete(id);
    }



    public int persistOrUpdate(){
        return mainframe.persistOrUpdate();
    }



    @Path("/getTestObject")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTestObject(){
        mycontroller.persist(new File("C:\\Users\\Philipp Admin\\IdeaProjects\\anna\\src\\testfiles\\test.txt"));
        FileEntity f = mycontroller.retrieve(1);
        return Response.status(200).entity(f).build();
    }

    public void setMainframe(MainFrame mainframe){
        this.mainframe = mainframe;
    }
    public void setController(FileController controller){
        this.mycontroller = controller;
    }

}
