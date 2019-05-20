package de.bfom.anna.business.file.boundary;

import de.bfom.anna.business.file.controller.FileController;
import de.bfom.anna.business.file.entity.FileEntity;
import de.bfom.anna.business.file.entity.ReducedFileEntity;


import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("/DBAccess")
public class FileBoundary {

    @Inject
    private FileController mycontroller;

/*
    public void persist(File file) {
        mycontroller.persistOrUpdate(file);
    }


 */
    @Path("/get/{projectId}")
    @GET
    public Response retrieveFile(@PathParam("projectId") int id){
        FileEntity f = mycontroller.retrieve(id);
        if(f != null){
            return Response.ok(f.getFile(), f.getMime()).build();
        }
        else return Response.status(Response.Status.NOT_FOUND).build();

    }

    @Path("/getAll")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllReduced(){
        List<ReducedFileEntity> entities = mycontroller.retrieveAllReduced();
        if(!entities.isEmpty()){
            return Response.ok(entities).build();
        }
        else return Response.status(Response.Status.NO_CONTENT).build();
    }



    @Path("delete/{projectId}")
    @GET
    public Response delete(@PathParam("projectId") int id){
        boolean successfull = mycontroller.delete(id);
        if(successfull){
            return Response.ok().build();
        }
        else return Response.status(Response.Status.NOT_FOUND).build();
    }


/*
    public int persistOrUpdate(){
        return mainframe.persistOrUpdate();
    }

 */


}
