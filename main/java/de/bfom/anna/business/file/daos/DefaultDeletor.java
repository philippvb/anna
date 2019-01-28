package de.bfom.anna.business.file.daos;

import de.bfom.anna.business.file.entity.FileEntity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class DefaultDeletor implements Delete {
    EntityManagerFactory myfactory;

    public DefaultDeletor(EntityManagerFactory myfactory){
        this.myfactory = myfactory;
    }

    public boolean delete(int id){
        boolean succesfull = false;
        FileEntity torem;
        EntityManager em = myfactory.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            torem = em.find(FileEntity.class, id);
            if(torem != null){
                em.remove(torem);
                succesfull = true;
            }
            tx.commit();
        } catch( RuntimeException ex ) {
            if( tx != null && tx.isActive() ) tx.rollback();
            throw ex;
        } finally {
            em.close();
        }
        return succesfull;
    }
}
