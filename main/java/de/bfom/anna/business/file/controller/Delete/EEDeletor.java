package de.bfom.anna.business.file.controller.Delete;


import de.bfom.anna.business.file.entity.FileEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EEDeletor implements Delete{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public boolean delete(int id) {
        FileEntity torem;
        torem = entityManager.find(FileEntity.class, id);
        if(torem != null){
            entityManager.remove(torem);
            return true;
        }
        else return false;
    }
}
