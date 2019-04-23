package de.bfom.anna.business.file.controller.Update;

import de.bfom.anna.business.file.entity.FileEntity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;


public class DefaultUpdate implements Update {
    private EntityManagerFactory myfactory;

    public DefaultUpdate(EntityManagerFactory myfactory){
        this.myfactory = myfactory;
    }

    public void update(FileEntity file){
        EntityManager em = myfactory.createEntityManager();
        EntityTransaction tx = null;
        tx = em.getTransaction();
        tx.begin();
        em.merge(file);
        tx.commit();
        em.close();
    }
}
