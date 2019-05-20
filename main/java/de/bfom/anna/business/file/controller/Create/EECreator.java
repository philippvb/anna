package de.bfom.anna.business.file.controller.Create;


import de.bfom.anna.business.file.entity.FileEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EECreator implements Create{

    @PersistenceContext
    private EntityManager entityManager;

    public void save(FileEntity f){
        entityManager.persist(f);
    }



}
