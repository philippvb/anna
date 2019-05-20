package de.bfom.anna.business.file.controller.Retrieve;

import de.bfom.anna.business.file.entity.FileEntity;
import de.bfom.anna.business.file.entity.ReducedFileEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Stateless
public class EERetriever implements Retrieve {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public FileEntity retrieve(int id) {
        return entityManager.find(FileEntity.class, id);
    }

    @Override
    public List<FileEntity> retrieveAll() {
        Query q = entityManager.createNativeQuery("SELECT * FROM files", FileEntity.class);
        List<FileEntity> results = q.getResultList();
        return results;
    }

    @Override
    public List<FileEntity> retrieve(String name) {
        Query q = entityManager.createNativeQuery("SELECT * FROM files WHERE files.name = \"" + name + "\"",
                FileEntity.class);
        List<FileEntity> results = q.getResultList();
        return results;
    }

    @Override
    public List<ReducedFileEntity> retrieveAllReduced() {
        List<Object[]> results = entityManager.createNativeQuery("SELECT id, name, mime, created FROM files").getResultList();
        List<ReducedFileEntity> reducedResults = new ArrayList<>(); // right type of list??
        for(Object[] result : results){
            reducedResults.add(new ReducedFileEntity(FileEntity.newFileEntity().id((int)result[0]).
                    name((String) result[1]).mime((String) result[2]).
                    created(((Timestamp) result[3]).toLocalDateTime()).
                    buildWithID()));
        }
        return reducedResults;
    }
}
