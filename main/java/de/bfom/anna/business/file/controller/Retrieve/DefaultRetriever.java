package de.bfom.anna.business.file.controller.Retrieve;

import de.bfom.anna.business.file.controller.Retrieve.Retrieve;
import de.bfom.anna.business.file.entity.FileEntity;
import de.bfom.anna.business.file.entity.ReducedFileEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DefaultRetriever implements Retrieve {
    private EntityManagerFactory myfactory;

    public DefaultRetriever(EntityManagerFactory myfactory){
        this.myfactory = myfactory;
    }

    public FileEntity retrieve(int id){
        EntityManager em = myfactory.createEntityManager();
        EntityTransaction tx = null;
        FileEntity retobject = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            retobject = em.find(FileEntity.class, id);
            tx.commit();
        } catch( RuntimeException ex ) {
            if( tx != null && tx.isActive() ) tx.rollback();
            throw ex;
        } finally {
            em.close();
        }
        return retobject;
    }

    public List<FileEntity> retrieveAll(){
        EntityManager em = myfactory.createEntityManager();
        Query q = em.createNativeQuery("SELECT * FROM files", FileEntity.class);
        List<FileEntity> results = q.getResultList();
        return results;
    }

    public List<FileEntity> retrieve(String name){
        EntityManager em = myfactory.createEntityManager();
        Query q = em.createNativeQuery("SELECT * FROM files WHERE files.name = \"" + name + "\"",
                FileEntity.class);
        List<FileEntity> results = q.getResultList();
        return results;
    }

    public List<ReducedFileEntity> retrieveAllReduced(){
        EntityManager em = myfactory.createEntityManager();
        List<Object[]> results = em.createNativeQuery("SELECT id, name, mime, created FROM files").getResultList();
        List<ReducedFileEntity> reducedResults = new ArrayList<>(); // right type of list??

        for(Object[] result : results){
            reducedResults.add(new ReducedFileEntity(FileEntity.newFileEntity().id((int)result[0]).
                    name((String) result[1]).mime((String) result[2]).
                    created(((Timestamp) result[3]).toLocalDateTime()).
                    build()));
        }

        return reducedResults;
    }
}
