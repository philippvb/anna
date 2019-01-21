package de.bfom.anna.business.file.daos;

import de.bfom.anna.business.file.entity.FileEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class GetAllQuery implements GetAll {
    private EntityManagerFactory myfactory;

    public GetAllQuery(EntityManagerFactory myfactory){
        this.myfactory = myfactory;
    }

    public List<FileEntity> getAll(){
        EntityManager em = myfactory.createEntityManager();
        Query q = em.createNativeQuery("SELECT * FROM files", FileEntity.class);
        List<FileEntity> results = q.getResultList();
        return results;
    }


}
