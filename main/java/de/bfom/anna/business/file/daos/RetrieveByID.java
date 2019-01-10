package de.bfom.anna.business.file.daos;

import de.bfom.anna.business.file.entity.FileEntity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class RetrieveByID {
    private EntityManagerFactory myfactory;

    public RetrieveByID(EntityManagerFactory myfactory){
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
}
