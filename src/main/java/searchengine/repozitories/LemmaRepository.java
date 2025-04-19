package src.main.java.searchengine.repozitories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import searchengine.model.LemmaEntity;

import java.util.Optional;

@Repository
public interface LemmaRepository extends CrudRepository<LemmaEntity, Integer> {

    @Query(value = "SELECT id FROM lemmas WHERE `site_id` = :site_id AND `lemma` = :lemma", nativeQuery = true)
    Optional<Integer> getLemmaId(int site_id, String lemma);

    @Query(value = "SELECT id FROM lemmas WHERE `site_id` = :site_id", nativeQuery = true)
    Iterable<Integer> getLemmaIdBySiteId(int site_id);

    @Query(value = "SELECT COUNT(*) FROM lemmas WHERE `site_id` = :site_id", nativeQuery = true)
    int countLemmaBySiteId(int site_id);

    @Query(value = "SELECT SUM(`frequency`) FROM lemmas WHERE `lemma` = :lemma", nativeQuery = true)
    Optional<Integer> getSumFrequency(String lemma);

    @Query(value = "SELECT id FROM lemmas WHERE `lemma` = :lemmaName AND site_id IN :sites_ids", nativeQuery = true)
    Iterable<Integer> getLemmaIdsByLemmaName(String lemmaName, Integer[] sites_ids);

    @Query(value = "SELECT * FROM `lemmas` WHERE site_id = :site_id AND lemma IN :lemmaNames", nativeQuery = true)
    Iterable<LemmaEntity> getLemmasBySiteIdAndLemmaName(int site_id, String[] lemmaNames);

}