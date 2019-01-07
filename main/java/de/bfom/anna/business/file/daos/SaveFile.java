package de.bfom.anna.business.file.daos;


import de.bfom.anna.business.file.entity.FileEntity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class SaveFile implements FileSaver {
    private EntityManagerFactory myfactory;

    public SaveFile(EntityManagerFactory myfactory){
        this.myfactory = myfactory;
    }

    @Override
    public void save(FileEntity file) throws RuntimeException{
        EntityManager em = myfactory.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(file);
            tx.commit();
        } catch( RuntimeException ex ) {
            if( tx != null && tx.isActive() ) tx.rollback();
            throw ex;
        } finally {
            em.close();
        }
    }
}
