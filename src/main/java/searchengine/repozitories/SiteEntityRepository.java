package src.main.java.searchengine.repozitories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import searchengine.model.SiteEntity;

@Repository
public interface SiteEntityRepository extends CrudRepository<SiteEntity, Integer> {

    @Query(value = "SELECT COUNT(*) from sites where `status` = 'INDEXING'" , nativeQuery = true)
    int countIndexing();

    @Query(value = "SELECT `id` from sites where `status` = 'INDEXING'", nativeQuery = true)
    int[] listOfIndexing();

    @Query(value = "SELECT 'id' FROM sites WHERE 'status' = 'INDEXED'", nativeQuery = true)
    int[] listOfIndexed();





}
