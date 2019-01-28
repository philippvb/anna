package de.bfom.anna.business.file.controller.Retrieve;

import de.bfom.anna.business.file.controller.Retrieve.Retrieve;
import de.bfom.anna.business.file.entity.FileEntity;
import de.bfom.anna.business.file.entity.ReducedFileEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
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
        Query q = em.createNativeQuery("SELECT files.name, files.mime, files.created FROM files", FileEntity.class);
        List<FileEntity> results = q.getResultList();
        List<ReducedFileEntity> reducedResults = null;
        for(int i = 0; i < results.size(); i++){
            reducedResults.add(new ReducedFileEntity(results.get(i)));
        }
        return reducedResults;
    }
}
