package de.bfom.anna.business.file.controller.Update;

import de.bfom.anna.business.file.entity.FileEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EEUpdate implements Update {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void update(FileEntity f) {
        entityManager.merge(f);
    }
}
